package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;
import dataController.MovieScheduleDataControl;

public class ModuleSelectionUi {

	/**
	 * First page to choose which module you want to work with
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void display() throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("Enter Module Choice");
			System.out.println("1.Admin Module");
			System.out.println("2.Movie Goer Module");
			String s = sc.nextLine();
			choice = ValidationControl.validateYesNoAndReturnIntegerValue(s);
			if (choice < 1 || choice > 2) {
				System.out.println("Invalid input, please try again" );
				choice = 200;
			} else if (choice == 1) {

				AdminLoginUi lg = new AdminLoginUi();
				lg.displayLogin();
			} else if (choice == 2) {

				CustMain.displayCustMain();
			}

		} while (choice > 3);

	}

}
