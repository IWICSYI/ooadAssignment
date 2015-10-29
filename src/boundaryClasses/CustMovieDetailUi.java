package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.ValidationControl;
import data.Movie;
import data.MovieSchedule;
import dataController.MovieDataControl;

public class CustMovieDetailUi {
	
	public void displayNowShowingMovieDetailsSelection(ArrayList<ObjectContainer> oList) throws IOException, ParseException
	{
		//ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Now Showing");
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
		
		System.out.println("1.Select number beside movie to view details");
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		displayMovieDetails(oList.get(choice-1).getM().getMovieId());
	}
	
	
	public void displayMovieDetails(int movieId) throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		//ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedOnStartingDate();
		Movie movieDetails=MovieDataControl.readMovieBasedOnId(movieId);
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Movie Name:"+movieDetails.getMovieName()+" ");
		System.out.println("1.Read Review");
		System.out.println("2.Buy Ticket");
		System.out.println("3.Go Back");
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		if(choice==1){
			System.out.println("Haven't implement yet");//havent done yet
		}
		if(choice==2){
			CustBuyTicketUi u=new CustBuyTicketUi();
			u.displayBuyTicket(movieId,0,0,movieDetails);
		}
		if(choice==3){
			CustomerDisplayMovieListingUi ui=new CustomerDisplayMovieListingUi();
			ui.displayNowShowing();
		}
	}
}
