

import java.io.IOException;
import java.text.ParseException;

import boundaryClasses.ModuleSelectionUi;
import controllerClasses.ValidationControl;
import dataController.MovieScheduleDataControl;
/**
 * Main class to run this program
 * @author Chang En Kai
 *
 */
public class RunThis{

	public static void main(String[] args) throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		ModuleSelectionUi.display();
	}
}