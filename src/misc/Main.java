package misc;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import controllerClasses.DataControl;
import data.ShowTime;
import boundaryClasses.*;

public class Main {

	public static void main(String[] args) throws IOException {
		ModuleSelectionUi ui=new ModuleSelectionUi();
		//ui.display();
		MovieEntryUi ui2=new MovieEntryUi();
		//ui2.displayMain();
		DataControl test=new DataControl();
		//test.readMovie();
		SchedulerUi ui3=new SchedulerUi();
		//ui3.displayCreatePage();
		validateTimeSlotClash(1950);
		

	}

	
	
	public static boolean validateCinemaShowTime(ArrayList<ShowTime> sTArray) throws IOException
	{
		Set<Integer> set = new HashSet<Integer>();
		for(int i=0;i<sTArray.size();i++)
		{
			System.out.print(sTArray.get(i).getCinemaId()+" ");
			set.add(sTArray.get(i).getCinemaId());
		}
		Iterator<Integer> a=set.iterator();
		System.out.println();
		while(a.hasNext()){
			System.out.println(a.next());
		
	
		
		}
		return false;
		
	}
	
	public static boolean validateCinemaShowTime2(ArrayList<ShowTime> sTArray) throws IOException
	{
		Set<Integer> set = new HashSet<Integer>();
		ArrayList<ShowTime> sTArray2=sTArray;
		ShowTime s=new ShowTime();
		//Set<Integer> newST=new ArrayList<Integer>();
		ArrayList<ObjectContainer> pairList=new ArrayList<ObjectContainer>();
		int checkCinemaId,checkMovieId;
		int checkCinemaId2,checkMovieId2;
		for(int i=0;i<sTArray.size();i++)
		{
			checkCinemaId=sTArray.get(i).getCinemaId();
			checkMovieId=sTArray.get(i).getMovieId();
			
			for(int j=i+1;j<sTArray.size();j++)
			{	
				checkCinemaId2=sTArray.get(j).getCinemaId();
				checkMovieId2=sTArray.get(j).getMovieId();
				if(checkCinemaId==checkCinemaId2)
				{
					System.out.println(i+1+" compare with "+(j+1));
					//set=sTArray.get(j).getShowTimeArrayValue();
					//Pairer a=new Pairer(checkCinemaId,newST);
					//pairList.add(a);
					//for(int k=0;k<newST.size();k++)
					//{
						//sTArray.get(i).getShowTimeArray().add(newST.get(k));
						
						
					//}
				}
			}
			
		}
		
		for(int i=0;i<set.size();i++){
			//System.out.print(newST.get(i).intValue()+" ");
		}
		
		for(int i=0;i<sTArray.size();i++)
		{
		  // sTArray.get(i).printShowTimeArray();
		 }
		String st="";
		for(int i=0;i<pairList.size();i++)
		{
		  st=st+"cinema id:"+pairList.get(i).getCinemaId()+"\n";
		  st=st+"int[] value:"+pairList.get(i).printNewSTList()+"\n";
		  set=pairList.get(i).getUniqueNewSTList();
		 }
		System.out.println(st);
		
		return false;
		
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
