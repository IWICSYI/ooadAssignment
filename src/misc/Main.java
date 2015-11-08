package misc;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import controllerClasses.MiscControl;
import controllerClasses.AdminShowTimeController;
import controllerClasses.ValidationControl;
import data.Movie;
import data.MovieSchedule;
import data.Seats;
import data.ShowTime;
import dataController.DataControl;
import dataController.MovieScheduleDataControl;
import boundaryClasses.*;

public class Main extends ValidationControl{

	public static void main(String[] args) throws IOException, ParseException {
		MovieScheduleDataControl.updateScheduleStatus();
		ModuleSelectionUi.display();
	}
}