package data;
import data.Cineplex;
import data.Movie;

import java.io.Serializable;
import java.util.*;
//1cineplexId|1movieUniqueId|4listingId|startDate|endDate|1typeofDay|status
public class MovieSchedule implements Serializable{
	public MovieSchedule(){}
	

	private int cineplexId;
	private int cinemaId;
	private int movieId;
	private int listingId;
	private Date startDate;
	private Date endDate;
	private int typeofDay;
	private int status;
	private int threeDOrNot;
	private int blockBuster;
	private int platOrNot;
	private int previewStatus;
	
	private ArrayList<ShowTime> showTimeList;
	
	
	
	public MovieSchedule(int cineplexId, int cinemaId, int movieId,
			int listingId, Date startDate, Date endDate, int typeofDay,
			int status, int threeDOrNot,int blockOrNot, int platOrNot, int previewStatus) {
		super();
		this.cineplexId = cineplexId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.listingId = listingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeofDay = typeofDay;
		this.status = status;
		this.threeDOrNot = threeDOrNot;
		this.blockBuster=blockOrNot;
		this.platOrNot = platOrNot;
		this.previewStatus = previewStatus;
	}
	public int getPreviewStatus() {
		return previewStatus;
	}
	public void setPreviewStatus(int previewStatus) {
		this.previewStatus = previewStatus;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
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

	public int getThreeDOrNot() {
		return threeDOrNot;
	}

	public void setThreeDOrNot(int threeDOrNot) {
		this.threeDOrNot = threeDOrNot;
	}

	public int getPlatOrNot() {
		return platOrNot;
	}

	public void setPlatOrNot(int platOrNot) {
		this.platOrNot = platOrNot;
	}
	public int getBlockBuster() {
		return blockBuster;
	}
	public void setBlockBuster(int blockBuster) {
		this.blockBuster = blockBuster;
	}

}
