package misc;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import controllerClasses.DataControl;
import controllerClasses.ValidationControl;
import data.ShowTime;
import boundaryClasses.*;

public class Main extends ValidationControl{

	public static void main(String[] args) throws IOException, ParseException {
		ModuleSelectionUi ui=new ModuleSelectionUi();
		//ui.display();
		MovieEntryUi ui2=new MovieEntryUi();
		//ui2.displayMain();
		DataControl test=new DataControl();
		//test.readMovie();
		SchedulerUi ui3=new SchedulerUi();
		ui3.displayCreatePage();
		
		
		CustomerUi ui4=new CustomerUi();
		//ui4.displayNowShowing();
		
		CustBuyTicketUi u=new CustBuyTicketUi();
		//u.displayBuyTicket(4);
		//validateTimeSlotClash(1950);
		int time = ValidationControl.validateAndReturnTime("1900");
		CustSeatsUi a=new CustSeatsUi();
		//a.displaySeat(4);
		
		String time2=minutesPlusTime2(20,0000);
		//System.out.println(time2);
		//System.out.println(time);
		Date startDate=validateDate("1-2-2015");
		//calculatePreviewStartDate(startDate);
		
		

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
