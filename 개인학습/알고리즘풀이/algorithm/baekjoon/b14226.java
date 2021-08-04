import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b14226 { // 이모티콘
	static int N;
	static int minRst=Integer.MAX_VALUE;
	static boolean[][][]v;
	static class Emoticon{
		int func;
		int cnt;
		int curNum;
		int clip;
		public Emoticon(int func, int cnt, int curNum, int clip) {
			this.func = func;
			this.cnt = cnt;
			this.curNum = curNum;
			this.clip = clip;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		v = new boolean[4][1000][1001*2];
		v[0][0][1] = true;
		bfs(new Emoticon(0,0,1,0));
		System.out.println(minRst);
	}
	static void bfs(Emoticon e) {
		Queue<Emoticon> q = new LinkedList<>();
		Emoticon temp;
		q.offer(e);
		int cnt,curNum,clip;
		while(!q.isEmpty()) {
			temp = q.poll();
			cnt = temp.cnt+1;
			curNum = temp.curNum;
			clip = temp.clip;
			if(temp.func == 0) { // 복사
				clip = curNum;
			}else if(temp.func == 1) { // 붙여넣기
				curNum += clip;
			}else if(temp.func == 2) {//삭제
				curNum --;
			}
			if(curNum == N) {
				minRst = cnt;
				return;
			}
			if(clip >= 1000)
				continue;
			if(curNum >= 2000)
				continue;
			for(int l=0;l<3;l++) {
				if(v[l][clip][curNum]) continue;
				if(l==1 && clip == 0) continue; // 없는데 붙여넣기 제외
				if(l==2 && curNum <=1) continue; // 작아지는것 가지치기 
				v[l][clip][curNum] = true;
				q.offer(new Emoticon(l,cnt,curNum,clip));
			}
		}
	}
}
