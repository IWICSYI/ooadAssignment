package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import dataController.SeatsDataControl;

/**
 * Class that works in conjunction with CustBuyTickets to deal with the nitty
 * gritty of seats selections.
 * 
 * @author Chang En Kai
 *
 */
public class CustSeatsControl {

	/**
	 * Manage and generate seats selection information
	 * 
	 * @param sT
	 *            A showtime object
	 * @param showTimeId
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ObjectContainer> manageSeats(ShowTime sT,
			int showTimeId) throws IOException, ParseException {

		ArrayList<Seats> seatList = new ArrayList<Seats>();
		ArrayList<ObjectContainer> selectedSeats = new ArrayList<ObjectContainer>();
		ArrayList<SeatsInformation> sINfoList = SeatsDataControl
				.readSeatInfor(showTimeId);

		SeatsInformation sInfor = new SeatsInformation();
		sInfor.setSeatInfoId(showTimeId);
		sInfor.setCinemaId(sT.getCinemaId());
		sInfor.setMovieId(sT.getMovieId());
		sInfor.setCineplexId(sT.getCineplexId());
		sInfor.setNoOfSeats(sT.getNoOfSeats());
		sInfor.setPrice(sT.getTicketPrice());
		sInfor.setshowTimeId(showTimeId);
		sInfor.setStartEndTime(sT.getShowTimeValue());
		sInfor.setStartDate(sT.getStartDate());
		if (sINfoList.size() == 0) {
			SeatsDataControl.createSeatInformation(sInfor);
		}
		seatList = SeatsDataControl.readSeats(showTimeId);
		Scanner sc = new Scanner(System.in);

		String seatName = "";
		char fA = 'A';
		int seatA = 1, seatB = 1;
		int seatAmount = sT.getNoOfSeats();
		int seatId = 1;

		// create new seats!
		if (seatList.isEmpty()) {
			System.out
			.println("-------------------------------------------SCREEN----------------------------------------------");
	
			for (int i = 0; i < seatAmount; i++) {
				seatName = fA + "" + seatA;

				seatA = seatA + 1;
				if (seatA > 20) {
					seatA = 1;
				}

				System.out.print("[" + seatName + "]");
				if ((i + 1) % 10 == 0 && i != 0) {

					System.out.print("|     |");
				}
				if ((i + 1) % 20 == 0 && i != 0) {
					fA = (char) ('A' + seatB);
					System.out.println();
					seatB++;
				}

				Seats s = new Seats();
				s.setOccupied(false);
				// s.setShowTimeId(sTList.getShowTimeId());
				s.setMovieId(sT.getMovieId());
				s.setCinemaId(sT.getCinemaId());
				s.setSeatName(seatName);
				s.setSeatId(seatId++);
				s.setSeatsInformationId(showTimeId);
				seatList.add(s);
				SeatsDataControl.createIndiviualSeat(seatList);
				ObjectContainer oS = new ObjectContainer();
				oS.setName(seatName);
				oS.setSeatList(seatList);
				oS.setSeat(s);
				selectedSeats.add(oS);
			}
		} else {
			selectedSeats.clear();
			for (int i = 0; i < seatList.size(); i++) {
				// System.out.println(seatList.size());
				ObjectContainer oS = new ObjectContainer();
				oS.setName(seatList.get(i).getSeatName());
				oS.setId(seatList.get(i).getSeatId());
				oS.setI(seatList.get(i).getSeatsInformationId());
				oS.setSeatList(seatList);
				oS.setSeat(seatList.get(i));
				selectedSeats.add(oS);

			}
			designSeats(seatList);

		}
		return selectedSeats;
	}

	/**
	 * Design seats to be displayed for selections
	 * 
	 * @param seatList
	 *            a List of seats
	 */
	public static void designSeats(ArrayList<Seats> seatList) {
		System.out
				.println("-------------------------------------------SCREEN----------------------------------------------");
		for (int i = 0; i < seatList.size(); i++) {

			if (!seatList.get(i).isOccupied()) {
				System.out.print("[" + seatList.get(i).getSeatName() + "]");
			} else {
				if(seatList.get(i).getSeatName().length()==2){
				System.out.print("[X ]");}
				else if(seatList.get(i).getSeatName().length()==3){
					System.out.print("[ X ]");
				}
			}
			if ((i + 1) % 10 == 0 && i != 0) {
				System.out.print("|     |");
			}
			if ((i + 1) % 20 == 0 && i != 0) {
				System.out.println();
			}
		}
	}

	/**
	 * Search seat information by seat name.
	 * 
	 * @param seatList
	 * @param name
	 * @return
	 */
	public Seats searchSeat(ArrayList<Seats> seatList, String name) {
		Seats a = new Seats();
		for (int i = 0; i < seatList.size(); i++) {

			if (seatList.get(i).getSeatName().equals(name)) {
				a = seatList.get(i);
				return a;

			}

		}

		return null;
	}

	/**
	 * Check if selected seats are occupied or not.
	 * 
	 * @param seatList
	 *            A list of seat that user selected
	 * @param name
	 *            seat name
	 * @return
	 */
	public boolean checkOccupied(ArrayList<Seats> seatList, String name) {
		Seats a = new Seats();
		for (int i = 0; i < seatList.size(); i++) {

			if (seatList.get(i).getSeatName().equals(name)) {
				if (seatList.get(i).isOccupied()) {
					System.out.println("Seat already occupied, please try again!");
					
					return false;
				}

			}

		}
		return true;
	}

}
