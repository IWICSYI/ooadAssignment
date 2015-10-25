package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.DataControl;
import controllerClasses.MovieEntryController;
import controllerClasses.MiscControl;
import controllerClasses.SchedulerController;
import controllerClasses.ShowTimeController;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class SchedulerUi extends DataControl {

	public void displayMain() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		
		
		
		
		MovieEntryController mec=new MovieEntryController();
		do{
			System.out.println("#############################################");
			System.out.println("#            Schedule Manager Page          #");
			System.out.println("#          1.Create Movie Listing           #");
			System.out.println("#          2.Update Movie Listing           #");
			System.out.println("#          3.Sort Movies by Rankings        #");
			System.out.println("#############################################");
			test=sc.nextLine();
			validation=vl.isInteger(test);
			if(validation)
				choice=Integer.parseInt(test);
			else
			{
				System.out.println("Invalid choice, please try again");
				choice=200;
			}
			if(choice==1)
			{
				displayCreatePage();
			}
			
		}while(choice<4);
		
		
	}
	
	
	public void displayCreatePage() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,movieId,movieLen,movieType, cinplexId;
		boolean validDate;
		MovieSchedule sch=new MovieSchedule();
		
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=readCineplex();
		
		ArrayList<Movie> movieList= new ArrayList<Movie>();
		movieList=readMovie();
		
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		
	
	
		
		
		System.out.println("#############################################");
		System.out.println("#            Schedule Creation Page         #");
		System.out.println("#############################################");
		
		System.out.println("Please choose Cineplex");
		for(int i=0;i<cnList.size();i++)
		{
			System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
		}
			cinplexId=vl.validateAndReturnIntegerValue(sc.nextLine());
			sch.setCineplexId(cinplexId);
		
		System.out.println("Please select which movie to be scheduled");
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print((i+1)+":"+movieList.get(i).getMovieName()+" ");
			pair.add(oC.idPairerWithMovieLength(i, movieList.get(i).getMovieId(), movieList.get(i).getMovieLength(),movieList.get(i).getMovieType()));
		}
		choice=vl.validateAndReturnIntegerValue(sc.nextLine());
		movieId=pair.get(choice-1).getId();
		movieLen=pair.get(choice-1).getMovieLen();
		movieType=pair.get(choice-1).getMovieType();
		sch.setMovieId(movieId);
		pair.clear();
		
		if(movieType==2)
		{
			System.out.println("Please select whether movie will be shown in 3D?");
			System.out.println("1.No");
			System.out.println("2.Yes");
			choice=vl.validateYesNoAndReturnIntegerValue(sc.nextLine());
			if(choice<1||choice>2)
			{
				System.out.println("Invalid input, please try again");
				choice=200;
			}
			else
			{
				sch.setThreeDOrNot(choice);
			}
		}
	
		
		System.out.println("Please enter starting date of the movie");
		String tmp=sc.nextLine();
		Date startDate=ValidationControl.validateDate(tmp);
		sch.setStartDate(startDate);
		
		System.out.println("How many days will the movie be shown?");
		int runDate=vl.validateAndReturnIntegerValue(sc.nextLine());
		sch.setEndDate(calculateEndDate(startDate,runDate));
		
		System.out.println("Please select whether movie will have a preview?");
		System.out.println("1.No");
		System.out.println("2.Yes");
		choice=vl.validateYesNoAndReturnIntegerValue(sc.nextLine());
		sch.setPreviewStatus(choice);
		
		
		
		ShowTimeController sTC=new ShowTimeController();
		sTC.TimeSlotHandler(sch,movieId,movieLen,movieType,cinplexId, pair);
		
		
		displayMain();
			
		
	}

}
