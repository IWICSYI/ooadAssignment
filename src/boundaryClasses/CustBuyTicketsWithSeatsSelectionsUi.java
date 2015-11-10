package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.CustBuyTicketControl;
import controllerClasses.CustSeatsControl;
import controllerClasses.ValidationControl;
import data.Movie;
import data.Seats;
import data.ShowTime;
import dataController.SeatsDataControl;
import dataController.ShowTimeDataControl;

public class CustBuyTicketsWithSeatsSelectionsUi extends CustBuyTicketChooseTimeSlotUi {
	
	/**
	 * Display page to record customer information
	 * @param showTimeId
	 * @param m Movie object
	 * @param listingId
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayCustomerInfo(int showTimeId,Movie m, int listingId) throws IOException, ParseException{
		ShowTime sTList = ShowTimeDataControl.readShowTimesBasedOnShowTimeId(showTimeId);
		ObjectContainer cust=CustBuyTicketControl.customerManagement(sTList, m,listingId);
		displaySeat(showTimeId, m, cust);
	}
	
	
	/**
	 * Display seat selctions
	 * @param showTimeId
	 * @param m Movie object
	 * @param cust A object container that contains transaction, movie, movie show time object
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displaySeat(int showTimeId,Movie m,ObjectContainer cust) throws IOException, ParseException
	{
		ShowTime sTList = ShowTimeDataControl.readShowTimesBasedOnShowTimeId(showTimeId);
		Scanner sc=new Scanner(System.in);
		System.out.println(m.getMovieName());
		ArrayList<ObjectContainer> selectedSeats = CustSeatsControl.manageSeats(sTList,showTimeId);
		System.out.println();
		if(cust.isOld()){
			System.out.println("Senior citizen discount applied		");
		}
		if(cust.isChild()){
			System.out.println("Children price discount applied		");
		}
		if(cust.isHoliday()){
			System.out.println("Holiday surcharged applied		");
		}
		if(cust.isBlockBuster()){
			System.out.println("Blockbuster surcharged applied		");
		}
		if(cust.isPlat()){
			System.out.println("Platinum Suite surcharged applied		");
		}
		
		if(cust.isWeekEnd()){
			System.out.println("Weekend surcharged applied		");
		}
		
		if(cust.istD())
		{
			System.out.println("Three D surcharged applied		");
		}
		System.out.println();
		
		final double initalprice=cust.getInitialprice();
		int choice=0;
		do{
			System.out.println("Price for 1 ticket $"+initalprice);
			System.out.println("1.Purchase Ticket");
			String s=sc.nextLine();
			choice=ValidationControl.validateAndReturnIntegerValue(s);
		}while(choice<=0||choice>1);
			
		if(choice==1)
		{
			ArrayList<Seats> seatList = SeatsDataControl.readSeats(showTimeId);
			
			displayPurchaseSeat(showTimeId,seatList,selectedSeats,cust,sTList,m);
		}
		
	}
	
	/**
	 * Display page to purchase seats
	 * @param showTimeId
	 * @param seatList
	 * @param selectedSeats
	 * @param cust  A object container that contains transaction, movie, movie show time object
	 * @param sTlist A list of seats
	 * @param m Movie 
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displayPurchaseSeat(int showTimeId, ArrayList<Seats> seatList, ArrayList<ObjectContainer> selectedSeats,ObjectContainer cust,ShowTime sTlist,Movie m) throws IOException, ParseException{
		ArrayList<Seats> seatsToValidate =new ArrayList<Seats>();
		ArrayList<Seats> actualSeats =new ArrayList<Seats>();
		Scanner sc=new Scanner(System.in);
		
		int num=0;
		boolean valid=false;
		do{
			System.out.println("Choose number of seats you want to purchase");
			String s=sc.nextLine();
			num=ValidationControl.validateAndReturnIntegerValue(s);
		}while(num<=0||num>seatList.size());
		
		
		CustSeatsControl scon=new CustSeatsControl();
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
			valid=CustBuyTicketControl.confirmSeats(seatsToValidate);
			if(valid)
			{
				actualSeats=seatsToValidate;
			}
			else
			{
				for(int i=0;i<seatsToValidate.size();i++)
				{
					seatsToValidate.get(i).setOccupied(false);
				}
				seatsToValidate.clear();
				valid=false;
				System.out.println("Press anything to resume.");
				sc.nextLine();
				displaySeat( showTimeId, m, cust);
				
			}
		}while(!valid);
		
		SeatsDataControl.reflectSeatSelections(seatList,actualSeats);
		ArrayList<Seats> tmpSeats = SeatsDataControl.readTmpSeats();
		CustSeatsControl.designSeats(tmpSeats);
		System.out.print("Seats selected=");
		for(int i=0;i<actualSeats.size();i++)
		{
			System.out.print(" "+actualSeats.get(i).getSeatName());
		}
		cust.setPrice(actualSeats.size()*cust.getPrice());
		
		System.out.println("\nPrice will be "+cust.getPrice()+". Confirm purchase?");
		System.out.println("1.Yes");
		System.out.println("2.No");
		String sTemp=sc.nextLine();
		int choice=ValidationControl.validateYesNoAndReturnIntegerValue(sTemp);
		
		if(choice==1)
		{
			SeatsDataControl.reflectAndConfirmSeatSelections(seatList, actualSeats);
			CustBuyTicketControl.purchaseTicket(cust, sTlist,actualSeats);
		}
		else if(choice==2)
		{
			cust.setPrice(cust.getInitialprice());
			displaySeat(showTimeId,m,cust);
		}
			
	}

}
