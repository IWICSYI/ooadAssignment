package boundaryClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.SeatsControl;
import data.Seats;
import data.ShowTime;
import misc.ObjectContainer;

public class CustSeatsUi {
	
	public void displaySeat(int showTimeId) throws IOException
	{
		
		SeatsControl sC=new SeatsControl();
		ArrayList<ShowTime> sTList = sC.readShowTimesBasedOnShowTimeId(showTimeId);
		ArrayList<Seats> seatList =new ArrayList<Seats>();
		seatList=sC.readSeats(showTimeId);
		Scanner sc=new Scanner(System.in);
		
		String seatName = "";
		char fA='A';
		int seatA=1,seatB=1;
		int seatAmount=sTList.get(0).getNoOfSeats();
		int seatId = 1;
		
		
		if(seatList.isEmpty())
		{
			for(int i=0;i<seatAmount;i++)
			{
				seatName=fA+""+seatA;
				
				seatA=seatA+1;
				if(seatA>20){
					seatA=1;
				}
			
				System.out.print("["+seatName+"]");
				if((i+1)%10==0&& i!=0){
					
					System.out.print(" ");
				}
				if((i+1)%20==0 && i!=0)
				{
					fA=(char) ('A'+seatB);
					System.out.println();
					seatB++;
				}
			
				Seats s=new Seats();
				s.setOccupied(false);
				s.setShowTimeId(sTList.get(0).getShowTimeId());
				s.setMovieId(sTList.get(0).getMovieId());
				s.setCinemaId(sTList.get(0).getCinemaId());
				s.setSeatName(seatName);
				s.setSeatId(seatId++);
				seatList.add(s);
				sC.createSeats(seatList);
			
				
			}
		}
		else
		{
			for(int i=0;i<seatList.size();i++)
			{
				if(!seatList.get(i).isOccupied())
				{
					System.out.print("["+seatList.get(i).getSeatName()+"]");
				}
				else
				{
					System.out.print("[X]");
				}
				if((i+1)%10==0&& i!=0){
					System.out.print(" ");
				}
				if((i+1)%20==0 && i!=0)
				{
					System.out.println();
				}
			}
			System.out.println("Choose number of seats you want to purchase");
			String s=sc.nextLine();
		
		
		
		
		}
		
	}

}
