package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.CustReviewControl;
import controllerClasses.ValidationControl;
import data.Movie;
import data.Review;
import dataController.MovieDataControl;
import dataController.ReviewDataControl;

/**
 * Display ui for movie details
 * @author Chang En Kai
 *
 */
public class CustMovieDetailUi extends CustDisplayMovieListingUi {

	/**
	 * Display movie selection to view movie details
	 * 
	 * @param oList
	 *            An object container that pair user's choice with movie details
	 * @param plat
	 *            platinum status
	 * @param type
	 *            listing type, can be now showing, coming soon or preview.
	 * @throws IOException
	 * @throws ParseException
	 */
	public void displayNowShowingMovieDetailsSelection(ArrayList<ObjectContainer> oList, int plat, String type)throws IOException, ParseException {
		
		if(oList.size()==0){
			System.out.println("Cannot select because no movie is showing!");
			
			if(type.equals("ComingSoon")){
				CustDisplayMovieListingUi.displayComingSoon(plat);
			}
			else if(type.equals("now")){
				CustDisplayMovieListingUi.displayNowShowing(plat);
			}
			else if(type.equals("preview")){
				CustDisplayMovieListingUi.displayPreview(plat);
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Now Showing");
		System.out
				.println("------------------------------------------------------");
		for (int i = 0; i < oList.size(); i++) {
			//System.out.print((i + 1) + ":" + oList.get(i).getM().getMovieName());
			if(i%2==0)
			{
				System.out.print(String.format((i + 1) + ":%-20s",oList.get(i).getM().getMovieName())+"		");
			}else
			{
				System.out.print(String.format((i + 1) + ":"+oList.get(i).getM().getMovieName()));
			}

			if ((i + 1) % 2 == 0)
				System.out.println();
		}
		System.out.println();
		System.out
				.println("------------------------------------------------------");
		int choice = 0;
		do {
			System.out.println("1.Select number beside movie to view details");
			choice = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
		} while (choice <= 0 || choice > oList.size());
		displayMovieDetails(oList.get(choice - 1).getM().getMovieId(), oList.get(choice - 1).getMovieListing().getListingId(), type, plat);
	}

	/**
	 * display movie details
	 * 
	 * @param movieId
	 * @param listingId
	 * @param type
	 * @param plat
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayMovieDetails(int movieId, int listingId,
			String type, int plat) throws IOException, ParseException {
		// ArrayList<MovieSchedule>
		// schList=cl.readScheduleListingBasedOnStartingDate();
		Movie movieDetails = MovieDataControl.readMovieBasedOnId(movieId);
		ArrayList<ObjectContainer> pair = new ArrayList<ObjectContainer>();
		Scanner sc = new Scanner(System.in);

		movieDetails.printMovieDetails();
		if (type.equals("now")) {
			int choice = 0;
			do {
				System.out.println("1.Read Review");
				System.out.println("2.Write Review");
				System.out.println("3.Buy Ticket");
				System.out.println("4.Go Back");
				String s = sc.nextLine();
				choice = ValidationControl.validateAndReturnIntegerValue(s);
			} while (choice <= 0 || choice > 4);

			if (choice == 1) {
				CustReviewControl.retrieveReviewList(movieId, listingId, type,
						plat);

			}
			// Write review
			if (choice == 2) {
				Review review = new Review();
				// DateFormat df = new
				// SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				Date date = new Date();
				review.setReviewDate(date);
				review.setMovieId(movieId);

				boolean valid = false;
				String check = "";
				do {
					System.out.println("Enter your name:");
					check = sc.nextLine();
					valid = ValidationControl.validateEmptyString(check);
				} while (!valid);
				review.setReviewerName(check);

				int score = 0;
				do {
					System.out.println("Enter score(1-5):");
					score = ValidationControl.validateAndReturnIntegerValue(sc
							.nextLine());
				} while (score <= 0 || score > 5);

				if (score > 0 && score <= 5) {
					review.setScore(score);
				}

				String reviewWords = "";
				boolean valid2 = false;
				do {
					System.out.println("Enter review:");
					reviewWords = sc.nextLine();
					valid2 = ValidationControl.validateEmptyString(reviewWords);
				} while (!valid2);
				review.setReview(reviewWords);

				ReviewDataControl.createReview(review);
				System.out.println("Review sucessfully recorded");
				ReviewDataControl.updateMovieReviewAverageScore(movieId);
				displayMovieDetails(movieId, listingId, type, plat);
			}
			if (choice == 3) {
				CustBuyTicketChooseTimeSlotUi u = new CustBuyTicketChooseTimeSlotUi();
				u.displayBuyTicket(type, movieId, 0, 0, movieDetails, listingId,plat);
			}
			if (choice == 4) {
				CustDisplayMovieListingUi.displayNowShowing(plat);
			}
		}

		else if (type.equals("preview")) {
			int choice = 0;
			do {
				System.out.println("1.Buy Ticket");
				System.out.println("2.Go Back");
				String s = sc.nextLine();
				choice = ValidationControl.validateAndReturnIntegerValue(s);
			} while (choice <= 0 || choice > 2);
			if (choice == 1) {
				CustBuyTicketChooseTimeSlotUi u = new CustBuyTicketChooseTimeSlotUi();
				u.displayBuyTicket(type, movieId, 0, 0, movieDetails, listingId,plat);
			} else if (choice == 2) {
				CustDisplayMovieListingUi.displayPreview(plat);
			}
		} else if (type.equals("ComingSoon")) {

			int choice = 0;
			do {
				System.out.println("1.Go Back");
				String s = sc.nextLine();
				choice = ValidationControl.validateAndReturnIntegerValue(s);
			} while (choice <= 0 || choice > 1);

			if (choice == 1) {
				CustDisplayMovieListingUi.displayComingSoon(plat);
			}
		}

	}// end of method
}
