package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;

import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.Transaction;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.TransactionDataControl;

public class CustTransactionControl {

	public static boolean searchTransaction(String email) throws IOException{
		
		ArrayList<Transaction> tList = TransactionDataControl.readTranscation();
		boolean exist=false;
		int count=1;
		for(int i=0;i<tList.size();i++)
		{
			if(email.equals(tList.get(i).getEmail())){
				System.out.println("\nBooking no."+count);
				count++;
				Movie m=MovieDataControl.readMovieBasedOnId(tList.get(i).getMovieId());
				Cineplex c=CineplexDataControl.readCineplexBasedonCinplexId(tList.get(i).getCineplexId());
				Cinema ch=CinemaDataControl.readCinemaByCinemaId(tList.get(i).getCinemaId());
				System.out.println("Transcation Id:"+tList.get(i).getTranscationId());
				System.out.println("Customer Name:"+tList.get(i).getCustName());
				System.out.println("Mobile Phone:"+tList.get(i).getMobilePhone());
				System.out.println("Email:"+tList.get(i).getEmail());
				System.out.println("Movie Name:"+m.getMovieName());
				System.out.println("Cineplex:"+c.getCineplexName());
				System.out.println("Cinema hall:"+ch.getCinemaHallName());
				System.out.println("Seats:"+tList.get(i).getSeats());
				System.out.println("Total Price:"+(tList.get(i).getPrice()*tList.get(i).getNumSeat()));
				exist=true;
			}
			System.out.println();
		}
		return exist;
		
	}
	
}
