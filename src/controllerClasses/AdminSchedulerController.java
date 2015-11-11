package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Days;

import misc.ObjectContainer;
import boundaryClasses.AdminSchedulerCreateUi;
import boundaryClasses.AdminSchedulerUpdateUi;
import data.Movie;
import data.MovieSchedule;
import data.Transaction;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TransactionDataControl;

/**
 * Class that deals with formatting and controlling the UI for CRUD of schedule.
 * 
 * @author Chang En Kai
 *
 */
public class AdminSchedulerController {

	/**
	 * Check if schedule is duplicated during movie schedule creation, if it is,
	 * redirect to update page. The condition of duplicated is if the same movie
	 * has the same movie listing that are of now showing and coming soon
	 * status. * A movie listing is not duplicated if one of the same movie is
	 * showing on a platinum suite and one isn't. A movie listing is not
	 * duplicated if one of the same movie had already ended its showing. This
	 * is to make sure that cinema staff can rerun new showing after it a list
	 * has ended.
	 * 
	 * @param sch
	 *            Movie schedule to check if inputed schedule exist or not
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public boolean checkDuplicatedSchedule(MovieSchedule sch)throws IOException, ParseException {
		ArrayList<MovieSchedule> schList = MovieScheduleDataControl.readScheduleListing();
		int movie = 0, plat = 0, preview;
		Date startDate = new Date();
		Date endDate = new Date();
		for (int i = 0; i < schList.size(); i++) {
			startDate = schList.get(i).getStartDate();
			endDate = schList.get(i).getEndDate();
			movie = schList.get(i).getMovieId();
			plat = schList.get(i).getPlatOrNot();
			preview = schList.get(i).getPreviewStatus();
			if (movie == sch.getMovieId() && plat == sch.getPlatOrNot()&& preview == schList.get(i).getPreviewStatus()&& schList.get(i).getStatus() != 4) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Format forms to create movie schedule based on types such as now showing
	 * or coming soon
	 * 
	 * @param type
	 *            Movie listing type, can be now showing, coming soon and
	 *            preview
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void createScheduleGeneric(int type) throws IOException,
			ParseException {
		Scanner sc = new Scanner(System.in);
		int choice = 0, movieId, movieLen, movieType;

		MovieSchedule sch = new MovieSchedule();

		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList = MovieDataControl.readMovie();

		ArrayList<ObjectContainer> pair = new ArrayList<ObjectContainer>();

		do {
			System.out
					.println("Please select which movie to be scheduled(input -1 to return)");
			for (int i = 0; i < movieList.size(); i++) {
				System.out.print((i + 1) + ":"
						+ movieList.get(i).getMovieName() + " ");
				if ((i + 1) % 4 == 0 & i != 0) {
					System.out.println();
				}
				pair.add(MiscControl.idPairerWithMovieLength(i, movieList
						.get(i).getMovieId(),
						movieList.get(i).getMovieLength(), movieList.get(i)
								.getMovieType(), movieList.get(i)
								.getBlockbuster(), movieList.get(i)));
			}
			System.out.println();
			String s = sc.nextLine();
			choice = ValidationControl.validateAndReturnIntegerValue(s);
			if (choice > movieList.size()) {
				System.out.println("Invalid input, please try again.");
			} else if (s.equals("-1")) {
				AdminSchedulerCreateUi.displaySchedulerCreatePageMain();
			}

		} while (choice <= 0 || choice > (movieList.size()));

		movieId = pair.get(choice - 1).getId();
		sch.setMovieId(movieId);

		AdminSchedulerController sC = new AdminSchedulerController();

		Movie movie = pair.get(choice - 1).getM();
		movieLen = pair.get(choice - 1).getMovieLen();
		movieType = pair.get(choice - 1).getMovieType();
		int movieBlock = pair.get(choice - 1).getBlock();

		pair.clear();
		sch.setThreeDOrNot(movieType);

		sch.setBlockBuster(movieBlock);

		if (type == 1) {
			sch.setStatus(1);
		}

		if (type == 2) {
			sch.setPreviewStatus(1);
			sch.setStatus(2);
		} else {
			sch.setPreviewStatus(0);
		}
		if (type == 3) {
			sch.setStatus(3);
		}

		Calendar today = Calendar.getInstance();
		Date startDate;
		int choice4 = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String tmp = sdf.format(today.getTime());
		do {
			if (type == 1)
				sch.setStartDate(today.getTime());
			else if (type == 2) {
				System.out
						.println("Preview date that you are going to input must not be today or before today's date(input -1 to choose movie again).");
				System.out
						.println("Please enter preview date(Eg.25/10/2015) of the movie:");
				tmp = sc.nextLine();
				if (tmp.equals("-1")) {
					createScheduleGeneric(type);
				}
			} else if (type == 3) {
				System.out
						.println("Preview date that you are going to input must not be today or before today's date(input -1 to choose movie again).");
				System.out
						.println("Please enter comning soon date(Eg.25/10/2015) of the movie:");
				tmp = sc.nextLine();
				if (tmp.equals("-1")) {
					createScheduleGeneric(type);
				}
			}

			startDate = ValidationControl.validateDate(tmp, type);

		} while (startDate == null);

		sch.setStartDate(startDate);

		int runDate = -2;
		do {
			System.out.println("How many days will the movie be shown?");
			runDate = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
		} while (runDate <= 0);
		sch.setEndDate(TimeDateControl.calculateEndDate(startDate, runDate));

		int plat = 0;
		do {
			System.out.println("Is this listing for platinum suite?");
			System.out.println("1.No");
			System.out.println("2.Yes");
			plat = ValidationControl.validateYesNoAndReturnIntegerValue(sc
					.nextLine());
		} while (plat <= 0);

		sch.setPlatOrNot(plat - 1);

		boolean duplicate = sC.checkDuplicatedSchedule(sch);
		int dChoice = 0;
		if (duplicate) {
			do {
				System.out
						.println("Listing for this movie already found, do you want to update it?");
				System.out
						.println("If you choose no, you need to rechoose another movie again.");
				System.out.println("1.Yes");
				System.out.println("2.No");
				String s = sc.nextLine();
				dChoice = ValidationControl
						.validateYesNoAndReturnIntegerValue(s);
				if (dChoice == 1) {
					AdminSchedulerUpdateUi.displayUpdateMain();
					return;
				} else if (dChoice == 2) {
					createScheduleGeneric(type);
					return;
				}
			} while (dChoice <= 0);

		}

		int schid = 0;
		ArrayList<MovieSchedule> sch2 = new ArrayList<MovieSchedule>();
		sch2 = MovieScheduleDataControl.readScheduleListing();

		if (!sch2.isEmpty()) {
			schid = sch2.get(sch2.size() - 1).getListingId() + 1;
		} else {
			schid = 1;
		}
		sch.setListingId(schid);
		AdminShowTimeController.timeSlotHandler("c", 0, sch, movieId, movieLen,
				movieType, runDate, movie);

	}

	/**
	 * Method to edit start date
	 * 
	 * @param movieSchedule
	 * @param m
	 *            Movie details
	 * @param cineId
	 *            cineplex id
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void editStartDate(MovieSchedule sch, Movie m, int cineId)
			throws IOException, ParseException {
		MovieSchedule newsch = sch;
		Date newDate;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Enter new start date(input -1 to return):");
			String s = sc.nextLine();
			newDate = ValidationControl.validateDate(s, 0);
			if (s.equals("-1")) {
				AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
			}

		} while (newDate == null);

		Date esDate = sch.getStartDate();
		Date edDate = sch.getEndDate();
		if (newDate.equals(esDate)) {
			System.out
					.println("Cannot enter same start date! Returning to previous page!");
			AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
			return;
		}

		else if (newDate.after(edDate)) {
			System.out
					.println("Start date cannot be after end date! Returning to previous page!");
			AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);

			return;
		}

		if (newDate.after(esDate)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(newDate);
			boolean canRemove = ShowTimeDataControl
					.removeShowTimeWhenEditStartDate(sch.getListingId(), cal);
			newsch.setStartDate(newDate);
			if (canRemove) {
				MovieScheduleDataControl.updateSchedule(newsch);
			} else
				AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
		} else {

			newsch.setStartDate(newDate);
			MovieScheduleDataControl.updateSchedule(newsch);
			DateTime dt1 = new DateTime(newDate);
			DateTime dt2 = new DateTime(esDate);

			int extend = Days.daysBetween(dt1, dt2).getDays();
			AdminShowTimeController.timeSlotHandler("u", 0, sch,
					m.getMovieId(), m.getMovieLength(), m.getMovieType(),
					extend, m);

		}

		AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
	}

	/**
	 * Edit ending date of movie listing
	 * 
	 * @param sch
	 *            Movie schedule
	 * @param m
	 *            movie details
	 * @param cineId
	 *            cineplex ID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void editEndDate(MovieSchedule sch, Movie m, int cineId)
			throws IOException, ParseException {
		MovieSchedule newsch = sch;
		MovieSchedule tmpsch = sch;
		Date newDate;

		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Enter new end date(input -1 to return):");
			String s = sc.nextLine();
			newDate = ValidationControl.validateDate(s, 0);
			if (s.equals("-1")) {
				AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
			}

		} while (newDate == null);

		Date edDate = sch.getEndDate();
		Date esDate = sch.getStartDate();
		newDate.setHours(23);
		newDate.setMinutes(59);

		if (newDate.before(esDate)) {
			System.out
					.println("End date cannot be before start date! Returning to previous page!");
			AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);

		}

		else if (newDate.equals(edDate)) {
			System.out
					.println("Cannot enter same end date! Returning to previous page!");
			AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);

		}

		if (newDate.before(edDate)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(newDate);
			boolean canRemove = ShowTimeDataControl.removeShowTimeWhenEditEndDate(sch.getListingId(), cal);
			newsch.setEndDate(newDate);
			if (canRemove)
				MovieScheduleDataControl.updateSchedule(newsch);
			else
				AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);
		} 
		else {

			newsch.setEndDate(newDate);
			MovieScheduleDataControl.updateSchedule(newsch);
			DateTime dt1 = new DateTime(newDate);
			DateTime dt2 = new DateTime(edDate);
			DateTime dt3 = new DateTime(edDate);
			dt2.plusMinutes(30);
			int extend = Days.daysBetween(dt2, dt1).getDays();
			tmpsch.setStartDate(dt3.plusDays(1).toDate());
			AdminShowTimeController.timeSlotHandler("u", 0, tmpsch,
					m.getMovieId(), m.getMovieLength(), m.getMovieType(),
					extend, m);

		}

		AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);

	}

	/**
	 * Method to handle the form of editing specific timeslot
	 * 
	 * @param pairingIdWithSlot
	 *            a list of object that contains movie, schedule, show time and
	 *            number that associate with them.
	 * @param pair
	 *            required arguement to return back to previous page
	 * @param choice
	 *            User selection
	 * @param sch
	 *            Movie schedule
	 * @param m
	 *            Movie detail
	 * @param cineId
	 *            cineplex ID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void handleEditTimeSlot(
			ArrayList<ObjectContainer> pairingIdWithSlot,
			ArrayList<ObjectContainer> pair, int choice, MovieSchedule sch,
			Movie m, int cineId) throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		int choose = 0;
		do {
			System.out
					.println("Select number beside timeslot to edit(input -1 to return):");
			for (int i = 0; i < pairingIdWithSlot.size(); i++) {
				System.out.print((i + 1) + ":"
						+ pairingIdWithSlot.get(i).getTimeValue() + "	");
			}
			System.out.println();
			String s = sc.nextLine();
			choose = ValidationControl.validateAndReturnIntegerValue(s);
			if (s.equals("-1")) {
				AdminSchedulerUpdateUi.displayUpdatePageDetails(sch, m, cineId);

			}

		} while (choose <= 0 || choose > pairingIdWithSlot.size());

		int showTimeId = pairingIdWithSlot.get(choose - 1).getShowTimeId();
		int cinemaId = pairingIdWithSlot.get(choose - 1).getsT().getCinemaId();

		boolean clash = false;
		ArrayList<Transaction> tList = TransactionDataControl.readTranscation();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).getShowtimeId() == showTimeId) {
				System.out
						.println("Cannot edit timeslot because transcation already exist!");
				clash = true;
				break;
			}
		}
		if (!clash) {
			Boolean valid2 = false;
			int time = 0;
			do {
				String dateValue = pairingIdWithSlot.get(choose - 1).getDate();
				String intTime = pairingIdWithSlot.get(choose - 1)
						.getTimeValue();
				Calendar temp = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				temp.setTime(sdf.parse(dateValue));

				System.out
						.println("This cinema already have these time slot allocated on "
								+ sdf.format(temp.getTime()));
				ArrayList<String> allocatedShowTime = AdminShowTimeController
						.cinemaallocatedTime(cinemaId, temp);
				for (int l = 0; l < allocatedShowTime.size(); l++) {
					System.out.print(allocatedShowTime.get(l) + "	");

				}
				System.out.println();
				System.out.println("Enter edited timeslot:");
				String s = sc.nextLine();
				time = ValidationControl.validateAndReturnTime(s);
				if (time != -2) {
					valid2 = AdminShowTimeController
							.checkTimeSlotClashforUpdate(m.getMovieLength(),
									cinemaId, s, intTime, temp);
				}
				if (valid2 && time != -2) {
					String showTimeValue = TimeDateControl.minutesPlusTime(
							m.getMovieLength(), s);
					ShowTimeDataControl.updateShowTime(showTimeId,
							showTimeValue);

				}
			} while (!valid2 || time <= -2);

		} else {
			AdminSchedulerUpdateUi.displayUpdateTimeSlot(pair, choice, sch, m,
					cineId);
		}
	}

}
