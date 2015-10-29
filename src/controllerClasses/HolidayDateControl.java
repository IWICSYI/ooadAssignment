import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import data.HolidayDate;
import dataController.DataControl;

public class HolidayDateControl extends DataControl {
	

	int uID;
	int count;
	String fileName;
	HolidayDate HDE = new HolidayDate();
	public void createHoliday()
	{
		//loop out all the entries in Holiday txt
		
		
		
		uID = HDE.getHolidayId();
		//count = HDE.getCount();
		//fileName = HDE.getFileName();
		//System.out.println(al.toString());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Holiday Date (dd/MM/yyyy): ");
		
		Date date;
		String tempDate = sc.nextLine();
		try {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = dateFormat.parse(tempDate);
		String inDate = date.toString();
		
		
		PrintWriter out = null;
		try {
		    out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		    out.println(uID+1 + " " + tempDate);
		    System.out.println("Succeeded in creating a holiday date");
		}catch (IOException e) {
		    System.err.println(e);
		}finally{
		    if(out != null){
		        out.close();
		    }
		} 
            
		
		 } catch (ParseException e) {
		        System.out.println("Not valid format");
		}
	}
	
	public void showHoliday()
	{
		al = HDE.getAL();
		uIDList = HDE.getuIDList();
		uID = HDE.getuID();
		count = HDE.getCount();
		fileName = HDE.getFileName();
		
		System.out.println("Index\tUniqueID\tDate");
		for(int i = 0 ; i < count; i++)
		{
			System.out.println(i+1 + "\t" +uIDList.get(i)+"\t\t"+ al.get(i));
		}
	}
	
	public void updateHoliday()
	{
		al = HDE.getAL();
		uIDList = HDE.getuIDList();
		uID = HDE.getuID();
		count = HDE.getCount();
		fileName = HDE.getFileName();
		
		System.out.println("Index\tUniqueID\tDate");
		for(int i = 0 ; i < count; i++)
		{
			System.out.println(i+1 + "\t" +uIDList.get(i)+"\t\t"+ al.get(i));
		}
		
		System.out.println("Please select one(index) to update : ");
		Scanner sc = new Scanner(System.in);
		String uniqueID = sc.nextLine();
		String refer = uIDList.get(Integer.parseInt(uniqueID)-1) +" "+ al.get(Integer.parseInt(uniqueID)-1);
		
		System.out.println("Please enter date to update entry: ");
		Date date;
		String tempDate = sc.nextLine();
		try {
			String replace = uIDList.get(Integer.parseInt(uniqueID)-1) +" "+ tempDate;
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			date = dateFormat.parse(tempDate);
			String inDate = date.toString();
			
			replaceLineFromFile(fileName,replace,refer);
	            
			System.out.println("Succeeded in updating the holiday date.");
			 } catch (ParseException e) {
			        System.out.println("Not valid format");
			}
		
		
		
		
		
		
		
		
	}
	
	public void removeHoliday()
	{
		al = HDE.getAL();
		uIDList = HDE.getuIDList();
		uID = HDE.getuID();
		count = HDE.getCount();
		fileName = HDE.getFileName();
		System.out.println(al.toString());
		
		
		System.out.println("Index\tUniqueID\tDate");
		for(int i = 0 ; i < count; i++)
		{
			System.out.println(i+1 + "\t" +uIDList.get(i)+"\t\t"+ al.get(i));
		}
		
		//no error check for invalid values
		System.out.println("Please select one(index) to delete : ");
		Scanner sc = new Scanner(System.in);
		String uniqueID = sc.nextLine();
		String delete = uIDList.get(Integer.parseInt(uniqueID)-1) +" "+ al.get(Integer.parseInt(uniqueID)-1);
		System.out.println(delete);
		removeLineFromFile(fileName,delete);
		System.out.println("Succeeded in removing the holiday date.");
	}
	
	
	//taken from somewhere
	
}
