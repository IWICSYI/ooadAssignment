package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import data.Seats;
import data.SeatsInformation;

public class SeatsDataControl extends DataControl {
	
	public static ArrayList<Seats> readSeats(int seatInfoId) throws IOException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seatsForShowTime"+seatInfoId+".txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int seatId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  seatInforId = Integer.parseInt(star.nextToken().trim());
				String seatName=star.nextToken().trim();
				String ocu = star.nextToken().trim();
				boolean occupied=Boolean.parseBoolean(ocu);
				
				// add to  list
				
				if(seatInforId==seatInfoId)
				{	
					// add to  list
					Seats u = new Seats(seatId,movieId,cinemaId,seatInforId,seatName,occupied);
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	
	public static ArrayList<SeatsInformation> readSeatInfor(int j) throws IOException, ParseException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seatsInformation.txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
//				private int seatInfoId;
				//	private int noOfSeats;
				//	private int noOfEmptySeats;
				//	private int movieId;
				//	private int cinemaId;
				//	private int cineplexId;
				//stid
				//price
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				String startEndDate=star.nextToken().trim();
				Date date=finalDateFormatter.parse(startEndDate);
				String time=star.nextToken().trim();
				int seatInfoId=Integer.parseInt(star.nextToken().trim());
				int  noOfSeats = Integer.parseInt(star.nextToken().trim());
				int  noOfEmptySeats = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  cineplexId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				double  price = Double.parseDouble(star.nextToken().trim());
				
				
				
				
				// add to  list
				
				// add to  list
				if(seatInfoId==j){
					SeatsInformation u = new SeatsInformation(date,time,seatInfoId,noOfSeats,noOfEmptySeats,movieId,cinemaId,cineplexId,showTimeId,price);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}


}
