package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import misc.ObjectContainer;
import data.*;

public class ValidationControl extends DataControl{

	public ValidationControl(){
		
	}
	
	
	public boolean validateShowTime(int movieId) throws IOException
	{
		ArrayList<Movie> movie=new ArrayList<Movie>();
		ArrayList<ShowTime> showTime=new ArrayList<ShowTime>();
		movie=readMovie();
		int movieLength;
		for(int i=0;i<movie.size();i++)
		{
			if(movie.get(i).getMovieId()==movieId)
			{
				movieLength=movie.get(i).getMovieLength();
				break;
			}
			else{
				System.out.println("Movie not found");
			}
		}
		
		
		return true;
	}
	
	
	public boolean validateCinemaShowTime(ArrayList<ShowTime> sTArray) throws IOException
	{
		ArrayList<ShowTime> existST=readShowTimes();
		ArrayList<ObjectContainer> pairList=new ArrayList<ObjectContainer>();
		ArrayList<ObjectContainer> pairList2=new ArrayList<ObjectContainer>();
		StringBuilder st =  new StringBuilder() ;
		ArrayList<ShowTime> newST=readShowTimes();
		int checkCinemaId,checkMovieId;
		int checkCinemaId2,checkMovieId2;
		Set<Integer> set = new HashSet<Integer>();
		
		
		for(int i=0;i<existST.size();i++)
		{
			ObjectContainer p=new ObjectContainer(existST.get(i).getCinemaId(),existST.get(i).getMovieId());
			pairList.add(p);
		}
		
		for(int i=0;i<sTArray.size();i++)
		{
			ObjectContainer p2=new ObjectContainer(sTArray.get(i).getCinemaId(),sTArray.get(i).getMovieId());
			pairList2.add(p2);
		}
		
		
		for(int i=0;i<existST.size();i++)
		{
			checkCinemaId=pairList.get(i).getI();
			checkMovieId=pairList.get(i).getId();
			
			for(int j=0;j<sTArray.size();j++)
			{	
				checkCinemaId2=pairList2.get(j).getI();
				checkMovieId2=pairList2.get(j).getId();
				if(checkCinemaId==checkCinemaId2 && checkMovieId==checkMovieId2)
				{
					newST.add(sTArray.get(j));
				}
			}
		}
		return false;
		
	}
	
	
	public boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	
	
	
	public int validateAndReturnIntegerValue(String s)
	{
		boolean intValid=isInteger(s);
		if(intValid && Integer.parseInt(s)!=0){
			return Integer.parseInt(s);
		}
		else
			return 200;
		
	}




	public void validateTimeSlotClash(int movieId, int cinemaId, int time, ArrayList<String> showTimeArray) {
		// TODO Auto-generated method stub
		
	}
}
