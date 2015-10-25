package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.DataControl;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.TimeDateControl;
import controllerClasses.ValidationControl;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;

public class CustBuyTicketUi {

	
	public void displayBuyTicket(int movieId) throws IOException, ParseException
	{
		MovieListingControl cl=new MovieListingControl();
		Scanner sc =new Scanner(System.in);
		
		DataControl dl=new DataControl();
		ArrayList<Cineplex> cnList= dl.readCineplex();
		ArrayList<ObjectContainer> pair= new ArrayList<ObjectContainer>();
		
		
		System.out.println("Please choose Cineplex");
		for(int i=0;i<cnList.size();i++)
		{
			System.out.println((i+1)+":"+cnList.get(i).getCineplexName());
		}
		int cinplexId=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		ArrayList<MovieSchedule> schList=cl.readScheduleListingBasedonMovieandCineplexId(movieId, cinplexId);
		ArrayList<ShowTime> showTimeList=new ArrayList<ShowTime>();

		ArrayList<Integer> listIdList=new ArrayList<Integer>();
		ArrayList<Integer> showTimeId=new ArrayList<Integer>();
		ArrayList<String> showTimeArray=new ArrayList<String>();

		for(int i=0;i<schList.size();i++)
		{	
			listIdList.add(schList.get(i).getListingId());
		}


		int dayType=0;
		//schList.get(0).getStartDate().getDay();
		Calendar cal = Calendar.getInstance();
		Calendar calTemp,calTemp2, aDate=Calendar.getInstance();
		calTemp = (Calendar) cal.clone();
		calTemp2 = (Calendar) cal.clone();
		Date endDate=schList.get(0).getEndDate();
		aDate.setTime(endDate);
		System.out.println("Date                     |TimeSlot                     ");
		System.out.println("------------------------------------------------------");
		for(int i=1;i<=7;i++)
		{
			System.out.print(TimeDateControl.retrieveDaySpell(calTemp)+"|");
			dayType=TimeDateControl.dayType(calTemp.getTime());
			ArrayList<String> pairStringArray=new ArrayList<String>();
			//MiscControl mc=new MiscControl();
			for(int k=0;k<listIdList.size();k++){
				showTimeList=cl.readShowTimesBasedOnListingId(listIdList.get(k), dayType);
				
				for(int p=0;p<showTimeList.size();p++)
				{	showTimeId.add(showTimeList.get(p).getShowTimeId());
					showTimeArray.add(showTimeList.get(p).getShowTimeValue());
					pairStringArray.add(showTimeList.get(p).getShowTimeValue());
				}
				//if(!showTimeList.isEmpty())
			//	{
			//		System.out.println("pairId="+i+"dayType="+dayType+"showTimeList"+showTimeList.get(1).getShowTimeId());
			//	}
			}
			
			pair.add(MiscControl.idPairerWithShowTime(i, dayType, pairStringArray,showTimeId));
			
			for(int j=0;j<showTimeArray.size();j++)
			{
				System.out.print(showTimeArray.get(j)+" ");
			}
			showTimeArray.clear();
			
			System.out.println();
			if(calTemp.before(aDate))
			{
				calTemp.add(calTemp.DATE, 1);
				
			}
		}
		System.out.println("------------------------------------------------------");
		

		System.out.println("Choose the day you want to purchase the ticket");
		for(int i=1;i<=7;i++)
		{
			System.out.println((i+":"+TimeDateControl.retrieveDaySpell(calTemp2)+""));
			dayType=TimeDateControl.dayType(calTemp2.getTime());
			calTemp2.add(calTemp2.DATE, 1);
			
		}
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		
		
		displayTicketForDay(pair.get(choice-1));
	}
	
	
	public void displayTicketForDay(ObjectContainer o) throws IOException, ParseException
	{
		
		ArrayList<String>s=o.getStringArray();
		
		for(int i=1;i<=s.size();i++)
		{
			System.out.println(i+":"+s.get(i-1));
		}
		Scanner sc=new Scanner(System.in);
		int choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		
		CustSeatsUi ui=new CustSeatsUi();
		ui.displaySeat(o.getsTIdList().get(choice-1));
		
		
	}
	
	
	
	
}
