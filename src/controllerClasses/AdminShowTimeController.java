package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import boundaryClasses.AdminSchedulerMainUi;
import misc.ObjectContainer;
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
 * @param movieLen
 * @param cinemaId
 * @param inputTime
 * @param time2
 * @param cal
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
	 * @param cal
	 * @param movieLen
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
	 * @param cinemaId
	 * @param tmp
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
	 * @param operation
	 * @param cinpleId
	 * @param sch
	 * @param movieId
	 * @param movieLen
	 * @param movieType
	 * @param runDate
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void timeSlotHandler(String operation,int cinpleId,MovieSchedule sch, int movieId, int movieLen, int movieType,int runDate) throws IOException, ParseException {	
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
				System.out.println("Please choose which cineplex to create time slot for selected movie");
				for(int i=0;i<cnList.size();i++){
					System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
				}
				 cineplexId=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
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
			System.out.println("Please choose Cinema Hall:");
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
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
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
						
					}
					System.out.print("\nPlease enter show time(eg.1900 for 7pm) for slot number "+(counter+1)+".");
					String s=sc.nextLine();
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
		MovieScheduleDataControl.createSchedule(sch);}
	
	int repeat=0;
	do
	{	
		System.out.println("Time slot and listing created. Do you want to create more time slot for different cineplex?");
		System.out.println("1.Yes");
		System.out.println("2.No");
		repeat=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
	}while(repeat<=0);
	
	if(repeat==1){
		timeSlotHandler("r",0, sch,movieId,movieLen,movieType,runDate);
	}
	else if(repeat==2)
	{
			AdminSchedulerMainUi.displaySchedulerMain();		
	}
		
	
		
  }

	/* data entry function, not for real time use
	
	public static void TimeSlotCopier(MovieSchedule sch, int cineplexId) throws IOException, ParseException
	{
		ArrayList<ShowTime> stList=ShowTimeDataControl.readShowTimesBasedOnListingId(sch.getListingId());
		
		for(int i=0;i<stList.size();i++)
		{
			ShowTime copier=stList.get(i);
			ShowTime copier2=stList.get(i);
			if(cineplexId==1)
			{
				copier.setCinemaId(stList.get(i).getCinemaId()+3);
				copier.setCineplexId(stList.get(i).getCineplexId()+1);
				ShowTimeDataControl.createTimeSlot(copier,sch);
				copier2.setCinemaId(stList.get(i).getCinemaId()+3);
				copier2.setCineplexId(stList.get(i).getCineplexId()+1);
				ShowTimeDataControl.createTimeSlot(copier2,sch);
			}
			else if(cineplexId==2){
				copier.setCinemaId(stList.get(i).getCinemaId()+3);
				copier.setCineplexId(stList.get(i).getCineplexId()+1);
				ShowTimeDataControl.createTimeSlot(copier,sch);
				copier.setCinemaId(stList.get(i).getCinemaId()-6);
				copier.setCineplexId(stList.get(i).getCineplexId()-2);
				ShowTimeDataControl.createTimeSlot(copier,sch);
			}
			else if(cineplexId==3){
				copier.setCinemaId(stList.get(i).getCinemaId()-3);
				copier.setCineplexId(stList.get(i).getCineplexId()-1);
				ShowTimeDataControl.createTimeSlot(copier,sch);
				copier.setCinemaId(stList.get(i).getCinemaId()-3);
				copier.setCineplexId(stList.get(i).getCineplexId()-1);
				ShowTimeDataControl.createTimeSlot(copier,sch);
			}
		}
		
	}

	int copy=0;
		if(!operation.equals("r"))
		{
			do
			{	
				System.out.println("Do you want to copy the same timeslots to other cineplex?");
				System.out.println("1.Yes");
				System.out.println("2.No");
				copy=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
			}while(copy<=0);
			
			if(copy==1){
				
				TimeSlotCopier(sch,cineplexId);
			}
			else if(copy==2)
			{
					
			}

	*/
	
}
