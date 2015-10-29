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
		int id=movie.size()+1;
		
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
		//st.append("\n");
		alw.add(st.toString()) ;
		

		write("data/movies.txt",alw);
	}

}
