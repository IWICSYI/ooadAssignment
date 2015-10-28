package dataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import data.Cineplex;

public class CineplexDataControl extends DataControl {

	
public static  ArrayList<Cineplex> readCineplex() throws IOException{
		ArrayList stringArray = (ArrayList)read("data/cineplexes.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

				int cineplexId=Integer.parseInt(star.nextToken().trim());
				String  cineplexName = star.nextToken().trim();	
				
				Cineplex u = new Cineplex(cineplexId,cineplexName);
				// add to  list
				alr.add(u) ;
			}
			return alr ;
	}



public static  ArrayList<Cineplex> readCineplexBasedonCinplexId(int id) throws IOException{
	ArrayList stringArray = (ArrayList)read("data/cineplexes.txt");
	ArrayList alr = new ArrayList() ;// to store data

    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

			int cineplexId=Integer.parseInt(star.nextToken().trim());
			String  cineplexName = star.nextToken().trim();	
			if(cineplexId==id){
			Cineplex u = new Cineplex(cineplexId,cineplexName);
			// add to  list
			alr.add(u) ;}
		}
		return alr ;
}
}
