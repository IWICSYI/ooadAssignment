package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import misc.ObjectContainer;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import dataController.SeatsDataControl;

/**
 * Class that works in conjunction with CustBuyTickets to deal with the nitty gritty of seats selections.
 * @author Chang En Kai
 *
 */
public class CustSeatsControl  {
	
	
	/**
	 * Manage and generate seats selection information
	 * @param sTList
	 * @param showTimeId
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ObjectContainer> manageSeats(ShowTime sTList, int showTimeId) throws IOException, ParseException{
		
		
		ArrayList<Seats> seatList =new ArrayList<Seats>();
		ArrayList<ObjectContainer> selectedSeats=new ArrayList<ObjectContainer> ();
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(showTimeId);
		
		SeatsInformation sInfor=new SeatsInformation();
		sInfor.setSeatInfoId(showTimeId);
		sInfor.setCinemaId(sTList.getCinemaId());
		sInfor.setMovieId(sTList.getMovieId());
		sInfor.setCineplexId(sTList.getCineplexId());
		sInfor.setNoOfSeats(sTList.getNoOfSeats());
		sInfor.setPrice(sTList.getTicketPrice());
		sInfor.setshowTimeId(showTimeId);
		sInfor.setStartEndTime(sTList.getShowTimeValue());
		sInfor.setStartDate(sTList.getStartDate());
		if(sINfoList.size()==0)
		{
			SeatsDataControl.createSeatInformation(sInfor);
		}
		seatList=SeatsDataControl.readSeats(showTimeId);
		Scanner sc=new Scanner(System.in);
		
		String seatName = "";
		char fA='A';
		int seatA=1,seatB=1;
		int seatAmount=sTList.getNoOfSeats();
		int seatId = 1;
		
		
		//create new seats!
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
				//s.setShowTimeId(sTList.getShowTimeId());
				s.setMovieId(sTList.getMovieId());
				s.setCinemaId(sTList.getCinemaId());
				s.setSeatName(seatName);
				s.setSeatId(seatId++);
				s.setSeatsInformationId(showTimeId);
				seatList.add(s);
				SeatsDataControl.createIndiviualSeat(seatList);
				ObjectContainer oS= new ObjectContainer();
				oS.setName(seatName);
				oS.setSeatList(seatList);
				oS.setSeat(s);
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
				oS.setI(seatList.get(i).getSeatsInformationId());
				oS.setSeatList(seatList);
				oS.setSeat(seatList.get(i));
				selectedSeats.add(oS);
				
			}
			designSeats(seatList);
			
		}
		return selectedSeats;
	}
	
	
	/**
	 * Design seats to be displayed for selections
	 * @param seatList
	 */
	public static void designSeats(ArrayList<Seats> seatList){
		System.out.println("-------------------------------------------SCREEN----------------------------------------------");
		for(int i=0;i<seatList.size();i++)
		{
			
			
			
			if(!seatList.get(i).isOccupied())
			{
				System.out.print("["+seatList.get(i).getSeatName()+"]");
			}
			else
			{
				System.out.print("[X ]");
			}
			if((i+1)%10==0&& i!=0){
				System.out.print("|     |");
			}
			if((i+1)%20==0 && i!=0)
			{
				System.out.println();
			}
	}
	}
	/**
	 * Search seat information by seat name.
	 * @param seatList
	 * @param name
	 * @return
	 */
	public Seats searchSeat(ArrayList<Seats> seatList,String name)
	{
		Seats a=new Seats();
		for(int i=0;i<seatList.size();i++)
		{
			
			if(seatList.get(i).getSeatName().equals(name))
			{		
				a=seatList.get(i);
					return a;
				
			}
			
		}
		
		return null;
	}
	
	/**
	 * Check if selected seats are occupied or not.
	 * @param seatList
	 * @param name
	 * @return
	 */
	public boolean checkOccupied(ArrayList<Seats> seatList,String name)
	{
		Seats a=new Seats();
		for(int i=0;i<seatList.size();i++)
		{
			
			if(seatList.get(i).getSeatName().equals(name))
			{		
				if(!seatList.get(i).isOccupied())
				{
					return true;
				}
			
			}
		
		}
		System.out.println("Seat already occupied, please try again!");
		return false;
	}
	
	/**
	 * Method to deal check if seat selections are valid, if valid, confirm the purchase
	 * @param selectedSeats
	 * @return
	 */
	public boolean confirmSeats(ArrayList<Seats> selectedSeats)
	{
		ArrayList<String> seatName=new ArrayList<String>();
		int seatNum=0,seatNum2 = 2;
		if(selectedSeats.size()==1)
		{
			return true;
		
		}
		
		for(int i=0;i<selectedSeats.size();i++)
		{
			System.out.println(selectedSeats.get(i).getSeatName());
			seatName.add(selectedSeats.get(i).getSeatName());
			
		}
		boolean valid=false;
		MiscControl ms=new MiscControl();
		ms.setStringList(seatName);
		ms.sort();
		ArrayList<String> sorted=ms.getStringList();
		int k=1;
		
		for(int i=0;i<sorted.size();i++)
		{
			//System.out.print(sorted.get(i));
			char a=sorted.get(i).toUpperCase().charAt(0);
			//System.out.println("a="+a);
			if (sorted.get(i).length()==2)
			{
				 seatNum=Integer.parseInt(sorted.get(i).substring(1, 2));
				//System.out.println("seatA="+seatNum);
			}
			else if(sorted.get(i).length()==3)
			{
				 seatNum=Integer.parseInt(sorted.get(i).substring(1, 3));
				// System.out.println("seatb="+seatNum);
				
			}
			for(int j=k;j<sorted.size();j++)
			{
				k++;
				if (sorted.get(j).length()==2)
				{
					 seatNum2=Integer.parseInt(sorted.get(j).substring(1, 2));
					// System.out.println("seatA2="+seatNum2);
				}
				else if(sorted.get(j).length()==3)
				{
					 seatNum2=Integer.parseInt(sorted.get(j).substring(1, 3));
						//	 System.out.println("seatB2="+seatNum2);
					
				}
				char b=sorted.get(j).toUpperCase().charAt(0);
				 
				if(a==b && seatNum2!=(seatNum+2)&& seatNum<20 && seatNum>0)
				{
					valid=true;
					break;
				}
				else
				{
					System.out.println("Invalid seat combination, there might be a gap between your choosen seats!");
					valid=false;
					
					break;
				}
			}
		}
		return valid;
	}

	
		
		
	}
		
	
