import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main2217 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int MAX = Integer.MIN_VALUE;
        Arrays.sort(arr);
        for(int i = 0; i < N; i++) {
            MAX = Math.max((N - i) * arr[i], MAX);
        }
        bw.write(Integer.toString(MAX));
        br.close();
        bw.flush();
        bw.close();
    }
}