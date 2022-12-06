import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11724 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[][] arr;
    public static boolean[] visited;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = true;
            arr[to][from] = true;
        }
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                while(!queue.isEmpty()) {
                    int current = queue.poll();
                    for(int k = 1; k <= N; k++) {
                        if(arr[current][k] && !visited[k]) {
                            queue.add(k);
                            visited[k] = true;
                        }
                    }
                }
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}