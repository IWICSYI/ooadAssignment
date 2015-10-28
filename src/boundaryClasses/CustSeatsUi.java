package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.SeatsControl;
import controllerClasses.ValidationControl;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import dataController.SeatsDataControl;
import dataController.ShowTimeDataControl;
import misc.ObjectContainer;

public class CustSeatsUi {
	
	public void displaySeat(int showTimeId) throws IOException, ParseException
	{
		
		ArrayList<ObjectContainer> selectedSeats = SeatsControl.manageSeats(showTimeId);
		Scanner sc= new Scanner(System.in);
		System.out.println("1.Purchase Ticket");
		System.out.println("2.Check Ticket Price");
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		if(choice==1)
		{
			ArrayList<Seats> seatList = SeatsDataControl.readSeats(selectedSeats.get(0).getI());
			
			displayPurchaseSeat(showTimeId,seatList,selectedSeats);
		}
		
	}
	
	
	public void displayPurchaseSeat(int showTimeId, ArrayList<Seats> seatList, ArrayList<ObjectContainer> selectedSeats) throws IOException, ParseException{
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
		SeatsControl sC=new SeatsControl();
		sC.updateSeats(seatList,actualSeats);
		displaySeat(showTimeId);
	}

}
