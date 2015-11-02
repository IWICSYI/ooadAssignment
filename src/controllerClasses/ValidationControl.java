package controllerClasses;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import misc.ObjectContainer;
import data.*;
import dataController.MovieDataControl;

public class ValidationControl extends MovieListingControl{

	public ValidationControl(){
		
	}
	
	
	public boolean validateShowTime(int movieId) throws IOException
	{
		ArrayList<Movie> movie=new ArrayList<Movie>();
		ArrayList<ShowTime> showTime=new ArrayList<ShowTime>();
		movie=MovieDataControl.readMovie();
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
	
	

	
	
	public static boolean isInteger(String s) {
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
	
	public static boolean isDouble(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	
	
	
	public static int validateAndReturnIntegerValue(String s)
	{
		boolean intValid=isInteger(s);
		if(intValid && Integer.parseInt(s)>=0){
			return Integer.parseInt(s);
		}
		else{
			System.out.println("Invalid integer input!");
			return -2;
		}
	}
	
	public static Double validateAndReturnDoubleValue(String s)
	{
		boolean DoubleValid=isDouble(s);
		if(DoubleValid && Double.parseDouble(s)>=0.0){
			return Double.parseDouble(s);
		}
		else{
			System.out.println("Invalid integer input!");
			return -2.0;
		}
	}

	public static int validateYesNoAndReturnIntegerValue(String s)
	{
		boolean intValid=isInteger(s);
		if(intValid && Integer.parseInt(s)>=0 && Integer.parseInt(s)>=1 &&Integer.parseInt(s)<=2){
			return Integer.parseInt(s);
		}
		else
			return -2;
		
	}
	



	public static Date validateDate(String dateString) {
		String[] formats= {"d/M/yyyy", "d-M-yyyy", 
                "dd/MM/yyyy", "dd-mm-yyyy", 
                "d/MM/yyyy", "d-MM-yyyy", 
                "dd/m/yyyy", "dd-m-yyyy"};
		Date date = null;
		boolean valid=false;
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(dateString.length()<8||dateString.length()>10)
		{
			System.out.println("Input date is not acceptable, please try again!");
			return null;
		}
		
		if(!dateString.contains("/"))
		{
			System.out.println("Input date must be sperated by /");
			return null;
		}
		if(dateString.contains("/"))
		{
			String[] dateCheck=	dateString.split("/");
			if(dateCheck[0].length()<0||dateCheck[0].length()>2)
			{
				System.out.println("Input day is not valid, please try again");
				return null;
				
			}
			if(dateCheck[1].length()<0||dateCheck[1].length()>2)
			{
				System.out.println("Input month is not valid, please try again");
				
				return null;
				
			}
			if(dateCheck[2].length()!=4)
			{	
				System.out.println("Input year is not valid, it must be 4 letters in lenght, please try again");
			
				return null;
				
			}
			
		}
		for(int i=0;i<formats.length;i++)
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(formats[i]);
			try {
				date=dateFormatter.parse(dateString);
				valid=true;
				break;
			}
			catch (ParseException e) {

				valid= false;
			}
	      
		}
	
	if(valid)
		return date;
	else
		return null;

		 
		
		
		
		
		
		// TODO Auto-generated method stub
	
	}


	public static int validateAndReturnTime(String s) {
		boolean intValid=isInteger(s);
		boolean valid=true;
		int first = 0,second=0,third=0,four=0,i=1;
		if(!intValid){
			System.out.println("Input time is not acceptable, please try again!");
			return -2;
		}
		
		if(intValid && Integer.parseInt(s)>=0 && s.length()==4){
			 first=Character.getNumericValue(s.charAt(0));
			 second=Character.getNumericValue(s.charAt(1));
			 third=Character.getNumericValue(s.charAt(2));
			 four=Character.getNumericValue(s.charAt(3));	
		}
		else{
			System.out.println("Input time is not acceptable, please try again!");
			return -2;
		}
		if(first<0||first>2){
			valid=false;
			//System.out.println("3");
		}
		else if(first>1&&second>4)
		{
			valid=false;
			//System.out.println("4");
		}
		else if(third>5)
		{
			valid=false; 
			//System.out.println("5");
		}
		else
			valid=true;
		
		if(valid)
			return Integer.parseInt(s);
		else
		{
			System.out.println("Input time is not acceptable, please try again!");
			return -2;
		}
	}

}
