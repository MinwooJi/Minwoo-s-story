import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class b6987 { // 월드컵 조합+dfs+백트레킹
    static int[] home;
    static int[] away;
    static Result[] rstArr;
    static boolean rst;
    static int matchNum = 5+4+3+2+1;
	static class Result{
		int win;
		int draw;
		int lose;
		public Result(int win, int draw, int lose) {
			this.win = win;
			this.draw = draw;
			this.lose = lose;
		}
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = 4;
		int num = 6;
		home = new int[matchNum];
		away = new int[matchNum];
		int idx = 0;
		for (int i = 0; i < num; i++) {
			for (int j = i+1; j < num; j++, idx++) {
				home[idx] = i;
				away[idx] = j;
			}
		}
		rstArr = new Result[num];
		Outer:
		for (int t = 1; t <= tc; t++) {
			rst = false;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < rstArr.length; i++) {
				rstArr[i] = new Result(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				if(rstArr[i].win + rstArr[i].lose + rstArr[i].draw != 5) {
					sb.append(0).append(" ");
					continue Outer;
				}
			}
			sol(0);
			sb.append(rst?1:0).append(" ");
		}
		System.out.println(sb);
	}
    static void sol(int match) {
    	if(rst) return;
    	if(match == matchNum) {
    		rst = true;
    		return; 
    	}
    	int hTeam,aTeam;
    	hTeam = home[match];
    	aTeam = away[match];
    	// 홈팀
    	// 이길경우
    	if(rstArr[hTeam].win>0 && rstArr[aTeam].lose>0) {
    		rstArr[hTeam].win--;
    		rstArr[aTeam].lose--;
    		sol(match+1);
    		rstArr[hTeam].win++;
    		rstArr[aTeam].lose++;
    	}
    	// 질경우
    	if(rstArr[hTeam].lose>0 && rstArr[aTeam].win>0) {
    		rstArr[hTeam].lose--;
    		rstArr[aTeam].win--;
    		sol(match+1);
    		rstArr[hTeam].lose++;
    		rstArr[aTeam].win++;
    	}
    	// 비길경우
    	if(rstArr[hTeam].draw>0 && rstArr[aTeam].draw>0) {
    		rstArr[hTeam].draw--;
    		rstArr[aTeam].draw--;
    		sol(match+1);
    		rstArr[hTeam].draw++;
    		rstArr[aTeam].draw++;
    	}

    }
    
}
