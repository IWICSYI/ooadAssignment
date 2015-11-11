package dataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import data.LoginData;

/**
 * Class use to retrieve login information
 * 
 * @author Chang En Kai
 *
 */
public class LoginDataControl extends DataControl {

	/**
	 * Retrieve a list of login username/password
	 * 
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<LoginData> readLogin() throws IOException {

		ArrayList stringArray = (ArrayList) read("data/loginDetails.txt");
		ArrayList alr = new ArrayList();// to store data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass
																		// in
																		// the
																		// string
																		// to
																		// the
																		// string
																		// tokenizer
																		// using
																		// delimiter
																		// "|"

			String username = star.nextToken().trim(); // first token
			String password = star.nextToken().trim(); // second token
			// create Admin User object from file data
			LoginData u = new LoginData(username, password);
			// add to list
			alr.add(u);
		}
		return alr;
	}

}
