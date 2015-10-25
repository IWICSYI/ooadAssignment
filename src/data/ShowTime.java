package data;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import controllerClasses.DataControl;


public class ShowTime implements Serializable{

	private int listingId;
	private int cinemaId;
	private int movieId;
	private int showTimeId;
	private int noOfSeats;
	private int dayType;
	private String showTimeValue;
	private Date startDate;
	private Date endDate;
	private double ticketPrice;

	
	private ArrayList<Seats> listOfSeats;
	
	
	public ShowTime(int listingId, int cinemaId, int movieId, int showTimeId, int dayType,
			String showTime,int seats){
		super();
		this.listingId = listingId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.showTimeId = showTimeId;
		this.dayType=dayType;
		noOfSeats=seats;
		showTimeValue=showTime;
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
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public int getDayType() {
		return dayType;
	}
	public void setDayType(int dayType) {
		this.dayType = dayType;
	}

	public String getShowTimeValue() {
		return showTimeValue;
	}

	public void setShowTimeValue(String showTimeValue) {
		this.showTimeValue = showTimeValue;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	
	
}
