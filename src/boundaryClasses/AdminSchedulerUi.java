package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.MiscControl;
import controllerClasses.MovieListingControl;
import controllerClasses.AdminSchedulerController;
import controllerClasses.AdminShowTimeController;
import controllerClasses.ValidationControl;
import data.Cinema;
import data.Cineplex;
import data.Movie;
import data.MovieSchedule;
import data.ShowTime;
import dataController.CineplexDataControl;
import dataController.DataControl;
import dataController.MovieDataControl;

public class AdminSchedulerUi extends AdminMainUi {

	public static void displaySchedulerMain() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		
		MovieDataControl mec=new MovieDataControl();
		do{
			System.out.println("#############################################");
			System.out.println("#            Schedule Manager Page          #");
			System.out.println("#          1.Create Movie Listing           #");
			System.out.println("#          2.Update Movie Listing           #");
			System.out.println("#          3.Sort Movies by Rankings        #");
			System.out.println("#          4.Return to main menu            #");
			System.out.println("#############################################");
			test=sc.nextLine();
			validation=vl.isInteger(test);
			if(validation)
				choice=Integer.parseInt(test);
			else
			{
				System.out.println("Invalid choice, please try again");
				choice=200;
			}
			if(choice==1)
			{
				displaySchedulerCreatePageMain();
			}
			else if(choice==2){
				AdminSchedulerUpdateUi up=new AdminSchedulerUpdateUi();
				up.displayUpdateMain();
			}
			else if(choice==3){
				//tobedone
			}
			else if(choice==4){
				AdminMainUi.displayAdminMain();
			
			}
		}while(choice<5);
		
		
	}
	
	
	
	
	public static void displaySchedulerCreatePageMain() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		System.out.println("#############################################");
		System.out.println("#            Schedule Creation Page         #");
		System.out.println("#############################################");
		int choice=0;
	do{
		System.out.println("1.Create listing for now showing.");
		System.out.println("2.Create listing for preview.");
		System.out.println("3.Create listing for comming soon.");
		System.out.println("4.Go Back.");
		choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		
		if(choice==4){
			displaySchedulerMain();
		}
	
	}while(choice<0||choice>4);
	
	AdminSchedulerController.createScheduleGeneric(choice);
		//displayMain();
			
		
	}
	
	
	

}
