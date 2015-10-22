package data;

import java.util.*;


public class ShowTime {

	private int listingId;
	private int cinemaId;
	private int showTimeId;
	private int noOfShowTimes;
	private ArrayList<Date> showTimeArray;
	private ArrayList<Seats> listOfSeats;
	public int getListingId() {
		return listingId;
	}
	public void setListingId(int listingId) {
		this.listingId = listingId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public int getShowTimeId() {
		return showTimeId;
	}
	public void setShowTimeId(int showTimeId) {
		this.showTimeId = showTimeId;
	}
	public int getNoOfShowTimes() {
		return noOfShowTimes;
	}
	public void setNoOfShowTimes(int noOfShowTimes) {
		this.noOfShowTimes = noOfShowTimes;
	}
	public ArrayList<Date> getShowTimeArray() {
		return showTimeArray;
	}
	public void setShowTimeArray(ArrayList<Date> showTimeArray) {
		this.showTimeArray = showTimeArray;
	}
	public ArrayList<Seats> getListOfSeats() {
		return listOfSeats;
	}
	public void setListOfSeats(ArrayList<Seats> listOfSeats) {
		this.listOfSeats = listOfSeats;
	}
}
