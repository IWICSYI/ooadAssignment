package data;
/**
 * Cinema object
 * @author Chang En Kai
 *
 */
public class Cinema {

	
	private int cinplexId;
	private int cinemaId;
	private String cinemaHallName;
	private int cinemaType;
	private int seats;

	/**
	 * Constructor for cinema, use by data controller to read cinema details.
	 * 
	 * @param cinplexId
	 * @param cinemaId
	 * @param cinemaHallName
	 * @param cinemaType
	 * @param seats
	 */
	public Cinema(int cinplexId, int cinemaId, String cinemaHallName,
			int cinemaType, int seats) {
		super();
		this.cinplexId = cinplexId;
		this.cinemaId = cinemaId;
		this.cinemaHallName = cinemaHallName;
		this.cinemaType = cinemaType;
		this.seats = seats;

	}

	/**
	 * Empty constructor
	 */
	public Cinema() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return cineplexId
	 */
	public int getCinplexId() {
		return cinplexId;
	}

	/**
	 * 
	 * @param cinplexId
	 */
	public void setCinplexId(int cinplexId) {
		this.cinplexId = cinplexId;
	}

	/**
	 * 
	 * @return cinemaId
	 */
	public int getCinemaId() {
		return cinemaId;
	}

	/**
	 * 
	 * @param cinemaId
	 */
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	/**
	 * 
	 * @return cinemaHallName
	 */
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

}
