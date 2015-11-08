package controllerClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.HolidayDate;
import data.Prices;
import dataController.DataControl;
import dataController.TicketPriceAndHolidayDataControl;

public class AdminConfigTicketPriceControl extends DataControl {

	
	public static boolean checkPrice(int choice,double baseprice , double price)
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

	



}
