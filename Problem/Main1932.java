import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1932 {
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[sumN(N)];
        int idx = 1;
        int max = Integer.MIN_VALUE;
        arr[0] = 0;
    /*          0
                1
              2   3
            4   5   6
          7   8   9  10
        11  12  13  14  15
    */
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 1; j <= i; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(j == 1) {
                    arr[idx] = arr[idx - (i - 1)] + num;
                } else if (j == i) {
                    arr[idx] = arr[idx - i] + num;
                } else {
                    // 사이값 비교
                    if(arr[idx - i] > arr[idx - i + 1]) {
                        arr[idx] = arr[idx - i] + num;
                    } else {
                        arr[idx] = arr[idx - i + 1] + num;
                    }
                }
                
                if(arr[idx] >= max) {
                    max = arr[idx];
                }
                idx++;
            }

        }
        
        System.out.println(max);
    }
// [[7], [10, 15], [18, 1, 15], [20, 8, 5, 19], [24, 13, 10, 11, 24]]
    public static int sumN(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum + 1;
    }
}
