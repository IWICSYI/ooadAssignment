package data;

import java.io.IOException;
import java.util.*;

import controllerClasses.DataControl;


public class ShowTime {

	private int listingId;
	private int cinemaId;
	private int movieId;
	private int showTimeId;
	private int noOfShowTimes;
	private ArrayList<String> showTimeArray;
	private ArrayList<Seats> listOfSeats;
	
	
	public ShowTime(int listingId, int cinemaId, int movieId, int showTimeId,
			int noOfShowTimes, ArrayList<String> showTimeArray) {
		super();
		this.listingId = listingId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.showTimeId = showTimeId;
		this.noOfShowTimes = noOfShowTimes;
		this.showTimeArray = showTimeArray;
	}
	public ShowTime() {
		// TODO Auto-generated constructor stub
	}
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
	public ArrayList<String> getShowTimeArray() {
		return showTimeArray;
	}
	public void setShowTimeArray(ArrayList<String> showTimeArray) {
		this.showTimeArray = showTimeArray;
	}
	public ArrayList<Seats> getListOfSeats() {
		return listOfSeats;
	}
	public void setListOfSeats(ArrayList<Seats> listOfSeats) {
		this.listOfSeats = listOfSeats;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	
	
}
