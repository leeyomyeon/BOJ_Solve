import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15650 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static boolean[] visited;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N + 1];
        backTrack(1, 0);
        bw.write("");
        br.close();
        bw.flush();
        bw.close();
    }
    
    public static void backTrack(int idx, int cnt) throws Exception {
        if(cnt == M) {
            for(int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.newLine();
            return;
        }

        for(int i = idx; i <= N; i++) {
            arr[cnt] = i;
            backTrack(i + 1, cnt + 1);
        }
    }
}