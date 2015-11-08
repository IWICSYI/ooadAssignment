package misc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import controllerClasses.AdminShowTimeController;
import controllerClasses.TimeDateControl;
import data.Movie;
import dataController.MovieDataControl;
import boundaryClasses.AdminSchedulerUpdateUi;
import boundaryClasses.CustBuyTicketsWithSeatsSelectionsUi;

public class test {
	
	public static void main(String []agrs) throws IOException, ParseException{
		//Movie m=MovieDataControl.readMovieBasedOnId(2);
		//CustBuyTicketsWithSeatsSelectiionsUi.displayCustomerInfo( 2, m);
		
		System.out.println(TimeDateControl.minutesPlusTime(99,"0040"));
		
	}
	public static int chooseCineplexToDisplay(ArrayList<Integer> cineId)
	{
		int a=0,b=0,c=0;
		for(int i=0;i<cineId.size();i++)
		{
			if(cineId.get(i)==1)
			{
				a++;
			}
			else if(cineId.get(i)==2)
			{
				b++;
			}
			else if(cineId.get(i)==3){
				c++;
			}
		}
		ArrayList<Integer> tmp=new ArrayList<Integer>();
		tmp.add(a);
		tmp.add(b);
		tmp.add(c);
		int largest = Collections.max(tmp);
		if(largest==a)
		{
			return 1;
		}
		else if(largest==b)
		{
			return 2;
		}
		else if(largest==c)
		{
			return 3;
		}
		return 0;
		
	}

}
