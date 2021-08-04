
public class Refrigerator extends Product {
	int L;
	public Refrigerator(int productNum, String productName, int price, int stock, int l) {
		super(productNum, productName, price, stock);
		L = l;
	}
	public Refrigerator() {
		super();
	}
	@Override
	public String toString() {
		return super.toString() + "[L=" + L + ", productNum=" + productNum + ", productName=" + productName + ", price="
				+ price + ", stock=" + stock + "]";
	}
	public int getL() {
		return L;
	}
	public void setL(int l) {
		L = l;
	}
	

	
}
