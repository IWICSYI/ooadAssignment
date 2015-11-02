package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.HolidayDate;
import data.Prices;
import dataController.DataControl;
import dataController.TicketPriceAndHolidayDataControl;

public class ConfigTicketPriceControl extends DataControl {

	
	public static boolean validatePrice(int choice,double baseprice , double price)
	{
		
		boolean valid = false;
		if(choice>1||choice<7)
		{
			if(price > baseprice)
			{
				valid = true;	
			}
			else
				valid=false;
		
		
		}
		if(choice==7||choice==8)
		{
			if(price < baseprice)
			{
				valid = true;
			}
			else
				valid = false;
		}
		
		return valid;
	}

	public static void updatePrice(Prices prices) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		int id=0;
		
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
	
				
		StringBuilder st =  new StringBuilder() ;
		st.append(prices.getNormal());
		st.append(SEPARATOR);
		st.append(prices.getPlat());
		st.append(SEPARATOR);
		st.append(prices.gettD());
		st.append(SEPARATOR);
		st.append(prices.getBlockbuster());
		st.append(SEPARATOR);
		st.append(prices.getOldPrice());
		st.append(SEPARATOR);
		st.append(prices.getChildPrice());
		st.append(SEPARATOR);
		st.append(prices.getHoli());
		st.append(SEPARATOR);
		st.append(prices.getWeekend());
		
		
		alw.add(st.toString()) ;
		

		writeB("data/ticketPrice.txt",alw);
		
	}






}
