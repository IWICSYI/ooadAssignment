package controllerClasses;

import java.util.ArrayList;
import java.util.Scanner;

import data.Movie;

public class AdminMovieEntryControl {

	public static String removeCast(Movie m){
		Scanner sc=new Scanner(System.in);
		int choice=0;
		String[] castArr=m.getCast().split(",");
		String newCast="";
		for(int i=0;i<castArr.length;i++){
			System.out.println((i+1)+"."+castArr[i]);
		}
		do{
			System.out.println("Select number beside cast member to remove them from cast list.");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=0||choice>castArr.length);
		
		castArr[choice-1]="";
			
		for(int i=0;i<castArr.length;i++)
		{
			newCast=newCast+castArr[i];
		}
		
		
		return newCast;
		
	}
	
}
