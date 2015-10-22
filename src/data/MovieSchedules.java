package data;
import data.Cineplex;
import data.Movie;
import java.util.*;
//1cineplexId|1movieUniqueId|4listingId|startDate|endDate|1typeofDay|status
public class MovieSchedules {
	private int cineplexId;
	private int movieId;
	private int listingId;
	private Date startDate;
	private Date endDate;
	private int typeofDay;
	private int status;
	private ArrayList<ShowTime> showTimeList;
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

}
