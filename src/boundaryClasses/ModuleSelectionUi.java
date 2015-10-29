package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;

public class ModuleSelectionUi {
	
	public static void display() throws IOException, ParseException
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
	
		
		do
		{
			System.out.println("Enter Module Choice");
			System.out.println("1.Admin Module");
			System.out.println("2.Movie Goer Module");
			String s=sc.nextLine();
			choice=ValidationControl.validateYesNoAndReturnIntegerValue(s);
			if(choice<1||choice>2)
			{
				System.out.println("Invalid input, please try again"+choice);
				choice=200;
			}
			else if(choice==1){

				AdminLoginUi lg=new AdminLoginUi();
				lg.display();
			}
			else if(choice==2){

				CustomerDisplayMovieListingUi cu=new CustomerDisplayMovieListingUi();
				cu.displayNowShowing();
			}


		}while(choice>3);

		
		
	}
	
}
