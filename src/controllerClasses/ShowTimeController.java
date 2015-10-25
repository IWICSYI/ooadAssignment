package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Cinema;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class ShowTimeController extends SchedulerController {
	
	public void TimeSlotHandler(MovieSchedule sch, int movieId, int movieLen, int movieType,int cineplexID, ArrayList<ObjectContainer> pair) throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,time,plat,dayType,noSeats;
		String cinemaName;
		String type;
		String showTimeValue = null;
		boolean valid=false,valid2=false;
		
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		
		ArrayList<Cinema> cinemaList= new ArrayList<Cinema>();
		cinemaList=readCinemaByCineplexIdAndMovieType(sch.getCineplexId(),movieType);
		
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<ObjectContainer> allocatedShowTime=new ArrayList<ObjectContainer>();
		
		do{
			System.out.println("Please choose Cinema Hall");
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
		
		cinemaName=pair.get(choice-1).getName();
		
		plat=pair.get(choice-1).getCineType();
		sch.setPlatOrNot(plat);
		
		noSeats=pair.get(choice-1).getSeatNo();
		
		pair.clear();
		
		for(int d=1;d<=3;d++)
		{	
			if(d==1)
				type="Weekday";
			else if(d==2)
				type="Weekend";
			else
				type="Holiday";
			
			sch.setTypeofDay(d);
			do
			{
				System.out.println("Please enter show time for "+type);
				System.out.println("This "+cinemaName+" already have these time slot allocated on "+type);
				allocatedShowTime=cinemaallocatedTime(movieId,d);
				for(int l=0;l<allocatedShowTime.size();l++)
				{
					ArrayList<String> allocatedTimeValue = allocatedShowTime.get(l).getStringArray();
					for(int k=0;k<allocatedTimeValue.size();k++)
					{
						//System.out.print(allocatedTimeValue+"lol");
					}
				}
				
				//allocatedShowTime=movieallocatedTime(cinemaId,d);
				//for(int l=0;l<allocatedShowTime.size();l++)
				//	allocatedShowTime.get(l).printStringArray();
				
				
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
					System.out.println(time);
					
					valid2=validateTimeSlotClash(movieId,cinemaId,d,time,showTimeValue);
					if(valid2)
					{
						showTimeValue=minutesPlusTime(movieLen, time);
						ShowTime sT=new ShowTime();
						//sT.setListingId(c);
						sT.setCinemaId(cinemaId);
						sT.setMovieId(movieId);
						sT.setShowTimeValue(showTimeValue);
						sT.setDayType(d);
						sT.setNoOfSeats(noSeats);
						createTimeSlot(sT);
						showTimeList.add(sT);
						counter++;
					}
					else
						System.out.println("fail!!!");
				}while(!valid2 && time==-2);
						
			}
				sch.setShowTimeList(showTimeList);
				createSchedule(sch);
			}
		
		
		
	}
	
	
	public boolean validateTimeSlotClash(int movieId, int cinemaId, int time, int dayT, String showTimeArray) throws IOException {
		boolean valid=true;
		
		ArrayList<ObjectContainer> allocatedShowTimeC = cinemaallocatedTime(cinemaId,dayT);
		
		for(int i=0;i<allocatedShowTimeC.size();i++)
		{
			ArrayList<String> tmp = allocatedShowTimeC.get(i).getStringArray();
			for(int j=0;j<tmp.size();j++)
			{
				String[] timeArr = tmp.get(j).split("-");
				int startTime=Integer.parseInt(timeArr[0]);
				int endTime=Integer.parseInt(timeArr[1]);
				if(time>=startTime && time<=endTime)
				{
					valid=false;
					break;
				}
					
			}
		}
		return valid;
		
		
		
	
	}
	
	
	
	
	public ArrayList<ObjectContainer> movieallocatedTime(int movieId, int dayType) throws IOException{
		ArrayList<ShowTime> stM=readShowTimesBasedOnMovieId(movieId, dayType);
		ArrayList<ObjectContainer> allocatedTime=new  ArrayList<ObjectContainer>();
		MiscControl ms=new MiscControl();
		ArrayList<String> unsorted=new  ArrayList<String>();
		
		
		for(int i=0;i<stM.size();i++)
		{
			ObjectContainer tmp=new ObjectContainer(1, stM.get(i).getShowTimeValue());
			tmp.getStringArray().add(stM.get(i).getShowTimeValue());
			allocatedTime.add(tmp);
		}
		
		
		
		for(int i=0;i<allocatedTime.size();i++)
		{
			unsorted=allocatedTime.get(i).getStringArray();
			ms.setStringList(unsorted);
			ms.sort();
			allocatedTime.get(i).setStringArray(ms.getStringList());
			
		}
		
		
		return allocatedTime;
		
	}

	public ArrayList<ObjectContainer> cinemaallocatedTime(int cinemaId, int dayType) throws IOException{
		ArrayList<ShowTime> stC=readShowTimesBasedOnCinemaId(cinemaId,dayType);
		ArrayList<ObjectContainer> allocatedTime=new  ArrayList<ObjectContainer>();
		MiscControl ms=new MiscControl();
		ArrayList<String> unsorted=new  ArrayList<String>();
		
		
		if(!stC.isEmpty()){
			for(int i=0;i<stC.size();i++)
			{
				System.out.println(stC.size());
				ObjectContainer tmp=new ObjectContainer(2, stC.get(i).getShowTimeValue());
				tmp.getStringArray().add(stC.get(i).getShowTimeValue());
				allocatedTime.add(tmp);
			}
			
			for(int i=0;i<allocatedTime.size();i++)
			{
				unsorted=allocatedTime.get(i).getStringArray();
				ms.setStringList(unsorted);
				ms.sort();
				allocatedTime.get(i).setStringArray(ms.getStringList());
				
			}
			
		}
		return allocatedTime;
		
	}


	
	
}
