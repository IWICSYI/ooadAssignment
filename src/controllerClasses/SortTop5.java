package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data.Movie;
import dataController.MovieDataControl;

  public class SortTop5 implements Comparable<Movie>{
	//Sort movies by top 5 score in descending order in array and print
	
	  
	  
	  
	  
	  public static void sortTopScore() throws IOException{
		List<Movie> movie = new ArrayList<Movie>();
		int i=0;
		// Fill it.
		movie=MovieDataControl.readMovie();
	
		Collections.sort(movie, new Comparator<Movie>() {
			@Override public int compare(Movie p1, Movie p2) {
		    	return Double.compare(p1.getOverallRating(),p2.getOverallRating());
		    }
		 });
		
		Collections.reverse(movie);
		for(i=0;i<=movie.size();i++){
			if(movie.get(i).getOverallRating()<=0)
			{
				
			}
			else
				System.out.print("Movie Title:"+movie.get(i).getMovieName()+" Movie Average Score:"+movie.get(i).getOverallRating()+"\n");
		
			if(i==5)
				break;
		}
	}
	//Sort movies by top 5 ticket sales in descending order in array and print
	public static void sortTopSales() throws IOException{
		List<Movie> movie = new ArrayList<Movie>();
		int i=0;
		// Fill it.
		movie=MovieDataControl.readMovie();
		
		Collections.sort(movie, new Comparator<Movie>() {
			@Override public int compare(Movie p1, Movie p2) {
			    return Double.compare(p1.getTicketSales(),p2.getTicketSales());
			 }
		});
			
		Collections.reverse(movie);
		for(i=0;i<=movie.size();i++){
			if(movie.get(i).getTicketSales()<0)
			{
				
			}
			else
				System.out.print("Movie Title:"+movie.get(i).getMovieName()+" Movie Sales:"+movie.get(i).getTicketSales()+"\n");
			if(i==5)
				break;
		}

    }

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return 0;
	}
}