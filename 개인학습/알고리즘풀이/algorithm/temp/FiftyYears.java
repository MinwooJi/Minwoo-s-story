import java.util.Arrays;
import java.util.Scanner;

public class FiftyYears {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] dx = {0,1,0,-1};
		int [] dy = {-1,0,1,0};
		int R = sc.nextInt(); // 세로
		int C = sc.nextInt(); // 가로
		String[] arr = new String[R];
		for(int i=0;i<arr.length;i++) {
			arr[i] = sc.next();
		}
		StringBuffer[] newArr = new StringBuffer[R];
		for(int i=0;i<arr.length;i++) {
			newArr[i] = new StringBuffer(arr[i]);
		}
		//		for(int i=0;i<3;i++) {
		//			System.out.println(arr[i]);
		//		}
		int cnt = 0;
		for(int i=0;i<arr.length;i++) {
			for(int j =0;j<arr[0].length();j++) {
				cnt = 0;
				if(arr[i].charAt(j) == 'X') {
					for(int l =0;l<4;l++) {
						int nx = j + dx[l];
						int ny = i + dy[l];
						if(nx < 0 || ny < 0 || ny>=R || nx>=C) {
							if(nx <0)
								cnt++;
							if(ny <0)
								cnt++;
							if(ny >=R)
								cnt++;
							if(nx >=C)
								cnt++;
							continue;
						}
						if(arr[ny].charAt(nx)!='X') {
							cnt++;
						}
					}
				}
				if(cnt >=3) {
					newArr[i].setCharAt(j, '.');
				}
			}
		} // 50년후
		// 지도 자르기
		int xCutS=0;
		int xCutE=0;
		int yCutS=0;
		int yCutE=0;
		int c = 0;
		for(int i=0;i<newArr.length;i++) {
			c = 0;
			for(int j=0;j<newArr[0].length();j++) {
				if(newArr[i].charAt(j) == '.') {
					c++;
				}
			}
			if(c==newArr[0].length()) {
				yCutS ++;
			}
			else {
				break;
			}
		}
		for(int i=newArr.length-1;i>=0;i--) {
			c = 0;
			for(int j=0;j<newArr[0].length();j++) {
				if(newArr[i].charAt(j) == '.') {
					c++;
				}
			}
			if(c==newArr[0].length()) {
				yCutE ++;
			}
			else {
				break;
			}
		}
		//세로줄
		for(int i=0;i<newArr[0].length();i++) {
			c = 0;
			for(int j=0;j<newArr.length;j++) {
				if(newArr[j].charAt(i) == '.') {
					c++;
				}
			}
			if(c==newArr.length) {
				xCutS ++;
			}
			else {
				break;
			}
		}
		for(int i=newArr[0].length()-1;i>=0;i--) {
			c = 0;
			for(int j=0;j<newArr.length;j++) {
				if(newArr[j].charAt(i) == '.') {
					c++;
				}
			}
			if(c==newArr.length) {
				xCutE++;
			}
			else {
				break;
			}
		}
		for(int i=yCutS;i<newArr.length-yCutE;i++) {
			for(int j=xCutS;j<newArr[0].length()-xCutE;j++) {
				System.out.print(newArr[i].charAt(j));
			}
			System.out.println();
		}
	
		
		
//		
//		for(int i=0;i<newArr.length;i++) {
//			System.out.println(newArr[i]);
//		}
	}

}
