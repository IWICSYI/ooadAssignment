package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import misc.ObjectContainer;
import data.Movie;
import data.Seats;
import data.SeatsInformation;
import data.ShowTime;
import dataController.DataControl;
import dataController.SeatsDataControl;
import dataController.ShowTimeDataControl;

public class SeatsControl extends DataControl {
	
	
	
	public static ArrayList<ObjectContainer> manageSeats(int showTimeId) throws IOException, ParseException{
		
		ShowTime sTList = ShowTimeDataControl.readShowTimesBasedOnShowTimeId(showTimeId);
		ArrayList<Seats> seatList =new ArrayList<Seats>();
		ArrayList<ObjectContainer> selectedSeats=new ArrayList<ObjectContainer> ();
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(sTList);
		int id=sINfoList.size()+1;
		
		SeatsInformation sInfor=new SeatsInformation();
		sInfor.setSeatInfoId(id);
		sInfor.setCinemaId(sTList.getCinemaId());
		sInfor.setMovieId(sTList.getMovieId());
		sInfor.setCineplexId(sTList.getCineplexId());
		sInfor.setNoOfSeats(sTList.getNoOfSeats());
		sInfor.setPrice(sTList.getTicketPrice());
		sInfor.setshowTimeId(showTimeId);
		if(sINfoList.size()==0)
		{
			createSeatInformation(sInfor);
		}
		seatList=SeatsDataControl.readSeats(id);
		Scanner sc=new Scanner(System.in);
		
		String seatName = "";
		char fA='A';
		int seatA=1,seatB=1;
		int seatAmount=sTList.getNoOfSeats();
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
				//s.setShowTimeId(sTList.getShowTimeId());
				s.setMovieId(sTList.getMovieId());
				s.setCinemaId(sTList.getCinemaId());
				s.setSeatName(seatName);
				s.setSeatId(seatId++);
				s.setSeatsInformationId(id);
				seatList.add(s);
				createIndiviualSeat(seatList);
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
				oS.setI(seatList.get(i).getSeatsInformationId());
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
		return selectedSeats;
	}
	
	
	
	public static void createSeatInformation(SeatsInformation seatInfos) throws IOException
	{
		List alw = new ArrayList() ;// to store Professors data
		
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
			st.append(seatInfos.getSeatInfoId());
			st.append(SEPARATOR);
			st.append(seatInfos.getNoOfSeats());
			st.append(SEPARATOR);
			st.append(seatInfos.getNoOfEmptySeats());
			st.append(SEPARATOR);
			st.append(seatInfos.getMovieId());
			st.append(SEPARATOR);
			st.append(seatInfos.getCinemaId());
			st.append(SEPARATOR);
			st.append(seatInfos.getCineplexId());
			st.append(SEPARATOR);
			st.append(seatInfos.getShowTimeId());
			st.append(SEPARATOR);
			st.append(seatInfos.getPrice());
			
		
			
		//st.append("\n");
		alw.add(st.toString()) ;
		

		write("data/seatsInformation.txt",alw);
	
		
	}
	
	

	public static void createIndiviualSeat(ArrayList<Seats> seatList) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(seatList.get(0).getSeatsInformationId());
		int seatInforid=sINfoList.size()+1;
		int id=0;
		id=id+1;
		
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
		for(int i=0;i<seatList.size();i++){
			ocu=String.valueOf(seatList.get(i).isOccupied());
		
			st.append(seatList.get(i).getSeatId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getCinemaId());
			st.append(SEPARATOR);
			st.append(seatInforid);
			st.append(SEPARATOR);
			st.append(seatList.get(i).getSeatName());
			st.append(SEPARATOR);
			st.append(ocu);
			st.append("\n");
		}
			
		//st.append("\n");
		alw.add(st.toString()) ;
		

		writeB("data/seatsForSeatInfoId"+seatInforid+".txt",alw);
	}
	
	
	public void updateSeats(ArrayList<Seats> seatList, ArrayList<Seats> actualSeats) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(seatList.get(0).getSeatsInformationId());
		
		int id=0;
		id=id+1;
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
		for(int i=0;i<seatList.size();i++){
			for(int j=0;j<actualSeats.size();j++)
			{
				if(seatList.get(i).getSeatId()==actualSeats.get(j).getSeatId())
				{
					ocu=String.valueOf(actualSeats.get(j).isOccupied());
					break;
				}else{
					ocu=String.valueOf(seatList.get(i).isOccupied());
				}
			}
			st.append(seatList.get(i).getSeatId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getMovieId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getCinemaId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getSeatsInformationId());
			st.append(SEPARATOR);
			st.append(seatList.get(i).getSeatName());
			st.append(SEPARATOR);
			st.append(ocu);
			st.append("\n");
		}
			
		//st.append("\n");
		alw.add(st.toString()) ;
		
		writeB("data/seatsForSeatInfoId"+sINfoList.get(0).getSeatInfoId()+".txt",alw);
	}
	
	
	
	public Seats searchSeat(ArrayList<ObjectContainer> o,String name)
	{
		Seats a=new Seats();
		for(int i=0;i<o.size();i++)
		{
			
			if(o.get(i).getName().equals(name))
			{		
				
					return o.get(i).getSeatList().get(i);
			}
			
		}
		return null;
	}
	
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
					System.out.println("b="+sorted.get(j)+" a="+sorted.get(i));
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
		
	
