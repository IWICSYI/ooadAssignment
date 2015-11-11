package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MovieListingControl;
import controllerClasses.ValidationControl;
import data.Movie;
import data.MovieSchedule;
import dataController.MovieScheduleDataControl;

public class CustDisplayMovieListingUi extends CustMain {

	/**
	 * Display movies that are now showing
	 * 
	 * @param plat
	 *            Platinum status
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayNowShowing(int plat) throws IOException,ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl
				.readScheduleListingBasedOnStatus(1, plat);
		ArrayList<Movie> movieList = MovieListingControl
				.filterUniqueMovieListFromSchedule(schList, plat);
		ArrayList<ObjectContainer> oList = new ArrayList<ObjectContainer>();
		String movieName = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Now Showing");
		System.out
				.println("------------------------------------------------------");
		if (schList.size() == 0) {
			System.out.println("No movie to be shown.");
		}

		for (int i = 0; i < schList.size(); i++) {
			ObjectContainer o = new ObjectContainer();
			o.setI((i + 1));
			o.setM(movieList.get(i));
			o.setMovieId(movieList.get(i).getMovieId());
			o.setMovieListing(schList.get(i));
			o.setListingId(schList.get(i).getListingId());

			oList.add(o);
			if(i%2==0)
			{
				System.out.print(String.format("%-20s",movieList.get(i).getMovieName())+"		");
			}else
			{
				System.out.print(String.format(movieList.get(i).getMovieName()));
			}
			movieName = "";
			if ((i + 1) % 2 == 0)
				System.out.println();
		}
		System.out.println();
		System.out
				.println("------------------------------------------------------");

		int choice = 0;
		do {
			System.out.println("1.View Movie Details");
			System.out.println("2.Display Preview");
			System.out.println("3.Display Coming soon");
			System.out.println("4.Back to main page");

			String s = sc.nextLine();
			choice = ValidationControl.validateAndReturnIntegerValue(s);
		} while (choice <= 0 || choice > 4);

		if (choice == 1) {
			CustMovieDetailUi c = new CustMovieDetailUi();
			c.displayNowShowingMovieDetailsSelection(oList, plat, "now");
		} else if (choice == 2) {
			displayPreview(plat);
		} else if (choice == 3) {
			displayComingSoon(plat);
		} else if (choice == 4) {
			displayCustMain();
		}

	}

	/**
	 * Display movies that are coming soon
	 * 
	 * @param plat
	 *            Platinum status
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayComingSoon(int plat) throws IOException,ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl
				.readScheduleListingBasedOnStatus(3, plat);
		ArrayList<Movie> movieList = MovieListingControl
				.filterUniqueMovieListFromSchedule(schList, plat);
		Scanner sc = new Scanner(System.in);
		ArrayList<ObjectContainer> oList = new ArrayList<ObjectContainer>();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Coming soon");
		System.out
				.println("------------------------------------------------------");

		if (movieList.size() == 0) {
			System.out.println("No movie to be shown.");
		}

		for (int i = 0; i < movieList.size(); i++) {
			ObjectContainer o = new ObjectContainer();
			o.setI((i + 1));
			o.setM(movieList.get(i));
			o.setMovieId(movieList.get(i).getMovieId());
			o.setMovieListing(schList.get(i));
			o.setListingId(schList.get(i).getListingId());
			oList.add(o);

			System.out.println("Movie Name:" + movieList.get(i).getMovieName()
					+ "	");
			System.out.println("Release Date:"
					+ sdf.format(schList.get(i).getStartDate()));
			System.out.println();
		}

		System.out
				.println("------------------------------------------------------");

		int choice = 0;
		do {
			System.out.println("1.View Movie Details");
			System.out.println("2.Display Now Showing");
			System.out.println("3.Display Preview");
			System.out.println("4.Back to main page");

			String s = sc.nextLine();
			choice = ValidationControl.validateAndReturnIntegerValue(s);
		} while (choice <= 0 || choice > 4);

		if (choice == 1) {
			CustMovieDetailUi c = new CustMovieDetailUi();
			c.displayNowShowingMovieDetailsSelection(oList, plat, "ComingSoon");
		} else if (choice == 2) {
			displayNowShowing(plat);
		} else if (choice == 3) {
			displayPreview(plat);
		} else if (choice == 4) {
			displayCustMain();
		}

	}

	/**
	 * Display movies that are on preview
	 * 
	 * @param plat
	 *            Platinum status
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayPreview(int plat) throws IOException,ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		int type = 1;
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl.readScheduleListingBasedOnStatus(2, plat);
		ArrayList<Movie> movieList = MovieListingControl.filterUniqueMovieListFromSchedule(schList, plat);
		ArrayList<ObjectContainer> oList = new ArrayList<ObjectContainer>();

		Scanner sc = new Scanner(System.in);
		System.out.println("Preview");
		System.out
				.println("------------------------------------------------------");
		if (schList.size() == 0) {
			System.out.println("No movie to be shown.");
		}

		for (int i = 0; i < schList.size(); i++) {
			ObjectContainer o = new ObjectContainer();
			o.setI((i + 1));
			o.setM(movieList.get(i));
			o.setMovieId(movieList.get(i).getMovieId());
			o.setMovieListing(schList.get(i));
			o.setListingId(schList.get(i).getListingId());
			oList.add(o);
			if(i%2==0)
			{
				System.out.print(String.format("%-20s",movieList.get(i).getMovieName())+"		");
			}else
			{
				System.out.print(String.format(movieList.get(i).getMovieName()));
			}
			if (i == 2)
				System.out.println();
		}
		System.out.println();
		System.out
				.println("------------------------------------------------------");
		int choice = 0;
		do {
			System.out.println("1.View Movie Details");
			System.out.println("2.Display Now Showing");
			System.out.println("3.Display Coming soon");
			System.out.println("4.Back to main page");

			String s = sc.nextLine();
			choice = ValidationControl.validateAndReturnIntegerValue(s);
		} while (choice <= 0 || choice > 4);
		if (choice == 1) {
			CustMovieDetailUi c = new CustMovieDetailUi();
			c.displayNowShowingMovieDetailsSelection(oList, plat, "preview");
		} else if (choice == 2) {
			displayNowShowing(plat);
		} else if (choice == 3) {
			displayComingSoon(plat);
		} else if (choice == 4) {
			displayCustMain();
		}

	}

}
