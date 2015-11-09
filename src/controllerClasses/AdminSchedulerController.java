package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import boundaryClasses.AdminSchedulerMainUi;
import boundaryClasses.AdminSchedulerUpdateUi;
import misc.ObjectContainer;
import data.Cinema;
import data.Cineplex;
import data.HolidayDate;
import data.Movie;
import data.MovieSchedule;
import data.Prices;
import data.ShowTime;
import data.Transaction;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TicketPriceAndHolidayDataControl;
import dataController.TransactionDataControl;

/**
 * Class that deals with formatting and controlling the UI for CRUD of schedule.
 * @author Chang En Kai
 *
 */
public class AdminSchedulerController extends MovieListingControl{
	
/**
 * Check if schedule is duplicated during movie schedule creation, if it is, redirect to update page.
 * The condition of duplicated is if the same movie has the same movie listing that are of now showing and coming soon status.	 * A movie listing is not duplicated if one of the same movie is showing on a platinum suite and one isn't. 
 * A movie listing is not duplicated if  one of the same movie had already ended its showing. 
 * This is to make sure that cinema staff can rerun new showing after it a list has ended.
 * @param sch
 * @return
 * @throws IOException
 * @throws ParseException
 */
	public boolean checkDuplicatedSchedule(MovieSchedule sch) throws IOException, ParseException{
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		int movie=0, plat=0,preview;
		Date startDate= new Date();
		Date endDate= new Date();
		for(int i=0;i<schList.size();i++)
		{
			startDate=schList.get(i).getStartDate();
			endDate=schList.get(i).getEndDate();
			movie=schList.get(i).getMovieId();
			plat=schList.get(i).getPlatOrNot();
			preview=schList.get(i).getPreviewStatus();
			if(movie==sch.getMovieId()&&plat==sch.getPlatOrNot()&&preview==schList.get(i).getPreviewStatus()&&schList.get(i).getStatus()!=4)
			{	
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Format forms to create movie schedule based on types such as now showing or coming soon
	 * @param type
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void createScheduleGeneric(int type) throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,movieId,movieLen,movieType, cinplexId;
		boolean validDate;
		MovieSchedule sch=new MovieSchedule();
		
		ArrayList<Movie> movieList= new ArrayList<Movie>();
		movieList=MovieDataControl.readMovie();
		
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		
	
		do{
			System.out.println("Please select which movie to be scheduled");
			for(int i=0;i<movieList.size();i++)
			{
				System.out.print((i+1)+":"+movieList.get(i).getMovieName()+" ");
				if((i+1)%4==0&i!=0){
					System.out.println();
				}
				pair.add(MiscControl.idPairerWithMovieLength(i, movieList.get(i).getMovieId(), movieList.get(i).getMovieLength(),movieList.get(i).getMovieType(),movieList.get(i).getBlockbuster()));
			}
			System.out.println();
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			if(choice>movieList.size()){
				System.out.println("Invalid input, please try again.");
			}
			
		}while(choice==-2|| choice>(movieList.size()));
		
		movieId=pair.get(choice-1).getId();
		sch.setMovieId(movieId);
		
		AdminSchedulerController sC=new AdminSchedulerController();
				
		
		
		
		
		movieLen=pair.get(choice-1).getMovieLen();
		movieType=pair.get(choice-1).getMovieType();
		int movieBlock=pair.get(choice-1).getBlock();
		
	
		pair.clear();
		sch.setThreeDOrNot(movieType-1);
	
		sch.setBlockBuster(movieBlock-1);
	    
		if(type==1)
		{
			sch.setStatus(1);
		}
		
	    if(type==2){
	    	sch.setPreviewStatus(1);
	    	sch.setStatus(2);
	    }
	    else{
	    	sch.setPreviewStatus(0);
	    }
	   if(type==3){
		   sch.setStatus(3);
	   }
	    
	    Calendar today=Calendar.getInstance();
		Date startDate;
		int choice4=0;
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String tmp=sdf.format(today.getTime());
		do{
			if(type==1)
				sch.setStartDate(today.getTime());
			else if(type==2){
				System.out.println("Please enter preview date(Eg.25/10/2015) of the movie:");
				tmp=sc.nextLine();	
			}
			else if(type==3){
				System.out.println("Coming soon date must not be today.");
				System.out.println("Please enter comning soon date(Eg.25/10/2015) of the movie:");
				tmp=sc.nextLine();	
			}
			
			startDate=ValidationControl.validateDate(tmp,type);
			
			}
		while(startDate==null);
		
		sch.setStartDate(startDate);
		
		int runDate=-2;
	do{
		System.out.println("How many days will the movie be shown?");
		 runDate=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}
	while(runDate<=0);
	sch.setEndDate(TimeDateControl.calculateEndDate(startDate,runDate));
	
	int plat=0;
	do{
		System.out.println("Is this listing for platinum suite?");
		System.out.println("1.No");
		System.out.println("2.Yes");
		plat=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
	}while(plat<=0);
		
	sch.setPlatOrNot(plat-1);
	
	
	boolean duplicate=sC.checkDuplicatedSchedule(sch);
	int dChoice=0;
	if(duplicate)
	{
		do{
			System.out.println("Listing for this movie already found, do you want to update it?");
			System.out.println("If you choose no, you need to rechoose another movie again.");
			System.out.println("1.Yes");
			System.out.println("2.No");
			String s=sc.nextLine();
			dChoice=ValidationControl.validateYesNoAndReturnIntegerValue(s);
			if(dChoice==1){
				AdminSchedulerUpdateUi.displayUpdateMain();
				return;
			}
			else if(dChoice==2)
			{
				createScheduleGeneric(type);
				return;
			}	
		}while(dChoice<=0);
		
	}

	
	
	int schid=0;
	ArrayList<MovieSchedule> sch2=new ArrayList<MovieSchedule>();
	sch2=MovieScheduleDataControl.readScheduleListing();
	
	if(!sch2.isEmpty()){
		 schid=sch2.get(sch2.size()-1).getListingId()+1;
	}
	else
	{
		schid=1;
	}
	sch.setListingId(schid);
	AdminShowTimeController.timeSlotHandler("c",0,sch,movieId,movieLen,movieType,runDate);


		
	    
	}
	
	
	

	
/**
 * Method to end a movie listing, can be assesed only on update movie schedule page
 * @param sch
 * @throws IOException
 * @throws ParseException
 */
	public static void endListing(MovieSchedule sch) throws IOException, ParseException {
		List alMS = new ArrayList() ;// to store Professors data
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		Calendar cal=Calendar.getInstance();
		
		
		
		int status=0;
		for(int i=0;i<schList.size();i++){
			
			String startDate=finalDateFormatter.format(schList.get(i).getStartDate());
			String endDate=finalDateFormatter.format(schList.get(i).getEndDate());
			
			if(schList.get(i).getListingId()==sch.getListingId()){
				status=4;
			}
			else
			{
				status=schList.get(i).getStatus();
			}
				

			//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
			StringBuilder st =  new StringBuilder() ;
			st.append(schList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(schList.get(i).getListingId());
			st.append(SEPARATOR);
			st.append(startDate);
			st.append(SEPARATOR);
			st.append(endDate);
			st.append(SEPARATOR);
			st.append(schList.get(i).getTypeofDay());
			st.append(SEPARATOR);
			st.append(status);
			st.append(SEPARATOR);
			st.append(schList.get(i).getThreeDOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getBlockBuster());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPlatOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPreviewStatus()+"\n");

			alMS.add(st.toString()) ;
		}

		writeB("data/movieScheduleListing.txt",alMS);
		System.out.println("Movie is set to end of showing!");
		AdminSchedulerUpdateUi.displayUpdateMain();
		
		
	}
/**
 * Method to extend either the start or end date of a movie listing
 * @param sOrE
 * @param movieSchedule
 * @param m
 * @param cineId
 * @throws IOException
 * @throws ParseException
 */
	public static void extendDate(int sOrE, MovieSchedule movieSchedule,Movie m, int cineId) throws IOException, ParseException {
		MovieSchedule sch=movieSchedule;
		int extend=0;
		
		Scanner sc=new Scanner(System.in);
		if(sOrE==1){
			do{
				System.out.println("Enter how long you want to extend the start date(input -1 to return):");
				String s=sc.nextLine();
				extend=ValidationControl.validateAndReturnIntegerValue(s);
				if(s.equals("-1")){
					AdminSchedulerUpdateUi.displayUpdatePageDetails( movieSchedule,  m, cineId);
				}
			
			}while(extend<=0);
				
			Date startDate=sch.getStartDate();
			Calendar cal=Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DATE, -extend);
			sch.setStartDate(cal.getTime());
			MovieScheduleDataControl.updateSchedule(sch);
			AdminShowTimeController.timeSlotHandler("u",0, sch,m.getMovieId(),m.getMovieLength(),m.getMovieType(),extend);
		}
		else if(sOrE==2){
			do{
				System.out.println("Enter how long you want to extend the end date(input -1 to return):");
				String s=sc.nextLine();
				extend=ValidationControl.validateAndReturnIntegerValue(s);
				if(s.equals("-1")){
					AdminSchedulerUpdateUi.displayUpdatePageDetails( movieSchedule,  m, cineId);
				}
			
			}while(extend<=0);
			
			Date endDate=sch.getEndDate();
			Calendar cal=Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, extend);
			sch.setEndDate(cal.getTime());
			MovieScheduleDataControl.updateSchedule(sch);
			sch.setStartDate(endDate);
			
		   AdminShowTimeController.timeSlotHandler("u",0,sch,m.getMovieId(),m.getMovieLength(),m.getMovieType(),extend);
			
		}
	}

	/**
	 * Method to handle the form of editing specific timeslot
	 * @param pairingIdWithSlot
	 * @param pair
	 * @param choice
	 * @param sch
	 * @param m
	 * @param cineId
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void handleEditTimeSlot(ArrayList<ObjectContainer>pairingIdWithSlot,ArrayList<ObjectContainer>pair,int choice,MovieSchedule sch,Movie  m,int cineId) throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choose=0;
		do{
			System.out.println("Select number beside timeslot to edit(input -1 to return):");
			for(int i=0;i<pairingIdWithSlot.size();i++)
			{
				System.out.print((i+1)+":"+pairingIdWithSlot.get(i).getTimeValue()+"	");
			}
			System.out.println();
			String s=sc.nextLine();
			choose=ValidationControl.validateAndReturnIntegerValue(s);
			if(s.equals("-1")){
				AdminSchedulerUpdateUi.displayUpdatePageDetails( sch,m, cineId);	
				
			}
			
		}while(choose<=0||choose>pairingIdWithSlot.size());
		
		int showTimeId=pairingIdWithSlot.get(choose-1).getShowTimeId();
		int cinemaId=pairingIdWithSlot.get(choose-1).getsT().getCinemaId();
	
		boolean clash=false;
		ArrayList<Transaction>tList=TransactionDataControl.readTranscation();
		for(int i=0;i<tList.size();i++)
		{
			if(tList.get(i).getShowtimeId()==showTimeId)
			{
				System.out.println("Cannot edit timeslot because transcation already exist!");
				clash=true;
				break;
			}
		}
		if(!clash)
		{
			Boolean valid2=false;
			int time=0;
			do
			{
				String dateValue=pairingIdWithSlot.get(choose-1).getDate();
				String intTime=pairingIdWithSlot.get(choose-1).getTimeValue();
				Calendar temp=Calendar.getInstance();
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				temp.setTime(sdf.parse(dateValue));
				
				System.out.println("This cinema already have these time slot allocated on "+sdf.format(temp.getTime()));
				ArrayList<String> allocatedShowTime = AdminShowTimeController.cinemaallocatedTime(cinemaId,temp);
				for(int l=0;l<allocatedShowTime.size();l++)
				{
					System.out.print(allocatedShowTime.get(l)+"	");
					
				}
				System.out.println();
				System.out.println("Enter edited timeslot:");
				String s=sc.nextLine();
				time = ValidationControl.validateAndReturnTime(s);
				if(time!=-2){
					valid2 = AdminShowTimeController.checkTimeSlotClashforUpdate(m.getMovieLength(),cinemaId, s,intTime,temp);
				}
				if(valid2 &&time!=-2)
				{
					String showTimeValue=TimeDateControl.minutesPlusTime(m.getMovieLength(), s);
					ShowTimeDataControl.updateShowTime(showTimeId, showTimeValue);
					
				}
			}while(!valid2 || time<=-2);
		
		}
		else{
			AdminSchedulerUpdateUi.displayUpdateTimeSlot( pair,  choice, sch,  m, cineId);
		}
	}
	
		
	}
	

	


