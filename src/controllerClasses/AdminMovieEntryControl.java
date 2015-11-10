package controllerClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import boundaryClasses.AdminMovieEntryUi;
import data.Movie;
import data.Transaction;
import dataController.MovieDataControl;
import dataController.TransactionDataControl;

/**
 * Class that communicate with MovieData Control to deal with CRUD of movie entries.
 * @author Chang En Kai
 *
 */
public class AdminMovieEntryControl {
/**
 * Method to remove cast member from cast list
 * @param m
 * @return
 */
	public static String removeCast(Movie m){
		Scanner sc=new Scanner(System.in);
		int choice=0;
		String[] castArr=m.getCast().split(",");
		String newCast="";
		for(int i=0;i<castArr.length;i++){
			System.out.println((i+1)+"."+castArr[i]);
		}
		System.out.println((castArr.length+1)+".Return back to previous page");
		do{
			System.out.println("Select number beside cast member to remove them from cast list.");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=0||choice>castArr.length+1);
		
		if(choice>castArr.length){
			return "-1";
		}
		
		castArr[choice-1]="";
			
		for(int i=0;i<castArr.length;i++)
		{
			newCast=newCast+castArr[i];
			if(i!=castArr.length-1&&!castArr[i].isEmpty()){
				newCast=newCast+",";
			}
			
		}
		
		
		return newCast;
		
	}
	
	/**
	 * Method to add cast members
	 * @param m
	 * @return
	 */
	public static String addCast(Movie m){
		Scanner sc=new Scanner(System.in);
		boolean choice=false;
		String[] castArr=m.getCast().split(",");
		String newCast="",add="";
		System.out.println("Existing cast member:");
		for(int i=0;i<castArr.length;i++){
			System.out.println((i+1)+"."+castArr[i]);
		}
		do{
			System.out.println("Enter new cast member(input -1 to return to previous page).");
			add=sc.nextLine();
			choice=ValidationControl.validateEmptyString(add);
		}while(!choice);
	
		for(int i=0;i<castArr.length;i++){
			newCast=newCast+castArr[i]+",";
		}
		newCast=newCast+add;
		
		return newCast;
		
	}
	
	/**
	 * Method to update cast list
	 * @param m
	 * @return
	 */
	public static String updateCast(Movie m){
		Scanner sc=new Scanner(System.in);
		int choice=0;
		String[] castArr=m.getCast().split(",");
		String newCast="";
		for(int i=0;i<castArr.length;i++){
			System.out.println((i+1)+"."+castArr[i]);
		}
		System.out.println((castArr.length+1)+".Return back to previous page");
		do{
			System.out.println("Select number beside cast member to edit selected cast.");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=0||choice>(castArr.length+1));
		
		if(choice==castArr.length+1){
			return "-1";
		}
		
		boolean valid=false;
		do{
			System.out.println("Enter edited cast name(input -1 to retry):");
			newCast=sc.nextLine();
			if(newCast.equals("-1"))
			{
				updateCast(m);
				return null;
			}
			valid=ValidationControl.validateEmptyString(newCast);
		}while(!valid);
		
		castArr[choice-1]=newCast;
		newCast="";
		for(int i=0;i<castArr.length;i++)
		{
			newCast=newCast+castArr[i];
			if(i!=castArr.length-1){
				newCast=newCast+",";
			}
		}
		
		
		return newCast;
		
	}

/**
 * Check to see if movie can remove or not, if transcation of movie exist, it will stop user from removing the movie
 * @param temp
 * @throws IOException
 * @throws ParseException
 */
	public static void removeMovieCheckHandler(Movie temp) throws IOException, ParseException {
		boolean tClash=false;
		ArrayList<Transaction>tList2=TransactionDataControl.readTranscation();
		for(int i=0;i<tList2.size();i++)
		{
			if(tList2.get(i).getMovieId()==temp.getMovieId())
			{
				System.out.println("Cannot remove timeslot because transcation already exist!");
				tClash=true;
				break;
			}
			
		}
		if(!tClash)
		{
			MovieDataControl.removeMovie(temp.getMovieId());
			System.out.println("Sucessfully removed!");
		}
		else{
		
			AdminMovieEntryUi.displayMovieUpdatePage();
		
		}
	
		
	}
	
	
	public static void handleMovieEdit(Movie temp) throws IOException, ParseException
	{
		int choice2=0;
		Scanner sc=new Scanner(System.in);
		do{
			temp.printMovieDetails();
			System.out.println("1.Edit Movie Name");
			System.out.println("2.Edit Age Rating");
			System.out.println("3.Edit Director");
			System.out.println("4.Edit Casts");
			System.out.println("5.Edit Synopsis");
			System.out.println("6.Remove Movie");
			System.out.println("7.Go back to main movie entry page");
			 choice2=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice2<=0||choice2>7);
		
		if(choice2==1)
		{
			String check="";
			boolean valid=false;
			do
			{
				System.out.println("Please enter edited movie name(input -1 to return):");
				check=sc.nextLine();
				valid=ValidationControl.validateEmptyString(check);
				if(check.equals("-1"))
				{
					handleMovieEdit(temp);
				}
			
			}while(!valid);
			temp.setMovieName(check);
			System.out.println("Successfully updated movie name!");
			
		}
		else if(choice2==2){
			String check="";
			boolean valid=false;
			do
			{
				System.out.println("Please enter age rating(input -1 to return):");
				check=sc.nextLine();
				valid=ValidationControl.validateEmptyString(check);
				if(check.equals("-1"))
				{
					handleMovieEdit(temp);
				}
			
			}while(!valid);
			temp.setAgeRating(check);
			System.out.println("Successfully updated age rating!");
			
		}
		
		else if(choice2==3){
			String check="";
			boolean valid=false;
			do
			{
				System.out.println("Please enter director name(input -1 to return):");
				check=sc.nextLine();
				valid=ValidationControl.validateEmptyString(check);
				if(check.equals("-1"))
				{
					handleMovieEdit(temp);
				}
			}while(!valid);
			temp.setDirector(check);
			System.out.println("Successfully updated director!");
			
		}
		else if(choice2==4){
			int castChoice=0;
			String newCast="";
			do{
				System.out.println("1.Remove cast member");
				System.out.println("2.Add cast member");
				System.out.println("3.Edit cast member");
				System.out.println("4.Return to previous page");
				castChoice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			}while(castChoice<=0);
			
			if(castChoice==1)
			{
				newCast=AdminMovieEntryControl.removeCast(temp);
				if(newCast.equals("-1")){
					handleMovieEdit(temp);
				}
				else
				{
					System.out.println("Successfully removed cast member!");
					temp.setCast(newCast);
				}
			
			}
			
			else if(castChoice==2)
			{
				newCast=AdminMovieEntryControl.addCast(temp);
				if(newCast.contains("-1")){
					handleMovieEdit(temp);
				}
				else
				{
					System.out.println("Successfully added cast member!");
					temp.setCast(newCast);
				}
				
			}
			
			else if(castChoice==3)
			{
				newCast=AdminMovieEntryControl.updateCast(temp);
				if(newCast.equals("-1")){
					handleMovieEdit(temp);
				}
				else
				{
					System.out.println("Successfully edited cast member!");
					temp.setCast(newCast);
				}
				
			}
			
			
			else if(castChoice>=4){
				AdminMovieEntryUi.displayMovieUpdatePage();
			}
		}
		
		
		
		
		else if(choice2==5){
			String check="";
			boolean valid=false;
			do
			{
				System.out.println("Enter new synopsis(input -1 to return to previous page):");
				check=sc.nextLine();
				valid=ValidationControl.validateEmptyString(check);
				if(check.equals("-1"))
				{
					handleMovieEdit(temp);
				}
			}while(!valid);
			temp.setSynopsis(check);
			System.out.println("Successfully updated synopsis!");
			
		}
		else if(choice2==6){
			AdminMovieEntryControl.removeMovieCheckHandler(temp);
			AdminMovieEntryUi.displayMovieUpdatePage();
		}
		
		else if(choice2==7){
			AdminMovieEntryUi.displayMovieMain();
		}
		
		MovieDataControl.updateMovie(temp);
		handleMovieEdit(temp);
			
	
		
	}
	

	
	
	

}
