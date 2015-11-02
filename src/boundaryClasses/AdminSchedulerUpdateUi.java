package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
	
	
	public void displayUpdateMain() throws IOException, ParseException
	{
		Scanner sc=new Scanner(System.in);
		int choice=0;
		do{
			System.out.println("#############################################");
			System.out.println("#            Schedule Update Page           #");
			System.out.println("#############################################");
			System.out.println("1.Search and list now showing show time to edit");
			System.out.println("2.Search and list comming soon show time to edit");	
			System.out.println("3.Go Back");	
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<0||choice>3);
			
		if(choice==1)
		{
			displayUpdatePageForNowShowingListing();
		}
		else if(choice==2)
		{
			//tobedone displayUpdatePageForNowShowingListing()
		}
		else if(choice==3){
			AdminSchedulerUi s=new AdminSchedulerUi();
			s.displayMain();
		}
		
		
		
	}
	
	
	
	public void displayUpdatePageForNowShowingListing() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		
		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>();
		
		ArrayList<MovieSchedule> schList=MovieScheduleDataControl.readScheduleListingBasedOnStartingDate();
		ArrayList<Movie> movieList=MovieListingControl.filterUniqueMovieListFromSchedule(schList,0);
	
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		
		
		for(int i=0;i<movieList.size();i++)
		{
			ObjectContainer o=new ObjectContainer();
			o.setI((i+1));
			o.setM(movieList.get(i));
			o.setMovieId(movieList.get(i).getMovieId());
			oList.add(o);
		}
		System.out.println("These are the movies that are currently now Showing");
		System.out.println("------------------------------------------------------");
		
		
		for(int i=0;i<oList.size();i++)
		{
			System.out.print((i+1)+":"+oList.get(i).getM().getMovieName()+" ");
			//pair.add(MiscControl.idPairerWithMovie((i+1), movieList.get(i)));
			
			if(i==2)
				System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println("Select number beside the movie to edit the listing");
		
		
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		
		int cinplexId=MovieListingControl.filterNowShowingListingByCineplexId(oList.get(choice-1).getM().getMovieId());
		
		ArrayList<MovieSchedule> schWork=MovieScheduleDataControl.readScheduleListingBasedonMovieandCineplexId(oList.get(choice-1).getM().getMovieId(), choice);
		
		
		
	}

	
	
	
	public void displayUpdateExistPage(MovieSchedule sch) {
		// TODO Auto-generated method stub
		
	}
}
