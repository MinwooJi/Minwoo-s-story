import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
public class swe1251_kruskal { // MST 크루스칼 // 하나로
	static int N;
	static int[] set;
	static int[][] pos;
	static ArrayList<Node>list;
	static class Node{
		int start;
		int end;
		double length;
		public Node(int start, int end, double length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			N = sc.nextInt();
			set = new int[N+1]; // 연결관계. 1버림
			pos = new int[N][2];
			list  = new ArrayList<>();
			for(int i=0;i<N;i++) {
				pos[i][0] = sc.nextInt();
			}
			for(int i=0;i<N;i++) {
				pos[i][1] = sc.nextInt();
			}
			double e = sc.nextDouble(); // 입력끝
			makeSet();
			for(int i=0;i<pos.length-1;i++) {
				for(int j=i+1;j<pos.length;j++) {
					list.add(new Node(i,j,calc(pos[i][0],pos[i][1],pos[j][0],pos[j][1],e)));
				}
			}
			Collections.sort(list, new Comparator<Node>() {
				@Override
				public int compare(Node arg0, Node arg1) {
					// TODO Auto-generated method stub
					if(arg0.length < arg1.length)
						return -1;
					else if(arg0.length > arg1.length)
						return 1;
					else return 0;
				}
			});
			System.out.printf("#%d ",t);
			System.out.println(Math.round(sol()));
		}
	}
	static double sol() {
		double rst = 0;
		int start,end;
		Node temp;
		for(int i=0;i<list.size();i++) {
			temp = list.get(i);
			start = temp.start;
			end = temp.end;
			if(findSet(start)!=findSet(end)) {
				union(start,end);
				rst += temp.length;
			}
		}
		return rst;
	}
	static double calc(int aI,int aJ,int bI,int bJ,double e) {
		return e*(Math.pow((aI-bI), 2) + Math.pow((aJ-bJ), 2)); 
	}
	static void union(int a,int b) {
		a = findSet(a);
		b = findSet(b);
		if(a==b) return;
		set[b] = a;
	}
	static int findSet(int idx) {
		if(set[idx]==idx) {
			return idx;
		}
		return set[idx] = findSet(set[idx]);
	}
	static void makeSet() {
		for(int i=1;i<N;i++) {
			set[i] = i;
		}
	}
}
