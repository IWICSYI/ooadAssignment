package misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ObjectContainer {
	private int i;
	private int id;
	private int cinemaId;
	private int cineType;
	private int movieId;
	private int movieLen;
	private int movieType;
	private String name;
	ArrayList<Integer> newST;
	ArrayList<String> stringArray;
	
	//Constructor to pair id and choice with name
	public ObjectContainer(int i, int id, String name, int cineType) {
		super();
		this.i = i;
		this.id = id;
		this.name = name;
		this.cineType=cineType;
	}
	
	public ObjectContainer(int i, int id, String name) {
		super();
		this.i = i;
		this.id = id;
		this.name = name;
		
	}
	
	public ObjectContainer(int id, ArrayList<String> stringArray) {
		super();
		this.id = id;
		this.stringArray = stringArray;
	}
	
	public ObjectContainer(int i, int id, int len, int movieType) {
		super();
		this.i = i;
		this.id = id;
		this.movieLen = len;
		this.movieType=movieType;
	}
	
	//Constructor to pair id and choice 
	public ObjectContainer(int i, int id) {
		super();
		this.i = i;
		this.id = id;
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

}
