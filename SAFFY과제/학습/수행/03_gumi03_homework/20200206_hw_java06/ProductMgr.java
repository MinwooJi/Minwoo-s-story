
public class ProductMgr {
	private Product[] p;
	public ProductMgr(Product[] p) {
		this.p = p;
	}

	public void searchAll() {
		for(int i=0;i<p.length;i++) {
			System.out.println(p[i].toString());
		}
	}
	public void searchTV() {
		for(int i=0;i<p.length;i++) {
			if(p[i] instanceof TV)
				System.out.println(p[i].toString());
		}
	}
	public void searchRefrige() {
		for(int i=0;i<p.length;i++) {
			if(p[i] instanceof Refrigerator)
				System.out.println(p[i].toString());
		}
	}
	public void searchProductNum(int productNum) {
		for(int i=0;i<p.length;i++) {
			if(p[i].getProductNum() == productNum) {
				System.out.println("( "+productNum+" )찾았습니다.");
				System.out.println(p[i].toString());
			}
		}
	}
	public void searchProductName(String productName) {
		for(int i=0;i<p.length;i++) {
			if(p[i].getProductName().equals(productName)) {
				System.out.println("( "+productName+" )찾았습니다.");
				System.out.println(p[i].toString());
			}
		}
	}
	public void deleteNum(int productNum) {
		System.out.println("( "+productNum+" )삭제.");
		Product[] temp = new Product[p.length-1];
		int cnt = 0;
		for(int i=0;i<p.length;i++) {
			if(p[i].getProductNum() != productNum) {
				temp[cnt++] = p[i];
			}
		}
		p = temp;
	}
	public int calStockPrice() {
		int rst = 0;
		for(int i=0;i<p.length;i++) {
			int temp = p[i].getStock();
			while(temp!=0) {
				temp--;
				rst += p[i].getPrice();
			}
		}
		return rst;
	}
}
