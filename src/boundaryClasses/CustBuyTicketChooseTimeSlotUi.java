package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import misc.ObjectContainer;
import controllerClasses.CustBuyTicketControl;
import controllerClasses.MovieListingControl;
import controllerClasses.ValidationControl;
import data.Cineplex;
import data.Movie;
import data.ShowTime;
import dataController.CineplexDataControl;
import dataController.ShowTimeDataControl;

public class CustBuyTicketChooseTimeSlotUi extends CustMovieDetailUi {
	
	/**
	 * Display time slot for user to buy ticket for the movie
	 * @param lType List Type, can be now showing, coming soon or preview
	 * @param movieId
	 * @param visit number of times this method is accessed, used to manipulate which cineplex to choose to display timeslots
	 * @param cineplexId
	 * @param movieDetails
	 * @param listingId
	 * @param plat 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void displayBuyTicket(String lType,int movieId,int visit,int cineplexId, Movie movieDetails, int listingId, int plat) throws IOException, ParseException
	{

		Scanner sc =new Scanner(System.in);
		int hitCount=visit;
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		Calendar cal = Calendar.getInstance();
		Calendar calTemp2;

		calTemp2 = (Calendar) cal.clone();

		//Variables that are needed to display cineplex with most timeslot;


		//this method will only run for the first time user visit this page
		int cineId=0;
		if(hitCount==0){
			System.out.println();
			cineId=CustBuyTicketControl.chooseCineplexToDisplay(movieId,listingId);
		}
		else{
			cineId=cineplexId;
		}
		pair=MovieListingControl.formatShowtimeforListing(listingId,cineId, movieId, lType, 7, movieDetails);
		int choice=0;
		int c=1;
		do{
			System.out.println("Choose the day you want to purchase the ticket or choose cineplex");

			for(c=1;c<=pair.size();c++)
			{

				System.out.print((c+":"+pair.get(c-1).getDate()+"	"));
				calTemp2.add(calTemp2.DATE, 1);
				if(c%2==0){
					System.out.println();
				}
			}
			System.out.println(c+".Choose cineplex");
			System.out.println((c+1)+".Return to Main Menu");
			String s=sc.nextLine();
			choice=ValidationControl.validateAndReturnIntegerValue(s);
		}while(choice<=0||choice>(c+1));


		if(choice==c){
			displayBasedOnCineplex(lType,movieId,hitCount,cineId,movieDetails,listingId, plat);
		}
		else if(choice==(c+1))
		{
			CustDisplayMovieListingUi.displayCustMain();
		}
		//System.out.println(listingId);
		displayTicketForDay(pair,choice,movieDetails,listingId, lType, plat, cineplexId);
	}

/**
 * Method to let user choose between multiple cineplex because each cineplex has their own time slot
 * @param lType List Type, can be now showing, coming soon or preview
 * @param movieId
 * @param visit number of times this method is accessed, used to manipulate which cineplex to choose to display timeslots.
 * @param cineId
 * @param movieDetails
 * @param listingId
 * @throws IOException
 * @throws ParseException
 */
	public void displayBasedOnCineplex(String lType,int movieId,int visit,int cineId, Movie movieDetails,int listingId,int plat) throws IOException, ParseException
	{
		Calendar cal= Calendar.getInstance();
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		ArrayList<Cineplex> cnList= CineplexDataControl.readCineplex();

		Scanner sc=new Scanner(System.in);
		int cinplexId=0;
		ArrayList<ShowTime> startList=ShowTimeDataControl.readShowTimesBasedOnListingId(listingId);
		Set<Integer> cineList=new HashSet<Integer>();

		for(int i=0;i<startList.size();i++){
			cineList.add(startList.get(i).getCineplexId());
		}

		if(cineList.size()<=1){
			System.out.println("Sorry, no other cineplex show this movie!");
			displayBuyTicket(lType, movieId,visit,cineId,movieDetails, listingId, plat);
		}
		else
		{
			cinplexId=CustBuyTicketControl.filterNowShowingListingByCineplexId(listingId);
			visit++;
			displayBuyTicket(lType,movieId,visit,cinplexId,movieDetails,listingId, plat);
		}
	}



/**
 * Display time slot selection. 
 * @param pair A list of object that contain showtime and schedule objects
 * @param choice2 User choice
 * @param m Movie object(required parameter to go back to previous page)
 * @param listingId
 * @throws IOException
 * @throws ParseException
 */
	public void displayTicketForDay(ArrayList<ObjectContainer> pair, int choice2,Movie m,int listingId,String lType,int plat,int cineplexId) throws IOException, ParseException
	{

		ArrayList<ObjectContainer> pairingIdWithSlot=new ArrayList<ObjectContainer>();
		for(int i=0;i<pair.size();i++)
		{
			if(pair.get(i).getI()==choice2)
			{
				for(int j=0;j<pair.get(i).getShowTimeList().size();j++)
				{	
					ObjectContainer o=new ObjectContainer();
					o.setI(i+1);
					o.setShowTimeId(pair.get(i).getShowTimeList().get(j).getShowTimeId());
					o.setTimeValue(pair.get(i).getShowTimeList().get(j).getShowTimeValue());
					o.setsT(pair.get(i).getShowTimeList().get(j));
					pairingIdWithSlot.add(o);
				}
			}
		}

		System.out.println("\nSelect number beside each time slot to purchase ticket for that time slot.");
		Collections.sort(pairingIdWithSlot, new Comparator<ObjectContainer>() {
			@Override public int compare(ObjectContainer p1, ObjectContainer p2) {
				return p1.getTimeValue().compareTo(p2.getTimeValue());
			}

		});
		for(int i=0;i<pairingIdWithSlot.size();i++){
			System.out.print((i+1)+":"+pairingIdWithSlot.get(i).getTimeValue()+"	");
		}
		System.out.println();

		Scanner sc=new Scanner(System.in);
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		int showTimeId=pairingIdWithSlot.get(choice-1).getShowTimeId();
		CustBuyTicketsWithSeatsSelectionsUi.displayCustomerInfo( showTimeId,  m,
				 listingId, lType,  plat, cineplexId);
		//

	}




}