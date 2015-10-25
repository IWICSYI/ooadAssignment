package boundaryClasses;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Scanner;

import controllerClasses.LoginControl;

public class LoginUi {
	
	public LoginUi(){
		
	}
	
	public void display() throws IOException, ParseException
	{
		Console console= System.console();
		 if (console == null) {
		        System.out.println("Couldn't get Console instance");
		        
		    }

		boolean result,check=false;
		String userName,password = null;
		Scanner sc=new Scanner(System.in);
		System.out.println("#########################");
		System.out.println("#	LOGIN PAGE	#");
		System.out.println("#########################");
		do{
			System.out.println("Please enter username:");
			userName=sc.nextLine();
			if (console != null) {
			        System.out.println("Couldn't get Console instance");
			        char[] pW=console.readPassword("Please your  password: ");
			        password=pW.toString();
			    }
			else
			{
				System.out.println("Please enter password:");
			    password=sc.nextLine();
			}
			
			LoginControl lg=new LoginControl();
		    result=lg.checkExist(userName, password);
			if(result)
			{
				check=true;
				AdminUi a=new AdminUi();
				a.display();
			}
			else
				System.out.println("Invalid username and password,please try again.\n");
				
		}while(!check);
	}

}
