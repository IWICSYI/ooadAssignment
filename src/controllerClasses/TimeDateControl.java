package controllerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeDateControl {
	
	public static String retrieveDaySpell(Calendar t){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy EEEE");
	    String a=dateFormatter.format(t.getTime());
	    return a;
		
	}
	
	public static Calendar resetTodayTime(){
		Calendar today=Calendar.getInstance();
		
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today;
		
		
	}
	
	public static int calculateRunDate(Date sD,Date eD)
	{
		Calendar a=Calendar.getInstance();
		Calendar b=(Calendar) a.clone();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		a.setTime(sD);
		b.setTime(eD);
		int minusDays = 0;
		while (true) {
		  minusDays++;

		  // Day increasing by 1
		  a.add(Calendar.DAY_OF_MONTH, 1);

		  if (dateFormat.format(a.getTime()).
		            equals(dateFormat.format(b.getTime()))) {
		    break;
		  }
		}
		System.out.println("The subtraction between two days is " + (minusDays + 1));
		
		return minusDays+1;
		
	}

	public static String minutesPlusTime(int minutes,String s)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
		Calendar temp= Calendar.getInstance();
		temp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s.substring(0, 2)));
		temp.set(Calendar.MINUTE,Integer.parseInt(s.substring(2, 4)));
		String startTimeSt=sdf.format(temp.getTime());
		temp.add(Calendar.MINUTE, minutes);
		String endTimeSt=sdf.format(temp.getTime());
		
		
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
