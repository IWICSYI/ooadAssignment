package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.SortTop5Control;
import controllerClasses.ValidationControl;
import dataController.MovieScheduleDataControl;
/**
 * Display ui when customer first enter the page
 * @author Chang En Kai
 *
 */
public class CustMain {

	/**
	 * Display the first page the customer will see. They can choose to display
	 * movies, look at best rated movies and search their booking history
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayCustMain() throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		System.out.println("#############################################");
		System.out.println("#            Customer Main Page             #");
		System.out.println("#############################################");

		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("1.List non platinum movie listing.");
			System.out.println("2.List platinum movie listing.");
			System.out.println("3.List top 5 movies based on rating.");
			System.out.println("4.List top 5 movies based on ticket sales.");
			System.out.println("5.Search booking history.");
			System.out.println("6.Go to module selection.");
			choice = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());
		} while (choice <= 0 || choice > 6);

		if (choice == 1) {
			CustDisplayMovieListingUi.displayNowShowing(0);
		} else if (choice == 2) {
			CustDisplayMovieListingUi.displayNowShowing(1);
		} else if (choice == 3) {
			SortTop5Control.sortTopScoreForCustomer();
			System.out.println("Press anything to resume... ...");
			sc.nextLine();
			displayCustMain();
		} else if (choice == 4) {
			SortTop5Control.sortTopSalesForCustomer();
			System.out.println("Press anything to resume... ...");
			sc.nextLine();
			displayCustMain();
		} else if (choice == 5) {
			CustSearchBookingUi.displaySearchBooking();
		} else if (choice == 6) {
			ModuleSelectionUi.display();
		}

	}

}
