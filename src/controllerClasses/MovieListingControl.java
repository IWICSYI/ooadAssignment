package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.StringTokenizer;

import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.DataControl;
import dataController.MovieDataControl;

public class MovieListingControl extends DataControl {
	

	
	
	
	
	public static ArrayList<Movie> filterUniqueMovieListFromSchedule(ArrayList<MovieSchedule>schList,int plat) throws IOException, ParseException
	{
		ArrayList<Integer> movieIdList=new ArrayList<Integer>();
		ArrayList<Integer> uniqueMovieIdList=new ArrayList<Integer>();
		
		for(int i=0;i<schList.size();i++)
		{
			if(schList.get(i).getPlatOrNot()==plat)
				
			{
				movieIdList.add(schList.get(i).getMovieId());
			}
		}
		uniqueMovieIdList=MiscControl.getUniqueInteger(movieIdList);
		//System.out.println(uniqueMovieIdList.get(1));
		ArrayList<Movie> movieList=new ArrayList<Movie>();
		for(int i=0;i<uniqueMovieIdList.size();i++){
			movieList.add(MovieDataControl.readMovieBasedOnId(uniqueMovieIdList.get(i)));
		}
		
		return movieList;
		
	}
	
	
	
	
	
	
	
}
