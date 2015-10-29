package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.BuyTicketControl;
import controllerClasses.SeatsControl;
import controllerClasses.ValidationControl;
import data.HolidayDate;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import dataController.SeatsDataControl;
import dataController.ShowTimeDataControl;
import misc.ObjectContainer;

public class CustBuyTicketsWithSeatsSelectiionsUi {
	
	public void displaySeat(int showTimeId) throws IOException, ParseException
	{
		ShowTime sTList = ShowTimeDataControl.readShowTimesBasedOnShowTimeId(showTimeId);
		double price=sTList.getTicketPrice();
		SimpleDateFormat finalDateFormatter=new SimpleDateFormat("dd/MM/yyyy");
		String startDate=finalDateFormatter.format(sTList.getStartDate());
		boolean holiday=false;
		ArrayList<HolidayDate> hDList=new ArrayList<HolidayDate>();
		for(int i=0;i<hDList.size();i++)
		{
			String date1=finalDateFormatter.format(hDList.get(i).getHolidayDate());
			if(date1.equals(startDate)){
				price=price+1;
				holiday=true;
				break;
			}
			
		}
		
		ArrayList<ObjectContainer> selectedSeats = SeatsControl.manageSeats(sTList,showTimeId);
		Scanner sc= new Scanner(System.in);
		System.out.println("1.Purchase Ticket");
		System.out.println("2.Check Ticket Price");
		String s=sc.nextLine();
		int choice=ValidationControl.validateAndReturnIntegerValue(s);
		if(choice==1)
		{
			ArrayList<Seats> seatList = SeatsDataControl.readSeats(showTimeId);
			
			displayPurchaseSeat(showTimeId,seatList,selectedSeats,price,sTList);
		}
		else if(choice==2)
		{	if(holiday){
				System.out.println("Holiday surcharged applied");
			}
			System.out.println("Price for 1 ticket $"+price);
		}
	}
	
	
	public void displayPurchaseSeat(int showTimeId, ArrayList<Seats> seatList, ArrayList<ObjectContainer> selectedSeats,double price,ShowTime sTlist) throws IOException, ParseException{
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
		boolean occuValid;
		do{
			for(int i=0;i<num;i++)
			{
				do{
					do{
						System.out.println("Select seat number "+(i+1)+" by typing seat name you selected(Eg.A1)");
						temp=sc.nextLine().toUpperCase();
						Seats a=new Seats();
						occuValid=scon.checkOccupied(seatList, temp);
					
						a=scon.searchSeat(seatList, temp);
						if(a!=null)
						{
							a.setOccupied(true);
							seatsToValidate.add(a);
						}
						else
						{
							System.out.println("Invalid seat name, please try again.");
						}
					}while(occuValid==false);
					
				}
				while(scon.searchSeat(seatList, temp)==null);
		
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
		 sC.updatechoosenSeats(seatList,actualSeats);
		ArrayList<Seats> tmpSeats = SeatsDataControl.readTmpSeats();
		SeatsControl.designSeats(tmpSeats);
		System.out.print("Seats selected=");
		for(int i=0;i<actualSeats.size();i++)
		{
			System.out.print(""+actualSeats.get(i).getSeatName());
		}
		price=actualSeats.size()*price;
		
		System.out.println("\nPrice will be "+price+". Confirm purchase?");
		System.out.println("1.Yes");
		System.out.println("2.No");
		String sTemp=sc.nextLine();
		int choice=ValidationControl.validateYesNoAndReturnIntegerValue(sTemp);
		
		if(choice==1)
		{
			sC.updateSeats(seatList, actualSeats);
			BuyTicketControl.purchaseTicket(price, sTlist);
		}
		else if(choice==2)
		{
			displaySeat(showTimeId);
		}
			
	}

}
