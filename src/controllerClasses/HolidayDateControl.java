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

public class HolidayDateControl extends DataControl {

	public static void createHoliday(HolidayDate hD) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		int id=0;
		
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		if(!holidayList.isEmpty())
		{
			id=holidayList.get(holidayList.size()-1).getHolidayId()+1;
		}
		else
			id=1;
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String date=finalDateFormatter.format(hD.getHolidayDate());
				
		StringBuilder st =  new StringBuilder() ;
		st.append(id);
		st.append(SEPARATOR);
		st.append(date);
		
		
		alw.add(st.toString()) ;
		

		write("data/holidayDates.txt",alw);
	}

	
	public static void updateHoliday(int hDid,HolidayDate hD) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		int id=0;
		String updateDate=finalDateFormatter.format(hD.getHolidayDate());
		
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for(int i=0;i<holidayList.size();i++)
		{		
			StringBuilder st =  new StringBuilder() ;
			if(hDid==holidayList.get(i).getHolidayId()){
				st.append(hDid);
				st.append(SEPARATOR);
				st.append(updateDate);
				st.append("\n");
				alw.add(st.toString()) ;
			}
			else
			{
				st.append(holidayList.get(i).getHolidayId());
				st.append(SEPARATOR);
				String hDExist=finalDateFormatter.format(holidayList.get(i).getHolidayDate());
				st.append(hDExist);
				st.append("\n");
				alw.add(st.toString()) ;
				
			}
		}

		writeB("data/holidayDates.txt",alw);
	}


	public static boolean validateDuplicatedHoliday(String s) throws IOException, ParseException {
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


	public static void removeHolidayDate(int id) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
	
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes

		for(int i=0;i<holidayList.size();i++)
		{		
			StringBuilder st =  new StringBuilder() ;
			if(id!=holidayList.get(i).getHolidayId()){
				st.append(holidayList.get(i).getHolidayId());
				st.append(SEPARATOR);
				String hDExist=finalDateFormatter.format(holidayList.get(i).getHolidayDate());
				st.append(hDExist);
				st.append("\n");
				alw.add(st.toString()) ;
			}
		}

		writeB("data/holidayDates.txt",alw);
		
	}



	
}
