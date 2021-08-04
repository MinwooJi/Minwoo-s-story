
public class Refrigerator extends Product{
	private int size;

	public Refrigerator(int productNum, String productName, int price, int stock, int size) {
		super(productNum, productName, price, stock);
		this.size = size;
	}
	public Refrigerator() {
		super();
		this.size = -1;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Refrigerator");
		sb.append(super.toString());
		sb.append(", Size = " + size + "]");
		return sb.toString();
	}

	
}
