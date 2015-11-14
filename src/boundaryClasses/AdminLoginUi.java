package boundaryClasses;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.AdminLoginControl;
/**
 * Display login page
 * 
 */
public class AdminLoginUi {

	/**
	 * Display login page
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void displayLogin() throws IOException, ParseException {
		Console console = System.console();
		if (console == null) {
			// System.out.println("Couldn't get Console instance");

		}

		boolean result, check = false;
		String userName, password = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("#########################");
		System.out.println("#	LOGIN PAGE	#");
		System.out.println("#########################");
		do {
			System.out.println("Please enter username:");
			userName = sc.nextLine();
			if (console != null) {
				
				char[] pW = console.readPassword("Please your  password: ");
				password = new String(pW);
				
			} else {
				System.out.println("Please enter password:");
				password = sc.nextLine();
			}

			AdminLoginControl lg = new AdminLoginControl();
			result = lg.checkExist(userName, password);
			if (result) {
				check = true;
				AdminMainUi.displayAdminMain();

			} else
				System.out
						.println("Invalid username and password,please try again.\n");

		} while (!check);
	}

}
