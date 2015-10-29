package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.SchedulerController;
import controllerClasses.ShowTimeController;
import controllerClasses.TimeDateControl;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CinemaDataControl;
import dataController.DataControl;

public class AdminTimeSlotUi extends DataControl {

	public void TimeSlotHandler(MovieSchedule sch, int movieId, int movieLen, int movieType,int cineplexID,int runDate) throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,time,plat,dayType,noSeats;
		String cinemaName;
		String type = "";
		String showTimeValue = null;
		boolean valid=false,valid2=false;
		
		ShowTimeController sControl=new ShowTimeController();
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		
		
		
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<String> allocatedShowTime=new ArrayList<String>();
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
		ArrayList<Cinema> cinemaList= new ArrayList<Cinema>();
		cinemaList=CinemaDataControl.readCinemaByCineplexId(sch.getCineplexId());
		do{
			System.out.println("Please choose Cinema Hall:");
			for(int j=0;j<cinemaList.size();j++)
			{
				System.out.print((j+1)+":"+cinemaList.get(j).getCinemaHallName()+"	");
				pair.add(MiscControl.idPairerWithCinema(j, cinemaList.get(j).getCinemaId(), cinemaList.get(j).getCinemaHallName(),cinemaList.get(j).getCinemaType(),cinemaList.get(j).getSeats()));
				
			}
			System.out.println();
			choice=vl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=-1);
		
		
		cinemaId=pair.get(choice-1).getId();
		sch.setCinemaId(cinemaId);
		System.out.println(cinemaId+" "+sch.getMovieId()+" "+sch.getCineplexId());
		SchedulerController sC=new SchedulerController();
		boolean duplicate=sC.checkDuplicatedSchedule(sch);
		int dChoice=0;
		if(duplicate)
		{
			System.out.println("Schedule for this movie in this cineplex in this cinema hall already found, do you want to update it?");
			System.out.println("If you choose no, you need to rechoose cinema hall again.");
			System.out.println("1.Yes");
			System.out.println("2.No");
			String s=sc.nextLine();
			dChoice=ValidationControl.validateYesNoAndReturnIntegerValue(s);
			if(dChoice==1){
				AdminSchedulerUpdateUi a=new AdminSchedulerUpdateUi();
				a.displayUpdateExistPage(sch);
				return;
			}
			else if(dChoice==2)
			{
				TimeSlotHandler(sch,  movieId,  movieLen,  movieType, cineplexID,runDate);
				return;
			}
			
			
			
			
		}
		
		
		cinemaName=pair.get(choice-1).getName();
		
		plat=pair.get(choice-1).getCineType();
		sch.setPlatOrNot(plat);
		
		noSeats=pair.get(choice-1).getSeatNo();
		
		pair.clear();
		Calendar cal=Calendar.getInstance();
		cal.setTime(sch.getStartDate());
		Calendar temp=(Calendar) cal.clone();
		
		
		for(int d=1;d<=runDate;d++)
		{	
			type=TimeDateControl.retrieveDaySpell(temp);	
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			String tmpDate=sdf.format(temp.getTime());
			
			do
			{
				System.out.println("Please enter show time for "+tmpDate);
				//SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				System.out.println("This "+cinemaName+" already have these time slot allocated on "+tmpDate);
				allocatedShowTime=sControl.cinemaallocatedTime(cinemaId,d,temp,sch);
				for(int l=0;l<allocatedShowTime.size();l++)
				{
					System.out.print(allocatedShowTime.get(l)+"	");
					
				}
				
				
				System.out.print("\nPlease enter no of slots for show times:");
				String s=sc.nextLine();
				valid=ValidationControl.isInteger(s);
				if(valid)
					num=ValidationControl.validateAndReturnIntegerValue(s);
				
			}
			while(!valid);
			
			int counter=0;
			while(counter<num)
			{
				do
				{
					System.out.print("Please enter show time(eg.1900 for 7pm) for slot number "+(counter+1)+".");
					time=ValidationControl.validateAndReturnTime(sc.nextLine());
					
					valid2=sControl.validateTimeSlotClash(movieId,cinemaId,d,time,showTimeValue,sch,temp);
					if(valid2 &&time!=-2)
					{
						showTimeValue=minutesPlusTime(movieLen, time);
						ShowTime sT=new ShowTime();
						sT.setCinemaId(cinemaId);
						sT.setMovieId(movieId);
						sT.setShowTimeValue(showTimeValue);
						sT.setDayType(d);
						sT.setNoOfSeats(noSeats);
						//SimpleDateFormat a=new SimpleDateFormat("dd/MM/yyyy");
						sT.setStartDate(temp.getTime());
						temp.add(Calendar.DATE, 1);
						sT.setEndDate(temp.getTime());
						sT.setCineplexId(sch.getCineplexId());
						sControl.createTimeSlot(sT,sch);
						showTimeList.add(sT);
						counter++;
					}
					else
						System.out.println("fail!!!");
				}while(!valid2 && time<=-2);
						
			}
				sch.setShowTimeList(showTimeList);
				
			}
		sControl.createSchedule(sch);
		AdminSchedulerUi ui=new AdminSchedulerUi();
		ui.displayMain();
		
	}
}
