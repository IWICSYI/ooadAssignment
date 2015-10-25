package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import data.Movie;
import data.MovieSchedule;

public class CustMovieDetailUi {
	
	public void displayNowShowingMovieDetailsSelection() throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedOnStartingDate();
		ArrayList<Movie> movieList=cl.retrieveUniqueMovieListFromSchedule(schList);
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		System.out.println("Now Showing");
		System.out.println("------------------------------------------------------");
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print((i+1)+"Movie Name:"+movieList.get(i).getMovieName()+" ");
			pair.add(MiscControl.idPairerWithMovie((i+1), movieList.get(i)));
			
			if(i==2)
				System.out.println();
		}
				
		System.out.println("------------------------------------------------------");
		
		System.out.println("1.Select number beside movie to view details");
		displayMovieDetails(pair.get(0).getM().getMovieId());
	}
	
	
	public void displayMovieDetails(int movieId) throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedOnStartingDate();
		Movie movieDetails=cl.readMovieBasedOnId(movieId);
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Movie Name:"+movieDetails.getMovieName()+" ");
		
		System.out.println("1.Read Review");
		System.out.println("2.Buy Ticket");
		String s=sc.nextLine();
		
		CustBuyTicketUi u=new CustBuyTicketUi();
		u.displayBuyTicket(4);
	}
}
