import java.util.Scanner;

public class Solution2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        int tc = 10;
        for(int t=0;t<tc;t++) {
            int num = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();
            result = Mypower(N,M);
            System.out.println(String.format("#%d %d", num,result));
        }
         
    }
    private static int Mypower(int base, int exp) {
        if(exp == 0)
            return 1;
        if(exp == 1)
            return base;
        int num = Mypower(base,exp/2);
        if(exp % 2 == 0) { // 나눠 떨어질때
            return num * num;
        }
        else {
            return num * num*base;
        }
    }
}