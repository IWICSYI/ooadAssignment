package controllerClasses;
/**
 * Control class for configuring tick prices
 * @author Chang En Kai
 *
 */
public class AdminConfigTicketPriceControl {

	/**
	 * Check if price is logical
	 * 
	 * @param choice
	 *            user's selection
	 * @param baseprice
	 * @param price
	 * @return
	 */
	public static boolean checkPrice(int choice, double baseprice, double price) {

		boolean valid = false;
		if (choice > 1 || choice < 7) {
			if (price > baseprice) {
				valid = true;
			} else
				valid = false;

		}
		if (choice == 7 || choice == 8) {
			if (price < baseprice) {
				valid = true;
			} else
				valid = false;
		}

		return valid;
	}

}
