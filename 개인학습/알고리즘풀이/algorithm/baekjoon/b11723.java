import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class b11723 { // 집합 : 버퍼드 리더/ 라이더 연습
					// 출력이 너무 많이 나오게 될경우 sysout 은 별로.. StringBulder에 저장했다가 출력하거나 버퍼드 라이더로 출력
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		String func;
		int num;
		LinkedList<Integer> s = new LinkedList<>();
		for(int t=0;t<n;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			func = st.nextToken();
			if("add".equals(func)) {
				num = Integer.parseInt(st.nextToken());
				if(!s.contains(num)) {
					s.offer(num);
				}
			}else if("remove".equals(func)) {
				num = Integer.parseInt(st.nextToken());
				if(s.contains(num)) {
					s.remove(s.indexOf(num));
				}
			}else if("check".equals(func)) {
				num = Integer.parseInt(st.nextToken());
				if(s.contains(num)) {
					bw.write(1+"\n");
				}else {
					bw.write(0+"\n");
				}
			}else if("toggle".equals(func)) {
				num = Integer.parseInt(st.nextToken());
				if(s.contains(num)) {
					s.remove(s.indexOf(num));
				}else {
					s.offer(num);
				}
			}else if("all".equals(func)) {
				s.clear();
				for(int i=1;i<=20;i++) {
					s.offer(i);
				}
			}else if("empty".equals(func)) {
				s.clear();
			}
		}
		bw.flush();
		bw.close();
		
	}
}
