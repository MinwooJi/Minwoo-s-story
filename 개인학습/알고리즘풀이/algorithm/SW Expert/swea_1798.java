import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
 
public class swea_1798 { // 범준이의 제주도 여행 계획
    static int[][] adj;
    static boolean[] v;
    static Node nodeArr[];
    static int N,M;
    static int maxRst;
    static int airport;
    static String finalPath;
    static class Node{
        char kinds;
        int time;
        int satisfaction;
        public Node(char kinds, int time, int satisfaction) {
            this.kinds = kinds;
            this.time = time;
            this.satisfaction = satisfaction;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int t = 1; t <= tc; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점
            M = Integer.parseInt(st.nextToken()); // 휴가기간
            maxRst = 0;
            finalPath = "";
            adj = new int[N][N];
            v = new boolean[N];
            nodeArr = new Node[N];
            for (int i = 0; i < N-1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = i+1; j < N; j++) {
                    adj[i][j] = adj[j][i] = Integer.parseInt(st.nextToken());
                }
            }
            char kinds;
            for (int i = 0; i < N; i++) {
                int time = -1,satisfaction = -1;
                st = new StringTokenizer(br.readLine());
                kinds = st.nextToken().charAt(0);
                if(kinds == 'P') {
                    time = Integer.parseInt(st.nextToken());
                    satisfaction = Integer.parseInt(st.nextToken());
                }else if(kinds == 'A') airport = i;
                nodeArr[i] = new Node(kinds, time, satisfaction);
            }
            // 입력끝
            v[airport] = true;
            dfs(airport,0,0,1,"");
            sb.append("#").append(t).append(" ").append(maxRst).append(finalPath).append("\n");
        }
        System.out.print(sb);
    }
    static void dfs(int idx,int curTime,int curSatis,int day,String path) {
        for (int i = 0; i < N; i++) {
            if(nodeArr[i].kinds == 'P') {
                if(v[i]) continue;
                if(curTime + nodeArr[i].time + adj[idx][i] > 9*60)continue;
                v[i] = true;
                dfs(i,curTime + nodeArr[i].time + adj[idx][i], curSatis+nodeArr[i].satisfaction, day,path+" "+(i+1));
                v[i] = false;
            }
        } // 호텔이나 공항으로 돌아가기
        if(day != M) {
            for (int i = 0; i < N; i++) {
                if(nodeArr[i].kinds == 'H' && curTime + adj[idx][i] <= 9*60) {
                    dfs(i,0,curSatis,day+1,path+" "+(i+1));
                }
            }
        }else {
            if(curTime + adj[idx][airport] <= 9*60) {
                if(maxRst < curSatis) {
                    maxRst = curSatis;
                    finalPath = path+" "+(airport+1);
                }
                return;
            }
        }
    }
}