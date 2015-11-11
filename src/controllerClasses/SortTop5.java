package controllerClasses;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import data.Movie;
import data.MovieSchedule;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;

/**
 * Class that deals with ranking of the movies based on different criteria
 * 
 * @author Chang En Kai
 *
 */
public class SortTop5 implements Comparable<Movie> {
	// Sort movies by top 5 score in descending order in array and print

	/**
	 * Rank movies based on top score for admin, does not care if movie has
	 * ended already.
	 * 
	 * @throws IOException
	 */
	public static void sortTopScoreForAdmin() throws IOException {
		List<Movie> movie = new ArrayList<Movie>();
		int i = 0;
		// Fill it.
		movie = MovieDataControl.readMovie();

		Collections.sort(movie, new Comparator<Movie>() {
			@Override
			public int compare(Movie p1, Movie p2) {
				return Double.compare(p1.getOverallRating(),
						p2.getOverallRating());
			}
		});
		int rank=1;
		Collections.reverse(movie);
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out
				.println("-----------------------------------------------------------------");
		System.out
				.println("|These are the top rated movies throughout the entire history:   |");
		System.out
				.println("-----------------------------------------------------------------");
		for (i = 0; i <= movie.size(); i++) {
			if (movie.get(i).getOverallRating() <= 0) {

			} 
			else{
				System.out.print(rank+".Movie Title:" + movie.get(i).getMovieName()
						+ " Movie Average Score:"
						+ formatter.format(movie.get(i).getOverallRating()) + "\n");
				rank++;
			}
			if (i == 5)
				break;
		}
		System.out
				.println("-----------------------------------------------------------------");
	}

	/**
	 * Rank movie based on rating for customer. Will not display movies that
	 * already ended showing
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void sortTopScoreForCustomer() throws IOException,
			ParseException {
		List<Movie> movie = new ArrayList<Movie>();
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl
				.readScheduleListing();

		int i = 0;
		// Fill it.
		movie = MovieDataControl.readMovie();

		Collections.sort(movie, new Comparator<Movie>() {
			@Override
			public int compare(Movie p1, Movie p2) {
				return Double.compare(p1.getOverallRating(),
						p2.getOverallRating());
			}
		});
		int rank=1;
		Collections.reverse(movie);
		System.out
				.println("---------------------------------------------------");
		System.out
				.println("|These are the top rated movies that are showing!!!|");
		System.out
				.println("----------------------------------------------------");
		NumberFormat formatter = new DecimalFormat("#0.00");
		for (i = 0; i <= movie.size(); i++) {
			if (movie.get(i).getOverallRating() <= 0) {

			} else {
				for (int j = 0; j < schList.size(); j++) {
					if (schList.get(j).getMovieId() == movie.get(i).getMovieId() && schList.get(i).getStatus() == 1)
					{
						System.out.print(rank + ".Movie Title:"	+ movie.get(i).getMovieName()
								+ " Movie Average Score:"+ formatter.format(movie.get(i).getOverallRating()) + "\n");
						rank++;
						break;
					}
				}
			}
			if (i == 5)
				break;
		}
		System.out
				.println("----------------------------------------------------");

	}

	/**
	 * Rank movie based on top sales for admin. Does not care if movie already
	 * ended its showing.
	 * 
	 * @throws IOException
	 */
	public static void sortTopSalesForAdmin() throws IOException {
		List<Movie> movie = new ArrayList<Movie>();

		int i = 0;
		// Fill it.
		movie = MovieDataControl.readMovie();

		Collections.sort(movie, new Comparator<Movie>() {
			@Override
			public int compare(Movie p1, Movie p2) {
				return Double.compare(p1.getTicketSales(), p2.getTicketSales());
			}
		});
		int rank=1;
		Collections.reverse(movie);
		System.out
				.println("-----------------------------------------------------------------");

		System.out
				.println("|These are the best selling movies throughout the entire history:|");
		System.out
				.println("------------------------------------------------------------------");

		for (i = 0; i <= movie.size(); i++) {
			if (movie.get(i).getTicketSales() <= 0) {

			} 
			else{
				System.out.print(rank+"Movie Title:" + movie.get(i).getMovieName()
						+ " Movie Sales:" + movie.get(i).getTicketSales()
						+ "\n");
				rank++;
			}
			if (i == 5)
				break;
		}
		System.out
				.println("------------------------------------------------------------------");
	}

	/**
	 * Rank movie based of top sale, for customer use only. Will not rank movies
	 * that are not showing.
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void sortTopSalesForCustomer() throws IOException,
			ParseException {
		List<Movie> movie = new ArrayList<Movie>();
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl
				.readScheduleListing();

		int i = 0;
		// Fill it.
		movie = MovieDataControl.readMovie();

		Collections.sort(movie, new Comparator<Movie>() {
			@Override
			public int compare(Movie p1, Movie p2) {
				return Double.compare(p1.getTicketSales(), p2.getTicketSales());
			}
		});

		Collections.reverse(movie);
	
		System.out
				.println("------------------------------------------------------");

		System.out
				.println("|These are the best selling movies that are showing!!!|");
		System.out
				.println("------------------------------------------------------");

		int rank=1;
		for (i = 0; i <= movie.size(); i++) {
			if (movie.get(i).getTicketSales() <= 0) {

			} 
			else {
				for (int j = 0; j < schList.size(); j++) {
					if (schList.get(j).getMovieId() == movie.get(i).getMovieId() && schList.get(j).getStatus() == 1)
					{
						System.out.print(rank + ".Movie Title:"+ movie.get(i).getMovieName() + "\n");
						rank++;
						break;
					}
				}
			}
			if (i == 5)
				break;
		}
		System.out
				.println("------------------------------------------------------");

	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return 0;
	}
}