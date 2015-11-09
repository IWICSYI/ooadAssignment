package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.AdminSchedulerController;
import controllerClasses.ValidationControl;

public class AdminSchedulerCreateUi extends AdminSchedulerMainUi {
	
	
/**
 * Display scheduler page that deals with creation of movie schedule and showtime.
 * @throws IOException
 * @throws ParseException
 */
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
		
		}while(choice<=0||choice>4);
	
		AdminSchedulerController.createScheduleGeneric(choice);
		//displayMain();
			
		
	}
	
	

}
