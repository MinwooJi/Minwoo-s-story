import java.util.Arrays;
import java.util.Scanner;

public class Conference {
	static class Data implements Comparable<Data>{
		int start;
		int end;
		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public Data() {
			super();
		}
		@Override
		public int compareTo(Data o) {
			// TODO Auto-generated method stub
			return this.end == o.end ? this.start - o.start : this.end - o.end;
		}
		@Override
		public String toString() {
			return "Data [start=" + start + ", end=" + end + "]";
		}
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Data[] conferences = new Data[N];
		for(int i=0;i<N;i++) {
			conferences[i] = new Data(sc.nextInt(),sc.nextInt());
		}
		Arrays.sort(conferences);
//		for(int i=0;i<conferences.length;i++) {
//			System.out.println(conferences[i]);
//		}
		Data[] rst = new Data[N];
		for(int i=0;i<rst.length;i++) {
			rst[i] = new Data();
		}
		rst[0] = conferences[0];
		int cnt = 0;
		for(int i=1;i<N;i++) { // 끝나는 시간이 같을떄 시작시간이 짧은거
			if(rst[cnt].end <= conferences[i].start) {
				rst[++cnt] = conferences[i];
			}
		}
		System.out.println(cnt+1);
	}
}
