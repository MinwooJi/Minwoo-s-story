import java.util.List;

public interface IProductMgr {
	void insert(Product pro);
	List<Product> searchAll();
	Product searchProductNum(int productNum);
	Product searchProductName(String name);
	List<Product> searchTV();
	List<Product> searchRef();
	List<Product> findRef();
	List<Product> findTV();
	boolean setPrice(int productNum,int price);
	boolean removeProduct(int productNum);
	int calcStockPrice();
}
