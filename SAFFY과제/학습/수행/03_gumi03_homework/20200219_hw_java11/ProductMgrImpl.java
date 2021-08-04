import java.util.ArrayList;
import java.util.List;

public class ProductMgrImpl implements IProductMgr{
	List<Product> list = new ArrayList<>();
	public void insert(Product pro) {
		list.add(pro);
	}
	public List<Product> searchAll() {
		return list;
	}
	public Product searchProductNum(int productNum) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getProductNum() == productNum) {
				return list.get(i);
			}
		}
		return null;
	}
	public Product searchProductName(String name) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getProductName().equals(name)) {
				return list.get(i);
			}
		}
		return null;
	}
	public List<Product> searchTV() {
		List<Product> temp = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof TV) {
				temp.add(list.get(i));
			}
		}
		return temp;
	}
	public List<Product> searchRef() {
		List<Product> temp = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof Refrigerator) {
				temp.add(list.get(i));
			}
		}
		return temp;
	}
	public List<Product> findRef() {
		List<Product> temp = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof Refrigerator && ((Refrigerator)list.get(i)).getL()>=400) {
				temp.add(list.get(i));
			}
		}
		return temp;
	}
	public List<Product> findTV() {
		List<Product> temp = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			if(list.get(i) instanceof TV && ((TV)list.get(i)).getInch()>=50) {
				temp.add(list.get(i));
			}
		}
		return temp;
	}
	public boolean setPrice(int productNum, int price) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getProductNum() == productNum) {
				list.get(i).setPrice(price);
				return true;
			}
		}
		return false;
	}
	public boolean removeProduct(int productNum) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getProductNum() == productNum) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}
	public int calcStockPrice() {
		int sum = 0;
		for(int i=0;i<list.size();i++) {
			sum += list.get(i).getPrice();
		}
		return sum;
	}

}
