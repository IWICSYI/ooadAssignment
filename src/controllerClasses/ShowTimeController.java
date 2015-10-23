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
	
	public MovieSchedule TimeSlotHandler(MovieSchedule sch, int movieId, int movieLen, ArrayList<ObjectContainer> pair) throws IOException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,time;
		String cinemaName;
		
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		
		ArrayList<Cinema> cinemaList= new ArrayList<Cinema>();
		cinemaList=readCinemaByCineplexId(sch.getCineplexId());
		ArrayList<String> showTimeArray=new ArrayList<String>();
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();
		ArrayList<String> allocatedShowTime=new ArrayList<String>();
		
		System.out.println("Please enter no of slots for show times");
		num=vl.validateAndReturnIntegerValue(sc.nextLine());
		
		
		for(int i=0;i<num;i++)
		{
			System.out.println("Please choose Cinema Hall");
			for(int j=0;j<cinemaList.size();j++)
			{
				System.out.println((j+1)+":"+cinemaList.get(j).getCinemaHallName());
				pair.add(oC.idPairerWithName(j, cinemaList.get(j).getCinemaId(), cinemaList.get(j).getCinemaHallName()));
			}
			choice=vl.validateAndReturnIntegerValue(sc.nextLine());
			cinemaId=pair.get(choice-1).getId();
			cinemaName=pair.get(choice-1).getName();
			
			ShowTime sT=new ShowTime();
			sT.setCinemaId(cinemaId);
			sT.setMovieId(movieId);
			pair.clear();
			
			//existingShowTime=readShowTimesBasedOnCinemaId(cinemaId);
			
			System.out.println("This "+cinemaName+" already have these time slot allocated");
			allocatedShowTime=sT.getShowTimeArray();
			
			System.out.println("Please enter show time for slot number "+(i+1)+".");
			time=vl.validateAndReturnIntegerValue(sc.nextLine());
			System.out.println();
			
			showTimeArray.add(minutesPlusTime(movieLen, time));
			sT.setShowTimeArray(showTimeArray);
			showTimeList.add(sT);
		}
		sch.setShowTimeList(showTimeList);
		return sch;
		
	}


	
	
}
