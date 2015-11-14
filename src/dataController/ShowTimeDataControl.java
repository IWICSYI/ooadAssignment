package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.HolidayDate;
import data.MovieSchedule;
import data.Prices;
import data.ShowTime;
import data.Transaction;

/**
 * Class that deal with actual crud of Show time data.
 * 
 * @author Chang En Kai
 *
 */
public class ShowTimeDataControl extends DataControl {
	/**
	 * Read all show times
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */

	protected static final SimpleDateFormat finalDateFormatter2 = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm");

	/**
	 * Read a list of show time
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ShowTime> readShowTimes() throws IOException,
			ParseException {

		ArrayList stringArray = (ArrayList) read("data/showTimes.txt");
		ArrayList alr = new ArrayList();// to store data

		// 1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
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
			int listingId = Integer.parseInt(star.nextToken().trim());
			int movieId = Integer.parseInt(star.nextToken().trim());
			int cinemaId = Integer.parseInt(star.nextToken().trim());
			int showTimeId = Integer.parseInt(star.nextToken().trim());
			int dayType = Integer.parseInt(star.nextToken().trim());
			String showTimeValue = star.nextToken().trim();
			int seats = Integer.parseInt(star.nextToken().trim());
			String startDateString = star.nextToken().trim();
			Date startDate = finalDateFormatter2.parse(startDateString);
			String endDateString = star.nextToken().trim();
			Date endDate = finalDateFormatter2.parse(endDateString);
			double price = Double.parseDouble(star.nextToken().trim());
			int preview = Integer.parseInt(star.nextToken().trim());
			int cineId = Integer.parseInt(star.nextToken().trim());

			ShowTime u = new ShowTime(listingId, movieId, cinemaId, showTimeId,
					dayType, showTimeValue, seats, startDate, endDate, price,
					preview, cineId);
			// add to list
			alr.add(u);

		}
		return alr;
	}

/**
 * Read showtime for now showing time slot selections
 * @param listId listing id
 * @param calTemp date to read
 * @param cId cineplex id
 * @return
 * @throws IOException
 * @throws ParseException
 */
	public static ArrayList<ShowTime> readShowTimesBasedOnListingIdAndCineplexIdAndNowShowing(int listId,Calendar calTemp,int cId) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		Calendar today=(Calendar) calTemp.clone();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		Calendar end=(Calendar) today.clone();
		end.add(Calendar.DATE, 1);
		
		Calendar timeCheck=Calendar.getInstance();
		Calendar timeCheck2=Calendar.getInstance();
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				int preview = Integer.parseInt(star.nextToken().trim());
				String arr[]=showTimeValue.split("-");
				
				timeCheck2.setTime(startDate);
				timeCheck2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0].substring(0, 2)));
				timeCheck2.set(Calendar.MINUTE, Integer.parseInt(arr[0].substring(2, 4)));
				timeCheck2.set(Calendar.SECOND, 0);
				timeCheck2.set(Calendar.MILLISECOND, 0);
				//System.out.println(timeCheck.getTime()+"	"+timeCheck2.getTime()+""+timeCheck2.getTime().after(timeCheck.getTime()));
				
				//startDate.setHours(10);
				
				// add to  list
				int cineId = Integer.parseInt(star.nextToken().trim());
				if(listingId==listId && today.getTime().equals(startDate)&&end.getTime().equals(endDate)&&cId==cineId)
				{	
					// add to  list
					if(timeCheck2.getTime().after(timeCheck.getTime())){
						ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview,cineId);
						alr.add(u) ;
					}
					
				}
				
				
			}
			return alr ;
	}
	/**
	 * Read show times that are already on cinema in specific date, use to validate timeslot clash
	 * @param cinemaID cinema ID
	 * @param tmp calendar for dates that needs to check
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ShowTime> readShowTimesBasedOnCinemaIdAndNowShowing(
			int cinemaID, Calendar tmp) throws IOException, ParseException {
		ArrayList stringArray = (ArrayList) read("data/showTimes.txt");
		ArrayList alr = new ArrayList();// to store data
		Calendar temp = (Calendar) tmp.clone();

		temp.set(Calendar.HOUR_OF_DAY, 0);
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		Date sD = temp.getTime();
		temp.add(Calendar.DATE, 1);
		Date eD = temp.getTime();

		// 1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
			int listingId = Integer.parseInt(star.nextToken().trim());
			int movieId = Integer.parseInt(star.nextToken().trim());
			int cinemaId = Integer.parseInt(star.nextToken().trim());
			int showTimeId = Integer.parseInt(star.nextToken().trim());
			int dayType = Integer.parseInt(star.nextToken().trim());
			String showTimeValue = star.nextToken().trim();
			int seats = Integer.parseInt(star.nextToken().trim());
			String startDateString = star.nextToken().trim();
			Date startDate = finalDateFormatter2.parse(startDateString);
			String endDateString = star.nextToken().trim();
			Date endDate = finalDateFormatter2.parse(endDateString);
			double price = Double.parseDouble(star.nextToken().trim());
			int preview = Integer.parseInt(star.nextToken().trim());

			int cineId = Integer.parseInt(star.nextToken().trim());

			ShowTime u = new ShowTime(listingId, movieId, cinemaId, showTimeId,
					dayType, showTimeValue, seats, startDate, endDate, price,
					preview, cineId);
			// add to list

			if (startDate.equals(sD) && endDate.equals(eD)
					&& cinemaId == cinemaID) {
				// add to list
				alr.add(u);
			}

		}
		return alr;
	}

	/**
	 * Read show time based on listing id and cineplex id on random dates.
	 * 
	 * @param listId
	 *            Listing ID
	 * @param calTemp
	 *            Calendar object that contain date to check which show time to
	 *            retreive
	 * @param cId
	 *            Cineplex ID
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ShowTime> readShowTimesBasedOnListingIdAndCineplexId(
			int listId, Calendar calTemp, int cId) throws IOException,
			ParseException {
		ArrayList stringArray = (ArrayList) read("data/showTimes.txt");
		ArrayList alr = new ArrayList();// to store data

		Calendar today = (Calendar) calTemp.clone();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);

		Calendar end = (Calendar) today.clone();
		end.add(Calendar.DATE, 1);

		// 1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
			int listingId = Integer.parseInt(star.nextToken().trim());
			int movieId = Integer.parseInt(star.nextToken().trim());
			int cinemaId = Integer.parseInt(star.nextToken().trim());
			int showTimeId = Integer.parseInt(star.nextToken().trim());
			int dayType = Integer.parseInt(star.nextToken().trim());
			String showTimeValue = star.nextToken().trim();
			int seats = Integer.parseInt(star.nextToken().trim());
			String startDateString = star.nextToken().trim();
			Date startDate = finalDateFormatter2.parse(startDateString);
			String endDateString = star.nextToken().trim();
			Date endDate = finalDateFormatter2.parse(endDateString);
			double price = Double.parseDouble(star.nextToken().trim());
			int preview = Integer.parseInt(star.nextToken().trim());
			String arr[] = showTimeValue.split("-");

			// startDate.setHours(10);

			// add to list
			int cineId = Integer.parseInt(star.nextToken().trim());
			if (listingId == listId && today.getTime().equals(startDate)
					&& end.getTime().equals(endDate) && cId == cineId) {
				// add to list

				ShowTime u = new ShowTime(listingId, movieId, cinemaId,
						showTimeId, dayType, showTimeValue, seats, startDate,
						endDate, price, preview, cineId);
				alr.add(u);

			}

		}
		return alr;
	}

	/**
	 * Read show time based on listing id
	 * 
	 * @param listId
	 *            Listing ID
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ShowTime> readShowTimesBasedOnListingId(int listId)
			throws IOException, ParseException {
		ArrayList stringArray = (ArrayList) read("data/showTimes.txt");
		ArrayList alr = new ArrayList();// to store data

		// 1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
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
			int listingId = Integer.parseInt(star.nextToken().trim());
			int movieId = Integer.parseInt(star.nextToken().trim());
			int cinemaId = Integer.parseInt(star.nextToken().trim());
			int showTimeId = Integer.parseInt(star.nextToken().trim());
			int dayType = Integer.parseInt(star.nextToken().trim());
			String showTimeValue = star.nextToken().trim();
			int seats = Integer.parseInt(star.nextToken().trim());
			String startDateString = star.nextToken().trim();
			Date startDate = finalDateFormatter2.parse(startDateString);
			String endDateString = star.nextToken().trim();
			Date endDate = finalDateFormatter2.parse(endDateString);
			double price = Double.parseDouble(star.nextToken().trim());
			int preview = Integer.parseInt(star.nextToken().trim());
			String arr[] = showTimeValue.split("-");

			// startDate.setHours(10);

			// add to list
			int cineId = Integer.parseInt(star.nextToken().trim());

			if (listingId == listId) {
				// add to list

				ShowTime u = new ShowTime(listingId, movieId, cinemaId,
						showTimeId, dayType, showTimeValue, seats, startDate,
						endDate, price, preview, cineId);
				alr.add(u);

			}

		}
		return alr;
	}

	/**
	 * Read one show time based on the show time id. Use for purchasing of seats
	 * to identify which seat ties to which show time
	 * 
	 * @param showTime
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ShowTime readShowTimesBasedOnShowTimeId(int showTime)
			throws IOException, ParseException {
		ArrayList stringArray = (ArrayList) read("data/showTimes.txt");
		ArrayList alr = new ArrayList();// to store data

		// 1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
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
			int listingId = Integer.parseInt(star.nextToken().trim());
			int movieId = Integer.parseInt(star.nextToken().trim());
			int cinemaId = Integer.parseInt(star.nextToken().trim());
			int showTimeId = Integer.parseInt(star.nextToken().trim());
			int dayType = Integer.parseInt(star.nextToken().trim());
			String showTimeValue = star.nextToken().trim();
			int seats = Integer.parseInt(star.nextToken().trim());
			String startDateString = star.nextToken().trim();
			Date startDate = finalDateFormatter2.parse(startDateString);
			String endDateString = star.nextToken().trim();
			Date endDate = finalDateFormatter2.parse(endDateString);
			double price = Double.parseDouble(star.nextToken().trim());
			int preview = Integer.parseInt(star.nextToken().trim());

			// add to list
			int cineId = Integer.parseInt(star.nextToken().trim());

			if (showTimeId == showTime) {
				// add to list
				ShowTime u = new ShowTime(listingId, movieId, cinemaId,
						showTimeId, dayType, showTimeValue, seats, startDate,
						endDate, price, preview, cineId);
				return u;
			}
		}
		return null;
	}

	/**
	 * Create time slots
	 * 
	 * @param sT
	 *            ShowTime
	 * @param sch2
	 *            grab details of movie schedule because they shared some same
	 *            data.
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static void createTimeSlot(ShowTime sT, MovieSchedule sch2)
			throws IOException, ParseException {

		ArrayList<ShowTime> existST = ShowTimeDataControl.readShowTimes();
		int stID;
		if (!existST.isEmpty()) {
			stID = existST.get(existST.size() - 1).getShowTimeId() + 1;
		} else {
			stID = 1;
		}
		int plat = sch2.getPlatOrNot();
		int tD = sch2.getThreeDOrNot();
		int bB = sch2.getBlockBuster();
		int dtype = sT.getDayType();

		sT.getStartDate().setHours(00);
		sT.getEndDate().setHours(00);
		sT.getStartDate().setMinutes(00);
		sT.getEndDate().setMinutes(00);
		String startDate = finalDateFormatter2.format(sT.getStartDate());
		String endDate = finalDateFormatter2.format(sT.getEndDate());

		ArrayList<HolidayDate> hDList = new ArrayList<HolidayDate>();
		for (int i = 0; i < hDList.size(); i++) {
			String date1 = finalDateFormatter2.format(hDList.get(i)
					.getHolidayDate());
			if (date1.equals(startDate)) {
				dtype = 8;
				break;
			}

		}

		ArrayList<Prices> prices = TicketPriceAndHolidayDataControl.readPrice();
		double finalPrice = prices.get(0).getNormal();
		;
		if (plat == 1) {
			finalPrice = finalPrice + prices.get(0).getPlat();
		}
		if (tD == 1) {
			finalPrice = finalPrice + prices.get(0).gettD();
		}

		if (bB == 1) {
			finalPrice = finalPrice + prices.get(0).getBlockbuster();
		}
		if (dtype == 1 || dtype == 7) {
			finalPrice = finalPrice + prices.get(0).getWeekend();
		} else if (dtype == 8) {
			finalPrice = finalPrice + prices.get(0).getHoli();
		}

		StringBuilder st2 = new StringBuilder();
		List alTS = new ArrayList();
		st2.append(sch2.getListingId());
		st2.append(SEPARATOR);
		st2.append(sT.getMovieId());
		st2.append(SEPARATOR);
		st2.append(sT.getCinemaId());
		st2.append(SEPARATOR);
		st2.append(stID);
		st2.append(SEPARATOR);
		st2.append(sT.getDayType());
		st2.append(SEPARATOR);
		st2.append(sT.getShowTimeValue());
		st2.append(SEPARATOR);
		st2.append(sT.getNoOfSeats());
		st2.append(SEPARATOR);
		st2.append(startDate);
		st2.append(SEPARATOR);
		st2.append(endDate);
		st2.append(SEPARATOR);
		st2.append(finalPrice);
		st2.append(SEPARATOR);
		st2.append(sch2.getPreviewStatus());
		st2.append(SEPARATOR);
		st2.append(sT.getCineplexId());

		alTS.add(st2.toString());
		write("data/showTimes.txt", alTS);

	}

	/**
	 * Remove a show time.
	 * 
	 * @param showTimeId
	 *            Showtime ID to identify which show time to remove
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void removeShowTime(int showTimeId) throws IOException,
			ParseException {
		List alw = new ArrayList();// to store Professors data

		ArrayList<ShowTime> eList = readShowTimes();
		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for (int i = 0; i < eList.size(); i++) {
			StringBuilder st2 = new StringBuilder();
			if (showTimeId != eList.get(i).getShowTimeId()) {
				st2.append(eList.get(i).getListingId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getDayType());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeValue());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getNoOfSeats());
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2.format(eList.get(i)
						.getStartDate()));
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2
						.format(eList.get(i).getEndDate()));
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getTicketPrice());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getPreviewStatus());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCineplexId());
				st2.append("\n");

				alw.add(st2.toString());

			}

		}
		writeB("data/showTimes.txt", alw);
		System.out.println("Sucessfully removed!");
	}

	/**
	 * Update a showtime
	 * 
	 * @param showTimeId
	 *            Show time id to identify what to update
	 * @param showTimeValue
	 *            start end time of show time value
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void updateShowTime(int showTimeId, String showTimeValue)
			throws IOException, ParseException {
		List alw = new ArrayList();// to store Professors data

		ArrayList<ShowTime> eList = readShowTimes();
		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for (int i = 0; i < eList.size(); i++) {
			StringBuilder st2 = new StringBuilder();
			if (showTimeId == eList.get(i).getShowTimeId()) {
				st2.append(eList.get(i).getListingId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getDayType());
				st2.append(SEPARATOR);
				st2.append(showTimeValue);
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getNoOfSeats());
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2.format(eList.get(i)
						.getStartDate()));
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2
						.format(eList.get(i).getEndDate()));
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getTicketPrice());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getPreviewStatus());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCineplexId());
				st2.append("\n");

				alw.add(st2.toString());

			} else {
				st2.append(eList.get(i).getListingId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getDayType());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeValue());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getNoOfSeats());
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2.format(eList.get(i)
						.getStartDate()));
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2
						.format(eList.get(i).getEndDate()));
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getTicketPrice());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getPreviewStatus());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCineplexId());
				st2.append("\n");

				alw.add(st2.toString());
			}

		}
		writeB("data/showTimes.txt", alw);
		System.out.println("Update Completed!");
	}

	/**
	 * Method to clean up show time when editing start date
	 * 
	 * @param listID
	 *            Listing ID
	 * @param newSD
	 *            inputed startdate
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean removeShowTimeWhenEditStartDate(int listID,
			Calendar newSD) throws IOException, ParseException {
		List alw = new ArrayList();// to store Professors data
		String sD = "";
		ArrayList<ShowTime> eList = readShowTimes();
		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		boolean clash = false;
		ArrayList<Transaction> tList = TransactionDataControl.readTranscation();

		for (int i = 0; i < eList.size(); i++) {
			Date existStartDate = eList.get(i).getStartDate();
			sD = finalDateFormatter.format(existStartDate);
			StringBuilder st2 = new StringBuilder();
			for (int j = 0; i < tList.size(); i++) {
				if (tList.get(j).getShowtimeId() == eList.get(i)
						.getShowTimeId()) {
					System.out
							.println("Cannot remove timeslot because transcation already exist!");
					clash = true;
					return false;
				}
			}

			if (listID == eList.get(i).getListingId()
					&& newSD.getTime().after(existStartDate)) {
				System.out.println(sD + " showtime deleted for this movie.");
			} else {
				st2.append(eList.get(i).getListingId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getDayType());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeValue());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getNoOfSeats());
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2.format(eList.get(i)
						.getStartDate()));
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2
						.format(eList.get(i).getEndDate()));
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getTicketPrice());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getPreviewStatus());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCineplexId());
				st2.append("\n");

				alw.add(st2.toString());
			}

		}
		writeB("data/showTimes.txt", alw);
		System.out
				.println("Show times not within start/end date sucessfully removed!");
		return true;
	}

	/**
	 * Method to clean up show time when editing end date
	 * 
	 * @param listID
	 *            Listing ID
	 * @param newSD
	 *            inputed endDate
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static boolean removeShowTimeWhenEditEndDate(int listID,
			Calendar newSD) throws IOException, ParseException {
		List alw = new ArrayList();// to store Professors data
		String eD = "";
		ArrayList<ShowTime> eList = readShowTimes();
		// 1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		newSD.set(Calendar.HOUR_OF_DAY, 00);
		newSD.set(Calendar.MINUTE, 00);
		boolean clash = false;
		ArrayList<Transaction> tList = TransactionDataControl.readTranscation();

		for (int i = 0; i < eList.size(); i++) {
			Date existEndDate = eList.get(i).getStartDate();
			eD = finalDateFormatter.format(existEndDate);

			// System.out.println(newSD.getTime()+" "+existEndDate+" "+newSD.getTime().before(existEndDate));
			StringBuilder st2 = new StringBuilder();
			for (int j = 0; i < tList.size(); i++) {
				if (tList.get(j).getShowtimeId() == eList.get(i)
						.getShowTimeId()) {
					System.out
							.println("Cannot remove timeslot because transcation already exist!");
					clash = true;
					return false;
				}
			}

			if (listID == eList.get(i).getListingId()
					&& newSD.getTime().before(existEndDate)) {

				System.out.println(eD + " showtime deleted for this movie.");

			} else {
				st2.append(eList.get(i).getListingId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getMovieId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCinemaId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeId());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getDayType());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getShowTimeValue());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getNoOfSeats());
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2.format(eList.get(i)
						.getStartDate()));
				st2.append(SEPARATOR);
				st2.append(finalDateFormatter2
						.format(eList.get(i).getEndDate()));
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getTicketPrice());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getPreviewStatus());
				st2.append(SEPARATOR);
				st2.append(eList.get(i).getCineplexId());
				st2.append("\n");

				alw.add(st2.toString());
			}

		}
		writeB("data/showTimes.txt", alw);
		System.out
				.println("Show times not within start/end date sucessfully removed!");
		return true;
	}

}
