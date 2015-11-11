package data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Review Object, contains reference to movie object via movieId.
 * 
 * @author Chang En Kai
 *
 */
public class Review {

	private int movieId;
	private int reviewId;
	private String reviewerName;
	private String review;
	private double score;
	private Date reviewDate;

	public Review() {
	}

	public Review(int reviewId, int movieId, String reviewerName,
			String review, double score, Date reviewDate) {
		// super();
		this.movieId = movieId;
		this.reviewId = reviewId;
		this.reviewerName = reviewerName;
		this.review = review;
		this.score = score;
		this.reviewDate = reviewDate;
	}

	// Get details
	public void getString() {
		// String scoreStar = "*";
		// for(int i=0; i<=score;i++)
		// scoreStar+=scoreStar;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println("\nReview ID:" + reviewId + " \nScore:" + score
				+ "\n By:" + reviewerName + "\nDate:" + sdf.format(reviewDate)
				+ "\nReview:\n" + review);

	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
}
