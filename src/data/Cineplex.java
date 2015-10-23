package data;

import java.util.ArrayList;

public class Cineplex {
	
	public Cineplex(int cinplexId, String cineplexName) {
		super();
		this.cinplexId = cinplexId;
		this.cineplexName = cineplexName;
	}

	private int cinplexId;
	private String cineplexName;
	private ArrayList<Cinema> cinemaList;

	public Cineplex() {
		
	}

	public int getCinplexId() {
		return cinplexId;
	}

	public void setCinplexId(int cinplexId) {
		this.cinplexId = cinplexId;
	}

	public String getCineplexName() {
		return cineplexName;
	}

	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}

	public void setCinemaList(ArrayList<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}
	
	
	

}
