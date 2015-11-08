package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.AdminSchedulerController;
import controllerClasses.AdminShowTimeController;
import controllerClasses.TimeDateControl;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import data.Transaction;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TransactionDataControl;

public class AdminSchedulerUpdateUi extends AdminSchedulerUi{
	
	
	public static void displayUpdateMain() throws IOException, ParseException
	{
		Scanner sc=new Scanner(System.in);
		int choice=0;
	
		System.out.println("#############################################");
		System.out.println("#            Schedule Update Page           #");
		System.out.println("#############################################");


		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();

		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		ArrayList<Movie> movieList=MovieDataControl.readMovie();
		String status = null;
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|S/N|	|Movie Name|                   |Status|		|Starting Date|		|Ending Date|		|Platinum/Normal|");
		for(int i=0;i<schList.size();i++)
		{
			for(int j=0;j<movieList.size();j++)
			{
				if(schList.get(i).getMovieId()==movieList.get(j).getMovieId()){
					if(schList.get(i).getStatus()==1){
						status="Now Showing";
					}
					else if(schList.get(i).getStatus()==2){
						status="Preview    ";
					}
					else if(schList.get(i).getStatus()==3){
						status="Coming Soon";
					}else if(schList.get(i).getStatus()==4){
						status="End Showing";
					}
					String white="";
					String plati="";
					int spacing=29-movieList.get(j).getMovieName().length();
					for(int k=0;k<spacing;k++)
					{
						white=white+" ";
					}
					int plat=schList.get(i).getPlatOrNot();
					
					if(plat==1)
					{
						plati="Platinum";
					}
					else
					{
						plati="Normal";
					}
					
					System.out.println("   "+(i+1)+"|	"+movieList.get(j).getMovieName()+white+status+"	"+sdf.format(schList.get(i).getStartDate())+"		"+sdf.format(schList.get(i).getEndDate())+"		     "+plati);
					ObjectContainer o=new ObjectContainer();
					o.setM(movieList.get(j));
					o.setI((i+1));
					o.setMovieListing(schList.get(i));
					pair.add(o);
				}
			}
		}
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------");do{	
			System.out.print("Select number beside the listing to edit (input 0 to retun back to previous page):");
			 choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			 if(choice<0||choice>schList.size())
				{
					System.out.println("Invalid choice, try again");
				}
				
		
		}while(choice<0||choice>schList.size());
			
		for(int i=0;i<pair.size();i++)
		{
				if(pair.get(i).getI()==choice)
				{
					displayUpdatePageDetails(pair.get(i).getMovieListing(),pair.get(i).getM(),0);
				}
		}
	}
	
	
	
	public static void displayUpdatePageDetails(MovieSchedule movieSchedule, Movie movie,int cineId) throws IOException, ParseException
	{
		String eOrSListing="";
		int choice=0;
		if(movieSchedule.getStatus()<4){
			eOrSListing="1.End Listing";
		}
		else if(movieSchedule.getStatus()==4){
			eOrSListing="1.Relist";
		}
		Scanner sc=new Scanner(System.in);
		
		do{
			System.out.println(eOrSListing);
			System.out.println("2.Extend Start Date");
			System.out.println("3.Extend End Date");
			System.out.println("4.Edit Exsiting Timeslot");
			System.out.println("5.Rechoose listing");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<0||choice>5);
		
		if(choice==1){
			if(movieSchedule.getStatus()<4)
			{
				AdminSchedulerController.endListing(movieSchedule);
			}
			else if(movieSchedule.getStatus()==4)
			{
				MovieScheduleDataControl.updateScheduleStatus();
				displayUpdateMain();
			}
			
		}
		else if(choice==2)
		{
			AdminSchedulerController.extendDate(1,movieSchedule, movie,cineId);
		}
		else if(choice==3)
		{
			AdminSchedulerController.extendDate(2,movieSchedule, movie,cineId);
		}
		else if(choice==4)
		{
			editExistingTimeSlot(movieSchedule, movie.getMovieId(), 0, movie);
		
		}
		else if(choice==5)
		{
			displayUpdateMain();
		}
		
		
	}
	
	public static void editExistingTimeSlot(MovieSchedule movieSchedule,int movieId,int cineId, Movie movie) throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int run=TimeDateControl.calculateRunDate(movieSchedule.getStartDate(), movieSchedule.getEndDate());
		int ucinI;
		int choice3;
		ArrayList<ObjectContainer> pair = MovieListingControl.formatShowtimeforListing(movieSchedule.getListingId(),cineId, movie.getMovieId(), "update", run, movie);
		cineId=pair.get(0).getCineplexId();
		ArrayList<Cineplex> cineList=CineplexDataControl.readCineplex();
		do
		{
			System.out.println("1.Switch Cineplex");
			System.out.println("2.Edit timeslot");
			System.out.println("3.Go back");
			choice3=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice3<0||choice3>3);
		if(choice3==1)
		{
			for(int i=0;i<cineList.size();i++)
			{
				System.out.println((i+1)+":"+cineList.get(i).getCineplexName());
			}
			ucinI=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			editExistingTimeSlot(movieSchedule, movieId, ucinI, movie);
		}
		else if(choice3==2)
		{
			
				System.out.println("Select number beside date to edit timeslot of that date.");
				for(int i=0;i<pair.size()-1;i++){
					System.out.print((i+1)+":"+pair.get(i).getDate()+"		");
					if((i+1)%2==0){
						System.out.println();
					}
				}
				System.out.println();
				int select=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
				displayUpdateTimeSlot(pair,  select, movieSchedule,  movie, cineId);
			
		}
		else if(choice3==3)
		{
			displayUpdatePageDetails(movieSchedule, movie, 0);
		}
		
		
	}
	
	

	public static void displayUpdateTimeSlot(ArrayList<ObjectContainer> pair, int choice,MovieSchedule movieSchedule, Movie movie,int cineId) throws IOException, ParseException {
		ArrayList<ObjectContainer> pairingIdWithSlot=new ArrayList<ObjectContainer>();
		String dateChoosen = "";
		for(int i=0;i<pair.size();i++)
		{
			if(pair.get(i).getI()==choice)
			{
				dateChoosen=pair.get(i).getDate();
				for(int j=0;j<pair.get(i).getShowTimeList().size();j++)
				{	
					ObjectContainer o=new ObjectContainer();
					o.setI(i+1);
					o.setShowTimeId(pair.get(i).getShowTimeList().get(j).getShowTimeId());
					o.setTimeValue(pair.get(i).getShowTimeList().get(j).getShowTimeValue());
					o.setsT(pair.get(i).getShowTimeList().get(j));
					o.setDate(pair.get(i).getDate());
					pairingIdWithSlot.add(o);
				}
			}
		}
		
		Collections.sort(pairingIdWithSlot, new Comparator<ObjectContainer>() {
	        @Override public int compare(ObjectContainer p1, ObjectContainer p2) {
	            return p1.getTimeValue().compareTo(p2.getTimeValue());
	        }

	    });
		System.out.println("Date choosen:"+pairingIdWithSlot.get(0).getDate());
		for(int i=0;i<pairingIdWithSlot.size();i++){
			System.out.print(pairingIdWithSlot.get(i).getTimeValue()+"	");
		}
		System.out.println();
		System.out.println("1.Go Back");
		System.out.println("2.Add timeslot");
		if(pairingIdWithSlot.size()!=0){
			System.out.println("3.Edit timeslot");
			System.out.println("4.Remove timeslot");
		}
	
		Scanner sc=new Scanner(System.in);
		int choice2=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		int showTimeId=0;
		if(choice2==1){
			editExistingTimeSlot(movieSchedule, movie.getMovieId(), cineId, movie);
		}
		if(choice2==2)
		{
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			MovieSchedule tmpSch = movieSchedule;
			tmpSch.setStartDate(sdf.parse(dateChoosen));
			AdminShowTimeController.TimeSlotHandler("add",cineId,tmpSch , movie.getMovieId(), movie.getMovieLength(), movie.getMovieType(), 1);
		}
		
		
		if(choice2==3||choice2==4){
			if(choice2==3)
			{
				AdminSchedulerController.handleEditTimeSlot(pairingIdWithSlot, pair, choice, movieSchedule, movie, cineId);
			}
			else if(choice2==4)
			{
				int remove=0;
				do{
					System.out.println("Select number remove timeslot(input -1 to return).");
					for(int i=0;i<pairingIdWithSlot.size();i++)
					{
						System.out.print((i+1)+":"+pairingIdWithSlot.get(i).getTimeValue()+"	");
					}
					String s=sc.nextLine();
					 remove=ValidationControl.validateAndReturnIntegerValue(s);
					if(s.equals("-1")){
						displayUpdateTimeSlot( pair,  choice, movieSchedule,  movie, cineId);
					}
				}while(remove<=0||remove>pairingIdWithSlot.size());
				
				showTimeId=pairingIdWithSlot.get(remove-1).getShowTimeId();
				boolean tClash=false;
				ArrayList<Transaction>tList2=TransactionDataControl.readTranscation();
				for(int i=0;i<tList2.size();i++)
				{
					if(tList2.get(i).getShowtimeId()==showTimeId)
					{
						System.out.println("Cannot remove timeslot because transcation already exist!");
						tClash=true;
						break;
					}
				}
				if(!tClash)
				{
					ShowTimeDataControl.removeShowTime(showTimeId);
				}
				else{
					displayUpdateTimeSlot( pair,  choice, movieSchedule,  movie, cineId);
				}
			}
		}
		editExistingTimeSlot(movieSchedule, movie.getMovieId(), cineId, movie);
		
	}
	
	
	
	
}

