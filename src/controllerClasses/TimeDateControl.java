package controllerClasses;

public class TimeDateControl {

	public String minutesPlusTime(int minutes,int time)
	{
		int hr=minutes/60;
		int min=minutes-(hr*60);
		int finalTime=time+hr*100+min;
		System.out.println(hr+"  "+min);
		return Integer.toString(finalTime);
		
	}
}
