package dataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import data.Cinema;

/**
 * Class used for the actual CRUD of cinema.
 * @author Chang En Kai
 *
 */
public class CinemaDataControl extends DataControl{
	
	/**
	 * Read all cinema
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Cinema> readCinema() throws IOException{
	
		
		ArrayList stringArray = (ArrayList)read("data/cinemas.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cineplexId=Integer.parseInt(star.nextToken().trim());
				
				int  cinemaId = Integer.parseInt(star.nextToken().trim());	
				String cinemaName=star.nextToken().trim();
				int cinemaType=Integer.parseInt(star.nextToken().trim());	
				int seats=Integer.parseInt(star.nextToken().trim());	
				Cinema u = new Cinema(cineplexId,cinemaId,cinemaName,cinemaType,seats);
				// add to  list
				alr.add(u) ;
				
			}
			return alr ;
	}
	
	/**
	 * Read one cinema by its unique id
	 * @param cId
	 * @return
	 * @throws IOException
	 */
	public static  Cinema readCinemaByCinemaId(int cId) throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/cinemas.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cineplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());	
				String cinemaName=star.nextToken().trim();
				int cinemaType=Integer.parseInt(star.nextToken().trim());	
				int seats=Integer.parseInt(star.nextToken().trim());	
				
				if(cinemaId==cId)
				{
					Cinema u = new Cinema(cineplexId,cinemaId,cinemaName,cinemaType,seats);
					// add to  list
					return u ;
				}
			}
			return null ;
	}
	

	/**
	 * Read all cinema in 1 cineplex
	 * @param cId
	 * @return
	 * @throws IOException
	 */
	public static  ArrayList<Cinema> readCinemaByCineplexId(int cId) throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/cinemas.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int cineplexId=Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());	
				String cinemaName=star.nextToken().trim();
				int cinemaType=Integer.parseInt(star.nextToken().trim());	
				int seats=Integer.parseInt(star.nextToken().trim());	
				
				if(cineplexId==cId)
				{
					Cinema u = new Cinema(cineplexId,cinemaId,cinemaName,cinemaType,seats);
					// add to  list
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	

}
