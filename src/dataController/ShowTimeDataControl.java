package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import controllerClasses.TimeDateControl;
import data.MovieSchedule;
import data.ShowTime;

public class ShowTimeDataControl extends DataControl {
	
public static ArrayList<ShowTime> readShowTimes() throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				int cineId = Integer.parseInt(star.nextToken().trim());
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}

	public static ArrayList<ShowTime> readShowTimesBasedOnCinemaIdAndNowShowing(int cinemaID,int dayT,MovieSchedule sch) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		Calendar cal = Calendar.getInstance();
		cal.setTime(sch.getStartDate());
	    Date sD=cal.getTime();
	    cal.setTime(sch.getEndDate());
	    Date eD=cal.getTime();
	    
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				if(preview==1){
					startDate=TimeDateControl.calculatePreviewStartDate(startDate);
				}
				int cineId = Integer.parseInt(star.nextToken().trim());
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
				// add to  list
				
				if(sD.after(startDate)&&eD.before(endDate)&&cinemaId==cinemaID&&dayType==dayT)
				{	
					// add to  list
					alr.add(u) ;
				}

			}
			return alr ;
	}
	
	
	public static ArrayList<ShowTime> readShowTimesBasedOnMovieId(int movieIDCheck,int dayT) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				int cineId = Integer.parseInt(star.nextToken().trim());
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
				// add to  list
				
				if(movieId==movieIDCheck && dayType==dayT)
				{	
					// add to  list
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	
	public static ArrayList<ShowTime> readShowTimesBasedOnListingIdAndNowShowing(int listId,Calendar calTemp) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
			
				if(listingId==listId && calTemp.getTime().after(startDate)&&calTemp.getTime().before(endDate))
				{	
					// add to  list
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
					alr.add(u) ;
					break;
				}
				
				
			}
			return alr ;
	}
	
	
	
	public static ShowTime readShowTimesBasedOnShowTimeId(int showTime) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
				
				if(showTimeId==showTime)
				{	
					// add to  list
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
					return u;
				}
			}
			return null ;
	}

	
	
	public ArrayList<ShowTime> readShowTimesBasedOnListingId(int movieIDCheck,int cinePlexId,int dayT) throws IOException, ParseException{
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
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				int cineId = Integer.parseInt(star.nextToken().trim());
				
				
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
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
