package dataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import controllerClasses.MiscControl;
import misc.ObjectContainer;
import data.Movie;

public class MovieDataControl extends DataControl {
/*
 * Need to add a method that goes like this
 * switch case to choose cineplex
 * switch case to choose cinemahall and rechoose cineplex
 * once cinema is choosen , query day and display movies and their showtime in a table by weeks
 * 
 * Create method to create new movie(specify show times and whether still showing and such)
 * 
 * Update method to update movie
 * 
 * Remove method to remove movie
 * 
 */
	public MovieDataControl() {
		// TODO Auto-generated constructor stub
		
		
	}

	
	
	
	public static ArrayList<Movie> readMovie() throws IOException{
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
			ArrayList stringArray = (ArrayList)read("data/movies.txt");
			ArrayList alr = new ArrayList() ;// to store data

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
					int movieId=Integer.parseInt(star.nextToken().trim());
					String  movieName = star.nextToken().trim();	
					int  movieType = Integer.parseInt(star.nextToken().trim());
					String  ageRating = star.nextToken().trim();
					String  director = star.nextToken().trim();
					String  synopsis = star.nextToken().trim();
					String  cast = star.nextToken().trim();
					double  overallRating=Double.parseDouble(star.nextToken().trim());
					long ticketSales=Long.parseLong(star.nextToken().trim());
					int time=Integer.parseInt(star.nextToken().trim());
					int block=Integer.parseInt(star.nextToken().trim());

					// create Admin User object from file data
					Movie m = new Movie(movieId,movieName,movieType,ageRating,director,synopsis,cast,overallRating,ticketSales,time,block);
					// add to  list
					alr.add(m) ;
					//System.out.println(stringArray.size()+st);
				}
	        
	        
				return alr ;
		}
		
		public static  Movie readMovieBasedOnId(int moveId) throws IOException{
			//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
				ArrayList stringArray = (ArrayList)read("data/movies.txt");
				ArrayList alr = new ArrayList() ;// to store data

		        for (int i = 0 ; i < stringArray.size() ; i++) {
						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
						int movieId=Integer.parseInt(star.nextToken().trim());
						String  movieName = star.nextToken().trim();	
						int  movieType = Integer.parseInt(star.nextToken().trim());
						String  ageRating = star.nextToken().trim();
						String  director = star.nextToken().trim();
						String  synopsis = star.nextToken().trim();
						String  cast = star.nextToken().trim();
						double  overallRating=Double.parseDouble(star.nextToken().trim());
						long ticketSales=Long.parseLong(star.nextToken().trim());
						int time=Integer.parseInt(star.nextToken().trim());
						int block=Integer.parseInt(star.nextToken().trim());

						// create Admin User object from file data
						// add to  list
						
						if(movieId==moveId){
							Movie m = new Movie(movieId,movieName,movieType,ageRating,director,synopsis,cast,overallRating,ticketSales,time,block);
							return m ;
						}
						
						//System.out.println(stringArray.size()+st);
					}
				return null;
		        
		        
					
			}
		
		
		

		public static void createMovie(Movie m) throws IOException {
			List alw = new ArrayList() ;// to store Professors data
			ArrayList<Movie> movie=new ArrayList<Movie>();
			movie=MovieDataControl.readMovie();
			int id=0;
			if(movie.isEmpty())
			{
				id=1;
			}else
			{
				id=movie.get(movie.size()-1).getMovieId()+1;
			}
			//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

					
			StringBuilder st =  new StringBuilder() ;
			st.append(id);
			st.append(SEPARATOR);
			st.append(m.getMovieName());
			st.append(SEPARATOR);
			st.append(m.getMovieType());
			st.append(SEPARATOR);
			st.append(m.getAgeRating());
			st.append(SEPARATOR);
			st.append(m.getDirector());
			st.append(SEPARATOR);
			st.append(m.getSynopsis());
			st.append(SEPARATOR);
			st.append(m.getCast());
			st.append(SEPARATOR);
			st.append(-1.1);
			st.append(SEPARATOR);
			st.append(0);
			st.append(SEPARATOR);
			st.append(m.getMovieLength());
			st.append(SEPARATOR);
			st.append(m.getBlockbuster());
			//st.append("\n");
			alw.add(st.toString()) ;
			

			write("data/movies.txt",alw);
		}

		public static void updateMovie(Movie m) throws IOException {
			
			List alw = new ArrayList() ;// to store Professors data
			ArrayList<Movie> movie=new ArrayList<Movie>();
			movie=MovieDataControl.readMovie();
			
			//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

			for(int i=0;i<movie.size();i++)	
			{
				StringBuilder st =  new StringBuilder() ;
				if(m.getMovieId()!=movie.get(i).getMovieId()){
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
				else
				{
					st.append(m.getMovieId());
					st.append(SEPARATOR);
					st.append(m.getMovieName());
					st.append(SEPARATOR);
					st.append(m.getMovieType());
					st.append(SEPARATOR);
					st.append(m.getAgeRating());
					st.append(SEPARATOR);
					st.append(m.getDirector());
					st.append(SEPARATOR);
					st.append(m.getSynopsis());
					st.append(SEPARATOR);
					st.append(m.getCast());
					st.append(SEPARATOR);
					st.append(m.getOverallRating());
					st.append(SEPARATOR);
					st.append(m.getTicketSales());
					st.append(SEPARATOR);
					st.append(m.getMovieLength());
					st.append(SEPARATOR);
					st.append(m.getBlockbuster());
					st.append("\n");
					alw.add(st.toString()) ;
				}
			}
				
			

			writeB("data/movies.txt",alw);
			// TODO Auto-generated method stub
			
		}


}
