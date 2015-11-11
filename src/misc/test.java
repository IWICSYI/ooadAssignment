package misc;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;

import data.MovieSchedule;
import data.ShowTime;
import dataController.MovieScheduleDataControl;
import dataController.ShowTimeDataControl;

public class test {

	public static void main(String[] args) throws IOException, ParseException {
		MovieSchedule newsch = MovieScheduleDataControl
				.readScheduleListingBasedOnListingId(12);
		// must create movie listing in amk cineplex to work.

		TimeSlotCopier(newsch, 1); // uncomment this to copy time slot data to
									// all cineplex, the 1 is listingId, change
		System.out.println("copy success");
		// it if you want to copy every show time in that listingid.
	}

	public static void TimeSlotCopier(MovieSchedule sch, int cineplexId)
			throws IOException, ParseException {
		ArrayList<ShowTime> stList = ShowTimeDataControl
				.readShowTimesBasedOnListingId(sch.getListingId());

		for (int i = 0; i < stList.size(); i++) {
			ShowTime copier = stList.get(i);
			ShowTime copier2 = stList.get(i);
			if (cineplexId == 1) {
				copier.setCinemaId(stList.get(i).getCinemaId() + 3);
				copier.setCineplexId(stList.get(i).getCineplexId() + 1);
				ShowTimeDataControl.createTimeSlot(copier, sch);
				copier2.setCinemaId(stList.get(i).getCinemaId() + 3);
				copier2.setCineplexId(stList.get(i).getCineplexId() + 1);
				ShowTimeDataControl.createTimeSlot(copier2, sch);
			} else if (cineplexId == 2) {
				copier.setCinemaId(stList.get(i).getCinemaId() + 3);
				copier.setCineplexId(stList.get(i).getCineplexId() + 1);
				ShowTimeDataControl.createTimeSlot(copier, sch);
				copier.setCinemaId(stList.get(i).getCinemaId() - 6);
				copier.setCineplexId(stList.get(i).getCineplexId() - 2);
				ShowTimeDataControl.createTimeSlot(copier, sch);
			} else if (cineplexId == 3) {
				copier.setCinemaId(stList.get(i).getCinemaId() - 3);
				copier.setCineplexId(stList.get(i).getCineplexId() - 1);
				ShowTimeDataControl.createTimeSlot(copier, sch);
				copier.setCinemaId(stList.get(i).getCinemaId() - 3);
				copier.setCineplexId(stList.get(i).getCineplexId() - 1);
				ShowTimeDataControl.createTimeSlot(copier, sch);
			}
		}

	}

}
