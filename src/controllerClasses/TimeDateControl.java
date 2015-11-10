package controllerClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;


/**
 * Class that deals with things related to time and date.
 * @author Chang En Kai
 *
 */
public class TimeDateControl {
	/**
	 * Spell the day(Monday,Tuesday... ...) of the date.
	 * @param t
	 * @return
	 */
	public static String retrieveDaySpell(Calendar t){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy EEEE");
	    String a=dateFormatter.format(t.getTime());
	    return a;
		
	}
	
	/**
	 * Reset today's time to 00:00:00:00 to specify start of day
	 * @return
	 */
	public static Calendar resetTodayTime(){
		Calendar today=Calendar.getInstance();
		
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today;
		
		
	}
	
	/**
	 * Calculate how long a movie is showing based on starting date and ending date
	 * @param sD
	 * @param eD
	 * @return
	 */
	public static int calculateRunDate(Date sD,Date eD)
	{
		
		int minusDays = 0;
		DateTime dt1 = new DateTime(sD);
		DateTime dt2 = new DateTime(eD);

		minusDays=Days.daysBetween(dt1, dt2).getDays();
		
		return minusDays;
		
	}
/**
 * Calculate the time range a movie will be showing by taking in the starting date and adding the movie length.
 * @param minutes
 * @param sD
 * @return
 */
	public static String minutesPlusTime(int minutes,String sD)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("HHmm");
		Calendar temp= Calendar.getInstance();
		temp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sD.substring(0, 2)));
		temp.set(Calendar.MINUTE,Integer.parseInt(sD.substring(2, 4)));
		String startTimeSt=sdf.format(temp.getTime());
		temp.add(Calendar.MINUTE, minutes);
		String endTimeSt=sdf.format(temp.getTime());
		
		
		String finalTime=startTimeSt+"-"+endTimeSt;
		return finalTime;
		
	}
	
	/**
	 * Given a start date and how long a movie will be showing, calculate the end date dynamically
	 * @param startDate
	 * @param runDate
	 * @return
	 * @throws ParseException
	 */
	public static Date calculateEndDate(Date startDate, int runDate) throws ParseException
	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
		cal.setTime(startDate);
		Calendar calTemp,calTemp2;
	  
	      // // NOTE!!! : Month from 0 to 11 => 3 is April
	      calTemp = (Calendar) cal.clone();
	      System.out.println("Starting date will be: " + calTemp.getTime());
	     
	      // add time
	     calTemp.add(Calendar.DATE, runDate);
	   
	     calTemp.add(Calendar.DATE, -1);
	     calTemp.set(Calendar.HOUR_OF_DAY,23);
	     calTemp.set(Calendar.MINUTE,59);
	     calTemp.set(Calendar.SECOND,59);
	     System.out.println("Ending date will be: " + calTemp.getTime());
	     Date endDate = calTemp.getTime();
	     
		return endDate;
		
	}
	
	
	

	/**
	 * Determine if day is a weekend or week day
	 * @param startDate
	 * @return
	 * @throws ParseException
	 */
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
