package misc;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import controllerClasses.MiscControl;
import controllerClasses.ShowTimeController;
import controllerClasses.ValidationControl;
import data.Movie;
import data.MovieSchedule;
import data.Seats;
import data.ShowTime;
import dataController.DataControl;
import boundaryClasses.*;

public class Main extends ValidationControl{

	public static void main(String[] args) throws IOException, ParseException {
		ModuleSelectionUi ui=new ModuleSelectionUi();
		//ui.display();
		AdminMovieEntryUi ui2=new AdminMovieEntryUi();
		//ui2.displayMain();
		DataControl test=new DataControl();
		//test.readMovie();
		AdminSchedulerUi ui3=new AdminSchedulerUi();
	//	ui3.displayCreatePage();
		
		MovieSchedule sch=new MovieSchedule();
		sch.setCineplexId(1);
		sch.setMovieId(1);
		
		sch.setThreeDOrNot(0);
		
		sch.setBlockBuster(0);
		SimpleDateFormat f=new SimpleDateFormat("dd/mm/yyyy");
		Date startDate=f.parse("25/10/2015");
		
		sch.setStartDate(startDate);
		
		
		sch.setEndDate(calculateEndDate(startDate,5));
		
		sch.setPreviewStatus(1);
		
		

		ArrayList<ObjectContainer> pair=new ArrayList<ObjectContainer>()  ;
		pair.add(MiscControl.idPairerWithMovieLength(1, 4, 90,1));
		AdminTimeSlotUi sTC=new AdminTimeSlotUi();
		//sTC.TimeSlotHandler(sch,1,90,1,1, 5);
		
		
		Calendar cal = Calendar.getInstance();
	    Date today=cal.getTime();
	    System.out.println(cal.getTime());
		
		CustomerDisplayMovieListingUi ui4=new CustomerDisplayMovieListingUi();
		//ui4.displayNowShowing();
		Movie m=new Movie();
		CustBuyTicketUi u=new CustBuyTicketUi();
		u.displayBuyTicket(1, 0, 0, m);
		//validateTimeSlotClash(1950);
		int time = ValidationControl.validateAndReturnTime("1900");
		CustBuyTicketsWithSeatsSelectiionsUi a=new CustBuyTicketsWithSeatsSelectiionsUi();
		a.displaySeat(4);
		
		String time2=minutesPlusTime2(20,0000);
		//System.out.println(time2);
		//System.out.println(time);
		Date startDate2=validateDate("25/10/2015");
		System.out.println(startDate2);
		//calculatePreviewStartDate(startDate);
		ArrayList<Seats> selectedSeats=new ArrayList<Seats>();
		Seats s=new Seats();
		s.setSeatName("A1");
		selectedSeats.add(s);
		Seats s2=new Seats();
		s2.setSeatName("A3");
		selectedSeats.add(s2);
		Seats s3=new Seats();
		s3.setSeatName("A2");
		selectedSeats.add(s3);
		Seats s4=new Seats();
		s4.setSeatName("A6");
		selectedSeats.add(s4);
		
		//System.out.println(confirmSeats(selectedSeats));
		
		

	}
	public static boolean confirmSeats(ArrayList<Seats> selectedSeats)
	{
		ArrayList<String> seatName=new ArrayList<String>();
		int seatNum=0,seatNum2 = 2;
		for(int i=0;i<selectedSeats.size();i++)
		{
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
			char a=sorted.get(i).charAt(0);
			//System.out.println("a="+a);
			if (sorted.get(i).length()==2)
			{
				 seatNum=Integer.parseInt(sorted.get(i).substring(1, 2));
				// System.out.println("seatA="+seatNum);
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
					//		 System.out.println("seatB2="+seatNum2);
					
				}
				char b=sorted.get(j).charAt(0);
				 
				if(a==b && seatNum2!=(seatNum+2))
				{
					System.out.println("b="+sorted.get(j)+" a="+sorted.get(i));
					valid=true;
					break;
				}
				else
				{
					//System.out.println("b="+sorted.get(j)+" a="+sorted.get(i));
					valid=false;
					break;
				}
			}
		}
		return valid;
	}
	
	
	
	public static String minutesPlusTime2(int minutes,int time)
	{
		int hr=minutes/60;
		int min=minutes-(hr*60);
		int endTime=time+hr*100+min;
		String startTimeSt = Integer.toString(time);
		String endTimeSt = Integer.toString(endTime);
		if(startTimeSt.length()==4){
			String a=startTimeSt.substring(0, 2);
			int check=Integer.parseInt(a);
			if(check>=24){
				check=check-24;
				a=Integer.toString(check);
				if(a.length()==0||a.length()==1){
					a="0"+a;
				}
			}
			startTimeSt=a+startTimeSt.substring(2, 4);
			
		}
		else if(startTimeSt.length()==3)
		{
			startTimeSt="0"+startTimeSt;
		}
		else if(startTimeSt.length()==2)
		{
			startTimeSt="00"+startTimeSt;
		}
		else if(startTimeSt.length()==1)
		{
			startTimeSt="000"+startTimeSt;
		}
		
		
		if(endTimeSt.length()==4){
			String a=endTimeSt.substring(0, 2);
			int check=Integer.parseInt(a);
			if(check>=24){
				check=check-24;
				a=Integer.toString(check);
				if(a.length()==0||a.length()==1)
				{
					a="0"+a;
				}
			}
			endTimeSt=a+endTimeSt.substring(2, 4);;
		}
		else if(endTimeSt.length()==3)
		{
			endTimeSt="0"+endTimeSt;
		}
		else if(endTimeSt.length()==2)
		{
			endTimeSt="00"+endTimeSt;
		}
		else if(endTimeSt.length()==1)
		{
			endTimeSt="000"+endTimeSt;
		}
		
		String finalTime=startTimeSt+"-"+endTimeSt;
		System.out.println(finalTime);
		return finalTime;
		
	}
	
	
	public static Date validateDate(String dateString) {
		String[] formats= {"d/M/yyyy", "d-M-yyyy", 
                "dd/MM/yyyy", "dd-mm-yyyy", 
                "d/MM/yyyy", "d-MM-yyyy", 
                "dd/m/yyyy", "dd-m-yyyy"};
		Date date = null;
		boolean valid=false;
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(dateString.length()<8||dateString.length()>10)
		{
			System.out.println("Input date is not acceptable, please try again!");
			return null;
		}
		
		if(!dateString.contains("/"))
		{
			System.out.println("Input date must be sperated by /");
			return null;
		}
		if(dateString.contains("/"))
		{
			String[] dateCheck=	dateString.split("/");
			if(dateCheck[0].length()<0||dateCheck[0].length()>2)
			{
				System.out.println("Input day is not valid, please try again");
				return null;
				
			}
			if(dateCheck[1].length()<0||dateCheck[1].length()>2)
			{
				System.out.println("Input month is not valid, please try again");
				
				return null;
				
			}
			if(dateCheck[2].length()!=4)
			{	
				System.out.println("Input year is not valid, it must be 4 letters in lenght, please try again");
			
				return null;
				
			}
			
		}
		for(int i=0;i<formats.length;i++)
		{
			SimpleDateFormat dateFormatter = new SimpleDateFormat(formats[i]);
			try {
				date=dateFormatter.parse(dateString);
				valid=true;
				break;
			}
			catch (ParseException e) {

				valid= false;
			}
	      
		}
	
	if(valid)
		return date;
	else
		return null;
	}
	
	
	public static boolean validateTimeSlotClash(int time) throws IOException {
		boolean valid=true;
		String tmp="1900-2100";
				String[] timeArr = tmp.split("-");
				int startTime=Integer.parseInt(timeArr[0]);
				int endTime=Integer.parseInt(timeArr[1]);
				if(time>=startTime && time<=endTime)
				{
					valid=false;
					
				}
				System.out.println(valid);
				return valid;
					
	
		
	
	}
}
