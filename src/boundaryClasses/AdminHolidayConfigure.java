package boundaryClasses;

import java.util.Scanner;

public class AdminHolidayConfigure {
	
	public void displayHolidayMain()
	{
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			HolidayDateController HDC = new HolidayDateController();
			System.out.println("Perform the following methods for Movie:");
			System.out.println("(1) Create Holiday");
			System.out.println("(2) Remove Holiday");
			System.out.println("(3) Update Holiday");
			System.out.println("(4) Show Holiday");
			System.out.println("(5) Exit");
			System.out.print("Enter the number of your choice: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1: HDC.createHoliday();
				break;
			case 2: HDC.removeHoliday();
				break;
			case 3: HDC.updateHoliday();
				break;
			case 4: HDC.showHoliday();
			break;
			case 5: System.out.println("Program terminating бн.");
			}
		}while (choice < 5);
	}

}
