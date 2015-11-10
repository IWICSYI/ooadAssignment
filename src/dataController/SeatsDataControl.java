package dataController;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import data.Seats;
import data.SeatsInformation;

/**
 * Class that deals with actual CRUD of seats
 * @author Chang En Kai
 *
 */
public class SeatsDataControl extends DataControl {
	
	/**
	 * Create seat informations for back up in case database of show time and transacation can't match-
	 * @param seatInfos Seat informations
	 * @throws IOException
	 */
	public static void createSeatInformation(SeatsInformation seatInfos) throws IOException
	{
		List alw = new ArrayList() ;// to store Professors data
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		
		StringBuilder st =  new StringBuilder() ;
			st.append(sdf.format(seatInfos.getStartDate()));
			st.append(SEPARATOR);
			st.append(seatInfos.getStartEndTime());
			st.append(SEPARATOR);
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
	
	
/**
 * Specific characteristics of each indiviual seat in a cinema hall
 * @param seatList A list of seats
 * @throws IOException
 * @throws ParseException
 */
	public static void createIndiviualSeat(ArrayList<Seats> seatList) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(seatList.get(0).getSeatsInformationId());
		
		
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
		String ocu="";
		int seatInfoId=seatList.get(0).getSeatsInformationId();
		StringBuilder st =  new StringBuilder() ;
		for(int i=0;i<seatList.size();i++){
			ocu=String.valueOf(seatList.get(i).isOccupied());
		
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
		

		writeB("data/seats/seatsForShowTime"+seatInfoId+".txt",alw);
	}
	
	/**
	 * Update and reflect seat selections
	 * @param seatList A list of existing seats
	 * @param actualSeats User selected seats
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void reflectAndConfirmSeatSelections(ArrayList<Seats> seatList, ArrayList<Seats> actualSeats) throws IOException, ParseException {
		File file = new File("data/seatsTmp.txt");
    	
		if(file.delete()){
			
		}else{
			System.out.println("Delete operation failed.");
		}
	   
		
		
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(seatList.get(0).getSeatsInformationId());
		
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
		
		writeB("data/seats/seatsForShowTime"+sINfoList.get(0).getSeatInfoId()+".txt",alw);
	}

/**
 * Reflect seat selections by creating a temp file that will display seats that customer selected without commiting to anything.
 * @param seatList A list of seats
 * @param actualSeats Seats user selected
 * @throws IOException
 * @throws ParseException
 */
	public static void reflectSeatSelections(ArrayList<Seats> seatList,
		ArrayList<Seats> actualSeats) throws IOException, ParseException {
		List alw = new ArrayList() ;// to store Professors data
		ArrayList<SeatsInformation> sINfoList=SeatsDataControl.readSeatInfor(seatList.get(0).getSeatsInformationId());
		
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
		
		writeB("data/seatsTmp.txt",alw);

		
	}
		
		
	
	
	
	/**
	 * Read seats based on seat info Id
	 * @param seatInfoId Seat infor ID
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Seats> readSeats(int seatInfoId) throws IOException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seats/seatsForShowTime"+seatInfoId+".txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int seatId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  seatInforId = Integer.parseInt(star.nextToken().trim());
				String seatName=star.nextToken().trim();
				String ocu = star.nextToken().trim();
				boolean occupied=Boolean.parseBoolean(ocu);
				
				// add to  list
				
				if(seatInforId==seatInfoId)
				{	
					// add to  list
					Seats u = new Seats(seatId,movieId,cinemaId,seatInforId,seatName,occupied);
					alr.add(u) ;
				}
			}
			return alr ;
	}
	
	
	/**
	 * Read seats that are created temporary to show selections
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<Seats> readTmpSeats() throws IOException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seatsTmp.txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				int seatId=Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  seatInforId = Integer.parseInt(star.nextToken().trim());
				String seatName=star.nextToken().trim();
				String ocu = star.nextToken().trim();
				boolean occupied=Boolean.parseBoolean(ocu);
				
				// add to  list
				
				// add to  list
					Seats u = new Seats(seatId,movieId,cinemaId,seatInforId,seatName,occupied);
					alr.add(u) ;
				
			}
			return alr ;
	}
	
	/**
	 * Read seat information
	 * @param seatInfoID  seat info ID
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<SeatsInformation> readSeatInfor(int seatInfoID) throws IOException, ParseException{
		ArrayList alr = new ArrayList() ;
		ArrayList stringArray =new ArrayList();
		try
		{
			 stringArray = (ArrayList)read("data/seatsInformation.txt");
		}
		catch(IOException e)
		{
				return alr;
		}
		for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				
//				private int seatInfoId;
				//	private int noOfSeats;
				//	private int noOfEmptySeats;
				//	private int movieId;
				//	private int cinemaId;
				//	private int cineplexId;
				//stid
				//price
				
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				String startEndDate=star.nextToken().trim();
				Date date=finalDateFormatter.parse(startEndDate);
				String time=star.nextToken().trim();
				int seatInfoId=Integer.parseInt(star.nextToken().trim());
				int  noOfSeats = Integer.parseInt(star.nextToken().trim());
				int  noOfEmptySeats = Integer.parseInt(star.nextToken().trim());
				int  movieId = Integer.parseInt(star.nextToken().trim());
				int  cinemaId = Integer.parseInt(star.nextToken().trim());
				int  cineplexId = Integer.parseInt(star.nextToken().trim());
				int  showTimeId = Integer.parseInt(star.nextToken().trim());
				double  price = Double.parseDouble(star.nextToken().trim());
				
				
				
				
				// add to  list
				
				// add to  list
				if(seatInfoId==seatInfoID){
					SeatsInformation u = new SeatsInformation(date,time,seatInfoId,noOfSeats,noOfEmptySeats,movieId,cinemaId,cineplexId,showTimeId,price);
					alr.add(u) ;
				}
				
			}
			return alr ;
	}


}
