
public class TV extends Product{
	int inch;
	String type;
	public TV(int productNum, String productName, int price, int stock, int inch, String type) {
		super(productNum, productName, price, stock);
		this.inch = inch;
		this.type = type;
	}
	public TV() {
		super();
	}
	@Override
	public String toString() {
		return super.toString() +"[inch=" + inch + ", type=" + type + "]";
	}
	public int getInch() {
		return inch;
	}
	public void setInch(int inch) {
		this.inch = inch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
