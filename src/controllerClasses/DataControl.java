package controllerClasses;

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import data.*;

public class DataControl {
	
	private static String[] fileName = { "data/loginDetails.txt",
		"data/movies.txt", "data/table.txt", "data/staff.txt", "data/reservation.txt", "data/order.txt"};
	private static final String SEPARATOR = "|";
	
	 private static ArrayList<String> read(String fileName) throws IOException {
			ArrayList<String> data = new ArrayList<String>() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }
	 
	public ArrayList<User> readLogin() throws IOException{
		
		ArrayList stringArray = (ArrayList)read(fileName[0]);
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"

				String  username = star.nextToken().trim();	// first token
				String  password = star.nextToken().trim();	// second token
				// create Admin User object from file data
				User u = new User(username,password);
				// add to  list
				alr.add(u) ;
			}
			return alr ;
	}
	
public ArrayList<User> readMovie() throws IOException{
	//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		ArrayList stringArray = (ArrayList)read(fileName[0]);
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
				// create Admin User object from file data
				//Movie m = new Movie(username,password);
				// add to  list
				//alr.add(u) ;
			}
			return alr ;
	}
		
	

}
