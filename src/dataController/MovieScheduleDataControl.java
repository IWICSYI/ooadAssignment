package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import data.MovieSchedule;

public class MovieScheduleDataControl extends DataControl{

	
public static ArrayList<MovieSchedule> readScheduleListingBasedOnStartingDate() throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		
		Calendar cal = Calendar.getInstance();
	    Date today=cal.getTime();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				int threeDOrNot=Integer.parseInt(star.nextToken().trim());
				int blockDOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockDOrNot,platOrNot,previewStatus);
				if(today.after(startDate)&&today.before(endDate))
				{
					
					alr.add(u) ;
				}
				
			}
			return alr ;
	}

	
	public static ArrayList<MovieSchedule> readScheduleListingBasedOnPreview() throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		
		Calendar cal = Calendar.getInstance();
	    Date today=cal.getTime();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				Date endDate=startDate;
				startDate=calculatePreviewStartDate(startDate);
				String endDateString=star.nextToken().trim();
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				int threeDOrNot=Integer.parseInt(star.nextToken().trim());
				int blockOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				if(previewStatus==1&& today.equals(startDate)||today.after(startDate)&&today.before(endDate))
					alr.add(u) ;
				
			}
			return alr ;
	}
	
	public static ArrayList<MovieSchedule> readScheduleListingBasedOnComingSoon() throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		
		Calendar cal = Calendar.getInstance();
	    Date today=cal.getTime();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				int threeDOrNot=Integer.parseInt(star.nextToken().trim());
				int blockOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				Date comingSoon=calculateComingSoon(startDate);
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				if(today.equals(comingSoon)||today.after(comingSoon)&&today.before(startDate))
					alr.add(u) ;
				
			}
			return alr ;
	}




	public static ArrayList<MovieSchedule> readScheduleListingBasedOnStartingDateAndMovieId(int moveId) throws ParseException, IOException {
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		
		Calendar cal = Calendar.getInstance();
	    Date today=cal.getTime();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				int threeDOrNot=Integer.parseInt(star.nextToken().trim());
				int blockOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				if(today.equals(startDate)||today.after(startDate)&&today.before(endDate)&&movieId==moveId)
					alr.add(u) ;
				
			}
			return alr ;
	}
	
	
public static ArrayList<MovieSchedule> readScheduleListingBasedonMovieandCineplexId(int movie,int cineplex) throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cinplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  listingId = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				int typeOfDay=Integer.parseInt(star.nextToken().trim());
				int status=Integer.parseInt(star.nextToken().trim());
				int threeDOrNot=Integer.parseInt(star.nextToken().trim());
				int blockOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				if(cineplex==cinplexId&&movie==movieId){
					MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}

}
