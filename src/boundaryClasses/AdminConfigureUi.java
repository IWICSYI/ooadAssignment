package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;

public class AdminConfigureUi extends AdminMainUi {

	/**
	 * Display main configuration page that can access holiday and price
	 * configuration pages/
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayConfigMain() throws IOException, ParseException {

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		System.out.println("#########################################");
		System.out.println("#        Configuration Page             #");
		System.out.println("#########################################");
		System.out.println("#   1.Config holiday date               #");
		System.out.println("#   2.Config price                      #");
		System.out.println("#   3.Go Back                           #");
		System.out.println("#########################################=");
		do {
			System.out.println("Select what you want to config:");
			choice = ValidationControl.validateAndReturnIntegerValue(sc
					.nextLine());

			if (choice == 1) {
				AdminConfigureHolidayUi.displayHolidayMain();
			} else if (choice == 2) {
				AdminConfigureTicketPriceUi.displayTicketConfigureMain();
			} else if (choice == 3) {
				AdminMainUi.displayAdminMain();
			}
		} while (choice <= 0 || choice > 3);

	}
}
