package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import data.*;

public class ObjectContainer {
	private boolean old;
	private boolean child;
	private boolean holiday;
	private boolean plat;
	private boolean blockBuster;
	private boolean tD;
	private boolean weekEnd;
	private int i;
	private int id;
	private int cineplexId;
	private int cinemaId;
	private int cineType;
	private int movieId;
	private int movieLen;
	private int movieType;
	private int block;
	private int dayType;
	private int seatNo;
	private int showTimeId;
	private int listingId;
	private String date;
	private double price;
	private double initialprice;
	private Movie m;
	private ShowTime sT;
	private Seats seat;
	private MovieSchedule movieListing;
	private Transaction transcation;
	private String name;
	private ArrayList<ShowTime> showTimeList;
	private ArrayList<Seats> seatList;
	private ArrayList<Integer> newST;
	private ArrayList<String> stringArray;
	private String timeValue;
	private ArrayList<Integer> sTIdList;
	public ObjectContainer(){}
	
	public int getCineplexId() {
		return cineplexId;
	}

	public void setCineplexId(int cineplexId) {
		this.cineplexId = cineplexId;
	}

	
	//Constructor to pair id and choice with name
	public ObjectContainer(int i, int id, String name, int cineType, int seatNo) {
		super();
		this.i = i;
		this.id = id;
		this.name = name;
		this.cineType=cineType;
		this.seatNo=seatNo;
	}
	
	public ObjectContainer(String name, ArrayList<Seats> seats) {
		super();
		
		this.name = name;
		this.seatList=seats;
	}
	
	public ObjectContainer(int i, int id, String name) {
		super();
		this.i = i;
		this.id = id;
		this.name = name;
		
	}
	
	public ObjectContainer(int id, String string) {
		super();
		this.id = id;
		this.setTimeValue(string);
	}
	
	public ObjectContainer(int i, int id, int len, int movieType, int block) {
		super();
		this.i = i;
		this.id = id;
		this.movieLen = len;
		this.movieType=movieType;
		this.block=block;
	}
	
	//Constructor to pair id and choice 
	public ObjectContainer(int i, int id) {
		super();
		this.i = i;
		this.id = id;
	}
	
	public ObjectContainer(int i2, Movie m2) {
		this.i=i2;
		this.m=m2;
	}

	public ObjectContainer(int i2, int dayOfWeek,String showTimeArray,int showTimeId) {
		this.i=i2;
		this.dayType=dayOfWeek;
		this.timeValue=showTimeArray;
		this.showTimeId=showTimeId;
				
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getId() {
		return id;
	}
	public void setId(int choice) {
		this.id = choice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public ArrayList<Integer> getNewST() {
		return newST;
	}

	public String printNewSTList() {
		String a = "";
		for(int i=0;i<newST.size();i++)
		{
			a=a+" "+newST.get(i).toString();
		}
		return a;
	}
	
	public Set<Integer> getUniqueNewSTList() {
		Set<Integer> a=new HashSet<Integer>();
		for(int i=0;i<newST.size();i++)
		{
			a.add(newST.get(i).intValue());
		}
		return a;
	}

	
	public void setNewST(ArrayList<Integer> newST) {
		this.newST = newST;
	}

	public int getMovieLen() {
		return movieLen;
	}

	public void setMovieLen(int movieLen) {
		this.movieLen = movieLen;
	}

	public ArrayList<String> getStringArray() {
		return stringArray;
	}
	
	public void printStringArray() {
		for(int i=0;i< stringArray.size();i++)
		if(id==1)
			System.out.print("Movie Already Allocated:"+stringArray.get(i)+" ");
		else if(id==2)
			System.out.print("Cinema Already Allocated:"+stringArray.get(i)+" ");
		
	}

	public void setStringArray(ArrayList<String> stringArray) {
		this.stringArray = stringArray;
	}

	public int getMovieType() {
		return movieType;
	}

	public void setMovieType(int movieType) {
		this.movieType = movieType;
	}

	public int getCineType() {
		return cineType;
	}

	public void setCineType(int cineType) {
		this.cineType = cineType;
	}

	public String getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(String timeValue) {
		this.timeValue = timeValue;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public Movie getM() {
		return m;
	}

	public void setM(Movie m) {
		this.m = m;
	}

	public int getDayType() {
		return dayType;
	}

	public void setDayType(int dayType) {
		this.dayType = dayType;
	}

	public ArrayList<Integer> getsTIdList() {
		return sTIdList;
	}

	public void setsTIdList(ArrayList<Integer> sTIdList) {
		this.sTIdList = sTIdList;
	}

	public int getShowTimeId() {
		return showTimeId;
	}

	public void setShowTimeId(int showTimeId) {
		this.showTimeId = showTimeId;
	}

	public ArrayList<Seats> getSeatList() {
		return seatList;
	}

	public void setSeatList(ArrayList<Seats> seatList) {
		this.seatList = seatList;
	}

	public ShowTime getsT() {
		return sT;
	}

	public void setsT(ShowTime sT) {
		this.sT = sT;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public MovieSchedule getMovieListing() {
		return movieListing;
	}

	public void setMovieListing(MovieSchedule movieListing) {
		this.movieListing = movieListing;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public ArrayList<ShowTime> getShowTimeList() {
		return showTimeList;
	}

	public void setShowTimeList(ArrayList<ShowTime> showTimeList) {
		this.showTimeList = showTimeList;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isOld() {
		return old;
	}

	public void setOld(boolean old) {
		this.old = old;
	}

	public boolean isChild() {
		return child;
	}

	public void setChild(boolean child) {
		this.child = child;
	}

	public boolean isHoliday() {
		return holiday;
	}

	public void setHoliday(boolean holiday) {
		this.holiday = holiday;
	}

	public Transaction getTranscation() {
		return transcation;
	}

	public void setTranscation(Transaction transcation) {
		this.transcation = transcation;
	}

	public boolean isPlat() {
		return plat;
	}

	public void setPlat(boolean plat) {
		this.plat = plat;
	}

	public boolean isBlockBuster() {
		return blockBuster;
	}

	public void setBlockBuster(boolean blockBuster) {
		this.blockBuster = blockBuster;
	}

	public boolean istD() {
		return tD;
	}

	public void settD(boolean tD) {
		this.tD = tD;
	}

	public boolean isWeekEnd() {
		return weekEnd;
	}

	public void setWeekEnd(boolean weekEnd) {
		this.weekEnd = weekEnd;
	}

	public double getInitialprice() {
		return initialprice;
	}

	public void setInitialprice(double initialprice) {
		this.initialprice = initialprice;
	}



}
