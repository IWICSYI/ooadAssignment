package data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 * Show time object that contains a reference to movie, cinema, cineplex and
 * movie schedule via their IDs. Implemented Comparator because it needs to be
 * sorted.
 * 
 * @author Chang En Kai
 *
 */
public class ShowTime implements Comparator<ShowTime> {

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
	private int previewStatus;
	private int cineplexId;

	private ArrayList<Seats> listOfSeats;

	/**
	 * Constructor for creation/update of showTime
	 * 
	 * @param listingId
	 * @param movieId
	 * @param cinemaId
	 * @param showTimeId
	 * @param dayType
	 * @param showTime
	 * @param seats
	 * @param startDate
	 * @param endDate
	 * @param ticketPrice
	 * @param prev
	 * @param cineId
	 */
	public ShowTime(int listingId, int movieId, int cinemaId, int showTimeId,
			int dayType, String showTime, int seats, Date startDate,
			Date endDate, double ticketPrice, int prev, int cineId) {
		super();
		this.listingId = listingId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.showTimeId = showTimeId;
		this.dayType = dayType;
		noOfSeats = seats;
		showTimeValue = showTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ticketPrice = ticketPrice;
		this.previewStatus = prev;
		this.cineplexId = cineId;
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

	public int getPreviewStatus() {
		return previewStatus;
	}

	public void setPreviewStatus(int previewStatus) {
		this.previewStatus = previewStatus;
	}

	@Override
	public int compare(ShowTime o1, ShowTime o2) {
		return o1.getShowTimeValue().compareTo(o2.getShowTimeValue());
	}

	public int getCineplexId() {
		return cineplexId;
	}

	public void setCineplexId(int cineplexId) {
		this.cineplexId = cineplexId;
	}

	@Override
	public String toString() {
		return "ShowTime [listingId=" + listingId + ", cinemaId=" + cinemaId
				+ ", movieId=" + movieId + ", showTimeId=" + showTimeId
				+ ", noOfSeats=" + noOfSeats + ", dayType=" + dayType
				+ ", showTimeValue=" + showTimeValue + ", startDate="
				+ startDate + ", endDate=" + endDate + ", ticketPrice="
				+ ticketPrice + ", previewStatus=" + previewStatus
				+ ", cineplexId=" + cineplexId + ", listOfSeats=" + listOfSeats
				+ "]";
	}

}
