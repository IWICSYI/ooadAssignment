package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.StringTokenizer;

import data.ShowTime;

public class BuyTicketControl extends MovieListingControl {
	
	public int chooseCineplexToDisplay(ArrayList<Integer> cineId)
	{
		int a=0,b=0,c=0;
		for(int i=0;i<cineId.size();i++)
		{
			if(cineId.get(i)==1)
			{
				a++;
			}
			else if(cineId.get(i)==2)
			{
				b++;
			}
			else if(cineId.get(i)==3){
				c++;
			}
		}
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		tmp.add(a);
		tmp.add(b);
		tmp.add(c);
		int largest = Collections.max(tmp);
		if(largest==a)
		{
			return 1;
		}
		else if(largest==b)
		{
			return 2;
		}
		else if(largest==c)
		{
			return 3;
		}
		return 0;
		
	}
	
	

	
	public ArrayList<ShowTime> readShowTimesBasedOnListingId(int movieIDCheck,int cinePlexId,int dayT) throws IOException, ParseException{
		ArrayList stringArray = (ArrayList)read("data/showTimes.txt");
		ArrayList alr = new ArrayList() ;// to store data
		
		
//1ListingId|1movieId|1cinemaid|1showTimeId|3noOfShowTimes|showTime1|showTime2|showTime3
		 for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				System.out.println((String)stringArray.get(i));
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
				
				
				
				
				
				ShowTime u = new ShowTime(listingId,movieId,cinemaId,showTimeId,dayType,showTimeValue,seats,startDate,endDate,price,preview);
				// add to  list
				
				if(movieId==movieIDCheck && dayType==dayT &&cinePlexId==listingId)
				{	
					// add to  list
					alr.add(u) ;
				}
				
			}
			return alr ;
	}
	

}
