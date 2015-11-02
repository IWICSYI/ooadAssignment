package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MovieEntryControl;
import controllerClasses.ValidationControl;
import data.Movie;
import dataController.MovieDataControl;

public class AdminMovieEntryUi {
	
	
	public static void displayMain() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		MovieDataControl mec=new MovieDataControl();
		do{
			System.out.println("#############################################");
			System.out.println("#            Movie Manager Page             #");
			System.out.println("#          1.Create Movie Entry             #");
			System.out.println("#          2.Update Movie Details           #");
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
			
			else if(choice==2)
			{
				displayUpdatePage();
			}
			else if(choice==4)
			{
				AdminMainUi a=new AdminMainUi();
				a.display();
			}
			
		}while(choice<5);
		
		
	}

	public static void displayCreatePage() throws IOException, ParseException{
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
			System.out.println("Please choose whehter movie is a 3D movie or not?");
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
				movie.setMovieType(choice);
				break;
			}
		}while(choice<=-1);
		
		do
		{	
			System.out.println("Please choose whehter movie is a blockbuster or not?");
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
				movie.setBlockbuster(choice);
				break;
			}
		}while(choice<=-1);
		
		if(movie.getThreeD()==2){
			movie.setMovieName(movie.getMovieName()+" (3D)");
		}
		
		if(movie.getBlockbuster()==2)
		{
			movie.setMovieName(movie.getMovieName()+" (BLOCKBUSTER!)");
		}
		
		
		
		MovieEntryControl.createMovie(movie);
		displayMain();
			
	
		
	}
	
	
	
	public static void displayUpdatePage() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		ValidationControl vl=new ValidationControl();
		Movie movie=new Movie();
		System.out.println("#############################################");
		System.out.println("#            Movie Update Page              #");
		System.out.println("#############################################");
		
		ArrayList<Movie> movieList=MovieDataControl.readMovie();
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		do{
			System.out.println("Please select movie to edit");
			for(int i=0;i<movieList.size();i++)
			{
				System.out.println((i+1)+":"+movieList.get(i).getMovieName());
				ObjectContainer o= new ObjectContainer();
				o.setI(i+1);
				o.setM(movieList.get(i));
				oList.add(o);
			}
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice==-2||choice>movieList.size()||choice<0);
		Movie temp=new Movie();
		for(int i=0;i<oList.size();i++){
			if(choice==oList.get(i).getI()){
				temp=oList.get(i).getM();
			}
		}
		int choice2=0;
		do{
			temp.printMovieDetails();
			System.out.println("1.Edit Movie Name");
			System.out.println("2.Edit Age Rating");
			System.out.println("3.Edit Director");
			System.out.println("4.Edit Casts");
			System.out.println("5.Edit Synopsis");
			System.out.println("6.Go back to main movie entry page");
			 choice2=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice2==-2||choice2>6||choice2<0);
		
		if(choice2==1){
			System.out.println("Enter new movie name:");
			temp.setMovieName(sc.nextLine());
		}
		else if(choice2==2){
			System.out.println("Enter new age rating:");
			temp.setAgeRating(sc.nextLine());
		}
		
		else if(choice2==3){
			System.out.println("Enter new director:");
			temp.setDirector(sc.nextLine());
		}
		else if(choice2==4){
			System.out.println("Enter new casts members:");
			temp.setCast(sc.nextLine());
		}
		else if(choice2==5){
			System.out.println("Enter new synopsis:");
			temp.setSynopsis(sc.nextLine());
		}
		else if(choice2==6){
			displayMain();
		}
		
		
		MovieEntryControl.updateMovie(temp);
		displayUpdatePage();
			
	
		
	}
	
	
	
	
}
