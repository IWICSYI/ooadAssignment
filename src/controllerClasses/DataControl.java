package controllerClasses;

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import data.*;

public class DataControl extends TimeDateControl {
	
	protected static final String SEPARATOR = "|";
	
	 private static ArrayList<String> read(String fileName) throws IOException {
			ArrayList<String> data = new ArrayList<String>() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		        
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }
	 
	public ArrayList<User> readLogin() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/loginDetails.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

				String  username = star.nextToken().trim();	// first token
				String  password = star.nextToken().trim();	// second token
				// create Admin User object from file data
				User u = new User(username,password);
				// add to  list
				alr.add(u) ;
			}
			return alr ;
	}
	
	public ArrayList<Movie> readMovie() throws IOException{
	//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		ArrayList stringArray = (ArrayList)read("data/movies.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int movieId=Integer.parseInt(star.nextToken().trim());
				String  movieName = star.nextToken().trim();	
				int  movieType = Integer.parseInt(star.nextToken().trim());
				String  ageRating = star.nextToken().trim();
				String  director = star.nextToken().trim();
				String  synopsis = star.nextToken().trim();
				String  cast = star.nextToken().trim();
				double  overallRating=Double.parseDouble(star.nextToken().trim());
				long ticketSales=Long.parseLong(star.nextToken().trim());
				int time=Integer.parseInt(star.nextToken().trim());

				// create Admin User object from file data
				Movie m = new Movie(movieId,movieName,movieType,ageRating,director,synopsis,cast,overallRating,ticketSales,time);
				// add to  list
				alr.add(m) ;
				//System.out.println(stringArray.size()+st);
			}
        
        
			return alr ;
	}
		


	public static void write(String fileName, List data) throws IOException  {
	PrintWriter out = new PrintWriter(new FileWriter(fileName,true));
		
		try {
			for (int i =0; i < data.size() ; i++) {
		  		out.println((String)data.get(i));
			}
	}
		finally {
		  out.close();
		}
	}
	
	public ArrayList<Cineplex> readCineplex() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/cineplexes.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

				int cineplexId=Integer.parseInt(star.nextToken().trim());
				String  cineplexName = star.nextToken().trim();	
				
				Cineplex u = new Cineplex(cineplexId,cineplexName);
				// add to  list
				alr.add(u) ;
			}
			return alr ;
	}
	
	public ArrayList<Cinema> readCinemaByCineplexId(int cId) throws IOException{
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=readCineplex();
		
		ArrayList stringArray = (ArrayList)read("data/cinemas.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cineplexId=Integer.parseInt(star.nextToken().trim());
				if(cineplexId==cId)
				{
					int  cinemaId = Integer.parseInt(star.nextToken().trim());	
					String cinemaName=star.nextToken().trim();
					int cinemaType=Integer.parseInt(star.nextToken().trim());	
					int seats=Integer.parseInt(star.nextToken().trim());	
					Cinema u = new Cinema(cineplexId,cinemaId,cinemaName,cinemaType,seats);
					// add to  list
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	public ArrayList<Cinema> readCinema() throws IOException{
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=readCineplex();
		
		ArrayList stringArray = (ArrayList)read("data/cinemas.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cineplexId=Integer.parseInt(star.nextToken().trim());
				
				int  cinemaId = Integer.parseInt(star.nextToken().trim());	
				String cinemaName=star.nextToken().trim();
				int cinemaType=Integer.parseInt(star.nextToken().trim());	
				int seats=Integer.parseInt(star.nextToken().trim());	
				Cinema u = new Cinema(cineplexId,cinemaId,cinemaName,cinemaType,seats);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}

	
	public ArrayList<ShowTime> readShowTimes() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<String> showTimeArray=new ArrayList<String>();
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				System.out.println((String)stringArray.get(i));
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());	
				int  noOfSlots = Integer.parseInt(star.nextToken().trim());
				
				for(int j=0;j<noOfSlots;j++)
				{
					showTimeArray.add(star.nextToken().trim());
				}
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,noOfSlots,showTimeArray);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}

	public ArrayList<ShowTime> readShowTimesBasedOnCinemaId(int cinemaID) throws IOException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<String> showTimeArray=new ArrayList<String>();
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				if(cinemaID==cinemaId)
				{
					int  showTimeId = Integer.parseInt(star.nextToken().trim());	
					int  noOfSlots = Integer.parseInt(star.nextToken().trim());
					
					for(int j=0;j<noOfSlots;j++)
					{
						showTimeArray.add(star.nextToken().trim());
					}
					
				
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,noOfSlots,showTimeArray);
					// add to  list
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	
	
	

	
	public ArrayList<MovieSchedule> readScheduleListing() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDate=star.nextToken().trim();
				String endDate=star.nextToken().trim();
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,movieId,listingId,startDate,endDate,typeOfDay,status);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}

}
