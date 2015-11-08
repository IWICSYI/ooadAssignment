package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.AdminMovieEntryControl;
import controllerClasses.SortTop5;
import controllerClasses.ValidationControl;
import data.Movie;
import dataController.MovieDataControl;

public class AdminMovieEntryUi extends AdminMainUi{
	
	
	public static void displayMovieMain() throws IOException, ParseException{
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
			System.out.println("#          3.Sort Movies by Rating          #");
			System.out.println("#          4.Sort Movies by Ticket Sales    #");
			System.out.println("#          5.Return to main menu            #");
			System.out.println("#############################################");
			test=sc.nextLine();
			choice=ValidationControl.validateAndReturnIntegerValue(test);
			
			if(choice==1)
			{
				displayCreatePage();
			}
			
			else if(choice==2)
			{
				displayUpdatePage();
			}
			else if(choice==3)
			{
				SortTop5.sortTopScore();
				System.out.println("Ready to resume?");
				sc.nextLine();
				displayMovieMain();
			}
			else if(choice==4)
			{
				SortTop5.sortTopSales();
				System.out.println("Ready to resume?");
				sc.nextLine();
				displayMovieMain();
			}
			else if(choice==5)
			{
				AdminMainUi.displayAdminMain();
			}
		}while(choice>5||choice<=0);
		
		
	}

	public static void displayCreatePage() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		ValidationControl vl=new ValidationControl();
		Movie movie=new Movie();
		System.out.println("#############################################");
		System.out.println("#            Movie Creation Page            #");
		System.out.println("#############################################");
		String check="";
		boolean valid=false;
		do
		{
			System.out.println("Please enter movie name:");
			check=sc.nextLine();
			valid=ValidationControl.validateEmptyString(check);
		
		}while(!valid);
		movie.setMovieName(check);
		
		do
		{
			System.out.println("Please enter age rating(Eg.PG13):");
			check=sc.nextLine();
			valid=ValidationControl.validateEmptyString(check);
		
		}while(!valid);
		movie.setAgeRating(check);
		
		do
		{
			System.out.println("Please enter director name:");
			check=sc.nextLine();
			valid=ValidationControl.validateEmptyString(check);
		
		}while(!valid);
		movie.setDirector(check);
		
		int num=0;
		String cast="";
		String checkcast="";
		
		do
		{
			System.out.println("Please enter number of cast members:");
			num=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(num<=0|| num>20);
		
		for(int i=0;i<num;i++){
			
			do
			{
				System.out.println("Please enter cast member no.:"+(i+1));
				checkcast=sc.nextLine();
				valid=ValidationControl.validateEmptyString(checkcast);
			
			}while(!valid);
			cast=cast+checkcast;
			
			if(i!=(num-1))
			{
				cast=cast+",";
			}
			
		}
		movie.setCast(cast);
		
		do{
			System.out.println("Please enter synopsis");
			valid=ValidationControl.validateEmptyString(sc.nextLine());
		}while(!valid);
			
		movie.setSynopsis(sc.nextLine());
		
		
		int len=0;
		do{
			System.out.println("Please enter movie length in minutes(Eg:130)");
			len=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(len<=0||len>1000);
		
		movie.setMovieLength(len);
		
		
		do
		{	
			System.out.println("Please choose whehter movie is a 3D movie or not?");
			System.out.println("1.No");
			System.out.println("2.Yes");
			choice=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
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
			choice=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
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
		
		MovieDataControl.createMovie(movie);
		displayMovieMain();
			
	
		
	}
	
	
	
	public static void displayUpdatePage() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		ValidationControl vl=new ValidationControl();
		//Movie movie=new Movie();
		System.out.println("			#############################################");
		System.out.println("			#            Movie Update Page              #");
		System.out.println("			#############################################");
		
		ArrayList<Movie> movieList=MovieDataControl.readMovie();
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		do{
			System.out.println("Please select movie to edit");
			for(int i=0;i<movieList.size();i++)
			{
				System.out.print((i+1)+":"+movieList.get(i).getMovieName()+"		");
				if((i+1)%4==0)
				{
					System.out.println();
				}
				ObjectContainer o= new ObjectContainer();
				o.setI(i+1);
				o.setM(movieList.get(i));
				oList.add(o);
			}
			System.out.println();
			System.out.println(movieList.size()+1+":Go to Movie Manager Page");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=0||choice>movieList.size()+1);
		if(choice==movieList.size()+1)
		{
			displayMovieMain();
			return;
		}
		
		Movie temp=new Movie();
		for(int i=0;i<oList.size();i++){
			if(choice==oList.get(i).getI()){
				temp=oList.get(i).getM();
			}
		}
		AdminMovieEntryControl.handleMovieEdit(temp);
	
	}
		
	
	
	
}
