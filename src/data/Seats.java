package data;
//showTimeId|TrueSeatStatus|A1seatName|seatId|transectionId
public class Seats {
	private int showTimeId;
	private boolean isOccupied;
	private String seatName;
	private int seatId;
	private int transcationId;
	public int getShowTimeId() {
		return showTimeId;
	}
	public void setShowTimeId(int showTimeId) {
		this.showTimeId = showTimeId;
	}
	public boolean isOccupied() {
		return isOccupied;
	}
	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public int getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(int transcationId) {
		this.transcationId = transcationId;
	}

}
