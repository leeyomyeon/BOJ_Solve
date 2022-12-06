import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main15656 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static int[] selected;
    public static boolean[] visited;
    public static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        selected = new int[M];
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        permutation(0, 0);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void permutation(int idx, int next) {
        if(idx == M) {
            for(int k : selected) {
                sb.append(k).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = next; i < N; i++) {
            selected[idx] = arr[i];
            permutation(idx + 1, i);
        }
    }
}