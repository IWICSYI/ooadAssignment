package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;

public class AdminSchedulerMainUi extends AdminMainUi {
	
	/**
	 * Display main movie schedule page where user can create and update movie schedule/listing.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displaySchedulerMain() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		int choice = 0;
		String test;
		
		
		do{
			System.out.println("#############################################");
			System.out.println("#            Schedule Manager Page          #");
			System.out.println("#          1.Create Movie Listing           #");
			System.out.println("#          2.Update Movie Listing           #");
			System.out.println("#          3.Return to main menu            #");
			System.out.println("#############################################");
			test=sc.nextLine();
			choice=ValidationControl.validateAndReturnIntegerValue(test);
			
			if(choice==1)
			{
				AdminSchedulerCreateUi.displaySchedulerCreatePageMain();
			}
			else if(choice==2){
				AdminSchedulerUpdateUi.displayUpdateMain();
			}
			else if(choice==3){
				AdminMainUi.displayAdminMain();
			
			}
		}while(choice>3||choice<=0);
		
		
	}
	
	
	
	
	

}
