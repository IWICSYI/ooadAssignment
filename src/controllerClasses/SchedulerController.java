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
		
			ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
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
			
			write("data/movieScheduleListing.txt",alMS);
		}
	
	public void creatTimeSlot(ArrayList<ShowTime> sTArray) throws IOException
	{
		ArrayList<MovieSchedule> sch=new ArrayList<MovieSchedule>();
		sch=readScheduleListing();
		int id=sch.size()+1;
		ArrayList<ShowTime> existST = readShowTimes();
		int stID=existST.size()+1;
		StringBuilder st2 =  new StringBuilder() ;
		List alTS = new ArrayList() ;
		
		for(int i=0;i<sTArray.size();i++){
			st2.append(id);
			st2.append(SEPARATOR);
			st2.append(sTArray.get(i).getMovieId());
			st2.append(SEPARATOR);
			st2.append(sTArray.get(i).getCinemaId());
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
		
	}
	

	

}
