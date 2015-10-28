package data;
//showTimeId|TrueSeatStatus|A1seatName|seatId|transectionId
public class Seats {
	private int seatsInformationId;
	private int cinemaId;
	private int movieId;
	private boolean isOccupied;
	private String seatName;
	private int seatId;
	private int transcationId;
	
	public Seats(){}
	
	
	
	


	public Seats(int seatId2, int movieId2, int cinemaId2, int showTimeId2,
			String seatName2, boolean occupied) {
		seatId=seatId2;
		movieId=movieId2;
		cinemaId=cinemaId2;
		seatsInformationId=showTimeId2;
		seatName=seatName2;
		isOccupied=occupied;
	}






	
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(int transcationId) {
		this.transcationId = transcationId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}






	public int getSeatsInformationId() {
		return seatsInformationId;
	}






	public void setSeatsInformationId(int seatsInformationId) {
		this.seatsInformationId = seatsInformationId;
	}

}
