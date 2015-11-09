package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import boundaryClasses.CustMain;
import misc.ObjectContainer;
import data.Cinema;
import data.Cineplex;
import data.HolidayDate;
import data.Movie;
import data.MovieSchedule;
import data.Prices;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import data.Transaction;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TicketPriceAndHolidayDataControl;
import dataController.TransactionDataControl;

/**
 * Class that deals with the purchase of a ticket. Mainly deal formating of show time for customer to select and work in conjunction with CustSeatsControl
 * @author Chang En Kai
 *
 */
public class CustBuyTicketControl   {
	
	/**
	 * Choose the cineplex that has the most time slot when customer first select time slot of a movie to purcahse
	 * @param movieId
	 * @param listId
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static int chooseCineplexToDisplay(int movieId,int listId) throws ParseException, IOException//ArrayList<Integer> cineId)
	{
		Calendar calTemp=Calendar.getInstance();
		ArrayList<ShowTime> startList=ShowTimeDataControl.readShowTimesBasedOnListingId(listId);
		ArrayList<Integer> cineList=new ArrayList<Integer>();
		
		//Start collecting timeslot size based on cineId
		for(int i=0;i<startList.size();i++){
			cineList.add(startList.get(i).getCineplexId());
		}
		
		int a=0,b=0,c=0;
		for(int i=0;i<cineList.size();i++)
		{
			if(cineList.get(i)==1)
			{
				a++;
			}
			else if(cineList.get(i)==2)
			{
				b++;
			}
			else if(cineList.get(i)==3){
				c++;
			}
		}
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		tmp.add(a);
		tmp.add(b);
		tmp.add(c);
		int largest = Collections.max(tmp);
		if(largest==a)
		{
			return 1;
		}
		else if(largest==b)
		{
			return 2;
		}
		else if(largest==c)
		{
			return 3;
		}
		return 0;
		
	}
	
	/**
	 * Method to handle purchase ticket. Will comunicate with data controllers to create new transactions. 
	 * @param cust
	 * @param stList
	 * @param actualSeats
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void purchaseTicket(ObjectContainer cust, ShowTime stList, ArrayList<Seats> actualSeats) throws IOException, ParseException{
		int movieId=stList.getMovieId();
		
		int cineplexId=stList.getCineplexId();
		int cinemaId=stList.getCinemaId();
		SeatsInformation sInfo=new SeatsInformation();
		Movie movie=MovieDataControl.readMovieBasedOnId(movieId);
	//	Cinema cinema=CinemaDataControl.readCinemaByCinemaId(cinemaId);
	//	Cineplex cineplex=CineplexDataControl.readCineplexBasedonCinplexId(cineplexId);
		movie.setTicketSales(movie.getTicketSales()+actualSeats.size());
		
		MovieDataControl.updateMovie(movie);
		Transaction t=cust.getTranscation();
		t.setNumSeat(actualSeats.size());
		String seats="";
		for(int i=0;i<actualSeats.size();i++)
		{
			seats=seats+actualSeats.get(i).getSeatName();
			if(i!=(actualSeats.size()-1)){
				seats=seats+",";
			}
		}
		t.setSeats(seats);
		t.setShowtimeId(stList.getShowTimeId());
		TransactionDataControl.createTranscation(t);
		System.out.println("Purchase successful!");
		System.out.println("Transcation Code:"+t.getTranscationId());
		CustMain.displayCustMain();
		
	}
	
	/**
	 * Format forms to take to obtain customer's information
	 * @param sTList
	 * @param m
	 * @param listingId
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ObjectContainer customerManagement(ShowTime sTList,Movie m, int listingId) throws IOException, ParseException{
		double price=sTList.getTicketPrice();
		MovieSchedule sch=MovieScheduleDataControl.readScheduleListingBasedOnListingId(listingId);
		SimpleDateFormat finalDateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		String startDate=finalDateFormatter.format(sTList.getStartDate());
		boolean holiday=false, oldcheck=false,childcheck=false,block=false,plat=false,tD=false,weekEnd=false;
		
		if(sch.getBlockBuster()==1)
		{
			System.out.println(listingId);
			block=true;
		}
		
		if(sch.getPlatOrNot()==1){
			plat=true;
		}
		
		if(sch.getThreeDOrNot()==1){
			tD=true;
		}
		
		if(sTList.getStartDate().getDay()==6||sTList.getStartDate().getDay()==0)
		{
			weekEnd=true;
		}
		
		ArrayList<HolidayDate> hDList=TicketPriceAndHolidayDataControl.readHoliday();
		ArrayList<Prices> priceList=TicketPriceAndHolidayDataControl.readPrice();
		for(int i=0;i<hDList.size();i++)
		{
			String date1=finalDateFormatter.format(hDList.get(i).getHolidayDate());
			//System.out.println(startDate+" "+date1+" "+date1.equals(startDate));
			if(date1.equals(startDate)){
				
				price=price+priceList.get(0).getHoli();
				holiday=true;
				break;
			}
			
		}
		Scanner sc= new Scanner(System.in);
		boolean valid=false;
		String name="";
		
		do
		{
			System.out.println("Please enter your name:");
			name=sc.nextLine();
			valid=ValidationControl.validateEmptyString(name);
		}while(!valid);
		
		String email="";
		
		
		do
		{
			System.out.println("Please enter your email:");
			email=sc.nextLine();
			valid=ValidationControl.validateEmptyString(email);
		}while(!valid);
		
		
		String mobile="";
		do{
			System.out.println("Please enter your mobile phone");
			mobile=sc.nextLine();
			valid=ValidationControl.validateEmptyString(mobile);
		}while(!valid);
		
		int age=0;
		do{
			System.out.println("Please enter your age");
			age=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(age<=0);
		
		if(age>=65){
			price=price+priceList.get(0).getOldPrice();
			
			oldcheck=true;
		}
		else if(age<=12)
		{
			price=price+priceList.get(0).getChildPrice();
			childcheck=true;
		}
		Calendar today=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDDhhmm");
		
		
		String transcationId=sdf.format(today.getTime());
		transcationId=m.getMovieName().substring(0, 3)+transcationId;
		//public Transcation(String transcationId, String email, String mobilePhone,
	//	String custName, int age, int cineplexId, int cinemaId, Movie movie) {
	
		Transaction cust=new Transaction(transcationId, email, mobile, name, age, sTList.getCineplexId(), sTList.getCinemaId(), m.getMovieId(),price);
		ObjectContainer o=new ObjectContainer();
		o.setTranscation(cust);
		o.setHoliday(holiday);
		o.setChild(childcheck);
		o.setOld(oldcheck);
		o.setBlockBuster(block);
		o.setWeekEnd(weekEnd);
		o.settD(tD);
		o.setPlat(plat);
		o.setPrice(price);
		o.setInitialprice(price);
		return o;
		
	}
	
	
	/**
	 * Communicate with data controllers to filter a list of time slots that are showing in a cineplex
	 * @param listId
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static int filterNowShowingListingByCineplexId(int listId) throws IOException, ParseException{
		Calendar cal=Calendar.getInstance();
		Scanner sc=new Scanner(System.in);
		ArrayList<ShowTime> schWork=ShowTimeDataControl.readShowTimesBasedOnListingId(listId);
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=CineplexDataControl.readCineplex();
		int cineId=0;
	
		Set<Integer> cineList=new HashSet<Integer>();
		for(int i=0;i<schWork.size();i++)
		{
			cineList.add(schWork.get(i).getCineplexId());
		}
		
		
		ArrayList<Integer> uniqueCnList=new ArrayList<Integer> ();
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		
		Iterator itr = cineList.iterator();  
		
		while(itr.hasNext())
		{
			uniqueCnList.add(Integer.parseInt(itr.next().toString()));
		}
		int choice3=0;
		do{
			System.out.println("Please choose cineplex:");
		for(int i=0;i<cnList.size();i++)
			{
				for(int j=0;j<uniqueCnList.size();j++)
				{
					if(uniqueCnList.get(j)==cnList.get(i).getCinplexId()){
						System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
						ObjectContainer o=new ObjectContainer();
						o.setI((i+1));
						o.setId(cnList.get(i).getCinplexId());
						o.setName(cnList.get(i).getCineplexName());
						oList.add(o);
					}
				}
			}
			
			choice3=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice3<=0||choice3>uniqueCnList.size());
		
		for(int i=0;i<oList.size();i++){
			if(choice3==oList.get(i).getI()){
				cineId=oList.get(i).getId();
			}
		}
		return cineId;
	}
	
	



}
