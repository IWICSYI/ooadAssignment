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
import controllerClasses.MovieEntryControl;
import controllerClasses.MovieListingControl;
import controllerClasses.SchedulerController;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieScheduleDataControl;

public class AdminSchedulerUpdateUi extends AdminSchedulerUi{
	
	
	public void displayUpdateMain()
	{
		System.out.println("#############################################");
		System.out.println("#            Schedule Update Page           #");
		System.out.println("#############################################");
		System.out.println("1.Search and list now showing listing to edit");
		System.out.println("2.Search and list comming soon listing to edit");
		System.out.println("3.Search and list ended listing to edit");
		
	}
	
	
	
	public void displayUpdatePageForNowShowingListing() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListingBasedOnStartingDate();
		ArrayList<Movie> movieList=MovieListingControl.filterUniqueMovieListFromSchedule(schList,0);
	
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		
		
		System.out.println("These are the movies that are currently now Showing");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<movieList.size();i++)
		{
			ObjectContainer o=new ObjectContainer();
			o.setI((i+1));
			o.setM(movieList.get(i));
			o.setMovieId(movieList.get(i).getMovieId());
			oList.add(o);
		}
		
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("Select number beside the movie to edit the listing");
		
		for(int i=0;i<oList.size();i++)
		{
			System.out.print((i+1)+":"+oList.get(i).getM().getMovieName()+" ");
			//pair.add(MiscControl.idPairerWithMovie((i+1), movieList.get(i)));
			
			if(i==2)
				System.out.println();
		}
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		filterByCineplexId(schList,oList.get(choice-1).getM().getMovieId());
	
		
		
	}

	public void filterByCineplexId(ArrayList<MovieSchedule> schList, int movieId) throws IOException, ParseException{
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
		
		ArrayList<MovieSchedule> schListWork=new ArrayList<MovieSchedule>();
		
		for(int i=0;i<schList.size();i++)
		{
			if(schList.get(i).getCineplexId()==cinplexId)
			{
				schListWork.add(schList.get(i));
			}
			
		}
	}
	
	
	public void displayUpdateExistPage(MovieSchedule sch) {
		// TODO Auto-generated method stub
		
	}
}
