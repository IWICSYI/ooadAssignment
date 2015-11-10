package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import boundaryClasses.AdminSchedulerCreateUi;
import boundaryClasses.AdminSchedulerMainUi;
import boundaryClasses.AdminSchedulerUpdateUi;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;


/**
 * Class that communicate with ShowTimeDataControl and deal with mainly the formating the UI that deals with CRUD of time slots
 * @author Chang En Kai
 *
 */
public class AdminShowTimeController extends AdminSchedulerController {
	
/**
 * Check for timeslot clash when admin insert new time slot for movie. This method is used during update of timeslot.
 * @param movieLen Movie length
 * @param cinemaId
 * @param inputTime
 * @param time2 Existed time
 * @param cal Calendar object
 * @return
 * @throws IOException
 * @throws ParseException
 */
	public static boolean checkTimeSlotClashforUpdate(int movieLen,int cinemaId,String inputTime, String time2,Calendar cal) throws IOException, ParseException {
		boolean valid=true;
		String sED=TimeDateControl.minutesPlusTime(movieLen,inputTime);
		
		String[] seDarr=sED.split("-");
		ArrayList<String> allocatedShowTimeC = cinemaallocatedTime(cinemaId,cal);
		Date startTime = new SimpleDateFormat("HHmm").parse(seDarr[0]);
		Date endTime = new SimpleDateFormat("HHmm").parse(seDarr[1]);

		
	
		for(int i=0;i<allocatedShowTimeC.size();i++)
		{
				String[] timeArr = allocatedShowTimeC.get(i).split("-");
				String[] timeArr2 = time2.split("-");
				
				Date startTimeExist= new SimpleDateFormat("HHmm").parse(timeArr[0]);
				Date endTimeExist=new SimpleDateFormat("HHmm").parse(timeArr[1]);
				
				Date startTimeUpdate= new SimpleDateFormat("HHmm").parse(timeArr2[0]);
				Date endTimeUpdate=new SimpleDateFormat("HHmm").parse(timeArr2[1]);
				
				if(startTimeUpdate.after(startTimeExist)&&startTimeUpdate.before(endTimeExist)|| startTimeUpdate.equals(startTimeExist)){
					valid=true;
					break;
				}
				
				else if(startTime.before(endTimeExist) && startTime.after(startTimeExist) || startTime.equals(startTimeExist))
				{
					System.out.println("Time clashed! Please try again!");
					valid=false;
					break;
				}
				
				else if(startTimeExist.after(startTime)&&startTimeExist.before(endTime))
				{
					System.out.println("Time clashed! Please try again!");
					valid=false;
					break;
				}
					
			
		}
		return valid;
	}

	
	/**
	 * Check for timeslot clash when admin insert new time slot for movie. This method is used during creation of timeslot for the first time.
	 * @param cinemaId
	 * @param inputTime
	 * @param cal Calendar object
	 * @param movieLen Movie length
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	
	public static boolean checkTimeSlotClash(int cinemaId,String inputTime,Calendar cal, int movieLen) throws IOException, ParseException {
		boolean valid=true;
		String sED=TimeDateControl.minutesPlusTime(movieLen,inputTime);
		String[] seDarr=sED.split("-");
		ArrayList<String> allocatedShowTimeC = cinemaallocatedTime(cinemaId,cal);
		Date startTime = new SimpleDateFormat("HHmm").parse(seDarr[0]);
		Date endTime = new SimpleDateFormat("HHmm").parse(seDarr[1]);

		
	
		for(int i=0;i<allocatedShowTimeC.size();i++)
		{
				String[] timeArr = allocatedShowTimeC.get(i).split("-");
				Date startTimeExist = new SimpleDateFormat("HHmm").parse(timeArr[0]);
				
				Date endTimeTimeExist = new SimpleDateFormat("HHmm").parse(timeArr[1]);
				
				
				if(startTime.before(endTimeTimeExist) && startTime.after(startTimeExist) || startTime.equals(startTimeExist))
				{
					System.out.println("Time clashed! Please try again!");
					valid=false;
					break;
				}
				
				else if(startTimeExist.after(startTime)&&startTimeExist.before(endTime))
				{
					System.out.println("Time clashed! Please try again!");
					valid=false;
					break;
				}
					
			
		}
		return valid;
	}

	/**
	 * Retrieve timeslot allocated on each cinema screen.
	 * @param cinemaId cinemaId
	 * @param tmp Calendar object that contain dates to check for
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<String> cinemaallocatedTime(int cinemaId,Calendar tmp) throws IOException, ParseException{
		ArrayList<ShowTime> stC=ShowTimeDataControl.readShowTimesBasedOnCinemaIdAndNowShowing(cinemaId,tmp);
		//ArrayList<ObjectContainer> allocatedTime=new  ArrayList<ObjectContainer>();
		MiscControl ms=new MiscControl();
		ArrayList<String> unsorted=new  ArrayList<String>();
		ArrayList<String> sorted=new  ArrayList<String>();
		//System.out.println();
		
		if(!stC.isEmpty()){
			for(int i=0;i<stC.size();i++)
			{
				
				unsorted.add(stC.get(i).getShowTimeValue());
			}
			for(int i=0;i<unsorted.size();i++)
			{
				//System.out.println("us"+unsorted.get(i));
				ms.setStringList(unsorted);
				ms.sort();
				sorted=ms.getStringList();
				//System.out.println("s"+sorted.get(i));
				
				
			}
			
		}
		
		return sorted;
		
	}
	
	/**
	 * Format form to handle creation/update of timeslots.
	 * @param operation Can be add, update or create, use to differentiate which operation is using this function
	 * @param cinpleId cineplex ID
	 * @param sch Movie schedule
	 * @param movieId
	 * @param movieLen Movie length
	 * @param movieType 3D or not
	 * @param runDate date the movie run
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void timeSlotHandler(String operation,int cinpleId,MovieSchedule sch, int movieId, int movieLen, int movieType,int runDate,Movie movie) throws IOException, ParseException {	
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,time,plat,noSeats;
		String cinemaName;
		String type = "";
		String showTimeValue = null;
		boolean valid=false,valid2=false;
		int cineplexId=0;
		
		
		if(cinpleId==0)
		{
			ArrayList<Cineplex> cnList= CineplexDataControl.readCineplex();
			do{
				System.out.println("Please choose which cineplex to create time slot for selected movie(input -1 to exit).");
				for(int i=0;i<cnList.size();i++){
					System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
				}
				String tmps=sc.nextLine();
				if(tmps.equals("-1"))
				{
					if(operation.equals("c"))
					{
						AdminSchedulerCreateUi.displaySchedulerCreatePageMain();
					}
					else if(operation.equals("u"))
						AdminSchedulerUpdateUi.displayUpdateMain();
					else if(operation.equals("add"))
						AdminSchedulerUpdateUi.displayEditExistingTimeSlot( sch, movieId, cinpleId,  movie);
				}
				cineplexId=ValidationControl.validateAndReturnIntegerValue(tmps);
			}while(cineplexId<=0||cineplexId>cnList.size());
		}
		else
		{
			cineplexId=cinpleId;
		}
		
		int platOrNot=sch.getPlatOrNot();
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<String> allocatedShowTime=new ArrayList<String>();
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
		ArrayList<Cinema> cinemaList= new ArrayList<Cinema>();
		cinemaList=CinemaDataControl.readCinemaByCineplexId(cineplexId);
		int count=1;
		do{
			System.out.println("Please choose Cinema Hall(input -1 to exist):");
			for(int j=0;j<cinemaList.size();j++)
			{
				if(cinemaList.get(j).getCinemaType()==platOrNot)
				{
					System.out.print(count+":"+cinemaList.get(j).getCinemaHallName()+"	");
					pair.add(MiscControl.idPairerWithCinema(j, cinemaList.get(j).getCinemaId(), cinemaList.get(j).getCinemaHallName(),cinemaList.get(j).getCinemaType(),cinemaList.get(j).getSeats()));
					count++;
				}
			}
			System.out.println();
			String tmps2=sc.nextLine();
			if(tmps2.equals("-1"))
			{
				if(operation.equals("c"))
				{
					AdminSchedulerCreateUi.displaySchedulerCreatePageMain();
				}
				else if(operation.equals("u"))
					AdminSchedulerUpdateUi.displayUpdateMain();
				else if(operation.equals("add"))
					AdminSchedulerUpdateUi.displayEditExistingTimeSlot( sch, movieId, cinpleId,  movie);
			}
			choice=ValidationControl.validateAndReturnIntegerValue(tmps2);
		}while(choice<=0||choice>pair.size());
		
		
		cinemaId=pair.get(choice-1).getId();
		//sch.setCinemaId(cinemaId);
		
		
		
		cinemaName=pair.get(choice-1).getName();
		
		plat=pair.get(choice-1).getCineType();
		
		noSeats=pair.get(choice-1).getSeatNo();
		
		pair.clear();
		Calendar cal=Calendar.getInstance();
		cal.setTime(sch.getStartDate());
		Calendar temp=(Calendar) cal.clone();
		Calendar end=(Calendar) cal.clone();
		end.add(Calendar.DATE, 1);
		
		
		
		for(int d=1;d<=runDate;d++)
		{	
			type=TimeDateControl.retrieveDaySpell(temp);	
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			
			do
			{
				if(sch.getPreviewStatus()==1){
					
					String tmpDate=sdf.format(temp.getTime());
					System.out.println("Please enter preview show time for "+tmpDate);
					
				}
				else{
					String tmpDate=sdf.format(temp.getTime());
					System.out.println("Please enter show time for "+tmpDate);
					
				}
				
				
				//SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				
				
				do
				{
					System.out.print("Please enter no of slots for show times:");
					String s=sc.nextLine();
					valid=ValidationControl.isInteger(s);
					num=ValidationControl.validateAndReturnIntegerValue(s);
					if(num>4)
					{
						System.out.println("Too many time slot.");
					}
				}while(num>4);
				
			}
			while(!valid);
			
			int counter=0;
			while(counter<num)
			{
				do
				{
					String tmpDate=sdf.format(temp.getTime());
					System.out.println("This "+cinemaName+" already have these time slot allocated on "+tmpDate);
					allocatedShowTime=AdminShowTimeController.cinemaallocatedTime(cinemaId,temp);
					for(int l=0;l<allocatedShowTime.size();l++)
					{
						System.out.print(allocatedShowTime.get(l)+"	");
						if(allocatedShowTime.isEmpty()){
							System.out.println("No timeslot for this cinema hall!");
							
						}
					}
					System.out.println();
					System.out.print("Please enter show time(eg.1900 for 7pm) for slot number "+(counter+1)+"(input -1 to rechoose everything).");
					String s=sc.nextLine();
					if(s.equals("-1"))
					{
						timeSlotHandler( operation, cinpleId, sch,  movieId,  movieLen,  movieType, runDate, movie);
					}
					
					time=ValidationControl.validateAndReturnTime(s);
					if(time!=-2)
					{
						valid2=checkTimeSlotClash(cinemaId,s,temp,movieLen);
					}
					if(valid2 &&time!=-2)
					{
						showTimeValue=TimeDateControl.minutesPlusTime(movieLen, s);
						ShowTime sT=new ShowTime();
						sT.setCinemaId(cinemaId);
						sT.setMovieId(movieId);
						sT.setShowTimeValue(showTimeValue);
						sT.setDayType(temp.get(Calendar.DAY_OF_WEEK));
						sT.setNoOfSeats(noSeats);
						//SimpleDateFormat a=new SimpleDateFormat("dd/MM/yyyy");
						sT.setStartDate(temp.getTime());
						
						
						sT.setEndDate(end.getTime());
						sT.setCineplexId(cineplexId);
						ShowTimeDataControl.createTimeSlot(sT,sch);
						
						
						showTimeList.add(sT);
						counter++;
					}
					
				}while(!valid2 && time<=-2);
				
			}
				temp.add(Calendar.DATE, 1);
				end.add(Calendar.DATE, 1);
				sch.setShowTimeList(showTimeList);
				
				
			}
	if(operation.equals("c")){
		MovieScheduleDataControl.createSchedule(sch);
		}
	
	int repeat=0;
	do
	{	
		System.out.println("Time slot and listing created. Do you want to create more time slot for different cineplex?");
		System.out.println("1.Yes");
		System.out.println("2.No");
		repeat=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
	}while(repeat<=0);
	
	if(repeat==1){
		timeSlotHandler("r",0, sch,movieId,movieLen,movieType,runDate,movie);
	}
	else if(repeat==2)
	{
		if(operation.equals("c"))
		{
			AdminSchedulerMainUi.displaySchedulerMain();
		}
		else if(operation.equals("u"))
			AdminSchedulerUpdateUi.displayUpdateMain();
		else if(operation.equals("add"))
			AdminSchedulerUpdateUi.displayEditExistingTimeSlot( sch, movieId, cinpleId,  movie);
	}
	
		
	
		
  }

	
	
}
