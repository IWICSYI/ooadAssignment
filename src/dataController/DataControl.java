package dataController;

import java.io.BufferedWriter;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import controllerClasses.TimeDateControl;
import data.*;

public class DataControl extends TimeDateControl {
	
	protected static final String SEPARATOR = "|";
	protected static final SimpleDateFormat finalDateFormatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	 protected static ArrayList<String> read(String fileName) throws IOException {
			ArrayList<String> data = new ArrayList<String>() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		        
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }

	
	
	
		


	public static void write(String fileName, List data) throws IOException  {
	PrintWriter out = new PrintWriter(new FileWriter(fileName,true));
		
		try {
			for (int i =0; i < data.size() ; i++) {
		  		out.println((String)data.get(i));
			}
	}
		finally {
		  out.close();
		}
	}
	
	public static void writeB(String fileName, List data) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));
			
			try {
				for (int i =0; i < data.size() ; i++) {
			  		out.print((String)data.get(i));
				}
		}
			finally {
			  out.close();
			}
		}
	
	
	
	
	
	public ArrayList<Prices> readPrice() throws IOException{
		
		ArrayList stringArray = (ArrayList)read("data/ticketPrice.txt");
		ArrayList alr = new ArrayList() ;// to store data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				//normal price=8*platinum=6*3D=1*blockbuster=*0.5*Age65>-2*holidayPrice=2*weekEndPrice=2
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter "|"
				double normal=Double.parseDouble(star.nextToken().trim());
				double  plat = Double.parseDouble(star.nextToken().trim());	
				double tD=Double.parseDouble(star.nextToken().trim());
				double blockbuster=Double.parseDouble(star.nextToken().trim());
				double age=Double.parseDouble(star.nextToken().trim());
				double child=Double.parseDouble(star.nextToken().trim());
				double holi=Double.parseDouble(star.nextToken().trim());
				double weekend=Double.parseDouble(star.nextToken().trim());
				
				
					Prices u = new Prices(normal,plat,tD,blockbuster,age,child,holi,weekend);
					// add to  list
					alr.add(u) ;
				}
        return alr ;
			}
			
	
	
	
	

	
	

	
	
}
