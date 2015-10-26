package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MovieListingControl;
import controllerClasses.ValidationControl;
import data.Movie;
import data.MovieSchedule;

public class CustomerUi {

	public void displayNowShowing() throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		ArrayList<MovieSchedule> schList=cl.readNonPlatScheduleListingBasedOnStartingDate();
		ArrayList<Movie> movieList=cl.retrieveUniqueMovieListFromSchedule(schList);
		System.out.println(movieList.size());
		ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Now Showing");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<movieList.size();i++)
		{
			ObjectContainer o=new ObjectContainer();
			o.setI((i+1));
			o.setM(movieList.get(i));
			oList.add(o);
			System.out.print("Movie Name:"+movieList.get(i).getMovieName()+"	");
			if(i==2)
				System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------------------");
		
		System.out.println("1.Display Comming Soon");
		System.out.println("2.Display Preview");
		System.out.println("3.View Movie Details");
		
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		CustMovieDetailUi c=new CustMovieDetailUi();
		c.displayNowShowingMovieDetailsSelection();
		
	}
	
	
	public void displayPreview() throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		ArrayList<MovieSchedule> schList=cl.readNonPlatScheduleListingBasedOnPreview();
		ArrayList<Movie> movieList=cl.retrieveUniqueMovieListFromSchedule(schList);
		Scanner sc=new Scanner(System.in);
		System.out.println("Preview");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print("Movie Name:"+movieList.get(i).getMovieName()+"	");
			if(i==2)
				System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------------------");
		
		System.out.println("1.Display Now Showing");
		System.out.println("2.Display Comming Soon");
		System.out.println("3.View Movie Details");
		//System.out.println("4.Purchase Ticket");
		String choice=sc.nextLine();
		CustMovieDetailUi c=new CustMovieDetailUi();
		//c.displayMovieDetails(4);
		
	}

}
