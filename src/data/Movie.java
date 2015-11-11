package data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Movie {

	private int movieId;
	private String movieName;
	/**
	 * 3D, to check if it is in 3D or not
	 */
	private int threeD;
	/**
	 * Age rating (PG13)
	 */
	private String ageRating;

	private String director;
	private String synopsis;
	private String cast;
	private double overallRating;
	private long ticketSales;
	private int movieLength;

	/**
	 * To check if movie is block buster or not
	 */
	private int blockbuster;

	public Movie() {
	}

	public Movie(int movieId, String movieName, int threeD, String ageRating,
			String director, String synopsis, String cast,
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
		blockbuster = block;
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

	/**
	 * 
	 * @return if movie is in 3D or not, 1=is/0=not
	 */
	public int getThreeD() {
		return threeD;
	}

	public void setThreeD(int threeD) {
		this.threeD = threeD;
	}

	/**
	 * 
	 * @return if movie is blockbuster or not(1=is/0=not)
	 */
	public int getBlockbuster() {
		return blockbuster;
	}

	public void setBlockbuster(int blockbuster) {
		this.blockbuster = blockbuster;
	}

	public void printMovieDetails() {
		NumberFormat formatter = new DecimalFormat("#0.00");     
		System.out.println("\nMovie Name:" + movieName);
		System.out.println("Age Rating:" + ageRating);
		System.out.println("Director:" + director);
		System.out.println("\nCast:" + cast);
		System.out.print("\nSynopsis:\n");
		String[] strArr = synopsis.split(" ");
		for (int i = 0; i < strArr.length; i++) {
			System.out.print(strArr[i] + " ");
			if ((i + 1) % 8 == 0) {
				System.out.println(" ");
			}
		}
		System.out.println();
		String rating = formatter.format(overallRating);
		if (overallRating <= 0) {
			rating = "N/A";
		}

		System.out.println("\nOverall Rating:" + rating + "\n");

	}
}
