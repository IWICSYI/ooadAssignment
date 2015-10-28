package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Cinema;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.ShowTimeDataControl;

public class ShowTimeController extends SchedulerController {
	
	
	
	
	public boolean validateTimeSlotClash(int movieId, int cinemaId, int dayT, int time, String showTimeArray,Date end) throws IOException, ParseException {
		boolean valid=true;
		
		
		ArrayList<String> allocatedShowTimeC = cinemaallocatedTime(cinemaId,dayT,end);
	
		
		System.out.println(cinemaId);
		//System.out.println("endTime:"+endTime);
		
		for(int i=0;i<allocatedShowTimeC.size();i++)
		{
				String[] timeArr = allocatedShowTimeC.get(i).split("-");
				int startTime=Integer.parseInt(timeArr[0]);
				int endTime=Integer.parseInt(timeArr[1]);
				
				if(time>=startTime && time<=endTime)
				{
					System.out.println("Time clashed! Please try again!");
					valid=false;
					break;
				}
					
			
		}
		return valid;
		
		
		
	
	}

	public ArrayList<String> cinemaallocatedTime(int cinemaId, int dayType,Date end) throws IOException, ParseException{
		ArrayList<ShowTime> stC=ShowTimeDataControl.readShowTimesBasedOnCinemaId(cinemaId,dayType,end);
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


	
	
}
