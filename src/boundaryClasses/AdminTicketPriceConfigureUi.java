package boundaryClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controllerClasses.AdminConfigTicketPriceControl;
import controllerClasses.ValidationControl;
import data.Prices;
import dataController.TicketPriceAndHolidayDataControl;
/**
 * Display ui to configure ticket and prices
 * @author Chang En Kai
 *
 */
public class AdminTicketPriceConfigureUi extends AdminConfigureUi {
	
	public static void displayTicketConfigureMain() throws IOException{
		System.out.println("#############################################");
		System.out.println("#        Ticket Configuration Page          #");
		System.out.println("#############################################");

		System.out.println("These are the ticket prices");
		Scanner sc=new Scanner(System.in);
		ArrayList<Prices>  priceList=TicketPriceAndHolidayDataControl.readPrice();
		Double baseprice;
		for(int i=0;i<priceList.size();i++)
		{
			baseprice = priceList.get(i).getNormal();
			System.out.println("Normal:"+priceList.get(i).getNormal()							);			
			System.out.println("Platinum:"+(baseprice+priceList.get(i).getPlat()				));
			System.out.println("3D:"+(baseprice+priceList.get(i).gettD()						));
			System.out.println("Blockbuster:"+(baseprice+priceList.get(i).getBlockbuster()		));
			System.out.println("Holiday:"+(baseprice+priceList.get(i).getHoli()					));
			System.out.println("Weekend:"+(baseprice+priceList.get(i).getWeekend()				));
			System.out.println("Senior Citizen:"+(baseprice+priceList.get(i).getOldPrice()			));
			System.out.println("Children:"+(baseprice+priceList.get(i).getChildPrice()		));
			
					
		}
		
		int choice;
		do
		{
			System.out.println("Select which category you want to edit:");
			System.out.println("1.Normal");
			System.out.println("2.Platinum");
			System.out.println("3.3D");
			System.out.println("4.Blockbuster");
			System.out.println("5.Holiday:");
			System.out.println("6.Weekend");
			System.out.println("7.Senior Citizen");
			System.out.println("8.Children");
			System.out.println("9.Go back to previous menu");
			choice = ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
		
		}while(choice <=0|| choice > 9 );
		displayUpdateTicketConfigureMain(choice,priceList);
	
	}
	
	
	public static void displayUpdateTicketConfigureMain(int choice,ArrayList<Prices> priceList) throws IOException{
		double tempprice;
		double base=priceList.get(0).getNormal();
		boolean valid = false;
		
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Enter the new price : ");
			tempprice =ValidationControl.validateAndReturnDoubleValue(sc.nextLine());
		
		
		switch(choice)
		{	
			case 1:
				priceList.get(0).setNormal(tempprice);
				valid=true;
				break;
			case 2:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				System.out.println(valid);
				if(valid)
				{
					priceList.get(0).setPlat(tempprice-base);
				}
				break;
			case 3:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).settD(tempprice-base);
				}
				break;
			case 4:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).setBlockbuster(tempprice-base);
				}
				break;
			case 5:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).setHoli(tempprice-base);
				}
				break;
			case 6:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).setWeekend(tempprice-base);
				}
				break;
			case 7:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).setOldPrice(tempprice-base);
				}
				break;
			case 8:
				valid=AdminConfigTicketPriceControl.checkPrice(choice, base, tempprice);
				if(valid)
				{
					priceList.get(0).setChildPrice(tempprice-base);
				}
				break;
	    }
		}while(tempprice <0||valid==false);
		
		TicketPriceAndHolidayDataControl.updatePrice(priceList.get(0));
		
		displayTicketConfigureMain();
		
	}
}
