package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;
/**
 * Display ui of what admin will see when they log in.
 * @author Chang
 *
 */
public class AdminMainUi {

	/**
	 * Display main admin page where user can choose to create/update remove
	 * movie, movie schedule and configure ticket price and price
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayAdminMain() throws IOException, ParseException {
		int choice = 0;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("#############################################");
			System.out.println("#                 Admin Page                #");
			System.out.println("#   1.Create/Update/Remove movie entries    #");
			System.out.println("#   2.Create/Update/Remove cinema showtimes #");
			System.out.println("#     and the movies to be shown            #");
			System.out.println("#   3.Configure System Setting              #");
			System.out.println("#   4.Go to module selector                 #");
			System.out.println("#############################################");
			String s = sc.nextLine();

			choice = ValidationControl.validateAndReturnIntegerValue(s);
			if (choice < 1 || choice > 4) {
				System.out.println("Invalid Input, please try again.");
			} else if (choice == 1) {
				AdminMovieEntryUi mUI = new AdminMovieEntryUi();
				mUI.displayMovieMain();
			} else if (choice == 2) {
				AdminSchedulerMainUi.displaySchedulerMain();
			} else if (choice == 3) {
				AdminConfigureUi.displayConfigMain();
			} else if (choice == 4) {
				ModuleSelectionUi.display();
			}

		} while (choice > 4 || choice <= 0);

	}

}
