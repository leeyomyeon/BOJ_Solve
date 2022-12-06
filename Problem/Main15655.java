import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main15655 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[] arr;
    public static boolean[] visited;
    public static int N, K;
    public static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        backTrack(0, 0);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void backTrack(int idx, int cnt) {
        if(cnt == K) {
            for(int i = 0; i < N; i++) {
                if(visited[i]) {
                    sb.append(arr[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for(int i = idx; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                backTrack(i, cnt + 1);
                visited[i] = false;
            }
        }
    }
}