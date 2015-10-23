package controllerClasses;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class SchedulerController extends DataControl{
	
	
	
	
	
	public void createSchedule(MovieSchedule m) throws IOException {
		
			List alMS = new ArrayList() ;// to store Professors data
			List alTS = new ArrayList() ;
			ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
			ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
			sch=readScheduleListing();
			
			int id=sch.size()+1;
			//1cineplexId|1movieUniqueId|1listingId|startEndDate|1typeofDay|status;		
			StringBuilder st =  new StringBuilder() ;
			st.append(m.getCineplexId());
			st.append(SEPARATOR);
			st.append(m.getMovieId());
			st.append(SEPARATOR);
			st.append(id);
			st.append(SEPARATOR);
			st.append("20.12.2015");
			st.append(SEPARATOR);
			st.append("25.12.2015");
			st.append(SEPARATOR);
			st.append(1);
			st.append(SEPARATOR);
			st.append(1);
			alMS.add(st.toString()) ;
			//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
			ArrayList<ShowTime> sTArray=new ArrayList<ShowTime>();
			sTArray=m.getShowTimeList();
			ArrayList<ShowTime> existST = readShowTimes();
			int stID=existST.size()+1;
			StringBuilder st2 =  new StringBuilder() ;
			
			for(int i=0;i<sTArray.size();i++){
				st2.append(id);
				st2.append(SEPARATOR);
				st2.append(sTArray.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(sTArray.get(i).getCinemaId());
				System.out.println(sTArray.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(stID++);
				st2.append(SEPARATOR);
				st2.append(sTArray.size());
				st2.append(SEPARATOR);
				for(int j=0;j<sTArray.get(i).getShowTimeArray().size();j++)
				{
					st2.append(sTArray.get(i).getShowTimeArray().get(j));
					st2.append(SEPARATOR);
				}
				alTS.add(st2.toString()) ;
				write("data/showTimes.txt",alTS);
				alTS.clear();
				st2=new StringBuilder();
			}
			write("data/movieScheduleListing.txt",alMS);
		}
	
	public void updateTimeSlot(ArrayList<ShowTime> sTArray) throws IOException
	{
		ArrayList<ShowTime> existST=readShowTimes();
		
		ArrayList<ObjectContainer> pairList=new ArrayList<ObjectContainer>();
		ArrayList<ObjectContainer> pairList2=new ArrayList<ObjectContainer>();
		StringBuilder st =  new StringBuilder() ;
	
		int checkCinemaId,checkMovieId;
		int checkCinemaId2,checkMovieId2;
		
		for(int i=0;i<existST.size();i++)
		{
			ObjectContainer p=new ObjectContainer(existST.get(i).getCinemaId(),existST.get(i).getMovieId());
			pairList.add(p);
		}
		
		for(int i=0;i<sTArray.size();i++)
		{
			ObjectContainer p2=new ObjectContainer(sTArray.get(i).getCinemaId(),sTArray.get(i).getMovieId());
			pairList2.add(p2);
		}
		
		for(int i=0;i<pairList.size();i++)
		{
			for(int j=0;j<pairList2.size();j++)
			{	
				checkCinemaId=pairList.get(i).getI();
				checkMovieId=pairList.get(i).getId();
				checkCinemaId2=pairList2.get(i).getI();
				checkMovieId2=pairList2.get(i).getId();
				if(checkCinemaId==checkCinemaId2 && checkMovieId==checkMovieId)
				{
					for(int h=0;h<sTArray.get(i).getShowTimeArray().size();h++)
					{
						
					}
				}
			}
		}
		
	}
	

	

}
