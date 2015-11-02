package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import misc.ObjectContainer;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CineplexDataControl;
import dataController.DataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;

public class MovieListingControl extends DataControl {
	
	public static int filterNowShowingListingByCineplexId(int movieId) throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		ArrayList<MovieSchedule> schWork=MovieScheduleDataControl.readScheduleListingBasedOnStartingDateAndMovieId(movieId);
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=CineplexDataControl.readCineplex();
		int cineId=0;
	
		Set<Integer> cineList=new HashSet<Integer>();
		for(int i=0;i<schWork.size();i++){
			cineList.add(schWork.get(i).getCineplexId());
		}
		
		
		ArrayList<Integer> uniqueCnList=new ArrayList<Integer> ();
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		
		Iterator itr = cineList.iterator();  
		
		while(itr.hasNext())
		{
			uniqueCnList.add(Integer.parseInt(itr.next().toString()));
		}
		int choice3=0;
		do{
			System.out.println("Please choose cineplex:");
		for(int i=0;i<cnList.size();i++)
			{
				for(int j=0;j<uniqueCnList.size();j++)
				{
					if(uniqueCnList.get(j)==cnList.get(i).getCinplexId()){
						System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
						ObjectContainer o=new ObjectContainer();
						o.setI((i+1));
						o.setId(cnList.get(i).getCinplexId());
						o.setName(cnList.get(i).getCineplexName());
						oList.add(o);
					}
				}
			}
			
			choice3=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice3==-2||choice3<uniqueCnList.size()||choice3<1);
		
		for(int i=0;i<oList.size();i++){
			if(choice3==oList.get(i).getI()){
				cineId=oList.get(i).getId();
			}
		}
		return cineId;
	}
	
	
	
	
	
	
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
