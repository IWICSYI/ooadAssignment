package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.HolidayDate;
import data.Prices;

/**
 * Class that deals with actual CRUD of holiday and ticket prices. They are
 * combined together because they are very related.
 * 
 * @author Chang En Kai
 *
 */
public class TicketPriceAndHolidayDataControl extends DataControl {

	/**
	 * Read all prices
	 * 
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Prices> readPrice() throws IOException {

		ArrayList stringArray = (ArrayList) read("data/ticketPrice.txt");
		ArrayList alr = new ArrayList();// to store data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
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
			double normal = Double.parseDouble(star.nextToken().trim());
			double plat = Double.parseDouble(star.nextToken().trim());
			double tD = Double.parseDouble(star.nextToken().trim());
			double blockbuster = Double.parseDouble(star.nextToken().trim());
			double age = Double.parseDouble(star.nextToken().trim());
			double child = Double.parseDouble(star.nextToken().trim());
			double holi = Double.parseDouble(star.nextToken().trim());
			double weekend = Double.parseDouble(star.nextToken().trim());

			Prices u = new Prices(normal, plat, tD, blockbuster, age, child,
					holi, weekend);
			// add to list
			alr.add(u);
		}
		return alr;
	}

	/**
	 * Read all holiday, use to make sure today is a holiday or not.
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<HolidayDate> readHoliday() throws IOException,
			ParseException {

		ArrayList stringArray = (ArrayList) read("data/holidayDates.txt");
		ArrayList alr = new ArrayList();// to store data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// normal
			// price=8*platinum=6*3D=1*blockbuster=*0.5*Age65>-2*holidayPrice=2*weekEndPrice=2
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
			int holiId = Integer.parseInt(star.nextToken());
			String date = star.nextToken();
			Date holiday = finalDateFormatter.parse(date);

			HolidayDate u = new HolidayDate(holiId, holiday);
			// add to list
			alr.add(u);
		}
		return alr;
	}

	/**
	 * Update ticket price
	 * 
	 * @param prices
	 * @throws IOException
	 */
	public static void updatePrice(Prices prices) throws IOException {
		List alw = new ArrayList();// to store Professors data
		int id = 0;

		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		StringBuilder st = new StringBuilder();
		st.append(prices.getNormal());
		st.append(SEPARATOR);
		st.append(prices.getPlat());
		st.append(SEPARATOR);
		st.append(prices.gettD());
		st.append(SEPARATOR);
		st.append(prices.getBlockbuster());
		st.append(SEPARATOR);
		st.append(prices.getOldPrice());
		st.append(SEPARATOR);
		st.append(prices.getChildPrice());
		st.append(SEPARATOR);
		st.append(prices.getHoli());
		st.append(SEPARATOR);
		st.append(prices.getWeekend());

		alw.add(st.toString());

		writeB("data/ticketPrice.txt", alw);

	}

	/**
	 * Create new holiday date!
	 * 
	 * @param hD
	 *            inputed holiday date
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void createHoliday(HolidayDate hD) throws IOException,
			ParseException {
		List alw = new ArrayList();// to store Professors data
		int id = 0;

		ArrayList<HolidayDate> holidayList = TicketPriceAndHolidayDataControl
				.readHoliday();
		if (!holidayList.isEmpty()) {
			id = holidayList.get(holidayList.size() - 1).getHolidayId() + 1;
		} else
			id = 1;
		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String date = finalDateFormatter.format(hD.getHolidayDate());

		StringBuilder st = new StringBuilder();
		st.append(id);
		st.append(SEPARATOR);
		st.append(date);

		alw.add(st.toString());

		write("data/holidayDates.txt", alw);
	}

	/**
	 * Update holiday date
	 * 
	 * @param hDid
	 *            Holiday date id
	 * @param hD
	 *            input holiday date
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void updateHoliday(int hDid, HolidayDate hD)
			throws IOException, ParseException {
		List alw = new ArrayList();// to store Professors data
		int id = 0;
		String updateDate = finalDateFormatter.format(hD.getHolidayDate());

		ArrayList<HolidayDate> holidayList = TicketPriceAndHolidayDataControl
				.readHoliday();

		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for (int i = 0; i < holidayList.size(); i++) {
			StringBuilder st = new StringBuilder();
			if (hDid == holidayList.get(i).getHolidayId()) {
				st.append(hDid);
				st.append(SEPARATOR);
				st.append(updateDate);
				st.append("\n");
				alw.add(st.toString());
			} else {
				st.append(holidayList.get(i).getHolidayId());
				st.append(SEPARATOR);
				String hDExist = finalDateFormatter.format(holidayList.get(i)
						.getHolidayDate());
				st.append(hDExist);
				st.append("\n");
				alw.add(st.toString());

			}
		}

		writeB("data/holidayDates.txt", alw);
	}

	/**
	 * Remove holiday date
	 * 
	 * @param id
	 *            hoildayDate ID
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void removeHolidayDate(int id) throws IOException,
			ParseException {
		List alw = new ArrayList();// to store Professors data

		ArrayList<HolidayDate> holidayList = TicketPriceAndHolidayDataControl
				.readHoliday();

		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for (int i = 0; i < holidayList.size(); i++) {
			StringBuilder st = new StringBuilder();
			if (id != holidayList.get(i).getHolidayId()) {
				st.append(holidayList.get(i).getHolidayId());
				st.append(SEPARATOR);
				String hDExist = finalDateFormatter.format(holidayList.get(i)
						.getHolidayDate());
				st.append(hDExist);
				st.append("\n");
				alw.add(st.toString());
			}
		}

		writeB("data/holidayDates.txt", alw);

	}

}
