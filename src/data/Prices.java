package data;

public class Prices {
	
	/**
	 * Constructor use to create/update price
	 * @param normal Base price
	 * @param plat  Platinum price
	 * @param tD 3D price
	 * @param blockbuster Blockbuster price
	 * @param oldPrice senior citizen price
	 * @param childPrice Children price
	 * @param holi Holiday price
	 * @param weekend Weekend price
	 */
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
	
	/**
	 * Get base price
	 * @return
	 */
	public double getNormal() {
		return normal;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}
	/**
	 * Get platinum price
	 * @return
	 */
	public double getPlat() {
		return plat;
	}
	public void setPlat(double plat) {
		this.plat = plat;
	}
	/**
	 * Get 3D price
	 * @return
	 */
	public double gettD() {
		return tD;
	}
	public void settD(double tD) {
		this.tD = tD;
	}
	
	/**
	 * Get blockbuster price
	 * @return
	 */
	public double getBlockbuster() {
		return blockbuster;
	}
	public void setBlockbuster(double blockbuster) {
		this.blockbuster = blockbuster;
	}
	/**
	 * Get elderly price
	 * @return
	 */
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(double oldPrice) {
		this.oldPrice = oldPrice;
	}
	/**
	 * Get children price
	 * @return
	 */
	public double getChildPrice() {
		return childPrice;
	}
	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}
	/**
	 * Get holiday price
	 * @return
	 */
	public double getHoli() {
		return holi;
	}
	public void setHoli(double holi) {
		this.holi = holi;
	}
	/**
	 * Get weekend price
	 * @return
	 */
	public double getWeekend() {
		return weekend;
	}
	public void setWeekend(double weekend) {
		this.weekend = weekend;
	}


}
