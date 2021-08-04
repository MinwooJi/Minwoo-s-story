import java.util.Scanner;
public class swe7699 { // 수지의 수지 맞는 여행
    static int bitMask;
    static int N,M;
    static int rst;
    static StringBuffer[] sb;
    static boolean v[][];
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        for(int t=0;t<tc;t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            v = new boolean[N][M];
            bitMask = 0;
            rst = 0;
            sb = new StringBuffer[N];
            for(int i=0;i<N;i++) {
                sb[i] = new StringBuffer(sc.next());
            }// 입력끝
            v[0][0] = true;
            bitMask = bitMask | (1<<(sb[0].charAt(0)-'A'));
            dfs(0,0,1);
            System.out.printf("#%d ", t+1);
            System.out.println(rst);
        }
    }
    static void dfs(int i,int j,int curVal) {
        rst = Math.max(rst, curVal);
        if(rst == 26) return;
        int ny,nx;
        for(int l=0;l<4;l++) {
            ny = i + dy[l];
            nx = j + dx[l];
            if(!isBound(ny,nx)) continue;
            if(v[ny][nx]) continue;
            if( (bitMask & (1<<(sb[ny].charAt(nx)-'A'))) != 0 ) continue;
            bitMask = bitMask | (1<<(sb[ny].charAt(nx)-'A'));
            v[ny][nx] = true;
            dfs(ny,nx,curVal+1);
            bitMask = bitMask &~ (1<<(sb[ny].charAt(nx)-'A'));
            v[ny][nx] = false;
        }
    }
    static boolean isBound(int ny,int nx) {
        return nx >=0 && ny >= 0 && ny <N && nx <M;
    }
}