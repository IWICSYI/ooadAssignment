package data;

import java.util.ArrayList;
import java.util.Date;
/**
 * Object to facilitate and link between seats and transaction.
 * @author Chang En Kai
 *
 */
public class SeatsInformation {
	
	
	private Date startDate;
	private String startEndTime;
	private int seatInfoId;
	private int noOfSeats;
	private int noOfEmptySeats;
	private int movieId;
	private int cinemaId;
	private int cineplexId;
	private int showTimeId;
	private double price;
	
	public SeatsInformation(Date startDate, String startEndTime, int seatInfoId, int noOfSeats, int noOfEmptySeats,
			int movieId, int cinemaId, int cineplexId, int showTimeId,
			double price) {
		super();
		this.seatInfoId = seatInfoId;
		this.noOfSeats = noOfSeats;
		this.noOfEmptySeats = noOfEmptySeats;
		this.movieId = movieId;
		this.cinemaId = cinemaId;
		this.cineplexId = cineplexId;
		this.showTimeId = showTimeId;
		this.price = price;
	}
	
	
	public SeatsInformation() {
		// TODO Auto-generated constructor stub
	}


	private ArrayList<Seats> seats;
	
	public int getSeatInfoId() {
		return seatInfoId;
	}
	public void setSeatInfoId(int seatInfoId) {
		this.seatInfoId = seatInfoId;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public int getNoOfEmptySeats() {
		return noOfEmptySeats;
	}
	public void setNoOfEmptySeats(int noOfEmptySeats) {
		this.noOfEmptySeats = noOfEmptySeats;
	}
	public ArrayList<Seats> getSeats() {
		return seats;
	}
	public void setSeats(ArrayList<Seats> seats) {
		this.seats = seats;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public int getCineplexId() {
		return cineplexId;
	}
	public void setCineplexId(int cineplexId) {
		this.cineplexId = cineplexId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getShowTimeId() {
		return showTimeId;
	}
	public void setshowTimeId(int seatId) {
		this.showTimeId = seatId;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public String getStartEndTime() {
		return startEndTime;
	}


	public void setStartEndTime(String startEndTime) {
		this.startEndTime = startEndTime;
	}


	public void setShowTimeId(int showTimeId) {
		this.showTimeId = showTimeId;
	}
	
}
