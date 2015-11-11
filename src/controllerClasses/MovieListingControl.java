package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import misc.ObjectContainer;
import data.Cineplex;
import data.HolidayDate;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CineplexDataControl;
import dataController.DataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TicketPriceAndHolidayDataControl;

/**
 * Control class used by both the customer and admin side of thing to deal with
 * the dynamic formating of show time based on today's date.
 * 
 * @author Chang En Kai
 *
 */
public class MovieListingControl {

	/**
	 * 
	 * Filter out all the movies details in a list of movie schedule
	 * 
	 * @param schList
	 *            Movie schedule list
	 * @param plat
	 *            platinum status
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<Movie> filterUniqueMovieListFromSchedule(
			ArrayList<MovieSchedule> schList, int plat) throws IOException,
			ParseException {
		ArrayList<Integer> movieIdList = new ArrayList<Integer>();

		for (int i = 0; i < schList.size(); i++) {
			if (schList.get(i).getPlatOrNot() == plat)

			{
				movieIdList.add(schList.get(i).getMovieId());
			}
		}
		// System.out.println(uniqueMovieIdList.get(1));
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		for (int i = 0; i < movieIdList.size(); i++) {
			movieList.add(MovieDataControl.readMovieBasedOnId(movieIdList
					.get(i)));
		}

		return movieList;

	}

	/**
	 * 
	 * Format show time of movies depending on type. It's use for selecting
	 * movie time slot to purchase and to edit specific timeslot
	 * 
	 * @param listingid
	 * @param cineplexid
	 * @param movieid
	 * @param type
	 * @param max
	 *            Max to check how many time a for loop will loop
	 * @param movieDetails
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */

	public static ArrayList<ObjectContainer> formatShowtimeforListing(
			int listingid, int cineplexid, int movieid, String type, int max,
			Movie movieDetails) throws IOException, ParseException {
		MovieSchedule schList = new MovieSchedule();
		MovieSchedule ms = new MovieSchedule();
		ArrayList<ShowTime> stListUpdate = new ArrayList<ShowTime>();
		if (type.equals("now")) {
			schList = MovieScheduleDataControl
					.readScheduleListingBasedOnListingId(listingid);
		} else if (type.equals("update") || type.equals("preview")) {
			if (cineplexid == 0) {
				cineplexid = CustBuyTicketControl.chooseCineplexToDisplay(
						movieid, listingid);
			}
			ms = MovieScheduleDataControl
					.readScheduleListingBasedOnListingId(listingid);

			stListUpdate = ShowTimeDataControl
					.readShowTimesBasedOnListingId(listingid);
		}
		ArrayList<ObjectContainer> pair = new ArrayList<ObjectContainer>();

		String cineplexName = "";
		ArrayList<Cineplex> cnList = CineplexDataControl.readCineplex();
		for (int i = 0; i < cnList.size(); i++) {
			if (cineplexid == cnList.get(i).getCinplexId()) {
				cineplexName = cnList.get(i).getCineplexName();
			}
		}

		// intialize variables needed to display time slot in dynamic way
		ArrayList<ShowTime> showTimeList = new ArrayList<ShowTime>();
		ArrayList<HolidayDate> hDList = TicketPriceAndHolidayDataControl
				.readHoliday();
		ArrayList<String> showTimeArray = new ArrayList<String>();

		// initialize date variables to get 7 days worth of timeslot (to Do
		// list, need to read holiday!!!)
		int dayType = 0;
		boolean crud = false;
		boolean holiday = false;
		int allowance = 0;

		Calendar cal = Calendar.getInstance();
		Calendar calTemp = (Calendar) cal.clone();
		if (type.equals("update") || type.equals("preview")) {
			calTemp.setTime(ms.getStartDate());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out
				.println("------------------------------------------------------");

		System.out.println("Time slot for " + movieDetails.getMovieName()
				+ " in " + cineplexName);
		System.out
				.println("Date                     |TimeSlot                     ");
		System.out
				.println("------------------------------------------------------");
		for (int i = 1; i <= max; i++) {

			showTimeList = ShowTimeDataControl
					.readShowTimesBasedOnListingIdAndCineplexId(listingid,
							calTemp, cineplexid);

			for (int h = 0; h < hDList.size(); h++) {
				String d1 = sdf.format(calTemp.getTime());
				String d2 = sdf.format(hDList.get(h).getHolidayDate());
				if (d1.equals(d2)) {
					holiday = true;
				} else
					holiday = false;
			}
			if (!holiday) {
				System.out.print(TimeDateControl.retrieveDaySpell(calTemp)
						+ "|");
			} else {
				System.out.print(TimeDateControl.retrieveDaySpell(calTemp)
						+ " (Holiday)|");
			}
			dayType = TimeDateControl.dayType(calTemp.getTime());
			MiscControl mc = new MiscControl();
			if (type.equals("now")) {
				crud = !showTimeList.isEmpty();
			} else if (type.equals("update") || type.equals("preview")) {
				crud = true;
			}
			if (crud) {
				ObjectContainer o = new ObjectContainer();
				ArrayList<ShowTime> showTimeListTemp = new ArrayList<ShowTime>();
				for (int p = 0; p < showTimeList.size(); p++) {

					showTimeArray.add(showTimeList.get(p).getShowTimeValue());
					ShowTime s = showTimeList.get(p);
					showTimeListTemp.add(s);
				}
				o.setShowTimeList(showTimeListTemp);
				o.setI(i);
				o.setCineplexId(cineplexid);
				o.setDate(sdf.format(calTemp.getTime()));
				pair.add(o);

				// showTimeListTemp.clear();
			}

			if (!showTimeArray.isEmpty()) {
				for (int j = 0; j < showTimeArray.size(); j++) {
					mc.setStringList(showTimeArray);
					mc.sort();
					// showTimeArray.clear();
					// showTimeArray=mc.getStringList();
					System.out.print(showTimeArray.get(j) + " ");
				}
				showTimeArray.clear();

				System.out.println();

				calTemp.add(calTemp.DATE, 1);

			} else {
				System.out.print("No timeslot ");
				System.out.println();
				calTemp.add(calTemp.DATE, 1);
				allowance++;
				if (type.equals("now") && allowance >= 2) {
					max = 0;
				}

			}
		}
		System.out
				.println("------------------------------------------------------");
		return pair;

	}

}
