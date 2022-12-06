import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main11403 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[][] arr, res;
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        res = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 0부터 탐색
        for(int start = 0; start < N; start++) {
            ArrayDeque<Integer> deque = new ArrayDeque<>();
            visited = new boolean[N];
            deque.add(start);
            while(!deque.isEmpty()) {
                int current = deque.poll();
                for(int i = 0; i < N; i++) {
                    if(arr[current][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        res[start][i] = 1;
                        deque.add(i);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}