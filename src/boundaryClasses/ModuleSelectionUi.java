package boundaryClasses;

import java.util.Scanner;

import controllerClasses.ValidationControl;

public class ModuleSelectionUi {
	
	public static void display()
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
	
		ValidationControl vl=new ValidationControl();
		do
		{
			System.out.println("Enter Module Choice");
			System.out.println("1.Admin Module");
			System.out.println("2.Movie Goer Module");
			try
			{
				choice=vl.validateAndReturnIntegerValue(sc.nextLine());
				if(choice<1||choice>2)
				{
					System.out.println("Invalid input, please try again");
					choice=200;
				}
				else if(choice==1){
					
					LoginUi lg=new LoginUi();
					lg.display();
				}
			}
			catch(Exception e)
				{
					System.out.println(e);
				
				}
		}while(choice>3||choice<0);
		
		
		
	}
	
}
