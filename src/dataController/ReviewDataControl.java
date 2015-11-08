package dataController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.Movie;
import data.Review;

public class ReviewDataControl extends DataControl {


	public static ArrayList<Review> readReview() throws IOException, ParseException{
	//reviewid|movieId|reviewerName|review|score|reviewDate
		ArrayList stringArray = (ArrayList)read("data/reviews.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int reviewId=Integer.parseInt(star.nextToken().trim());
				int movieId = Integer.parseInt(star.nextToken().trim());
				String reviewerName = star.nextToken().trim();
				String review = star.nextToken().trim();
				double score = Double.parseDouble(star.nextToken().trim());
				String reviewDateString = star.nextToken().trim();
				DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
				Date reviewDate = format.parse(reviewDateString);

				// create Admin User object from file data
				Review r = new Review(reviewId, movieId, reviewerName, review, score, reviewDate);
				// add to  list
				alr.add(r) ;
				//System.out.println(stringArray.size()+st);
			}
        
        
			return alr ;
	}
	
	
	
	public static ArrayList<Review> readReviewBasedOnMovieId(int movieIdC) throws IOException, ParseException{
		//reviewid|movieId|reviewerName|review|score|reviewDate
			ArrayList stringArray = (ArrayList)read("data/reviews.txt");
			ArrayList alr = new ArrayList() ;// to store data

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
					int reviewId=Integer.parseInt(star.nextToken().trim());
					int movieId = Integer.parseInt(star.nextToken().trim());
					String reviewerName = star.nextToken().trim();
					String review = star.nextToken().trim();
					double score = Double.parseDouble(star.nextToken().trim());
					String reviewDateString = star.nextToken().trim();
					DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
					Date reviewDate = format.parse(reviewDateString);

					// create Admin User object from file data
					Review r = new Review(reviewId, movieId, reviewerName, review, score, reviewDate);
					// add to  list
					if(movieId==movieIdC)
					{
						alr.add(r) ;
					}
					//System.out.println(stringArray.size()+st);
				}
	        
	        
				return alr ;
		}
	

	public static void createReview(Review m) throws IOException, ParseException {
		List alw = new ArrayList() ;
		ArrayList<Review> review=new ArrayList<Review>();
		review=readReview();
		int id=review.size()+1;
		
		//Create review data in review.txt in order of: reviewid|movieId|reviewerName|review|score|reviewDate
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		String reviewDate=df.format(m.getReviewDate());
			
		StringBuilder st =  new StringBuilder() ;
		st.append(id);
		st.append(SEPARATOR);
		st.append(m.getMovieId());
		st.append(SEPARATOR);
		st.append(m.getReviewerName());
		st.append(SEPARATOR);
		st.append(m.getReview());
		st.append(SEPARATOR);
		st.append(m.getScore());
		st.append(SEPARATOR);
		st.append(reviewDate);
		//st.append("\n");
		alw.add(st.toString()) ;
		

		write("data/reviews.txt",alw);
	}

	//method for printing list of reviews based on current movie id

	
	//method for updating movie avg ratings
	public static void updateMovieReviewAverageScore(int movieId) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		int id=0;
		double avgRating=0;
		
//		//String updateDate=finalDateFormatter.format(hD.getHolidayDate());
//		
//		Movie movie= new ArrayList<Movie>();
//		movie=readMovie();
//
////write a new method readReviewByMovieId(int movieId), example of how to write one can be found in readMovieBasedOnId but //you need to add a list to store a bunch or reviews and return an arraylist of review instead because one movie id can have lots of //reviews. This will be way easier because you will be sure that you are only dealing with one movie for the review.
//               
//		ArrayList<Review> review= new ArrayList<Review>();
//		review=readReviewByMovieId(movieId); 
		ArrayList<Movie> movie= new ArrayList<Movie>();
		movie=MovieDataControl.readMovie();
		ArrayList<Review> review= new ArrayList<Review>();
		review=readReviewBasedOnMovieId(movieId);



		//find average rating of all reviews of a particular movie
		for(int i=0;i<review.size();i++)
		{
				avgRating=(avgRating+review.get(i).getScore());
				
			
		}
		if(review.size()<=1){
        	avgRating=0;
        }
        else{
            avgRating=avgRating/review.size();
        }
		//update by appending all variables with themselves and changing only the avg rating
		for(int i=0;i<movie.size();i++)
		{		
			StringBuilder st =  new StringBuilder() ;
			if(movieId == movie.get(i).getMovieId()){
					st.append(movie.get(i).getMovieId());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieName());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieType());
					st.append(SEPARATOR);
					st.append(movie.get(i).getAgeRating());
					st.append(SEPARATOR);
					st.append(movie.get(i).getDirector());
					st.append(SEPARATOR);
					st.append(movie.get(i).getSynopsis());
					st.append(SEPARATOR);
					st.append(movie.get(i).getCast());
					st.append(SEPARATOR);
					st.append(avgRating);
					st.append(SEPARATOR);
					st.append(movie.get(i).getTicketSales());
					//st.append(movie.get(i).ticketSales());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieLength());
					st.append(SEPARATOR);
					st.append(movie.get(i).getBlockbuster());
					st.append("\n"); //append new line, if not the string will only be one row.
					alw.add(st.toString()) ;
			}
			else
			{
                    st.append(movie.get(i).getMovieId());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieName());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieType());
					st.append(SEPARATOR);
					st.append(movie.get(i).getAgeRating());
					st.append(SEPARATOR);
					st.append(movie.get(i).getDirector());
					st.append(SEPARATOR);
					st.append(movie.get(i).getSynopsis());
					st.append(SEPARATOR);
					st.append(movie.get(i).getCast());
					st.append(SEPARATOR);
					st.append(movie.get(i).getOverallRating());
					st.append(SEPARATOR);
					st.append(movie.get(i).getTicketSales());
					st.append(SEPARATOR);
					st.append(movie.get(i).getMovieLength());
					st.append(SEPARATOR);
					st.append(movie.get(i).getBlockbuster());
                    st.append("\n");
					alw.add(st.toString()) ;

			}

		writeB("data/movies.txt",alw);
	}
}

	
}
