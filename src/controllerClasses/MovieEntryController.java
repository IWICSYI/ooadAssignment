package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import misc.ObjectContainer;
import data.Movie;

public class MovieEntryController extends DataControl {
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
	public MovieEntryController() {
		// TODO Auto-generated constructor stub
		
		
	}

	public void createMovie(Movie m) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<Movie> movie=new ArrayList<Movie>();
		movie=readMovie();
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

	public void retrieveFullMovieList(ArrayList<ObjectContainer> pair) throws IOException
	{
		ArrayList<Movie> movieList= new ArrayList<Movie>();
		movieList=readMovie();
		MiscControl vl=new MiscControl();
		
		for(int i=0;i<movieList.size();i++)
		{
			System.out.print((i+1)+":"+movieList.get(i).getMovieName()+"	");
			if(i<5)
				System.out.println();
			
			pair.add(vl.idPairerWithName(i, movieList.get(i).getMovieId(), movieList.get(i).getMovieName()));
		}
	}

}
