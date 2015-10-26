package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.SeatsControl;
import controllerClasses.ValidationControl;
import data.Seats;
import data.ShowTime;
import misc.ObjectContainer;

public class CustSeatsUi {
	
	public void displaySeat(int showTimeId) throws IOException, ParseException
	{
		
		SeatsControl sC=new SeatsControl();
		ArrayList<ShowTime> sTList = sC.readShowTimesBasedOnShowTimeId(showTimeId);
		ArrayList<Seats> seatList =new ArrayList<Seats>();
		ArrayList<ObjectContainer> selectedSeats=new ArrayList<ObjectContainer> ();
		
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
				ObjectContainer oS= new ObjectContainer();
				oS.setName(seatName);
				oS.setSeatList(seatList);
				selectedSeats.add(oS);	
			}
		}
		else
		{
			
			
			selectedSeats.clear();
			for(int i=0;i<seatList.size();i++)
			{
				//System.out.println(seatList.size());
				ObjectContainer oS= new ObjectContainer();
				oS.setName(seatList.get(i).getSeatName());
				oS.setId(seatList.get(i).getSeatId());
				oS.setSeatList(seatList);
				selectedSeats.add(oS);
				
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
			
		}
		System.out.println("1.Purchase Ticket");
		System.out.println("2.Check Ticket Price");
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		if(choice==1)
		{
			displayPurchaseSeat(showTimeId,seatList,selectedSeats,sC);
		}
		
	}
	
	
	public void displayPurchaseSeat(int showTimeId, ArrayList<Seats> seatList, ArrayList<ObjectContainer> selectedSeats, SeatsControl sC) throws IOException, ParseException{
		ArrayList<Seats> seatsToValidate =new ArrayList<Seats>();
		ArrayList<Seats> actualSeats =new ArrayList<Seats>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter your name");
		
		System.out.println("Choose number of seats you want to purchase");
		String s=sc.nextLine();
		boolean valid=false;
		int num=ValidationControl.validateAndReturnIntegerValue(s);
		SeatsControl scon=new SeatsControl();
		String temp;
		do{
			for(int i=0;i<num;i++)
			{
				do{
					System.out.println("Select seat number "+(i+1)+" by typing seat name you selected(Eg.A1)");
					temp=sc.nextLine().toUpperCase();
					Seats a=new Seats();
					a=scon.searchSeat(selectedSeats, temp);
					a.setOccupied(true);
					if(a!=null)
					{
						seatsToValidate.add(a);
					}
					else
					{
						System.out.println("Invalid seat name, please try again.");
					}
				}
				while(scon.searchSeat(selectedSeats, temp)==null);
		
			}
			valid=scon.confirmSeats(seatsToValidate);
			if(valid)
			{
				actualSeats=seatsToValidate;
			}
			else
			{
				seatsToValidate.clear();
			}
		}while(!valid);
		
		sC.updateSeats(seatList,actualSeats);
		displaySeat(showTimeId);
	}

}
