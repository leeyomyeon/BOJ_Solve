import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15649 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static boolean[] visited;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        arr = new int[M];
        recursive(0);
        br.close();
        bw.flush();
        bw.close();

    }

    public static void recursive(int idx) throws Exception {
        if(idx == M) {
            for(int k : arr) {
                bw.write(k + " ");
            }
            bw.newLine();
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                arr[idx] = i + 1;
                recursive(idx + 1);
                visited[i] = false;
            }
        }
    }
}