package misc;


import java.io.IOException;
import java.text.ParseException;

import boundaryClasses.ModuleSelectionUi;
import controllerClasses.ValidationControl;
import dataController.MovieScheduleDataControl;

public class Main extends ValidationControl{

	public static void main(String[] args) throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		ModuleSelectionUi.display();
	}
}