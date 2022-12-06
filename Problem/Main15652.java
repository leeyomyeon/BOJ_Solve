import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15652 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static boolean[] visited;
    public static int[] arr;
    public static StringBuffer sb;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuffer();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        arr = new int[M];
        backTrack(0, 1);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void backTrack(int cnt, int next) throws Exception{
        if(cnt == M) {
            for(int r : arr) {
                sb.append(r).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = next; i <= N; i++) {
            arr[cnt] = i;
            backTrack(cnt + 1, i);
        }
    }
}