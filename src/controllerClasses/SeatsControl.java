package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import misc.ObjectContainer;
import data.Movie;
import data.Seats;
import data.ShowTime;

public class SeatsControl extends DataControl {
	
	public ArrayList<ShowTime> readShowTimesBasedOnShowTimeId(int showTime) throws IOException, ParseException{
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
				int preview = Integer.parseInt(star.nextToken().trim());
				
				// add to  list
				
				if(showTimeId==showTime)
				{	
					// add to  list
					ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview);
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	public ArrayList<Seats> readSeats(int showTime) throws IOException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seatsForShowTime"+showTime+".txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		// to store data
		
		
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
	
	
	public void updateSeats(ArrayList<Seats> seatList, ArrayList<Seats> actualSeats) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<Seats> movie=new ArrayList<Seats>();
		int id=0;
		id=id+1;
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
		for(int i=0;i<seatList.size();i++){
			for(int j=0;j<actualSeats.size();j++)
			{
				if(seatList.get(i).getSeatId()==actualSeats.get(j).getSeatId())
				{
					ocu=String.valueOf(actualSeats.get(j).isOccupied());
					break;
				}else{
					ocu=String.valueOf(seatList.get(i).isOccupied());
				}
			}
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
	
	
	
	public Seats searchSeat(ArrayList<ObjectContainer> o,String name)
	{
		Seats a=new Seats();
		for(int i=0;i<o.size();i++)
		{
			
			if(o.get(i).getName().equals(name))
			{		
				
					return o.get(i).getSeatList().get(i);
			}
			
		}
		return null;
	}
	
	public boolean confirmSeats(ArrayList<Seats> selectedSeats)
	{
		ArrayList<String> seatName=new ArrayList<String>();
		int seatNum=0,seatNum2 = 2;
		if(selectedSeats.size()==1)
		{
			return true;
		}
		
		for(int i=0;i<selectedSeats.size();i++)
		{
			System.out.println(selectedSeats.get(i).getSeatName());
			seatName.add(selectedSeats.get(i).getSeatName());
			
		}
		boolean valid=false;
		MiscControl ms=new MiscControl();
		ms.setStringList(seatName);
		ms.sort();
		ArrayList<String> sorted=ms.getStringList();
		int k=1;
		
		for(int i=0;i<sorted.size();i++)
		{
			//System.out.print(sorted.get(i));
			char a=sorted.get(i).toUpperCase().charAt(0);
			//System.out.println("a="+a);
			if (sorted.get(i).length()==2)
			{
				 seatNum=Integer.parseInt(sorted.get(i).substring(1, 2));
				//System.out.println("seatA="+seatNum);
			}
			else if(sorted.get(i).length()==3)
			{
				 seatNum=Integer.parseInt(sorted.get(i).substring(1, 3));
				// System.out.println("seatb="+seatNum);
				
			}
			for(int j=k;j<sorted.size();j++)
			{
				k++;
				if (sorted.get(j).length()==2)
				{
					 seatNum2=Integer.parseInt(sorted.get(j).substring(1, 2));
					// System.out.println("seatA2="+seatNum2);
				}
				else if(sorted.get(j).length()==3)
				{
					 seatNum2=Integer.parseInt(sorted.get(j).substring(1, 3));
						//	 System.out.println("seatB2="+seatNum2);
					
				}
				char b=sorted.get(j).toUpperCase().charAt(0);
				 
				if(a==b && seatNum2!=(seatNum+2)&& seatNum<20 && seatNum>0)
				{
					System.out.println("b="+sorted.get(j)+" a="+sorted.get(i));
					valid=true;
					break;
				}
				else
				{
					System.out.println("Invalid seat combination, there might be a gap between your choosen seats!");
					valid=false;
					break;
				}
			}
		}
		return valid;
	}
		
		
		
		
	}
		
	
