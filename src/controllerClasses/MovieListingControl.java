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

public class MovieListingControl extends DataControl {
	

	
	
	
	
	public ArrayList<Movie> retrieveUniqueMovieListFromSchedule(ArrayList<MovieSchedule>schList) throws IOException, ParseException
	{
		ArrayList<Integer> movieIdList=new ArrayList<Integer>();
		ArrayList<Integer> uniqueMovieIdList=new ArrayList<Integer>();
		
		for(int i=0;i<schList.size();i++)
		{
			movieIdList.add(schList.get(i).getMovieId());
		}
		uniqueMovieIdList=MiscControl.getUniqueInteger(movieIdList);
		//System.out.println(uniqueMovieIdList.get(1));
		ArrayList<Movie> movieList=new ArrayList<Movie>();
		for(int i=0;i<uniqueMovieIdList.size();i++){
			movieList.add(readMovieBasedOnId(uniqueMovieIdList.get(i)));
		}
		
		return movieList;
		
		
		
		
	}
	
	
	
	
	public ArrayList<MovieSchedule> readScheduleListingBasedOnStartingDate() throws IOException, ParseException{
		
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
				if(today.after(startDate)&&today.before(endDate))
				{
					
					alr.add(u) ;
				}
				
			}
			return alr ;
	}

	
	public ArrayList<MovieSchedule> readScheduleListingBasedOnPreview() throws IOException, ParseException{
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
				if(previewStatus==1&& today.equals(startDate)||today.after(startDate)&&today.before(endDate))
					alr.add(u) ;
				
			}
			return alr ;
	}
	
	public ArrayList<MovieSchedule> readScheduleListingBasedOnComingSoon() throws IOException, ParseException{
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				Date comingSoon=calculateComingSoon(startDate);
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
				if(today.equals(comingSoon)||today.after(comingSoon)&&today.before(startDate))
					alr.add(u) ;
				
			}
			return alr ;
	}




	public ArrayList<MovieSchedule> readScheduleListingBasedOnStartingDateAndMovieId(int moveId) throws ParseException, IOException {
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
				if(today.equals(startDate)||today.after(startDate)&&today.before(endDate)&&movieId==moveId)
					alr.add(u) ;
				
			}
			return alr ;
	}
	
public ArrayList<ShowTime> readShowTimes() throws IOException, ParseException{
		
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
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}

	public ArrayList<ShowTime> readShowTimesBasedOnCinemaId(int cinemaID,int dayT) throws IOException{
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
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate);
				// add to  list
				
				if(cinemaID==cinemaId && dayType==dayT)
				{	
					// add to  list
					alr.add(u) ;
				}

			}
			return alr ;
	}
	
	
	public ArrayList<ShowTime> readShowTimesBasedOnMovieId(int movieIDCheck,int dayT) throws IOException, ParseException{
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
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate);
				// add to  list
				
				if(movieId==movieIDCheck && dayType==dayT)
				{	
					// add to  list
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	
	public ArrayList<ShowTime> readShowTimesBasedOnListingId(int listId,int dayT) throws IOException{
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
				
				
				
				// add to  list
				
				if(listingId==listId && dayType==dayT)
				{	
					// add to  list
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate);
					alr.add(u) ;
				}
				
				
			}
			return alr ;
	}
	
	
	public ArrayList<MovieSchedule> readScheduleListingBasedonMovieandCineplexId(int movie,int cineplex) throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				if(today.after(startDate)&&today.before(endDate)&&movieId==movie&&cinplexId==cineplex)
				{
					MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
}
