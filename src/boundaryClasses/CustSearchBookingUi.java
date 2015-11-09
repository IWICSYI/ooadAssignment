package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.CustTransactionControl;
import controllerClasses.ValidationControl;

public class CustSearchBookingUi extends CustMain{
	
	/**
	 * Display page to search booking history.
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void displaySearchBooking() throws IOException, ParseException{
		Scanner sc=new Scanner(System.in);
		boolean valid=false;
		String email="";
		do
		{
			System.out.println("Please enter your email(abc@hotmail.com):");
			email=sc.nextLine();
			valid=ValidationControl.validateEmptyString(email);
		}while(!valid);
		
		boolean exist=CustTransactionControl.searchTransaction(email);
		
		if(!exist){
			System.out.println("No booking history for email:"+email);
			
			
			
		}
		
		System.out.println("Ready to resume?");
		sc.nextLine();
		
		CustMain.displayCustMain();
		
		
			
	}

}
