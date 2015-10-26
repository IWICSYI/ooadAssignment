package controllerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeDateControl {
	
	public static String retrieveDaySpell(Calendar t){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");
	    String a=dateFormatter.format(t.getTime());
	    return a;
		
	}

	public String minutesPlusTime(int minutes,int time)
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
		return finalTime;
		
	}
	
	public static Date calculateEndDate(Date startDate, int runDate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
		cal.setTime(startDate);
		Calendar calTemp;
	  
	      // // NOTE!!! : Month from 0 to 11 => 3 is April
	      calTemp = (Calendar) cal.clone();
	      System.out.println("Starting date will be: " + calTemp.getTime());

	      // add time
	     calTemp.add(Calendar.DATE, runDate );
	      System.out.println("Ending date will be: " + calTemp.getTime());
	      Date endDate = calTemp.getTime();
		return endDate;
		
	}
	
	
	
	public static Date calculatePreviewStartDate(Date startDate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
		cal.setTime(startDate);
		Calendar calTemp;

		// // NOTE!!! : Month from 0 to 11 => 3 is April
		calTemp = (Calendar) cal.clone();

		// add time

		calTemp.add(Calendar.DATE, -1 );
		startDate = calTemp.getTime();
		return startDate;
		
	}
	
	
	

	public static Date calculateComingSoon(Date startDate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
		cal.setTime(startDate);
		Calendar calTemp;
	  
	      // // NOTE!!! : Month from 0 to 11 => 3 is April
	      calTemp = (Calendar) cal.clone();
	      System.out.println(" Starting Date will be: " + calTemp.getTime());

	      // add time
	     
	     calTemp.add(Calendar.DATE, -7 );
	      System.out.println("1 days before, it will be: " + calTemp.getTime());
	      Date coming = calTemp.getTime();
		return coming;
		
	}
	
	public static int dayType(Date startDate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int dayType = 0;
		
		if(cal.getTime().getDay()>0&&cal.getTime().getDay()<6){
			dayType=1;
		}
		else if(cal.getTime().getDay()==0||cal.getTime().getDay()==6){
			dayType=2;
		}
		
		
		return dayType;
		
	}
	
	
	
	
}
