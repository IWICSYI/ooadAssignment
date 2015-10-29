package dataController;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import data.HolidayDate;
import data.Prices;

public class TicketPriceAndHolidayDataControl extends DataControl {
	

	public static ArrayList<Prices> readPrice() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/ticketPrice.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				//normal price=8*platinum=6*3D=1*blockbuster=*0.5*Age65>-2*holidayPrice=2*weekEndPrice=2
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				double normal=Double.parseDouble(star.nextToken().trim());
				double  plat = Double.parseDouble(star.nextToken().trim());	
				double tD=Double.parseDouble(star.nextToken().trim());
				double blockbuster=Double.parseDouble(star.nextToken().trim());
				double age=Double.parseDouble(star.nextToken().trim());
				double child=Double.parseDouble(star.nextToken().trim());
				double holi=Double.parseDouble(star.nextToken().trim());
				double weekend=Double.parseDouble(star.nextToken().trim());
				
				
					Prices u = new Prices(normal,plat,tD,blockbuster,age,child,holi,weekend);
					// add to  list
					alr.add(u) ;
				}
        return alr ;
			}
			
	
public static ArrayList<HolidayDate> readHoliday() throws IOException, ParseException{
		
		ArrayList stringArray = (ArrayList)read("data/holidayDates.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				//normal price=8*platinum=6*3D=1*blockbuster=*0.5*Age65>-2*holidayPrice=2*weekEndPrice=2
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int holiId=Integer.parseInt(star.nextToken());
				String date=star.nextToken();
				Date holiday=finalDateFormatter.parse(date);
				
				
					HolidayDate u = new HolidayDate(holiId,holiday);
					// add to  list
					alr.add(u) ;
				}
        return alr ;
			}
	


}