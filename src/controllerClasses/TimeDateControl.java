package controllerClasses;

public class TimeDateControl {

	public String minutesPlusTime(int minutes,int time)
	{
		int hr=minutes/60;
		int min=minutes-(hr*60);
		int endTime=time+hr*100+min;
		String finalTime=time+"-"+endTime;
		System.out.println(finalTime);
		return finalTime;
		
	}
}
