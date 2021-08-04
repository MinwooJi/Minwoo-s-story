import java.util.Scanner;
  
public class Solution{
    static int[] arr ;
    static int cnt;
    static int max;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t=0;t<T;t++) {
            sc.nextLine();
            String s = sc.next();
            char[] temp = s.toCharArray();
            arr = new int[temp.length];
            for(int i=0;i<arr.length;i++) {
                arr[i] = temp[i] - '0';
            }
            cnt= sc.nextInt();
            max = Integer.MIN_VALUE;
            dfs(0,0);
            System.out.printf("#%d ",t+1);
            System.out.println(max);
        }
    }
      
    static void dfs(int n, int k) {
        if(k==cnt) {
            StringBuffer sb = new StringBuffer();
            for(int i=0;i<arr.length;i++)
                sb.append(arr[i]);
            max = Math.max(max, Integer.parseInt(sb.toString()));
             
            return;
        }
        for(int i=n;i<arr.length;i++) {
            for(int j=i;j<arr.length;j++) {
                if(i==j)
                    continue;
                if(arr[i]<=arr[j]) {
                swap(i,j);
                dfs(i,k+1);
                swap(i,j);
                }
            }
        }
    }
      
    static void swap(int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}