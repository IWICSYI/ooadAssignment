package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.SortTop5;
import controllerClasses.ValidationControl;

public class CustMain {

	public static void displayCustMain() throws IOException, ParseException{
		System.out.println("#############################################");
		System.out.println("#            Customer Main Page             #");
		System.out.println("#############################################");

		
		
		Scanner sc=new Scanner(System.in);
		int choice=0;
		do{
			System.out.println("1.List non platinum movie listing.");
			System.out.println("2.List platinum movie listing.");
			System.out.println("3.List top 5 movies based on rating.");
			System.out.println("4.List top 5 movies based on ticket sales.");
			System.out.println("5.Search booking history.");
			System.out.println("6.Go to module selection.");
			choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		}while(choice<=0||choice>6);
		
		if(choice==1){
			CustDisplayMovieListingUi.displayNowShowing(0);
		}
		else if(choice==2){
			CustDisplayMovieListingUi.displayNowShowing(1);
		}
		else if(choice==3){
			SortTop5.sortTopScoreForCustomer();
			System.out.println("Ready to resume?");
			sc.nextLine();
			displayCustMain();
		}
		else if(choice==4){
			SortTop5.sortTopSalesForCustomer();
			System.out.println("Ready to resume?");
			sc.nextLine();
			displayCustMain();
		}
		else if(choice==5){
			CustSearchBookingUi.displaySearchBooking();
		}
		else if(choice==6){
			ModuleSelectionUi.display();
		}
		
	}
	
}
