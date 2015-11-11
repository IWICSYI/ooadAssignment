package data;

import java.util.Date;

public class HolidayDate {

	public HolidayDate(int holidayId, Date holidayDate) {
		super();
		this.holidayId = holidayId;
		this.holidayDate = holidayDate;
	}

	public HolidayDate() {
		// TODO Auto-generated constructor stub
	}

	private int holidayId;
	private Date holidayDate;

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
}
