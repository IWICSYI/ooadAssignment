package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	private int cinplexId;
	private int cinemaId;
	private String cinemaHallName;
	private int cinemaType;
	private int seats;
	private ArrayList<ShowTime> showTimes;
	
	
	public Cinema(int cinplexId, int cinemaId, String cinemaHallName,
			int cinemaType, int seats) {
		super();
		this.cinplexId = cinplexId;
		this.cinemaId = cinemaId;
		this.cinemaHallName = cinemaHallName;
		this.cinemaType = cinemaType;
		this.seats = seats;
		
	}

	
	public Cinema() {
		// TODO Auto-generated constructor stub
	}

	public int getCinplexId() {
		return cinplexId;
	}

	public void setCinplexId(int cinplexId) {
		this.cinplexId = cinplexId;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public String getCinemaHallName() {
		return cinemaHallName;
	}

	public void setCinemaHallName(String cinemaHallName) {
		this.cinemaHallName = cinemaHallName;
	}

	public int getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(int cinemaType) {
		this.cinemaType = cinemaType;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(ArrayList<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}

}
