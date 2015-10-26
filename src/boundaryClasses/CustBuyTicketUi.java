package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.BuyTicketControl;
import controllerClasses.DataControl;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.TimeDateControl;
import controllerClasses.ValidationControl;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class CustBuyTicketUi {

	
	public void displayBuyTicket(int movieId,int hit,int cineplexId, Movie movieDetails) throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		BuyTicketControl bl=new BuyTicketControl();
		Scanner sc =new Scanner(System.in);
		
		DataControl dl=new DataControl();
		ArrayList<Cineplex> cnList= dl.readCineplex();
		
		int visit=hit;
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		
		//Variables that are needed to display cineplex with most timeslot;
		ArrayList<MovieSchedule> startList=cl.readNonPlatScheduleListingBasedOnStartingDateAndMovieId(movieId);
		ArrayList<Integer> cineList=new ArrayList<Integer>();
		
		//Start collecting timeslot size based on cineId
		for(int i=0;i<startList.size();i++){
			cineList.add(startList.get(i).getCineplexId());
		}
		
		//this method will only run for the first time user visit this page
		int cineId=0;
		if(visit==0){
			System.out.println();
		 cineId=bl.chooseCineplexToDisplay(cineList);
		}
		else{
			cineId=cineplexId;
		}
		//grab cineplex name
		String cineplexName="";
		for(int i=0;i<cnList.size();i++){
			if(cineId==cnList.get(i).getCinplexId()){
				cineplexName=cnList.get(i).getCineplexName();
			}
		}
		
		//start retrieving schedule
		ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedonMovieandCineplexId(movieId, cineId);
		
		//intialize variables needed to display time slot in dynamic way
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<Integer> listIdList=new ArrayList<Integer>();
		ArrayList<Integer> showTimeId=new ArrayList<Integer>();
		ArrayList<String> showTimeArray=new ArrayList<String>();

		for(int i=0;i<schList.size();i++)
		{	
			listIdList.add(schList.get(i).getListingId());
		}

		//initialize date variables to get 7 days worth of timeslot (to Do list, need to read holiday!!!)
		int dayType=0;
		Calendar cal = Calendar.getInstance();
		Calendar calTemp,calTemp2, aDate=Calendar.getInstance();
		calTemp = (Calendar) cal.clone();
		calTemp2 = (Calendar) cal.clone();
		Date endDate = cal.getTime();
		if(!schList.isEmpty())
		{
			endDate=schList.get(0).getEndDate();
			aDate.setTime(endDate);
			System.out.println("Not empty");
		}
		System.out.println("Time slot for "+movieDetails.getMovieName()+" in "+cineplexName);
		System.out.println("Date                     |TimeSlot                     ");
		System.out.println("------------------------------------------------------");
		for(int i=1;i<=7;i++)
		{
			System.out.print(TimeDateControl.retrieveDaySpell(calTemp)+"|");
			dayType=TimeDateControl.dayType(calTemp.getTime());
			ArrayList<String> pairStringArray=new ArrayList<String>();
			MiscControl mc=new MiscControl();
			for(int k=0;k<listIdList.size();k++){
				showTimeList=cl.readShowTimesBasedOnListingId(listIdList.get(k), dayType);
				if(!showTimeList.isEmpty())
				{
				
					for(int p=0;p<showTimeList.size();p++)
					{	showTimeId.add(showTimeList.get(p).getShowTimeId());
						showTimeArray.add(showTimeList.get(p).getShowTimeValue());
						pairStringArray.add(showTimeList.get(p).getShowTimeValue());
						ObjectContainer o=new ObjectContainer();
						o.setShowTimeId(showTimeList.get(p).getShowTimeId());
						o.setsT(showTimeList.get(p));
						o.setI(i);
						o.setTimeValue(showTimeList.get(p).getShowTimeValue());
						pair.add(o);
					}
				}
			}
			
			if(!showTimeArray.isEmpty())
			{
				for(int j=0;j<showTimeArray.size();j++)
				{
					mc.setStringList(showTimeArray);
					mc.sort();
					//showTimeArray.clear();
					//showTimeArray=mc.getStringList();
					System.out.print(showTimeArray.get(j)+" ");
				}
				showTimeArray.clear();
				
				System.out.println();
				if(calTemp.before(aDate))
				{
					calTemp.add(calTemp.DATE, 1);
					
				}
			}
			else
			{		System.out.print("No timeslot on this movie for this cinplex");
					calTemp.add(calTemp.DATE, 1);
					System.out.println();
			}
		}
		System.out.println("------------------------------------------------------");
		

		System.out.println("Choose the day you want to purchase the ticket or choose cineplex");
		for(int i=1;i<=7;i++)
		{
			System.out.print((i+":"+TimeDateControl.retrieveDaySpell(calTemp2)+"	"));
			dayType=TimeDateControl.dayType(calTemp2.getTime());
			calTemp2.add(calTemp2.DATE, 1);
			if(i%2==0){
				System.out.println();
			}
		}
		System.out.println("\n8.Choose cineplex");
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);

		
		if(choice==8){
			displayBasedOnCineplex(movieId,visit,cnList,movieDetails);
		}
		displayTicketForDay(pair,choice);
	}
	
	
	public void displayBasedOnCineplex(int movieId,int visit, ArrayList<Cineplex> cnList, Movie movieDetails) throws IOException, ParseException
	{
		
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		Scanner sc=new Scanner(System.in);
		int cinplexId=0;
		
		do{
			System.out.println("Please choose Cineplex");
		
			for(int i=0;i<cnList.size();i++)
			{
				System.out.print((i+1)+":"+cnList.get(i).getCineplexName()+"");
			}
			cinplexId=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
				
		}while(cinplexId==-2|| cinplexId>cnList.size());
		visit++;
		displayBuyTicket(movieId,visit,cinplexId,movieDetails);
	}
	
	
	
	
	public void displayTicketForDay(ArrayList<ObjectContainer> pair, int choice2) throws IOException, ParseException
	{
		
		ArrayList<ObjectContainer> pairingIdWithSlot=new ArrayList<ObjectContainer>();
		for(int i=0;i<pair.size();i++)
		{
			if(pair.get(i).getI()==choice2)
			{
				ObjectContainer o=new ObjectContainer();
				o.setI(i+1);
				o.setShowTimeId(pair.get(i).getShowTimeId());
				o.setTimeValue(pair.get(i).getTimeValue());
				o.setsT(pair.get(i).getsT());
				pairingIdWithSlot.add(o);
			}
		}
		System.out.println("\nSelect number beside each time slot to purchase ticket for that time slot.");
		for(int i=0;i<pairingIdWithSlot.size();i++){
			System.out.print((i+1)+":"+pairingIdWithSlot.get(i).getTimeValue()+"	");
		}
		System.out.println();
		
		Scanner sc=new Scanner(System.in);
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		int showTimeId=pairingIdWithSlot.get(choice-1).getShowTimeId();
		System.out.println(showTimeId);
		CustSeatsUi ui=new CustSeatsUi();
		ui.displaySeat(showTimeId);
		//
		
	}
	
	
	
	
}
