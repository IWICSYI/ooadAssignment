package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.Movie;
import data.Seats;
import data.ShowTime;

public class SeatsControl extends DataControl {
	
	public ArrayList<ShowTime> readShowTimesBasedOnShowTimeId(int showTime) throws IOException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int listingId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				int  dayType = Integer.parseInt(star.nextToken().trim());
				String showTimeValue=star.nextToken().trim();
				int seats = Integer.parseInt(star.nextToken().trim());
				String startDateString=star.nextToken().trim();
				Date startDate=finalDateFormatter.parse(startDateString);
				String endDateString=star.nextToken().trim();
				Date endDate=finalDateFormatter.parse(endDateString);
				double price=Double.parseDouble(star.nextToken().trim());
				
				
				// add to  list
				
				if(showTimeId==showTime)
				{	
					// add to  list
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price);
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	public ArrayList<Seats> readSeats(int showTime) throws IOException{
		ArrayList stringArray = (ArrayList)read("data/seatsForShowTime"+showTime+".txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int seatId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				String seatName=star.nextToken().trim();
				String ocu = star.nextToken().trim();
				boolean occupied=Boolean.parseBoolean(ocu);
				
				// add to  list
				
				if(showTimeId==showTime)
				{	
					// add to  list
					Seats u = new Seats(seatId,movieId,cinemaId,showTimeId,seatName,occupied);
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	
	
	

	public void createSeats(ArrayList<Seats> seatList) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<Seats> movie=new ArrayList<Seats>();
		int id=0;
		id=id+1;
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
		for(int i=0;i<seatList.size();i++){
			ocu=String.valueOf(seatList.get(i).isOccupied());
		
			st.append(seatList.get(i).getSeatId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getCinemaId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getShowTimeId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getSeatName());
			st.append(SEPARATOR);
			st.append(ocu);
			st.append("\n");
		}
			
		//st.append("\n");
		alw.add(st.toString()) ;
		

		writeB("data/seatsForShowTime"+seatList.get(0).getShowTimeId()+".txt",alw);
		
	}

}
