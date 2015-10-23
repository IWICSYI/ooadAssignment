package data;

import java.util.*;

public class Movie {
	
	private int movieId;
	private String movieName;	
	private int movieType;
	private String ageRating;
	private String director;
	private String synopsis;
	private String cast;
	private double overallRating;
	private long ticketSales;
	private int movieLength;
	private ArrayList<Reviews> reviewList;
	
	public Movie(){}
	
	public Movie(int movieId, String movieName, int movieType,
			String ageRating, String director, String synopsis, String cast,
			double overallRating, long ticketSales, int movieLength) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieType = movieType;
		this.ageRating = ageRating;
		this.director = director;
		this.synopsis = synopsis;
		this.cast = cast;
		this.overallRating = overallRating;
		this.ticketSales = ticketSales;
		this.movieLength = movieLength;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public int getMovieType() {
		return movieType;
	}
	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}
	public String getAgeRating() {
		return ageRating;
	}
	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public double getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(double overallRating) {
		this.overallRating = overallRating;
	}
	public long getTicketSales() {
		return ticketSales;
	}
	public void setTicketSales(long ticketSales) {
		this.ticketSales = ticketSales;
	}
	public int getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(int movieLength) {
		this.movieLength = movieLength;
	}
	public ArrayList<Reviews> getReviewList() {
		return reviewList;
	}
	public void setReviewList(ArrayList<Reviews> reviewList) {
		this.reviewList = reviewList;
	}
}
	

	