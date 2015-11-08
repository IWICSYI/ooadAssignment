package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.HolidayDate;
import data.MovieSchedule;
import data.Prices;
import data.ShowTime;

public class MovieScheduleDataControl extends DataControl{
	
	public static ArrayList<MovieSchedule> readScheduleListing() throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
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
				
				MovieSchedule u = new MovieSchedule(movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				
				alr.add(u) ;
				
			}
			return alr ;
	}

	
	
	public static MovieSchedule readScheduleListingBasedOnListingId(int listId) throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
	
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
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
				if(listingId==listId){
					MovieSchedule u = new MovieSchedule(movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
					return u;
				}
				
				
			}
			return null ;
	}
	
	

	
	public static ArrayList<MovieSchedule> readScheduleListingBasedOnStatus(int stat,int plat) throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		
		Calendar cal = resetTodayTime();
		
	    Date today=cal.getTime();
	    
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
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
				
				MovieSchedule u = new MovieSchedule(movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockDOrNot,platOrNot,previewStatus);
				if(stat==status&&plat==platOrNot)
				{
					alr.add(u) ;
				}
				
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
				
				MovieSchedule u = new MovieSchedule(movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				if(today.equals(startDate)||today.after(startDate)&&today.before(endDate)&&movieId==moveId)
					alr.add(u) ;
				
			}
			return alr ;
	}
	
	
	public static ArrayList<MovieSchedule> readScheduleListingBasedonMovieId(int movie) throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/movieScheduleListing.txt");
		ArrayList alr = new ArrayList() ;// to store data
		ArrayList<Integer> showTimeArray=new ArrayList<Integer>();
		//1cineplexId|1movieUniqueId|1listingId|startDate|endDate|1typeofDay|status
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
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
				
				if(movie==movieId){
					MovieSchedule u = new MovieSchedule(movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}



	public static void createSchedule(MovieSchedule m) throws IOException, ParseException {
	
		List alMS = new ArrayList() ;// to store Professors data
	
		String startDate=finalDateFormatter.format(m.getStartDate());
		String endDate=finalDateFormatter.format(m.getEndDate());
		
		
		int movieid;
		
		
		//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
		StringBuilder st =  new StringBuilder() ;
		st.append(m.getMovieId());
		st.append(SEPARATOR);
		st.append(m.getListingId());
		st.append(SEPARATOR);
		st.append(startDate);
		st.append(SEPARATOR);
		st.append(endDate);
		st.append(SEPARATOR);
		st.append(m.getTypeofDay());
		st.append(SEPARATOR);
		st.append(m.getStatus());
		st.append(SEPARATOR);
		st.append(m.getThreeDOrNot());
		st.append(SEPARATOR);
		st.append(m.getBlockBuster());
		st.append(SEPARATOR);
		st.append(m.getPlatOrNot());
		st.append(SEPARATOR);
		st.append(m.getPreviewStatus());
		
		alMS.add(st.toString()) ;
		// public MovieSchedule(int cineplexId, int cinemaId, int movieId,
		//int listingId, Date startDate, Date endDate, int typeofDay,
		//int status, int threeDOrNot, int platOrNot, int previewStatus) {
	
		write("data/movieScheduleListing.txt",alMS);
	}
	
	
	
	public static void updateReListStatus() throws IOException, ParseException
	{
		List alMS = new ArrayList() ;// to store Professors data
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		Calendar cal=Calendar.getInstance();
		int preview=0;
		for(int i=0;i<schList.size();i++){
			
			preview=schList.get(i).getPreviewStatus();
			
			//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
			StringBuilder st =  new StringBuilder() ;
			st.append(schList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(schList.get(i).getListingId());
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getStartDate()));
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getEndDate()));
			st.append(SEPARATOR);
			st.append(schList.get(i).getTypeofDay());
			st.append(SEPARATOR);
			if(cal.getTime().after(schList.get(i).getStartDate())&&cal.getTime().before(schList.get(i).getEndDate()))
			{	
				st.append(1);
				preview=0;
			}
			else if(preview==1 ){
				st.append(2);
			}
			
			else if(cal.getTime().before(schList.get(i).getStartDate())&&cal.getTime().before(schList.get(i).getEndDate())&&preview==0)
			{	
				st.append(3);
			}
			else if(cal.getTime().after(schList.get(i).getEndDate()))
			{	
				st.append(4);
			}
			else
			{
				st.append(schList.get(i).getStatus());
			}
			st.append(SEPARATOR);
			st.append(schList.get(i).getThreeDOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getBlockBuster());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPlatOrNot());
			st.append(SEPARATOR);
			st.append(preview+"\n");
			
			alMS.add(st.toString()) ;
	}
	
	writeB("data/movieScheduleListing.txt",alMS);

	
}




	public static void updateScheduleStatus() throws IOException, ParseException
	{
		List alMS = new ArrayList() ;// to store Professors data
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		Calendar cal=Calendar.getInstance();
		int preview=0;
		for(int i=0;i<schList.size();i++){
			
			preview=schList.get(i).getPreviewStatus();
			
			//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
			StringBuilder st =  new StringBuilder() ;
			st.append(schList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(schList.get(i).getListingId());
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getStartDate()));
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getEndDate()));
			st.append(SEPARATOR);
			st.append(schList.get(i).getTypeofDay());
			st.append(SEPARATOR);
			if(cal.getTime().after(schList.get(i).getStartDate())&&cal.getTime().before(schList.get(i).getEndDate())&&schList.get(i).getStatus()!=4)
			{	
				st.append(1);
				preview=0;
			}
			else if(preview==1 && schList.get(i).getStatus()!=4){
				st.append(2);
			}
			
			else if(cal.getTime().before(schList.get(i).getStartDate())&&cal.getTime().before(schList.get(i).getEndDate())&&preview==0&& schList.get(i).getStatus()!=4)
			{	
				st.append(3);
			}
			else if(cal.getTime().after(schList.get(i).getEndDate()))
			{	
				st.append(4);
			}
			else
			{
				st.append(schList.get(i).getStatus());
			}
			st.append(SEPARATOR);
			st.append(schList.get(i).getThreeDOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getBlockBuster());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPlatOrNot());
			st.append(SEPARATOR);
			st.append(preview+"\n");
			
			alMS.add(st.toString()) ;
	}
	
	writeB("data/movieScheduleListing.txt",alMS);

	
}

	public static void updateSchedule(MovieSchedule sch) throws IOException, ParseException
	{
		List alMS = new ArrayList() ;// to store Professors data
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListing();
		Calendar cal=Calendar.getInstance();
		String startDate="",endDate="";
		for(int i=0;i<schList.size();i++){
		 if(sch.getListingId()!=schList.get(i).getListingId())
		 {
			StringBuilder st =  new StringBuilder() ;
			st.append(schList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(schList.get(i).getListingId());
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getStartDate()));
			st.append(SEPARATOR);
			st.append(finalDateFormatter.format(schList.get(i).getEndDate()));
			st.append(SEPARATOR);
			st.append(schList.get(i).getTypeofDay());
			st.append(SEPARATOR);
			st.append(schList.get(i).getStatus());
			st.append(SEPARATOR);
			st.append(schList.get(i).getThreeDOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getBlockBuster());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPlatOrNot());
			st.append(SEPARATOR);
			st.append(schList.get(i).getPreviewStatus()+"\n");
			
			alMS.add(st.toString()) ;
		}
		 else
		 {
			 StringBuilder st =  new StringBuilder() ;
				st.append(sch.getMovieId());
				st.append(SEPARATOR);
				st.append(sch.getListingId());
				st.append(SEPARATOR);
				st.append(finalDateFormatter.format(sch.getStartDate()));
				st.append(SEPARATOR);
				st.append(finalDateFormatter.format(sch.getEndDate()));
				st.append(SEPARATOR);
				st.append(SEPARATOR);
				st.append(sch.getTypeofDay());
				st.append(SEPARATOR);
				st.append(sch.getStatus());
				st.append(SEPARATOR);
				st.append(sch.getThreeDOrNot());
				st.append(SEPARATOR);
				st.append(sch.getBlockBuster());
				st.append(SEPARATOR);
				st.append(sch.getPlatOrNot());
				st.append(SEPARATOR);
				st.append(sch.getPreviewStatus()+"\n");
				
				alMS.add(st.toString()) ; 
		 }
		}
		writeB("data/movieScheduleListing.txt",alMS);
	
		
}




}
