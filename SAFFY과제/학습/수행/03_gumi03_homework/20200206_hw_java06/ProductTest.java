
public class ProductTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product[] p = new Product[5];
		p[0] = new TV(211488, "LGTW", 1010000, 3,50,"OLED"); 
		p[1] = new TV(158642, "SAMSUNGG1", 1190000, 4,56,"LED"); 
		p[2] = new Refrigerator(71877, "YASONG3", 1000000, 2,40); 
		p[3] = new Refrigerator(89451, "DOSUNG1", 890000, 1,35); 
		p[4] = new TV(416844, "SUBIN1", 1490000, 1,58,"OLED");
		ProductMgr pm = new ProductMgr(p);
		System.out.println("<전체 검색>");
		pm.searchAll();
		System.out.println("<TV만 검색>");
		pm.searchTV();
		System.out.println("<냉장고만 검색>");
		pm.searchRefrige();
		System.out.println("<제품 번호로 검색>");
		pm.searchProductNum(211488);
		System.out.println("<제품 이름으로 검색>");
		pm.searchProductName("SUBIN1");
		System.out.println("<제품번호로 삭제>");
		pm.deleteNum(158642);
		pm.searchAll();
		System.out.println("<현재 재고 가격 확인>");
		int rst = pm.calStockPrice();
		System.out.println(rst+"원");
		
	}

}
