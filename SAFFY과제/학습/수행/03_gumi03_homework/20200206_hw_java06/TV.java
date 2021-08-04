
public class TV extends Product{
	private int inch;
	private String type;
	public TV(int productNum, String productName, int price, int stock, int inch, String type) {
		super(productNum, productName, price, stock);
		this.inch = inch;
		this.type = type;
	}
	public TV() {
		super();
		this.inch = -1;
		this.type = "정보없음";
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
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TV");
		sb.append(super.toString());
		sb.append(", inch=" + inch + ", type=" + type + "]");
		return sb.toString();
	}
	
}
