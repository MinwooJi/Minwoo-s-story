import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_4366 { // 정식이의 은행업무 //  API/아이디어
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		String binary,thrdary;
		for (int t = 1; t <= tc; t++) {
			binary = br.readLine();
			thrdary = br.readLine();
			sb.append("#").append(t).append(" ").append(sol(binary, thrdary)).append("\n");
		}
		System.out.print(sb);
	}
	static long sol(String binary,String thrdary) {
		char[] bArr,tArr;
		long b,t;
		for (int i = 0; i < binary.length(); i++) {
			bArr = binary.toCharArray();
			bArr[i] = bArr[i] == '0' ? '1' : '0'; 
			for (int j = 0; j < thrdary.length(); j++) {
				for (int k = 0; k < 3; k++) {
					tArr = thrdary.toCharArray();
					if( (tArr[j]-'0') == k ) 
						continue;
					tArr[j] = (char)(k + '0');
					b = Long.parseLong(new String(bArr),2);
					t = Long.parseLong(new String(tArr),3);
					if(b==t) return b;
				}
			}
		}
		return -1;
	}
}
