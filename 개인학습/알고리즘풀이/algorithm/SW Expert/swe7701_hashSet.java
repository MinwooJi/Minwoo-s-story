import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

public class swe7701_hashSet {//염라대왕의 이름 정렬
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			int n = sc.nextInt();
			HashSet<String> sList = new HashSet<>();
			for(int i=0;i<n;i++) {
				sList.add(sc.next());
			}// 입력끝
			ArrayList<String> sArr = new ArrayList<>();
			sArr.addAll(sList);
			Collections.sort(sArr, new Comparator<String>() {
				@Override
				public int compare(String arg0, String arg1) {
					if(arg0.length()-arg1.length() <0) {
						return -1;
					}else if(arg0.length()-arg1.length() > 0) {
						return 1;
					}
					else {
						 for(int i=0;i<arg0.length();i++) {
							 if(arg0.charAt(i)-arg1.charAt(i)<0) {
								 return -1;
							 }else if(arg0.charAt(i)-arg1.charAt(i)>0)
								 return 1;
						 }
						 return 0;
					}
				}
			});
			StringBuilder sb = new StringBuilder(); // sb안쓰면 sysout 넘많음
			sb.append(String.format("#%d \n",t));
			for(int i=0;i<sArr.size();i++) {
				sb.append(sArr.get(i));
				sb.append("\n");
			}
			System.out.print(sb); 
		}
	}
}
