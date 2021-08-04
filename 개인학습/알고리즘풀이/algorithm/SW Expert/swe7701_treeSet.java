import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
public class swe7701_treeSet {//염라대왕의 이름 정렬 treeset버전
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			int n = sc.nextInt();
			TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
				@Override
				public int compare(String arg0, String arg1) {
					int l = arg0.length()-arg1.length();
					if(l <0) {
						return -1;
					}else if(l > 0) {
						return 1;
					}else
						return arg0.compareTo(arg1);
				}
			});
			for(int i=0;i<n;i++)// 입력끝
				set.add(sc.next());
			StringBuilder sb = new StringBuilder(); // sb안쓰면 sysout 넘많음
			Iterator<String>it = set.iterator();
			sb.append(String.format("#%d \n",t));
			while(it.hasNext()) {
				sb.append(it.next());
				sb.append("\n");
			}
			System.out.print(sb); 
		}
	}
}
