package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.SchedulerController;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;

public class AdminSchedulerUpdateUi extends AdminSchedulerUi{
	
	public void displayUpdatePage() throws IOException, ParseException{
		SchedulerController cl=new SchedulerController();
		Scanner sc=new Scanner(System.in);
		int cinplexId;
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=CineplexDataControl.readCineplex();
		String cineplexName="";
		do{
			System.out.println("Please choose Cineplex");
		
			for(int i=0;i<cnList.size();i++)
			{
				System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
			}
			cinplexId=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			 cineplexName=cnList.get(cinplexId-1).getCineplexName();
				
		}while(cinplexId==-2|| cinplexId>cnList.size());
		
		ArrayList<MovieSchedule> schList = cl.readScheduleListing();
		ArrayList<MovieSchedule> schListWork=new ArrayList<MovieSchedule>();
		
		for(int i=0;i<schList.size();i++)
		{
			if(schList.get(i).getCineplexId()==cinplexId)
			{
				schListWork.add(schList.get(i));
			}
			
		}
		ArrayList<Movie> movieList=cl.retrieveUniqueMovieListFromSchedule(schListWork);
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print((i+1)+":"+movieList.get(i).getMovieName()+" ");
			pair.add(MiscControl.idPairerWithMovie((i+1), movieList.get(i)));
			
			if(i==2)
				System.out.println();
		}
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		
		int movieId=pair.get(choice-1).getM().getMovieId();
		String moviename=pair.get(choice-1).getM().getMovieName();
		
		schListWork.clear();
		for(int i=0;i<schList.size();i++)
		{
			if(schList.get(i).getMovieId()==movieId &&schList.get(i).getCineplexId()==cinplexId)
			{
				schListWork.add(schList.get(i));
			}
		}
		
		ArrayList<Integer> cinemaIdList=new ArrayList<Integer>();
		
		for(int i=0;i<schListWork.size();i++)
		{
			cinemaIdList.add(schListWork.get(i).getCinemaId());
		}
		ArrayList<Integer> uniquecinemaIdList = MiscControl.getUniqueInteger(cinemaIdList);
		
		ArrayList<Cinema> cinemaList=CinemaDataControl.readCinema();
		ArrayList<Cinema> cinemaListWork=new ArrayList<Cinema>();
		for(int i=0;i<cinemaList.size();i++)
		{
			for(int j=0;j<uniquecinemaIdList.size();j++)
			{
				if(cinemaList.get(i).getCinemaId()==uniquecinemaIdList.get(j))
				{
					cinemaListWork.add(cinemaList.get(i));
				}
			}
		}
		
		ArrayList<ObjectContainer> pair2=new ArrayList<ObjectContainer>();
		int choice2;
		do{
			System.out.println("Please choose Cinema Hall:");
			for(int j=0;j<cinemaListWork.size();j++)
			{
				System.out.print((j+1)+":"+cinemaListWork.get(j).getCinemaHallName()+"	");
				pair2.add(MiscControl.idPairerWithCinema(j, cinemaListWork.get(j).getCinemaId(), cinemaListWork.get(j).getCinemaHallName(),cinemaListWork.get(j).getCinemaType(),cinemaListWork.get(j).getSeats()));
				
			}
			System.out.println();
			choice2=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice2<=-1);
		int cinemaId=pair2.get(choice2-1).getId();
		String cinemaName=pair2.get(choice2-1).getName();
		schListWork.clear();
		for(int i=0;i<schList.size();i++)
		{
			if(schList.get(i).getMovieId()==movieId &&schList.get(i).getCineplexId()==cinplexId&&schList.get(i).getCinemaId()==cinemaId)
			{
				schListWork.add(schList.get(i));
			}
		}
		
		int choice3=0;
		if(!schListWork.isEmpty())
		{
			System.out.println("Updating schedule for:");
			System.out.println("Cinplex:"+cineplexName);
			System.out.println("Movie name:"+moviename);
			System.out.println("Cinema hall name:"+cinemaName);
			System.out.println("1.Update start/end date");
			System.out.println("2.End the showing");
			System.out.println("3.Update Timeslots");
			choice3=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			String startDate=sdf.format(schListWork.get(0).getStartDate());
			String endDate=sdf.format(schListWork.get(0).getEndDate());
			if(choice3==1)
			{
				System.out.println("Current Start Date="+startDate);
				System.out.println("Enter updated date:");
				String tmp=sc.nextLine();
				Date newSD=ValidationControl.validateDate(tmp);
				int runDate;
				do{
					System.out.println("Enter how long will the movie runs:");
					 runDate=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
					}
				while(runDate==-2);
				
				for(int j=0;j<schListWork.size();j++)
				{
					schListWork.get(j).setStartDate(newSD);
					schListWork.get(j).setEndDate(calculateEndDate(newSD,runDate));	
					System.out.println(schListWork.get(j).getListingId());
					cl.updateSchedule(schListWork,schList,cinemaId,movieId,cinplexId);
				}
				
			}
			else if(choice3==2)
			{
				Date endShow=sdf.parse("01/01/0001");
				int choice4=0;
				System.out.println("Current Start Date="+startDate);
				System.out.println("Current End Date="+endDate);
				System.out.println("Do you want to end the showing?");
				System.out.println("1.Yes?");
				System.out.println("1.No?");
				choice4=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
				if(choice4==1)
				{
					for(int j=0;j<schListWork.size();j++)
					{
						schListWork.get(j).setStartDate(endShow);
						schListWork.get(j).setEndDate(endShow);	
						cl.updateSchedule(schListWork,schList,cinemaId,movieId,cinplexId);
					}
				}
				else if(choice4==2)
				{
					choice3=-2;
				}
				
			}
			
			
		
		}
		
	}

	public void displayUpdateExistPage(MovieSchedule sch) {
		// TODO Auto-generated method stub
		
	}
}
