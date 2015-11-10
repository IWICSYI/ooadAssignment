package data;

import java.io.IOException;

import dataController.CinemaDataControl;
import dataController.CineplexDataControl;
import dataController.MovieDataControl;

/**
 * Transaction object to print receipt. Contains reference to seats information, cinema, movie and cineplex via their IDs.
 * @author Chang En Kai
 *
 */
public class Transaction {

	
	
	

	private String transcationId;
	private String email;
	private String mobilePhone;
	private String custName;
	private int age;
	private int cineplexId;
	private int cinemaId;
	private int movieId;
	private int numOfSeats;
	private String seats;
	private double price;
	private int showtimeId;
	
	public Transaction(String transcationId, String email, String mobilePhone,
			String custName, int age, int cineplexId, int cinemaId,
			int movieId, int numSeat, String seats, double price,int showtimeId) {
		super();
		this.transcationId = transcationId;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.custName = custName;
		this.age = age;
		this.cineplexId = cineplexId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.numOfSeats = numSeat;
		this.seats = seats;
		this.price = price;
		this.setShowtimeId(showtimeId);
	}

	
	public Transaction(String transcationId, String email, String mobilePhone,
			String custName, int age, int cineplexId, int cinemaId, int movie, double price2) {
		super();
		this.transcationId = transcationId;
		this.email = email;
		this.mobilePhone = mobilePhone;
		this.custName = custName;
		this.age = age;
		this.cineplexId = cineplexId;
		this.cinemaId = cinemaId;
		this.movieId = movie;
		this.price=price2;
	}
	
	
	public String getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(String transcationId) {
		this.transcationId = transcationId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getCineplexId() {
		return cineplexId;
	}
	public void setCineplexId(int cineplexId) {
		this.cineplexId = cineplexId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movie) {
		this.movieId = movie;
	}


	public int getNumSeat() {
		return numOfSeats;
	}


	public void setNumSeat(int numSeat) {
		this.numOfSeats = numSeat;
	}


	public String getSeats() {
		return seats;
	}


	public void setSeats(String seats) {
		this.seats = seats;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getShowtimeId() {
		return showtimeId;
	}


	public void setShowtimeId(int showtimeId) {
		this.showtimeId = showtimeId;
	}


	public void printTranscation() throws IOException {
		
		Movie m=MovieDataControl.readMovieBasedOnId(movieId);
		Cineplex c=CineplexDataControl.readCineplexBasedonCinplexId(cineplexId);
		Cinema ch=CinemaDataControl.readCinemaByCinemaId(cinemaId);
		System.out.println("Transcation Id:"+transcationId);
		System.out.println("Customer Name:"+custName);
		System.out.println("Mobile Phone:"+mobilePhone);
		System.out.println("Email:"+email);
		System.out.println("Movie Name:"+m.getMovieName());
		System.out.println("Cineplex:"+c.getCineplexName());
		System.out.println("Cinema hall:"+ch.getCinemaHallName());
		System.out.println("Seats:"+seats);
		System.out.println("Total Price:"+(price*numOfSeats));
		
		
		
	}
	
}
