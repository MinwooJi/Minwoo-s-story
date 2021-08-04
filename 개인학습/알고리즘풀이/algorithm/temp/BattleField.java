import java.util.Arrays;
import java.util.Scanner;

public class BattleField {
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=0;t<tc;t++) {
			int H = sc.nextInt();
			int W = sc.nextInt();
			StringBuffer[] map = new StringBuffer[H];
			for(int i=0;i<H;i++) {
				map[i] = new StringBuffer(sc.next());
			}
			//전차 찾기
			int tankI=0;
			int tankJ=0;
			boolean breakfor = false;
			for(int i=0;i<H;i++) {
				if(breakfor)
					break;
				for(int j=0;j<W;j++) {
					if(map[i].charAt(j)=='^' || map[i].charAt(j)=='v' || map[i].charAt(j)=='<' || map[i].charAt(j)=='>') {
						tankI = i;
						tankJ = j;
						breakfor =true;
						break;
					}
				}
			}
			int orderNum = sc.nextInt();
			String order = sc.next();
			for(int i=0;i<order.length();i++) {
				if(order.charAt(i)=='U') {
					if(tankI-1<0) {
						map[tankI].setCharAt(tankJ, '^'); //못움직여서 방향만 바꿀떄
						continue;
					}
					if(map[tankI-1].charAt(tankJ) == '.') { // 움직일수있을때
						map[tankI].setCharAt(tankJ, '.'); 
						map[tankI-1].setCharAt(tankJ, '^'); 
						tankI --;
					}
					else {
						map[tankI].setCharAt(tankJ, '^'); //못움직여서 방향만 바꿀떄
					}
				}else if(order.charAt(i)=='D') {
					if(tankI+1>=H) {
						map[tankI].setCharAt(tankJ, 'v'); //못움직여서 방향만 바꿀떄
						continue;
					}
					if(map[tankI+1].charAt(tankJ) == '.') {
						map[tankI].setCharAt(tankJ, '.'); 
						map[tankI+1].setCharAt(tankJ, 'v'); 
						tankI++;
					}
					else {
						map[tankI].setCharAt(tankJ, 'v'); 
					}
					
				}else if(order.charAt(i)=='L') {
					if(tankJ-1<0) {
						map[tankI].setCharAt(tankJ, '<'); //못움직여서 방향만 바꿀떄
						continue;
					}
					if(map[tankI].charAt(tankJ-1) == '.') {
						map[tankI].setCharAt(tankJ, '.'); 
						map[tankI].setCharAt(tankJ-1, '<'); 
						tankJ --;
					}
					else {
						map[tankI].setCharAt(tankJ, '<'); 
					}
					
				}else if(order.charAt(i)=='R') {
					if(tankJ+1>=W ) {
						map[tankI].setCharAt(tankJ, '>'); //못움직여서 방향만 바꿀떄
						continue;
					}
					if(map[tankI].charAt(tankJ+1) == '.') {
						map[tankI].setCharAt(tankJ, '.'); 
						map[tankI].setCharAt(tankJ+1, '>'); 
						tankJ ++;
					}
					else {
						map[tankI].setCharAt(tankJ, '>'); 
					}
					
				}else if(order.charAt(i)=='S') {
					int tempI = tankI;
					int tempJ = tankJ;
					if(map[tankI].charAt(tankJ)=='^') {
						while(tempI>=0) {
							if(map[tempI].charAt(tankJ)=='*') {//벽돌
								map[tempI].setCharAt(tankJ, '.');
								break;
							}else if(map[tempI].charAt(tankJ)=='#') {//강철
								break;
							}
							
							tempI--;
						}
					}else if(map[tankI].charAt(tankJ)=='v') {
						while(tempI<H) {
							if(map[tempI].charAt(tankJ)=='*') {//벽돌
								map[tempI].setCharAt(tankJ, '.');
								break;
							}else if(map[tempI].charAt(tankJ)=='#') {//강철
								break;
							}
							
							tempI++;
						}
						
					}else if(map[tankI].charAt(tankJ)=='<') {
						while(tempJ>=0) {
							if(map[tankI].charAt(tempJ)=='*') {//벽돌
								map[tankI].setCharAt(tempJ, '.');
								break;
							}else if(map[tankI].charAt(tempJ)=='#') {//강철
								
								break;
							}
							
							tempJ--;
						}
					}else if(map[tankI].charAt(tankJ)=='>') {
						while(tempJ<W) {
							if(map[tankI].charAt(tempJ)=='*') {//벽돌
								map[tankI].setCharAt(tempJ, '.');
								break;
							}else if(map[tankI].charAt(tempJ)=='#') {//강철
								break;
							}
							
							tempJ++;
						}
					}
				}
			}
			System.out.printf("#%d ",t+1);
			for(int i=0;i<map.length;i++) {
				System.out.println(map[i]);
			}
		}
	}
}
