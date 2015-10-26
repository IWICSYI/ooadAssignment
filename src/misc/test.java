package misc;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import boundaryClasses.AdminSchedulerUpdateUi;

public class test {
	
	public static void main(String []agrs) throws IOException, ParseException{
		
		ArrayList<Integer> cineId=new ArrayList<Integer>();
		cineId.add(1);
		cineId.add(2);
		cineId.add(3);
		cineId.add(1);
		cineId.add(2);
		cineId.add(3);
		cineId.add(1);
		cineId.add(2);
		cineId.add(3);
		cineId.add(3);
		//System.out.println(chooseCineplexToDisplay(cineId));
		AdminSchedulerUpdateUi ui=new AdminSchedulerUpdateUi();
		ui.displayUpdatePage();
		
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
