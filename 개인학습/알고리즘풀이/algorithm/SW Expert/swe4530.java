import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swe4530 { // 극한의 청소작업
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String start,end;
		long cStart,cEnd;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			start = st.nextToken();			
			end = st.nextToken();
			cStart = toDec(toNine(start));
			cEnd = toDec(toNine(end));
			long rst = cEnd-cStart-1;//0
			if((cStart>0 && cEnd>0) || (cStart<0 && cEnd<0)) {
				rst ++;
			}
			sb.append(String.format("#%d ", t)).append(rst).append("\n");
		}
		System.out.println(sb);
	}
	static long toDec(String num) {
		long rst = 0;
		int pos = 0;
		boolean minus = false;
		for (int i = num.length()-1; i >= 0; i--) {
			if(num.charAt(i) == '-') {
				minus = true;
				break;
			}
			rst += (num.charAt(i)-'0') * Math.pow(9, pos++);
		}
		if(minus) return -rst;
		return rst;
	}
	static String toNine(String num) {
		char[] cArr = num.toCharArray();
		for (int i = 0; i < cArr.length; i++) {
			if(cArr[i] == '-') continue;
			if(cArr[i] > '4') {
				cArr[i] = (char)(cArr[i]-1);
			}
		}
		return new String(cArr);
	}
}
