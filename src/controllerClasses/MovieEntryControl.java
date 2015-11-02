package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import misc.ObjectContainer;
import data.Movie;
import dataController.DataControl;
import dataController.MovieDataControl;

public class MovieEntryControl extends DataControl {
	
	
	
	

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
