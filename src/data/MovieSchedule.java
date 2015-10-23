package data;
import data.Cineplex;
import data.Movie;

import java.util.*;
//1cineplexId|1movieUniqueId|4listingId|startDate|endDate|1typeofDay|status
public class MovieSchedule {
	public MovieSchedule(){}
	

	private int cineplexId;
	private int cinemaId;
	private int movieId;
	private int listingId;
	private String startDate;
	private String endDate;
	private int typeofDay;
	private int status;
	private ArrayList<ShowTime> showTimeList;
	
	public MovieSchedule(int cineplexId, int movieId,
			int listingId, String startDate, String endDate, int typeofDay,
			int status) {
		super();
		this.cineplexId = cineplexId;
		this.movieId = movieId;
		this.listingId = listingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeofDay = typeofDay;
		this.status = status;
	}
	
	public int getCineplexId() {
		return cineplexId;
	}
	public void setCineplexId(int cineplexId) {
		this.cineplexId = cineplexId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getListingId() {
		return listingId;
	}
	public void setListingId(int listingId) {
		this.listingId = listingId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getTypeofDay() {
		return typeofDay;
	}
	public void setTypeofDay(int typeofDay) {
		this.typeofDay = typeofDay;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ShowTime> getShowTimeList() {
		return showTimeList;
	}
	public void setShowTimeList(ArrayList<ShowTime> showTimeList) {
		this.showTimeList = showTimeList;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

}
