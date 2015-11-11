package data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Movie Schedule object, has reference to movie via movie id.
 * 
 * @author Chang En Kai
 *
 */
public class MovieSchedule {

	public MovieSchedule() {
	}

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

	public MovieSchedule(int movieId, int listingId, Date startDate,
			Date endDate, int typeofDay, int status, int threeDOrNot,
			int blockOrNot, int platOrNot, int previewStatus) {
		super();

		this.movieId = movieId;
		this.listingId = listingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.typeofDay = typeofDay;
		this.status = status;
		this.threeDOrNot = threeDOrNot;
		this.blockBuster = blockOrNot;
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

	/**
	 * 
	 * @return status of movie showing 1=now showing, 2=preview, 3=coming soon,
	 *         4=end of showing
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * set movie showing status 1=now showing, 2=preview, 3=coming soon, 4=end
	 * of showing
	 * 
	 * @param status
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 
	 * @return if movie is in 3D or not. 1=yes 0=no
	 */
	public int getThreeDOrNot() {
		return threeDOrNot;
	}

	/**
	 * set if movie is in 3D or not 1=yes, 0=no
	 * 
	 * @param threeDOrNot
	 */
	public void setThreeDOrNot(int threeDOrNot) {
		this.threeDOrNot = threeDOrNot;
	}

	/**
	 * 
	 * @return if movie is showing in platinum suite or not 1=yes, 0=no
	 */
	public int getPlatOrNot() {
		return platOrNot;
	}

	/**
	 * Set if movie is showing in platinum suite or not, 1=yes, 0=no
	 * 
	 * @param platOrNot
	 */
	public void setPlatOrNot(int platOrNot) {
		this.platOrNot = platOrNot;
	}

	/**
	 * 
	 * @return if movie is a block buster or not 1=yes, 0=now
	 */
	public int getBlockBuster() {
		return blockBuster;
	}

	/**
	 * set if movie is a blockbuster, 1=yes, 0=now
	 * 
	 * @param blockBuster
	 */
	public void setBlockBuster(int blockBuster) {
		this.blockBuster = blockBuster;
	}

	public ArrayList<ShowTime> getShowTimeList() {
		return showTimeList;
	}

	public void setShowTimeList(ArrayList<ShowTime> showTimeList) {
		this.showTimeList = showTimeList;
	}

}
