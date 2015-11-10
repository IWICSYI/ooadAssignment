package controllerClasses;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import data.HolidayDate;
import dataController.TicketPriceAndHolidayDataControl;

public class AdminHolidayDateControl {

	
/**
 * Check if holiday dates are duplicated.
 * @param s
 * @return
 * @throws IOException
 * @throws ParseException
 */
	public static boolean checkDuplicatedHoliday(Date s) throws IOException, ParseException {
		if(s==null){
			return false;
		}
		
		String existDate="";
		String inputDate=new SimpleDateFormat("dd/MM/yyyy").format(s);
				//
		boolean valid=false;
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		for(int i=0;i<holidayList.size();i++)
		{
			existDate=new SimpleDateFormat("dd/MM/yyyy").format(holidayList.get(i).getHolidayDate());
			if(existDate.equals(inputDate))
			{
				System.out.println("Duplicated date found in the system!");
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
