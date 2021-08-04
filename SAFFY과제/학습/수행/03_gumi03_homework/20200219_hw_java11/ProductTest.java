import java.util.ArrayList;
import java.util.List;

public class ProductTest {
	public static void main(String[] args) {
		ProductMgrImpl mgr = new ProductMgrImpl();
		TV[] tvArr = new TV[5];
		Refrigerator[] rfArr = new Refrigerator[5];
		tvArr[0] =new TV(12345, "Samsung G500", 1050000, 2, 55, "OLED");
		tvArr[1] =new TV(325, "LG 007", 950000, 1, 48, "LED");
		tvArr[2] =new TV(772, "Dongsun1", 800000, 2, 45, "LED");
		tvArr[3] =new TV(12345, "SONY_NZ", 1150000, 5, 60, "OLED");
		tvArr[4] =new TV(1183, "MyengGyeng", 750000, 2, 50, "LED");
		rfArr[0] = new Refrigerator(758, "KYAPRO", 1350000, 2, 450);
		rfArr[1] = new Refrigerator(8462, "Samsung CC1", 1500000, 4, 550);
		rfArr[2] = new Refrigerator(0051, "MINO1", 990000, 3, 350);
		rfArr[3] = new Refrigerator(789, "ELSA2", 1550000, 2, 550);
		rfArr[4] = new Refrigerator(654, "FROZENING1", 1150000, 1, 380);
		for(int i=0;i<tvArr.length;i++) {
			mgr.insert(tvArr[i]);
		}
		for(int i=0;i<rfArr.length;i++) {
			mgr.insert(rfArr[i]);
		}
		List<Product> temp = new ArrayList<>();
		temp = mgr.searchAll();
		System.out.println("-현재 재고 출력 : ");
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println("-제품번호로 검색 : " +  1183);
		System.out.println(mgr.searchProductNum(1183));
		System.out.println("-제품이름으로 검색 : " + "Dongsun1");
		System.out.println(mgr.searchProductName("Dongsun1"));
		System.out.println("-TV정보 출력");
		temp = mgr.searchTV();
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println("-냉장고 정보 출력");
		temp = mgr.searchRef();
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println("-용량 400L 이상 냉장고만 출력");
		temp = mgr.findRef();
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println("-50inch 이상 TV만 출력");
		temp = mgr.findTV();
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println(12345 +" 상품가격 " + 1600000 +" 으로 변경");
		if(mgr.setPrice(12345,1600000)){
			System.out.println("변경되었습니다.");
		}else {
			System.out.println("상품번호를 확인해 주세요");
		}
		System.out.println(325 +" 상품 삭제" );
		if(mgr.removeProduct(325)){
			System.out.println("삭제되었습니다.");
		}else {
			System.out.println("상품번호를 확인해 주세요");
		}
		temp = mgr.searchAll();
		System.out.println("-현재 재고 출력 : ");
		for(int i=0;i<temp.size();i++) {
			System.out.println(temp.get(i));
		}
		System.out.println("현재 재고량의 총 가격");
		System.out.println(mgr.calcStockPrice());
	}
}
