package boundaryClasses;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import controllerClasses.MovieEntryController;
import controllerClasses.ValidationControl;
import data.Movie;

public class MovieEntryUi {
	
	
	public static void displayMain() throws IOException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		MovieEntryController mec=new MovieEntryController();
		do{
			System.out.println("#############################################");
			System.out.println("#            Movie Manager Page             #");
			System.out.println("#          1.Create Movie Entry           #");
			System.out.println("#          2.Update Movie Details           #");
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

	public static void displayCreatePage() throws IOException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		ValidationControl vl=new ValidationControl();
		Movie movie=new Movie();
		System.out.println("#############################################");
		System.out.println("#            Movie Creation Page            #");
		System.out.println("#############################################");
		System.out.println("Please enter movie name");
		movie.setMovieName(sc.nextLine());
		
		System.out.println("Please enter age rating");
		movie.setAgeRating(sc.nextLine());
		
		System.out.println("Please enter director names");
		movie.setDirector(sc.nextLine());
		
		System.out.println("Please enter cast names");
		movie.setCast(sc.nextLine());
		
		System.out.println("Please enter synopsis");
		movie.setSynopsis(sc.nextLine());
		
		System.out.println("Please enter movie length in minutes(Eg:130)");
		int len=vl.validateAndReturnIntegerValue(sc.nextLine());
		
		movie.setMovieLength(len);
		
		
		do
		{	
			System.out.println("Please choose movie type");
			System.out.println("1.Digital Movie");
			System.out.println("2.Digital Block Buster Movie");
			System.out.println("3.3D Movie");
			choice=vl.validateAndReturnIntegerValue(sc.nextLine());
			if(choice<1||choice>3)
			{
				System.out.println("Invalid input, please try again");
				choice=200;
			}
			else
			{
				movie.setMovieType(choice);
				break;
			}
		}while(choice>4);
		
		MovieEntryController mec=new MovieEntryController();
		mec.createMovie(movie);
		displayMain();
			
	
		
	}
	
	
}
