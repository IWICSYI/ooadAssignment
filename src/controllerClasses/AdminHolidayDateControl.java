package controllerClasses;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import data.HolidayDate;
import data.Movie;
import dataController.DataControl;
import dataController.MovieDataControl;
import dataController.TicketPriceAndHolidayDataControl;

public class AdminHolidayDateControl extends DataControl {

	

	public static boolean checkDuplicatedHoliday(String s) throws IOException, ParseException {
		String existDate="";
				//
		boolean valid=false;
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		for(int i=0;i<holidayList.size();i++)
		{
			existDate=finalDateFormatter.format(holidayList.get(i).getHolidayDate());
			if(existDate.equals(s))
			{
				valid=false;
				break;
			}
			else{
				valid=true;
			}
		}
		
		return valid;
	}


	


	
}
