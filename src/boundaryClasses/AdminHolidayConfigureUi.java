package boundaryClasses;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import misc.ObjectContainer;
import controllerClasses.AdminHolidayDateControl;
import controllerClasses.ValidationControl;
import data.HolidayDate;
import dataController.TicketPriceAndHolidayDataControl;

public class AdminHolidayConfigureUi extends AdminConfigureUi {
	
	public static void displayHolidayMain() throws IOException, ParseException
	{
		int choice;
		Scanner sc = new Scanner(System.in);

		AdminHolidayDateControl HDC = new AdminHolidayDateControl();
		System.out.println("#############################################");
		System.out.println("#         Holiday Configuration Page        #");
		System.out.println("#############################################");
		
		ArrayList<HolidayDate> holidayList=TicketPriceAndHolidayDataControl.readHoliday();
		
		System.out.println("Already allocated HolidayDate:");
		if(holidayList.isEmpty())
		{
			System.out.println(" No holiday date inserted yet.");

		}
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String datetoprint;
		Date holiday;
		for(int i=0;i<holidayList.size();i++)
		{
			holiday=holidayList.get(i).getHolidayDate();
			datetoprint=sdf.format(holiday);

			System.out.print(datetoprint+"    ");

			if(i%5==0&&i!=0){
				System.out.println();
			}

		}
		System.out.println();
			do 
			{
				System.out.println("1.Insert new holiday date               ");
				System.out.println("2.Update holiday date                   ");
				System.out.println("3.Go Back                               ");
				System.out.print("Select your choice: ");
				choice = ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
				if(choice==1){
					displayCreate();
				}
				else if(choice==2){
					displayUpdateSelection(holidayList);
				}
				else if(choice==3){
					AdminMainUi.displayAdminMain();
					
				}
			
		}while (choice <=0||choice>3);
	}
	
	
	public static void displayCreate() throws IOException, ParseException
	{
		Scanner sc=new Scanner(System.in);
		Date day;
		HolidayDate hD;
	do{
		System.out.println("Enter holiday date you want to create(Eg.25/12/2015)");
	
		day=ValidationControl.validateDate(sc.nextLine(), 0);
		hD=new HolidayDate();
		hD.setHolidayDate(day);
		
		}while(day==null);
		TicketPriceAndHolidayDataControl.createHoliday(hD);
		displayHolidayMain();
	
	}
	
	
	public static void displayUpdateSelection(ArrayList<HolidayDate> holidayList) throws ParseException, IOException
	{
		if(!holidayList.isEmpty())
		{
			int choice=0;
			ArrayList<ObjectContainer> oList=new ArrayList<ObjectContainer>();
			
			do
			{
				System.out.println("Here are the existing holiday dates,select the number beside the date to choose which date to edit. ");
				Scanner sc=new Scanner(System.in);
				SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
				String datetoprint;
				Date holiday;
				for(int i=0;i<holidayList.size();i++)
				{
					holiday=holidayList.get(i).getHolidayDate();
					datetoprint=sdf.format(holiday);
					ObjectContainer o=new ObjectContainer();
					o.setId(holidayList.get(i).getHolidayId());
					o.setI((i+1));
					o.setTimeValue(datetoprint);
					oList.add(o);
					System.out.print((i+1)+":"+datetoprint+"    ");
				
					if(i%5==0)
					{
						System.out.println();
					}
			
				}
				
				System.out.println("");
				choice=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
				}while(choice<=0||(choice>holidayList.size()));
				displayUpdatePage(oList,choice);
			}
		else
		{
			System.out.println("No date to update! Return back to configuration page.");
			displayHolidayMain();
		}
		}
	
	
	public static void displayUpdatePage(ArrayList<ObjectContainer> oList, int choice) throws ParseException, IOException{
		int id = 0;
		String date="";
		for(int i=0;i<oList.size();i++)
		{
			if(choice==oList.get(i).getI()){
				id=oList.get(i).getId();
				date=oList.get(i).getTimeValue();
				break;
			}
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		Date holi=sdf.parse(date);
		int selected=0;
		do
		{
			System.out.println("You have choosen "+date+" to edit.");
			System.out.println("Please select what you want to do");
			System.out.println("1.Edit date");
			System.out.println("2.Remove date");
			System.out.println("3.Go back to holiday configuration page");
			Scanner sc=new Scanner(System.in);
			selected=ValidationControl.validateAndReturnIntegerValue(sc.nextLine());
			boolean valid=false;
			if(selected==1){
				do{
					System.out.println("Enter new updated date:");
					String s=sc.nextLine();
					Date nHD=ValidationControl.validateDate(s,0);
					valid=AdminHolidayDateControl.checkDuplicatedHoliday(s);
					if(valid)
					{
						HolidayDate hD=new HolidayDate();
						hD.setHolidayDate(nHD);
						hD.setHolidayId(id);
						TicketPriceAndHolidayDataControl.updateHoliday(id,hD);
					}
					else
					{
						System.out.println("Duplicated date found, please try again.");
					}
				}while(valid==false);
			}
			if(selected==2){
				int confirm=0;
				do{
					System.out.println("Confirm deletion?");
					
					System.out.println("1.Yes");
					System.out.println("2.No");
					confirm=ValidationControl.validateYesNoAndReturnIntegerValue(sc.nextLine());
					if(confirm==1){
						TicketPriceAndHolidayDataControl.removeHolidayDate(id);
					}
					else if(confirm==2)
					{
						displayUpdatePage(oList,choice);
					}
				}while(confirm<=0)	;
			}
		}while(selected<=0);
		displayHolidayMain();
		
		
		
	}

}
