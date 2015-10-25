package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import data.ShowTime;

public class BuyTicketControl extends MovieListingControl {
	


	
	public ArrayList<ShowTime> readShowTimesBasedOnListingId(int movieIDCheck,int cinePlexId,int dayT) throws IOException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
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
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats);
				// add to  list
				
				if(movieId==movieIDCheck && dayType==dayT &&cinePlexId==listingId)
				{	
					// add to  list
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	

}
