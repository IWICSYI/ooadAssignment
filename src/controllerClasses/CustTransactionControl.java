package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;

import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.Transaction;
import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;
import dataController.TransactionDataControl;

/**
 * Class use to keep track of all the transactions.
 * 
 * @author Chang En Kai
 *
 */
public class CustTransactionControl {
	/**
	 * Method to search through booking history based on email.
	 * 
	 * @param email
	 * @return
	 * @throws IOException
	 */
	public static boolean searchTransaction(String email) throws IOException {

		ArrayList<Transaction> tList = TransactionDataControl.readTranscation();
		boolean exist = false;
		int count = 1;
		for (int i = 0; i < tList.size(); i++) {
			if (email.equals(tList.get(i).getEmail())) {
				System.out.println("\nBooking no." + count);
				count++;
				tList.get(i).printTranscation();
				exist = true;
			}
			System.out.println();
		}
		return exist;

	}

}
