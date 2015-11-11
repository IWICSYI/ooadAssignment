package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.AdminSchedulerController;
import controllerClasses.AdminShowTimeController;
import controllerClasses.MovieListingControl;
import controllerClasses.TimeDateControl;
import controllerClasses.ValidationControl;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.Transaction;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;
import dataController.TransactionDataControl;

public class AdminSchedulerUpdateUi extends AdminSchedulerMainUi {

	/**
	 * Display schedule update main page for user to select which listing to
	 * edit
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayUpdateMain() throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		System.out.println("#############################################");
		System.out.println("#            Schedule Update Page           #");
		System.out.println("#############################################");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		ArrayList<ObjectContainer> pair = new ArrayList<ObjectContainer>();

		ArrayList<MovieSchedule> schList = MovieScheduleDataControl
				.readScheduleListing();
		ArrayList<Movie> movieList = MovieDataControl.readMovie();
		String status = null;
		Calendar cal = Calendar.getInstance();
		Date eD;
		String endDate = "";

		System.out
				.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out
				.println("|S/N|	|Movie Name|                	     	|Status|		|Starting Date|		|Ending Date|		|Platinum/Normal|");
		for (int i = 0; i < schList.size(); i++) {
			for (int j = 0; j < movieList.size(); j++) {
				if (schList.get(i).getMovieId() == movieList.get(j)
						.getMovieId()) {
					if (schList.get(i).getStatus() == 1) {
						status = "Now Showing";
					} else if (schList.get(i).getStatus() == 2) {
						status = "Preview    ";
					} else if (schList.get(i).getStatus() == 3) {
						status = "Coming Soon";
					} else if (schList.get(i).getStatus() == 4) {
						status = "End Showing";
					}

					String plati = "";

					int plat = schList.get(i).getPlatOrNot();

					if (plat == 1) {
						plati = "Platinum";
					} else {
						plati = "Normal";
					}
					String movieName = movieList.get(j).getMovieName();
					if (movieName.length() >= 23) {
						movieName = movieName.substring(0, 23);
						movieName = movieName + "......";
					}

					cal.setTime(schList.get(i).getEndDate());
					cal.add(Calendar.DATE, -1);

					System.out.format("%3d", (i + 1));
					System.out.print("	");
					System.out.printf("%-25s", movieName);
					System.out.print("		");
					System.out.format("%-8s", status);
					System.out.print("		");
					System.out.format("%-10s",
							sdf.format(schList.get(i).getStartDate()));
					System.out.print("		");
					System.out.format("%-10s",
							sdf.format(schList.get(i).getEndDate()));
					System.out.print("		");
					System.out.format("%-8s", plati);
					System.out.println();
					ObjectContainer o = new ObjectContainer();
					o.setM(movieList.get(j));
					o.setI((i + 1));
					o.setMovieListing(schList.get(i));
					pair.add(o);
				}
			}
		}
		System.out
				.println("--------------------------------------------------------------------------------------------------------------------------------------------------");
		do {
			System.out
					.print("Select number beside the listing to edit (input 0 to retun back to previous page):");
			choice = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
			if (choice < 0 || choice > schList.size()) {
				System.out.println("Invalid choice, try again");
			}

		} while (choice < 0 || choice > schList.size());

		if (choice == 0) {
			displaySchedulerMain();

		}

		for (int i = 0; i < pair.size(); i++) {
			if (pair.get(i).getI() == choice) {
				displayUpdatePageDetails(pair.get(i).getMovieListing(), pair
						.get(i).getM(), 0);
			}
		}
	}

	/**
	 * display listing details that user choose from the displayUpdate main
	 * method.
	 * 
	 * @param sch
	 *            Passing down selected movie schedule from displayUpdate
	 * @param movie
	 *            passing down of selected movie details from display update
	 * @param cineId
	 *            cineplexID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayUpdatePageDetails(MovieSchedule sch, Movie movie,
			int cineId) throws IOException, ParseException {

		String eOrSListing = "";

		String status = "";

		if (sch.getStatus() == 1) {
			status = "Now Showing";
		} else if (sch.getStatus() == 2) {
			status = "Preview    ";
		} else if (sch.getStatus() == 3) {
			status = "Coming Soon";
		} else if (sch.getStatus() == 4) {
			status = "End Showing";
		}

		String plati = "";

		int plat = sch.getPlatOrNot();

		if (plat == 1) {
			plati = "Platinum";
		} else {
			plati = "Normal";
		}

		int choice = 0;

		System.out.println("-------------------------------------");
		System.out.println("Editing for:" + movie.getMovieName());
		System.out.println("Cinema suite:" + plati);
		System.out.println("Showing Status" + status);
		System.out.println("Start date:"
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(sch
						.getStartDate()));
		System.out.println("End date:"
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(sch
						.getEndDate()) + "\n");
		System.out.println("-------------------------------------");

		if (sch.getStatus() < 4) {
			eOrSListing = "1.End Listing";
		} else if (sch.getStatus() == 4) {
			eOrSListing = "1.Relist";
		}
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println(eOrSListing);
			System.out.println("2.Edit Start Date");
			System.out.println("3.Edit End Date");
			System.out.println("4.Edit Exsiting Timeslot");
			System.out.println("5.Rechoose listing");
			choice = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
		} while (choice <= 0 || choice > 5);

		if (choice == 1) {
			if (sch.getStatus() < 4) {
				MovieScheduleDataControl.endListing(sch);
			} else if (sch.getStatus() == 4) {
				MovieScheduleDataControl.updateReListStatus(sch);
				displayUpdateMain();
			}

		} else if (choice == 2) {
			AdminSchedulerController.editStartDate(sch, movie, cineId);
		} else if (choice == 3) {
			AdminSchedulerController.editEndDate(sch, movie, cineId);
		} else if (choice == 4) {

			displayEditExistingTimeSlot(sch, movie.getMovieId(), 0, movie);

		} else if (choice == 5) {
			displayUpdateMain();
		}

	}

	/**
	 * display page to choose which specific timeslot to edit
	 * 
	 * @param sch
	 *            Passing down selected movie schedule from displayUpdate
	 * @param movieId
	 *            Movie ID
	 * @param movie
	 *            passing down of selected movie details from display update
	 * @param cineId
	 *            cineplexID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayEditExistingTimeSlot(MovieSchedule movieSchedule,
			int movieId, int cineId, Movie movie) throws IOException,
			ParseException {
		Scanner sc = new Scanner(System.in);

		int run = TimeDateControl.calculateRunDate(
				movieSchedule.getStartDate(), movieSchedule.getEndDate()) + 1;

		int ucinI;
		int choice3;
		ArrayList<ObjectContainer> pair = MovieListingControl
				.formatShowtimeforListing(movieSchedule.getListingId(), cineId,
						movie.getMovieId(), "update", run, movie);
		cineId = pair.get(0).getCineplexId();
		ArrayList<Cineplex> cineList = CineplexDataControl.readCineplex();
		do {
			System.out.println("1.Switch Cineplex");
			System.out.println("2.Edit timeslot");
			System.out.println("3.Go back");
			choice3 = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
		} while (choice3 <= 0 || choice3 > 3);
		if (choice3 == 1) {
			do {
				for (int i = 0; i < cineList.size(); i++) {
					System.out.println((i + 1) + ":"
							+ cineList.get(i).getCineplexName());
				}
				ucinI = ValidationControl.validateAndReturnIntegerValue(sc
						.nextLine());
				if (ucinI <= 0) {
					System.out.println("Invalid input!");
				}
			} while (ucinI <= 0);
			displayEditExistingTimeSlot(movieSchedule, movieId, ucinI, movie);
		} else if (choice3 == 2) {
			int select = 0;
			do {
				System.out
						.println("Select number beside date to edit timeslot of that date.");
				for (int i = 0; i < pair.size(); i++) {
					System.out.print((i + 1) + ":" + pair.get(i).getDate()
							+ "		");
					if ((i + 1) % 2 == 0) {
						System.out.println();
					}
				}
				System.out.println();
				select = ValidationControl.validateAndReturnIntegerValue(sc
						.nextLine());

				if (select <= 0 || select > pair.size()) {
					System.out.println("Invalid input!");
				}

			} while (select <= 0 || select > pair.size());
			displayUpdateTimeSlot(pair, select, movieSchedule, movie, cineId);

		} else if (choice3 == 3) {
			displayUpdatePageDetails(movieSchedule, movie, 0);
		}

	}

	/**
	 * Display timeslots detail choosen during displayEditExistingTimeSlot()
	 * method.
	 * 
	 * @param pair
	 *            A list of object that contains movie schedule, movie show time
	 *            and movie details
	 * @param choice
	 *            User selection
	 * @param movieSchedule
	 * @param movie
	 * @param cineId
	 *            cineplex ID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayUpdateTimeSlot(ArrayList<ObjectContainer> pair,
			int choice, MovieSchedule movieSchedule, Movie movie, int cineId)
			throws IOException, ParseException {
		ArrayList<ObjectContainer> pairingIdWithSlot = new ArrayList<ObjectContainer>();
		String dateChoosen = "";
		for (int i = 0; i < pair.size(); i++) {
			if (pair.get(i).getI() == choice) {
				dateChoosen = pair.get(i).getDate();
				for (int j = 0; j < pair.get(i).getShowTimeList().size(); j++) {
					ObjectContainer o = new ObjectContainer();
					o.setI(i + 1);
					o.setShowTimeId(pair.get(i).getShowTimeList().get(j)
							.getShowTimeId());
					o.setTimeValue(pair.get(i).getShowTimeList().get(j)
							.getShowTimeValue());
					o.setsT(pair.get(i).getShowTimeList().get(j));
					o.setDate(pair.get(i).getDate());
					pairingIdWithSlot.add(o);
				}
			}
		}

		Collections.sort(pairingIdWithSlot, new Comparator<ObjectContainer>() {
			@Override
			public int compare(ObjectContainer p1, ObjectContainer p2) {
				return p1.getTimeValue().compareTo(p2.getTimeValue());
			}

		});
		// System.out.println("Date choosen:"+pairingIdWithSlot.get(0).getDate());
		for (int i = 0; i < pairingIdWithSlot.size(); i++) {
			System.out.print(pairingIdWithSlot.get(i).getTimeValue() + "	");
		}
		int choice2;
		int check = 0;
		String s2 = "";
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println();
			System.out.println("1.Go Back");
			System.out.println("2.Add timeslot");
			if (pairingIdWithSlot.size() != 0) {
				System.out.println("3.Edit timeslot");
				System.out.println("4.Remove timeslot");
				check = 4;
			} else
				check = 2;

			s2 = sc.nextLine();
			choice2 = ValidationControl.validateAndReturnIntegerValue(s2);
		} while (choice2 <= 0 || choice2 > check);

		int showTimeId = 0;
		if (choice2 == 1) {
			displayEditExistingTimeSlot(movieSchedule, movie.getMovieId(),
					cineId, movie);
		}
		if (choice2 == 2) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			MovieSchedule tmpSch = movieSchedule;
			tmpSch.setStartDate(sdf.parse(dateChoosen));
			AdminShowTimeController.timeSlotHandler("add", cineId, tmpSch,
					movie.getMovieId(), movie.getMovieLength(),
					movie.getMovieType(), 1, movie);
		}

		if (choice2 == 3 || choice2 == 4) {
			if (choice2 == 3) {
				AdminSchedulerController.handleEditTimeSlot(pairingIdWithSlot,
						pair, choice, movieSchedule, movie, cineId);
			} else if (choice2 == 4) {
				int remove = 0;
				do {
					System.out
							.println("Select number to remove timeslot(input -1 to return).");
					for (int i = 0; i < pairingIdWithSlot.size(); i++) {
						System.out
								.print((i + 1)
										+ ":"
										+ pairingIdWithSlot.get(i)
												.getTimeValue() + "	");
					}
					System.out.println();
					String s = sc.nextLine();
					remove = ValidationControl.validateAndReturnIntegerValue(s);
					if (s.equals("-1")) {
						displayUpdateTimeSlot(pair, choice, movieSchedule,
								movie, cineId);
					}
				} while (remove <= 0 || remove > pairingIdWithSlot.size());

				showTimeId = pairingIdWithSlot.get(remove - 1).getShowTimeId();
				boolean tClash = false;
				ArrayList<Transaction> tList2 = TransactionDataControl
						.readTranscation();
				for (int i = 0; i < tList2.size(); i++) {
					if (tList2.get(i).getShowtimeId() == showTimeId) {
						System.out
								.println("Cannot remove timeslot because transcation already exist!");
						tClash = true;
						break;
					}
				}
				if (!tClash) {
					ShowTimeDataControl.removeShowTime(showTimeId);
				} else {
					displayUpdateTimeSlot(pair, choice, movieSchedule, movie,
							cineId);
				}
			}
		}
		displayEditExistingTimeSlot(movieSchedule, movie.getMovieId(), cineId,
				movie);

	}

}
