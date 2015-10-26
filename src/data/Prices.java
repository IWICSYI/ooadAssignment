package data;

public class Prices {
	public Prices(double normal, double plat, double tD, double blockbuster,
			double oldPrice, double childPrice, double holi, double weekend) {
		super();
		this.normal = normal;
		this.plat = plat;
		this.tD = tD;
		this.blockbuster = blockbuster;
		this.oldPrice = oldPrice;
		this.childPrice = childPrice;
		this.holi = holi;
		this.weekend = weekend;
	}
	private double normal;
	private double  plat;
	private double tD;
	private double blockbuster;
	private double oldPrice;
	private double childPrice;
	private double holi;
	private double weekend;
	public double getNormal() {
		return normal;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}
	public double getPlat() {
		return plat;
	}
	public void setPlat(double plat) {
		this.plat = plat;
	}
	public double gettD() {
		return tD;
	}
	public void settD(double tD) {
		this.tD = tD;
	}
	public double getBlockbuster() {
		return blockbuster;
	}
	public void setBlockbuster(double blockbuster) {
		this.blockbuster = blockbuster;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public double getChildPrice() {
		return childPrice;
	}
	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}
	public double getHoli() {
		return holi;
	}
	public void setHoli(double holi) {
		this.holi = holi;
	}
	public double getWeekend() {
		return weekend;
	}
	public void setWeekend(double weekend) {
		this.weekend = weekend;
	}


}
