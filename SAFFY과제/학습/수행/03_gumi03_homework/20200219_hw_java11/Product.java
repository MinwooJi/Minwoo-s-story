
public class Product {
	int productNum;
	String productName;
	int price;
	int stock;
	public Product(int productNum, String productName, int price, int stock) {
		super();
		this.productNum = productNum;
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "[productNum=" + productNum + ", productName=" + productName + ", price=" + price + ", stock="
				+ stock + "]";
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
