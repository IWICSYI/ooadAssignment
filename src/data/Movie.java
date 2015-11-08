package data;

import java.io.Serializable;
import java.util.*;

public class Movie implements Serializable{
	
	private int movieId;
	private String movieName;	
	private int threeD;
	private String ageRating;
	private String director;
	private String synopsis;
	private String cast;
	private double overallRating;
	private long ticketSales;
	private int movieLength;
	private int blockbuster;
	private ArrayList<Review> reviewList;
	
	public Movie(){}
	
	public Movie(int movieId, String movieName, int threeD,
			String ageRating, String director, String synopsis, String cast,
			double overallRating, long ticketSales, int movieLength, int block) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.threeD = threeD;
		this.ageRating = ageRating;
		this.director = director;
		this.synopsis = synopsis;
		this.cast = cast;
		this.overallRating = overallRating;
		this.ticketSales = ticketSales;
		this.movieLength = movieLength;
		blockbuster=block;
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
		return threeD;
	}
	public void setMovieType(int movieType) {
		this.threeD = movieType;
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
	public ArrayList<Review> getReviewList() {
		return reviewList;
	}
	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public int getThreeD() {
		return threeD;
	}

	public void setThreeD(int threeD) {
		this.threeD = threeD;
	}

	public int getBlockbuster() {
		return blockbuster;
	}

	public void setBlockbuster(int blockbuster) {
		this.blockbuster = blockbuster;
	}
	
	
	public void printMovieDetails(){
		System.out.println("\nMovie Name:"+movieName);
		System.out.println("Age Rating:"+ageRating);
		System.out.println("Director:"+director);
		System.out.println("\nCast:"+cast);
		System.out.print("\nSynopsis:");
		String[] strArr = synopsis.split(" ");
		for(int i=0;i<strArr.length;i++){
			System.out.print(strArr[i]+" ");
			if((i+1)%8==0){
				System.out.println(" ");
			}
		}
		System.out.println();
		String rating=String.valueOf(overallRating);
		if(overallRating<=0){
			 rating="N/A";
		}
	
			
		System.out.println("\nOverall Rating:"+rating+"\n");
	
	}
}
	

	