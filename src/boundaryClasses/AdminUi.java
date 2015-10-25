package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.ValidationControl;

public class AdminUi {

	public AdminUi(){
		
	}

	public void display() throws IOException, ParseException {
		Scanner sc=new Scanner(System.in);
		System.out.println("#############################################");
		System.out.println("#                 Admin Page                #");
		System.out.println("#   1.Create/Update/Remove movie entries    #");
		System.out.println("#   2.Create/Update/Remove cinema showtimes #");
		System.out.println("#     and the movies to be shown            #");
		System.out.println("#   3.Configure System Setting              #");
		System.out.println("#############################################");
		String s=sc.nextLine();
		int choice = 0;
		do
		{
				choice=ValidationControl.validateAndReturnIntegerValue(s);
				if(choice<1||choice>3)
				{
					System.out.println("Invalid Input, please try again"+choice);
				}
				else if(choice==1){
					MovieEntryUi mUI=new MovieEntryUi();
					mUI.displayMain();
				}
				else if(choice==2){
					SchedulerUi sUI=new SchedulerUi();
					sUI.displayMain();
				}
			
			
		}while(choice>3);
		
	}

}
