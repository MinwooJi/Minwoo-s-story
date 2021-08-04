import java.util.ArrayList;
import java.util.Scanner;

public class swe1808 { // 지희의 고장난 계산기
	static ArrayList<Integer> list;
	static ArrayList<Pair> listC;
	static ArrayList<dPair> listD;
	static ArrayList<Integer> divisors;
	static int minRst;
	static int target;
	static int[] pair;
	static int v[];
	static boolean dV[];
	static boolean dV2[];
	static class dPair{
		int i;
		int j;
		public dPair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static class Pair{
		int i;
		int num;
		public Pair(int i, int num) {
			this.i = i;
			this.num = num;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			list = new ArrayList<>();
			listC = new ArrayList<>();
			listD = new ArrayList<>();
			divisors = new ArrayList<>();
			minRst = Integer.MAX_VALUE;
			v = new int[1000001];
			dV2 = new boolean[1000001]; // 있어야하나?
			for(int i=0;i<10;i++) {
				if(sc.nextInt() == 1) {
					list.add(i);
				}
			}
			target = sc.nextInt();
			//약수 구하기
			int temp = target;
			int d = 2;
			while(d<=temp) {
				if(temp%d==0) {
					temp /= d;
					divisors.add(d);
					d=2;
					continue;
				}
				d++;
				if(d>temp/2)
					break;
			}
			pair = new int[divisors.size()];
			dV = new boolean[divisors.size()];
			int num = 1;
			temp = target;
			while((temp/=10) > 0) { //자릿수 확인
				num++;
			}
			num++;
			perm(0,num,0,0); // 숫자 여러번
			comb(0,0); // target 약수로 만들수있는 수
			for(int i=0;i<listD.size();i++) {
				if(v[target] != 0) { // 시간 줄이기
					minRst = v[target]+1;
					break;
				}
				comb2(listD.get(i).i,0,listD.get(i).i);
				if(v[listD.get(i).i]==0)
					continue;
				comb2(listD.get(i).j,0,listD.get(i).j);
				if(v[listD.get(i).i]!=0 && v[listD.get(i).j]!=0) {
					minRst = Math.min(minRst, v[listD.get(i).i] + v[listD.get(i).j]+2);
				}
			}
			System.out.printf("#%d ",t+1);
			if(minRst == Integer.MAX_VALUE) minRst = -1;
			System.out.println(minRst);
		}
	}
	static void comb2(int curTar,int curNum,int tar) { // 약수 분해하면서 순열로 곱해서 만들수있는지?
		if(v[target]!=0 &&curNum > v[target]) return;
		for(int i=0;i<listC.size();i++) {
			if(listC.get(i).i ==0 || listC.get(i).i ==1 ) continue;
			if(curTar < listC.get(i).i) continue;
			if(curTar % listC.get(i).i == 0) {
				if(curTar == listC.get(i).i) {
					if(v[tar] != 0) {
						v[tar] = Math.min(v[tar], curNum + listC.get(i).num);
					}else {
						v[tar] = curNum + listC.get(i).num;
					}
					continue;
				}
				comb2(curTar/listC.get(i).i, curNum + listC.get(i).num+1,tar);
			}
		}
	}
	static void comb(int cnt,int idx) { // 약수로 만들수있는 집합
		if(cnt>divisors.size()) return;
		if(cnt!=0) {
			int rst = 1;
			for(int i=0;i<cnt;i++) {
				rst *= pair[i];
			}
			if(!dV2[rst] && !dV2[target/rst]) {
				dV2[rst] = true;
				dV2[target/rst] = true;
				listD.add(new dPair(rst,target/rst));
			}
		}
		for(int i = idx;i<divisors.size();i++) {
			if(dV[i]) continue;
			dV[i] = true;
			pair[cnt] = divisors.get(i); 
			comb(cnt+1,i);
			dV[i] = false;
		}
	}
	static void perm(int cnt,int num,int now,int nowRst) { 
		if(cnt > num) return;
		if(now > target) return;
		if(now == target) { 
			minRst = Math.min(minRst, nowRst+1); // =누르기
		}
		if(cnt!=0)
			listC.add(new Pair(now,nowRst));
		for(int i=0;i<list.size();i++) {
			if(now*10 + list.get(i) > 1000000) break;
			if(v[now*10 + list.get(i)]!=0) {
				if(v[now*10 + list.get(i)] <= nowRst+1) {
					continue;
				}
			}
			v[now*10 + list.get(i)] = nowRst+1;
			perm(cnt+1,num,now*10 + list.get(i),nowRst+1);
		}
	}
}
