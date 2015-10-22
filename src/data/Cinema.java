package data;

import java.util.ArrayList;

public class Cinema {
	
	public Cinema(int cinplexId, int cinemaId, String cinemaHallName,
			String cinemaType, long seats, ArrayList<ShowTime> showTimes) {
		super();
		this.cinplexId = cinplexId;
		this.cinemaId = cinemaId;
		this.cinemaHallName = cinemaHallName;
		this.cinemaType = cinemaType;
		this.seats = seats;
		this.showTimes = showTimes;
	}

	private int cinplexId;
	private int cinemaId;
	private String cinemaHallName;
	private String cinemaType;
	private long seats;
	private ArrayList<ShowTime> showTimes;
	
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

	public String getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(String cinemaType) {
		this.cinemaType = cinemaType;
	}

	public long getSeats() {
		return seats;
	}

	public void setSeats(long seats) {
		this.seats = seats;
	}

	public ArrayList<ShowTime> getShowTimes() {
		return showTimes;
	}

	public void setShowTimes(ArrayList<ShowTime> showTimes) {
		this.showTimes = showTimes;
	}

}
