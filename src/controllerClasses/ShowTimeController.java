package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Cinema;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class ShowTimeController extends DataControl {
	
	public ArrayList<MovieSchedule> TimeSlotHandler(MovieSchedule sch, int movieId, int movieLen, int movieType, ArrayList<ObjectContainer> pair) throws IOException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,time,plat,dayType;
		String cinemaName;
		String type;
		boolean valid;
		
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		ArrayList<MovieSchedule> schList=new ArrayList<MovieSchedule>();
		ArrayList<Cinema> cinemaList= new ArrayList<Cinema>();
		cinemaList=readCinemaByCineplexIdAndMovieType(sch.getCineplexId(),movieType);
		ArrayList<String> showTimeArray=new ArrayList<String>();
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<ObjectContainer> allocatedShowTime=new ArrayList<ObjectContainer>();
		
		System.out.println("Please choose Cinema Hall");
		for(int j=0;j<cinemaList.size();j++)
		{
			System.out.print((j+1)+":"+cinemaList.get(j).getCinemaHallName()+"	");
			pair.add(oC.idPairerWithCinema(j, cinemaList.get(j).getCinemaId(), cinemaList.get(j).getCinemaHallName(),cinemaList.get(j).getCinemaType()));
			
		}
		choice=vl.validateAndReturnIntegerValue(sc.nextLine());
		cinemaId=pair.get(choice-1).getId();
		cinemaName=pair.get(choice-1).getName();
		plat=pair.get(choice-1).getCineType();
		sch.setPlatOrNot(plat);
		
		for(int d=1;d<=3;d++)
		{	

			ShowTime sT=new ShowTime();
			sT.setCinemaId(cinemaId);
			sT.setMovieId(movieId);
			pair.clear();
			if(d==1)
				type="Weekday";
			else if(d==2)
				type="Weekend";
			else
				type="Holiday";
			
			
			System.out.print("Please enter show time for "+type+":");
			System.out.println("This "+cinemaName+" already have these time slot allocated on "+type);
			
			allocatedShowTime=cinemaallocatedTime(movieId,d);
			for(int l=0;l<allocatedShowTime.size();l++)
				allocatedShowTime.get(l).printStringArray();
			
			allocatedShowTime=movieallocatedTime(cinemaId,d);
			for(int l=0;l<allocatedShowTime.size();l++)
				allocatedShowTime.get(l).printStringArray();
			
			int counter=0;
			System.out.print("\nPlease enter no of slots for show times:");
			num=vl.validateAndReturnIntegerValue(sc.nextLine());
			while(counter<num)
			{
				System.out.print("Please enter show time for slot number "+(counter+1)+".");
				time=vl.validateAndReturnIntegerValue(sc.nextLine());
				System.out.println();
				
				valid=validateTimeSlotClash(movieId,cinemaId,d,time,showTimeArray);
				showTimeArray.add(minutesPlusTime(movieLen, time));
				sT.setShowTimeArray(showTimeArray);
				showTimeList.add(sT);
				counter++;
			}
			sch.setShowTimeList(showTimeList);
			schList.add(sch);
		}
		
		return  schList;
		
	}
	
	
	public boolean validateTimeSlotClash(int movieId, int cinemaId, int time, int dayT, ArrayList<String> showTimeArray) throws IOException {
		boolean valid=true;
		ArrayList<ObjectContainer> allocatedShowTimeM = cinemaallocatedTime(movieId,dayT);
		
		for(int i=0;i<allocatedShowTimeM.size();i++)
		{
			ArrayList<String> tmp = allocatedShowTimeM.get(i).getStringArray();
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
			ObjectContainer tmp=new ObjectContainer(1, stM.get(i).getShowTimeArray());
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
		
		
		
		for(int i=0;i<stC.size();i++)
		{
			ObjectContainer tmp=new ObjectContainer(2, stC.get(i).getShowTimeArray());
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


	
	
}
