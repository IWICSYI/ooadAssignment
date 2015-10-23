package boundaryClasses;

import java.util.Scanner;

public class AdminUi {

	public AdminUi(){
		
	}

	public void display() {
		Scanner sc=new Scanner(System.in);
		System.out.println("#############################################");
		System.out.println("#                 Admin Page                #");
		System.out.println("#   1.Create/Update/Remove movie entries    #");
		System.out.println("#   2.Create/Update/Remove cinema showtimes #");
		System.out.println("#     and the movies to be shown            #");
		System.out.println("#   3.Configure System Setting              #");
		System.out.println("#############################################");
		
		int choice = 0;
		do
		{
			try
			{
				choice=sc.nextInt();
				if(choice<1||choice>3)
				{
					System.out.println("Invalid Input,please try again");
				}
				else if(choice==1){
					MovieEntryUi mUI=new MovieEntryUi();
					mUI.displayMain();
				}
				else if(choice==2){
					SchedulerUi sUI=new SchedulerUi();
					sUI.displayMain();
				}
			}
			catch(Exception e)
				{
					System.out.println("Invalid Input,please try again");
				
				}
		}while(choice>3||choice<0);
		
	}

}
