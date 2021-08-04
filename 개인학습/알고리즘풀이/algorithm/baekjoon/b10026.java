import java.util.Scanner;

public class b10026 {
   static StringBuffer[] mat;
   static StringBuffer[] hdMat;
   static boolean[][] visited;
   static int bound;
   static int cnt;
   static int hCnt=0;
   static int[] dx = {0,0,1,-1};
   static int[] dy = {1,-1,0,0};
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Scanner sc = new Scanner(System.in);
      bound = sc.nextInt();
      mat = new StringBuffer[bound];
      hdMat = new StringBuffer[bound];
      for(int i=0;i<bound;i++) {
    	  mat[i] = new StringBuffer(); 
    	  hdMat[i] = new StringBuffer();
      }
      visited = new boolean[bound][bound];
      cnt = 0;
      for(int i=0;i<bound;i++) {
         mat[i].append(sc.next());
      }
      for(int i=0;i<bound;i++) {
         hdMat[i].append(mat[i]);
         for(int j=0;j<bound;j++) {
            if(mat[i].charAt(j)=='G')
               hdMat[i].setCharAt(j, 'R'); 
         }
      }

      for(int i=0;i<bound;i++) {
         for(int j=0;j<bound;j++) {
            if(visited[i][j] == false) {
               cnt++;
               find(i,j,mat);
            }
         }
      }
      visited = new boolean[bound][bound];
      for(int i=0;i<bound;i++) {
    	  for(int j=0;j<bound;j++) {
    		  if(visited[i][j]==false) {
    			  hCnt ++;
    			  find(i,j,hdMat);
    		  }
    	  }
      }
      System.out.println(cnt+" "+hCnt);
   }
   public static void find(int y,int x, StringBuffer[] mat)
   {
      for(int i=0;i<4;i++) {
         int nx = x + dx[i];
         int ny = y + dy[i];
         if(nx>=0&&nx<bound&ny>=0&&ny<bound) {
            if(visited[ny][nx]==true) {
               continue;
            }
            if(mat[y].charAt(x) == mat[ny].charAt(nx)) {
               visited[ny][nx]=true;
               find(ny,nx,mat);
            }
            
         }
      }
   }

}