import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main1026 {
    static Integer[] A;
    static Integer[] B;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new Integer[N];
        B = new Integer[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        // A는 오름차순, B는 내림차순
        Arrays.sort(A);   
        Arrays.sort(B, Collections.reverseOrder());
        
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += (A[i] * B[i]);
        }

        System.out.println(sum);
    }
}