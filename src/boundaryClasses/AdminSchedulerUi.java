package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.SchedulerController;
import controllerClasses.ShowTimeController;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CineplexDataControl;
import dataController.DataControl;
import dataController.MovieDataControl;

public class AdminSchedulerUi extends DataControl {

	public void displayMain() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		
		MovieDataControl mec=new MovieDataControl();
		do{
			System.out.println("#############################################");
			System.out.println("#            Schedule Manager Page          #");
			System.out.println("#          1.Create Movie Listing           #");
			System.out.println("#          2.Update Movie Listing           #");
			System.out.println("#          3.Sort Movies by Rankings        #");
			System.out.println("#          4.Return to main menu            #");
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
			else if(choice==4){
				AdminMainUi ui=new AdminMainUi();
				ui.display();
			}
		}while(choice<5);
		
		
	}
	
	
	
	
	public void displayCreatePage() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0, num=0,cinemaId,movieId,movieLen,movieType, cinplexId;
		boolean validDate;
		MovieSchedule sch=new MovieSchedule();
		
		ValidationControl vl=new ValidationControl();
		MiscControl oC=new MiscControl();
		
		ArrayList<Cineplex> cnList= new ArrayList<Cineplex>();
		cnList=CineplexDataControl.readCineplex();
		
		ArrayList<Movie> movieList= new ArrayList<Movie>();
		movieList=MovieDataControl.readMovie();
		
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		
	
	
		
		
		System.out.println("#############################################");
		System.out.println("#            Schedule Creation Page         #");
		System.out.println("#############################################");
		
		do{
			System.out.println("Please choose Cineplex");
		
			for(int i=0;i<cnList.size();i++)
			{
				System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
			}
			cinplexId=vl.validateAndReturnIntegerValue(sc.nextLine());
				
		}while(cinplexId==-2|| cinplexId>cnList.size());
		sch.setCineplexId(cinplexId);
		
		do{
			System.out.println("Please select which movie to be scheduled");
			for(int i=0;i<movieList.size();i++)
			{
				System.out.print((i+1)+":"+movieList.get(i).getMovieName()+" ");
				pair.add(oC.idPairerWithMovieLength(i, movieList.get(i).getMovieId(), movieList.get(i).getMovieLength(),movieList.get(i).getMovieType()));
			}
			System.out.println();
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			
		}while(choice==-2|| choice>(movieList.size()+2));
		
		movieId=pair.get(choice-1).getId();
		movieLen=pair.get(choice-1).getMovieLen();
		movieType=pair.get(choice-1).getMovieType();
		
		
		sch.setMovieId(movieId);
		pair.clear();
		int choice2=0;
		do
		{
			if(movieType==2)
			{
				System.out.println("Please select whether movie will be shown in 3D?");
				System.out.println("1.No");
				System.out.println("2.Yes");
				choice2=vl.validateYesNoAndReturnIntegerValue(sc.nextLine());
				if(choice2<1||choice2>2)
				{
					System.out.println("Invalid input, please try again");
					choice2=200;
				}
				else
				{
					sch.setThreeDOrNot(choice2-1);
				}
			}
		}while(choice2>3);
		
		int choice3=0;
		do
		{
				System.out.println("Please select whether this movie is a blockbuster?");
				System.out.println("1.No");
				System.out.println("2.Yes");
				choice3=vl.validateYesNoAndReturnIntegerValue(sc.nextLine());
				if(choice3<1||choice3>2)
				{
					System.out.println("Invalid input, please try again");
					choice3=200;
				}
				else
				{
					sch.setBlockBuster(choice3-1);
				}
			
		}while(choice3>3);
		
		Date startDate;
		int choice4=0;
		do{
			System.out.println("Please enter starting date(Eg.25/10/2015) of the movie:");
			String tmp=sc.nextLine();
			startDate=ValidationControl.validateDate(tmp);
			
			}
		while(startDate==null);
		
		sch.setStartDate(startDate);
		
		int runDate=-2;
	do{
		System.out.println("How many days will the movie be shown?");
		 runDate=vl.validateAndReturnIntegerValue(sc.nextLine());
		}
	while(runDate==-2);
		sch.setEndDate(calculateEndDate(startDate,runDate));
		
		do
		{
				System.out.println("Please select whether this movie will have a preivew?");
				System.out.println("1.No");
				System.out.println("2.Yes");
				choice4=vl.validateYesNoAndReturnIntegerValue(sc.nextLine());
				if(choice4<1||choice4>2)
				{
					System.out.println("Invalid input, please try again");
					choice4=200;
				}
				else
				{
					sch.setPreviewStatus(choice4-1);
				}
			
		}while(choice4>3);
		
		
		AdminTimeSlotUi sTC=new AdminTimeSlotUi();
		sTC.TimeSlotHandler(sch,movieId,movieLen,movieType,cinplexId);
		
		
		displayMain();
			
		
	}

}
