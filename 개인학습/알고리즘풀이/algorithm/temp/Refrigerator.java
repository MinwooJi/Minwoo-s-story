import java.util.Arrays;
import java.util.Scanner;
 
public class Refrigerator {
    static class Deg implements Comparable<Deg>{
        int min;
        int max;
        int refNum;
        public Deg(int min, int max, int refNum) {
            super();
            this.min = min;
            this.max = max;
            this.refNum = refNum;
        }
        @Override
        public int compareTo(Deg o) {
            // TODO Auto-generated method stub
            return (this.min) -(o.min);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Deg[] arr = new Deg[N];
        for(int i=0;i<N;i++) {
            arr[i] = new Deg(sc.nextInt(),sc.nextInt(),1);
        }// 입력 끝
        Arrays.sort(arr);
            for(int i=0;i<N;i++) {
                for(int j = 0;j<N;j++) {
                    if(i==j)
                        continue;
                    if(arr[i].refNum != arr[j].refNum)
                        continue;
                    if(arr[i].min > arr[j].max || arr[i].max < arr[j].min) {
                        arr[j].refNum = arr[i].refNum+1;
                    }
                }
            }
 
        int num = 0;
        for(int i=0;i<N;i++) {
//          System.out.println(arr[i]);
            num = Math.max(num, arr[i].refNum);
        } // 냉장고수 계산
        System.out.println(num);
    }
}