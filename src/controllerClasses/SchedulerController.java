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
import data.HolidayDate;
import data.Movie;
import data.MovieSchedule;
import data.Prices;
import data.ShowTime;
import dataController.ShowTimeDataControl;
import dataController.TicketPriceAndHolidayDataControl;

public class SchedulerController extends MovieListingControl{
	
	public boolean checkDuplicatedSchedule(MovieSchedule sch) throws IOException, ParseException{
		ArrayList<MovieSchedule> schList=readScheduleListing();
		int cineplex=0,cinema=0,movie=0;
		for(int i=0;i<schList.size();i++)
		{
			cineplex=schList.get(i).getCineplexId();
			cinema=schList.get(i).getCinemaId();
			movie=schList.get(i).getMovieId();
			if(cineplex==sch.getCineplexId()&&cinema==sch.getCinemaId()&&movie==sch.getMovieId())
			{	
				return true;
			}
		}
		
		return false;
		
	}
	
	
	
	public void createSchedule(MovieSchedule m) throws IOException, ParseException {
		
			List alMS = new ArrayList() ;// to store Professors data
		
			ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
			sch=readScheduleListing();
			String startDate=finalDateFormatter.format(m.getStartDate());
			String endDate=finalDateFormatter.format(m.getEndDate());
			int id;
			if(!sch.isEmpty()){
				 id=sch.get(sch.size()-1).getListingId()+1;
			}
			else
			{
				id=1;
			}
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
	
	public void createTimeSlot(ShowTime sT, MovieSchedule sch2) throws IOException, ParseException
	{
		ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
		sch=readScheduleListing();
		int id;
		if(!sch.isEmpty()){
			 id=sch.get(sch.size()-1).getListingId()+1;
		}
		else
		{
			id=1;
		}
		ArrayList<ShowTime> existST = ShowTimeDataControl.readShowTimes();
		int stID;
		if(!existST.isEmpty())
		{
			stID=existST.get(existST.size()-1).getShowTimeId();
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
		if(dtype==0||dtype==6){
			finalPrice=finalPrice+prices.get(0).getWeekend();
		}
		else if(dtype==8){
			finalPrice=finalPrice+prices.get(0).getHoli();
		}
		
		
			
		
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
			st2.append(SEPARATOR);
			st2.append(startDate);
			st2.append(SEPARATOR);
			st2.append(endDate);
			st2.append(SEPARATOR);
			st2.append(finalPrice);
			st2.append(SEPARATOR);
			st2.append(sch2.getPreviewStatus());
			st2.append(SEPARATOR);
			st2.append(sch2.getCineplexId());
		
		
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
				int blockOrNot=Integer.parseInt(star.nextToken().trim());
				int platOrNot=Integer.parseInt(star.nextToken().trim());
				int previewStatus=Integer.parseInt(star.nextToken().trim());
				
				MovieSchedule u = new MovieSchedule(cinplexId,cinemaId,movieId,listingId,startDate,endDate,typeOfDay,status,threeDOrNot,blockOrNot,platOrNot,previewStatus);
				
				alr.add(u) ;
				
			}
			return alr ;
	}

	


	public void updateSchedule(ArrayList<MovieSchedule> newschListWork,ArrayList<MovieSchedule> schList, int cinemaId, int movieId, int cinplexId) throws IOException
	{
		List alMS = new ArrayList() ;// to store Professors data
		String startDate="",endDate="";
		for(int i=0;i<schList.size();i++){
			for(int j=0;j<newschListWork.size();j++)
			{
				if(newschListWork.get(j).getListingId()==schList.get(i).getListingId()&&schList.get(i).getCinemaId()==cinemaId&&schList.get(i).getMovieId()==movieId&&schList.get(i).getCineplexId()==cinplexId)
				{
					 startDate=finalDateFormatter.format(newschListWork.get(j).getStartDate());
					 endDate=finalDateFormatter.format(newschListWork.get(j).getEndDate());
				}
				else
				{
					 startDate=finalDateFormatter.format(schList.get(i).getStartDate());
					 endDate=finalDateFormatter.format(schList.get(i).getEndDate());
			
				}
			}
		//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
		StringBuilder st =  new StringBuilder() ;
		st.append(schList.get(i).getCineplexId());
		st.append(SEPARATOR);
		st.append(schList.get(i).getCinemaId());
		st.append(SEPARATOR);
		st.append(schList.get(i).getMovieId());
		st.append(SEPARATOR);
		st.append(schList.get(i).getListingId());
		st.append(SEPARATOR);
		st.append(startDate);
		st.append(SEPARATOR);
		st.append(endDate);
		st.append(SEPARATOR);
		st.append(schList.get(i).getTypeofDay());
		st.append(SEPARATOR);
		st.append(111);
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
		// public MovieSchedule(int cineplexId, int cinemaId, int movieId,
		//int listingId, Date startDate, Date endDate, int typeofDay,
		//int status, int threeDOrNot, int platOrNot, int previewStatus) {
	
		writeB("data/movieScheduleListing.txt",alMS);
	
		
	}
	
	
		
	}
	

	


