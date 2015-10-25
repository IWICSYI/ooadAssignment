package controllerClasses;

import java.io.IOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import misc.ObjectContainer;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class SchedulerController extends MovieListingControl{
	
	
	
	
	
	public void createSchedule(MovieSchedule m) throws IOException, ParseException {
		
			List alMS = new ArrayList() ;// to store Professors data
		
			ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
			sch=readScheduleListing();
			String startDate=finalDateFormatter.format(m.getStartDate());
			String endDate=finalDateFormatter.format(m.getEndDate());
			int id=sch.get(sch.size()-1).getListingId()+1;
			//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
			StringBuilder st =  new StringBuilder() ;
			st.append(m.getCineplexId());
			st.append(SEPARATOR);
			st.append(m.getCinemaId());
			st.append(SEPARATOR);
			st.append(m.getMovieId());
			st.append(SEPARATOR);
			st.append(id);
			st.append(SEPARATOR);
			st.append(startDate);
			st.append(SEPARATOR);
			st.append(endDate);
			st.append(SEPARATOR);
			st.append(m.getTypeofDay());
			st.append(SEPARATOR);
			st.append(111);
			st.append(SEPARATOR);
			st.append(m.getThreeDOrNot());
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
	
	public void createTimeSlot(ShowTime sT) throws IOException, ParseException
	{
		ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
		sch=readScheduleListing();
		int id=sch.get(sch.size()-1).getListingId()+1;
		ArrayList<ShowTime> existST = readShowTimes();
		int stID=existST.size()+1;
		StringBuilder st2 =  new StringBuilder() ;
		List alTS = new ArrayList() ;
			st2.append(id);
			st2.append(SEPARATOR);
			st2.append(sT.getMovieId());
			st2.append(SEPARATOR);
			st2.append(sT.getCinemaId());
			st2.append(SEPARATOR);
			st2.append(stID++);
			st2.append(SEPARATOR);
			st2.append(sT.getDayType());
			st2.append(SEPARATOR);
			st2.append(sT.getShowTimeValue());
			st2.append(SEPARATOR);
			st2.append(sT.getNoOfSeats());
		
		
			alTS.add(st2.toString()) ;
			write("data/showTimes.txt",alTS);
			
		}
	
	public ArrayList<MovieSchedule> readScheduleListing() throws IOException, ParseException{
		
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
				
				alr.add(u) ;
				
			}
			return alr ;
	}

	public ArrayList<MovieSchedule> readScheduleListingBasedonMovieandCineplexId(int movie,int cineplex) throws IOException, ParseException{
		
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
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				if(cineplex==cinplexId&&movie==movieId){
					MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,platOrNot,previewStatus);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	
	
		
	}
	

	


