package dataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import data.Transaction;


/**
 * Class that deals with actual CRUD of transaction. Transactions happen when a customer buys movie tickets/
 * @author Chang En Kai
 *
 */
public class TransactionDataControl extends DataControl{
	

	
	/**
	 * Read all transcation
	 * @return every transactions
	 * @throws IOException
	 */
	public static ArrayList<Transaction> readTranscation() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/transaction.txt");
		
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				String transcationId=star.nextToken().trim();
			    String email=star.nextToken().trim();
				String mobilePhone=star.nextToken().trim();
				String custName=star.nextToken().trim();
				int age=Integer.parseInt(star.nextToken().trim());
			    int cineplexI=Integer.parseInt(star.nextToken().trim());
				int cinemaId=Integer.parseInt(star.nextToken().trim());
				int movie=Integer.parseInt(star.nextToken().trim());
				int numSeat=Integer.parseInt(star.nextToken().trim());
				String seats=star.nextToken().trim();
				double price=Double.parseDouble(star.nextToken().trim());
				int showTimeId=Integer.parseInt(star.nextToken().trim());
				

				Transaction u = new Transaction(transcationId, email, mobilePhone, custName, age, cineplexI, cinemaId, movie,numSeat,seats,price,showTimeId);
					// add to  list
				alr.add(u) ;
				}
        return alr ;
			}
			
/**
 * Create transaction
 * @param t new transcation
 * @throws IOException
 */
	
	public static void createTranscation(Transaction t) throws IOException {
		List alw = new ArrayList() ;// to store Professors data
		int id=0;
		
		
		//1movieUniqueId|moviName|movieType|ageRating|directer|synopsis|cast|4Overallrating|100longticketSales|120lengthMinutes
	
				
		StringBuilder st =  new StringBuilder() ;
		st.append(t.getTranscationId());
		st.append(SEPARATOR);
		st.append(t.getEmail());
		st.append(SEPARATOR);
		st.append(t.getMobilePhone());
		st.append(SEPARATOR);
		st.append(t.getCustName());
		st.append(SEPARATOR);
		st.append(t.getAge());
		st.append(SEPARATOR);
		st.append(t.getCineplexId());
		st.append(SEPARATOR);
		st.append(t.getCinemaId());
		st.append(SEPARATOR);
		st.append(t.getMovieId());
		st.append(SEPARATOR);
		st.append(t.getNumSeat());
		st.append(SEPARATOR);
		st.append(t.getSeats());
		st.append(SEPARATOR);
		st.append(t.getPrice());
		st.append(SEPARATOR);
		st.append(t.getShowtimeId());
		
		
		alw.add(st.toString()) ;
		
	
		write("data/transaction.txt",alw);
		
	}


}
