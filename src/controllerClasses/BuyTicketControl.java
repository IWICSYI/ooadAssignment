package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;

import data.MovieSchedule;
import data.ShowTime;
import dataController.MovieScheduleDataControl;

public class BuyTicketControl extends MovieListingControl {
	
	public static int chooseCineplexToDisplay(int movieId) throws ParseException, IOException//ArrayList<Integer> cineId)
	{
		ArrayList<MovieSchedule> startList=MovieScheduleDataControl.readScheduleListingBasedOnStartingDateAndMovieId(movieId);
		ArrayList<Integer> cineList=new ArrayList<Integer>();
		
		//Start collecting timeslot size based on cineId
		for(int i=0;i<startList.size();i++){
			cineList.add(startList.get(i).getCineplexId());
		}
		
		int a=0,b=0,c=0;
		for(int i=0;i<cineList.size();i++)
		{
			if(cineList.get(i)==1)
			{
				a++;
			}
			else if(cineList.get(i)==2)
			{
				b++;
			}
			else if(cineList.get(i)==3){
				c++;
			}
		}
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		tmp.add(a);
		tmp.add(b);
		tmp.add(c);
		int largest = Collections.max(tmp);
		if(largest==a)
		{
			return 1;
		}
		else if(largest==b)
		{
			return 2;
		}
		else if(largest==c)
		{
			return 3;
		}
		return 0;
		
	}
	
	



}
