package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.MovieListingControl;
import data.Movie;
import data.MovieSchedule;

public class CustomerUi {

	public void displayNowShowing() throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedOnStartingDate();
		ArrayList<Movie> movieList=cl.retrieveUniqueMovieListFromSchedule(schList);
		Scanner sc=new Scanner(System.in);
		System.out.println("Now Showing");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print("Movie Name:"+movieList.get(i).getMovieName()+" ");
			if(i==2)
				System.out.println();
		}
		System.out.println();
		System.out.println("------------------------------------------------------");
		
		System.out.println("1.Display Comming Soon");
		System.out.println("2.Display Preview");
		System.out.println("3.View Movie Details");
		System.out.println("4.Purchase Ticket");
		String choice=sc.nextLine();
		CustMovieDetailUi c=new CustMovieDetailUi();
		c.displayMovieDetails(4);
		
	}

}
