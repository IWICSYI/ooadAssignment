package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import controllerClasses.TimeDateControl;
import data.HolidayDate;
import data.MovieSchedule;
import data.Prices;
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

	public static ArrayList<ShowTime> readShowTimesBasedOnCinemaIdAndNowShowing(int cinemaID,Calendar tmp) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		Calendar temp=(Calendar) tmp.clone();
		
		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
	    Date sD=temp.getTime();
	    temp.add(Calendar.DATE, 1);
	  
	    Date eD=temp.getTime();
	    
		
		
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
				
		
				if(startDate.equals(sD)&&endDate.equals(eD)&&cinemaId==cinemaID)
				{	
					// add to  list
					alr.add(u) ;
				}

			}
			return alr ;
	}
	
	
	public static ArrayList<ShowTime> readShowTimesBasedOnMovieId(int movieIDCheck) throws IOException, ParseException{
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
				
				if(movieId==movieIDCheck)
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
		
		Calendar today=(Calendar) calTemp.clone();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		Calendar end=(Calendar) today.clone();
		end.add(Calendar.DATE, 1);
		
		
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
				String arr[]=showTimeValue.split("-");
				
				//startDate.setHours(10);
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
				if(listingId==listId && today.getTime().equals(startDate)&&end.getTime().equals(endDate))
				{	
					// add to  list
					
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
					alr.add(u) ;
					
				}
				
				
			}
			return alr ;
	}
	
	
	
	public static ArrayList<ShowTime> readShowTimesBasedOnListingIdAndCineplexIdAndNowShowing(int listId,Calendar calTemp,int cId) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		Calendar today=(Calendar) calTemp.clone();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		Calendar end=(Calendar) today.clone();
		end.add(Calendar.DATE, 1);
		
		
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
				String arr[]=showTimeValue.split("-");
				
				//startDate.setHours(10);
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
				if(listingId==listId && today.getTime().equals(startDate)&&end.getTime().equals(endDate)&&cId==cineId)
				{	
					// add to  list
					
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
					alr.add(u) ;
					
				}
				
				
			}
			return alr ;
	}
	
	
	public static ArrayList<ShowTime> readShowTimesBasedOnListingId(int listId) throws IOException, ParseException{
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
				String arr[]=showTimeValue.split("-");
				
				//startDate.setHours(10);
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
			
				if(listingId==listId)
				{	
					// add to  list
					
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
					alr.add(u) ;
					
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
	
	
	public static void createTimeSlot(ShowTime sT, MovieSchedule sch2) throws IOException, ParseException
	{
		
		ArrayList<ShowTime> existST = ShowTimeDataControl.readShowTimes();
		int stID;
		if(!existST.isEmpty())
		{
			stID=existST.get(existST.size()-1).getShowTimeId()+1;
		}
		else{
			stID=1;
		}
		int plat=sch2.getPlatOrNot();
		int tD=sch2.getThreeDOrNot();
		int bB=sch2.getBlockBuster();
		int dtype=sT.getDayType();
		
		String startDate=finalDateFormatter.format(sT.getStartDate());
		String endDate=finalDateFormatter.format(sT.getEndDate());

		ArrayList<HolidayDate> hDList=new ArrayList<HolidayDate>();
		for(int i=0;i<hDList.size();i++)
		{
			String date1=finalDateFormatter.format(hDList.get(i).getHolidayDate());
			if(date1.equals(startDate)){
				dtype=8;
				break;
			}
			
		}
		
		
		
		ArrayList<Prices>prices=TicketPriceAndHolidayDataControl.readPrice();
		double finalPrice=prices.get(0).getNormal();;
		if(plat==1){
			finalPrice=finalPrice+prices.get(0).getPlat();
		}
		if(tD==1){
			finalPrice=finalPrice+prices.get(0).gettD();
		}
		
		if(bB==1){
			finalPrice=finalPrice+prices.get(0).getBlockbuster();
		}
		if(dtype==1||dtype==7){
			finalPrice=finalPrice+prices.get(0).getWeekend();
		}
		else if(dtype==8){
			finalPrice=finalPrice+prices.get(0).getHoli();
		}
		
		
			
		
		StringBuilder st2 =  new StringBuilder() ;
		List alTS = new ArrayList() ;
			st2.append(sch2.getListingId());
			st2.append(SEPARATOR);
			st2.append(sT.getMovieId());
			st2.append(SEPARATOR);
			st2.append(sT.getCinemaId());
			st2.append(SEPARATOR);
			st2.append(stID);
			st2.append(SEPARATOR);
			st2.append(sT.getDayType());
			st2.append(SEPARATOR);
			st2.append(sT.getShowTimeValue());
			st2.append(SEPARATOR);
			st2.append(sT.getNoOfSeats());
			st2.append(SEPARATOR);
			st2.append(startDate);
			st2.append(SEPARATOR);
			st2.append(endDate);
			st2.append(SEPARATOR);
			st2.append(finalPrice);
			st2.append(SEPARATOR);
			st2.append(sch2.getPreviewStatus());
			st2.append(SEPARATOR);
			st2.append(sT.getCineplexId());
		
		
			alTS.add(st2.toString()) ;
			write("data/showTimes.txt",alTS);
			
		}

		public static void removeShowTime(int showTimeId) throws IOException, ParseException {
			List alw = new ArrayList() ;// to store Professors data
		
			ArrayList<ShowTime> eList=readShowTimes();
			//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

			for(int i=0;i<eList.size();i++)
			{		
				StringBuilder st2 =  new StringBuilder() ;
				if(showTimeId!=eList.get(i).getShowTimeId()){
					st2.append(eList.get(i).getListingId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getMovieId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCinemaId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getShowTimeId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getDayType());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getShowTimeValue());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getNoOfSeats());
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getStartDate()));
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getEndDate()));
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getTicketPrice());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getPreviewStatus());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCineplexId());
					st2.append("\n");
				
					alw.add(st2.toString()) ;
					
				}
			
		}
			writeB("data/showTimes.txt",alw);
			System.out.println("Sucessfully removed!");
	}

		
		public static void updateShowTime(int showTimeId,String showTimeValue) throws IOException, ParseException {
			List alw = new ArrayList() ;// to store Professors data
		
			ArrayList<ShowTime> eList=readShowTimes();
			//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

			for(int i=0;i<eList.size();i++)
			{		
				StringBuilder st2 =  new StringBuilder() ;
				if(showTimeId==eList.get(i).getShowTimeId()){
					st2.append(eList.get(i).getListingId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getMovieId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCinemaId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getShowTimeId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getDayType());
					st2.append(SEPARATOR);
					st2.append(showTimeValue);
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getNoOfSeats());
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getStartDate()));
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getEndDate()));
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getTicketPrice());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getPreviewStatus());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCineplexId());
					st2.append("\n");
				
					alw.add(st2.toString()) ;
					
				}
				else
				{
					st2.append(eList.get(i).getListingId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getMovieId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCinemaId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getShowTimeId());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getDayType());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getShowTimeValue());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getNoOfSeats());
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getStartDate()));
					st2.append(SEPARATOR);
					st2.append(finalDateFormatter.format(eList.get(i).getEndDate()));
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getTicketPrice());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getPreviewStatus());
					st2.append(SEPARATOR);
					st2.append(eList.get(i).getCineplexId());
					st2.append("\n");
				
					alw.add(st2.toString()) ;
				}
			
		}
			writeB("data/showTimes.txt",alw);
			System.out.println("Update Completed!");
	}

		
}

