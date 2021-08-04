import java.util.LinkedList;
import java.util.Scanner;
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = 10;
        for(int t=0;t<tc;t++) {
            LinkedList<String> list = new LinkedList<>();
            int length = sc.nextInt();
            for(int i=0;i<length;i++) {
                list.offer(sc.next());
            }
            int num = sc.nextInt();
            while(num>0) {
                num--;
                char ch = sc.next().charAt(0);
                if(ch == 'I') {
                    int n1 = sc.nextInt(); // idx n1뒤로
                    int n2 = sc.nextInt(); // n2개 삽입
                    for(int i=0;i<n2;i++) {
                        list.add(n1++, sc.next());
                    }
                }
                else if(ch == 'D') {
                    int n3 = sc.nextInt(); // idx n3 바로 뒤부터
                    int n4 = sc.nextInt(); // n4개 삭제
                    for(int i=0;i<n4;i++) {
                        list.remove(n3);
                    }
                }
            }
            System.out.print(String.format("#%d ", t+1));
            for(int i=0;i<10;i++) {
                System.out.print(String.format("%s ", list.poll()));
            }
            System.out.println();
        }
    }
}