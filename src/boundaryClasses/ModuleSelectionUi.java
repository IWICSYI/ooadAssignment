package boundaryClasses;

import java.util.Scanner;

import controllerClasses.ValidationControl;

public class ModuleSelectionUi {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		String test;
		boolean validation;
		ValidationControl vl=new ValidationControl();
		do
		{
			System.out.println("Enter Module Choice");
			System.out.println("1.Admin Module");
			System.out.println("2.Movie Goer Module");
			try
			{
				test=sc.nextLine();
				validation=vl.isInteger(test);
				if(validation)
					choice=Integer.parseInt(test);
				else
					choice=200;
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
