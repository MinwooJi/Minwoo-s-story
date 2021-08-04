import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class swea_5658 { // 보물상자 비밀번호 (시뮬레이션)
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		String temp;
		StringBuilder sb = new StringBuilder();
		int N,K;
		char[] arr;
		HashSet<Integer> set;
		List<Integer>list;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			set = new HashSet<>();
			list = new ArrayList<>();
			temp = br.readLine();
			arr = new char[N+(N/4)-1];
			for (int i = 0; i < temp.length(); i++) {
				arr[i] = temp.charAt(i);
			}
			int sideNum = N/4;
			char[] password = new char[sideNum];
			int idx = 0;
			for (int i = N; i < arr.length; i++) {
				arr[i] = arr[idx++];
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < password.length; j++) {
					password[j] = arr[i+j];
				}
				set.add(Integer.parseInt(new String(password), 16));
			}
			list.addAll(set);
			Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer arg0, Integer arg1) {
					return Integer.compare(arg0, arg1) * -1;
				}
			});
			sb.append("#").append(t).append(" ").append(list.get(K-1)).append("\n");
		}
		System.out.print(sb);
	}
}
