import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main2644 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, start, end, res;
    public static boolean[][] arr;
    public static boolean[] visited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int vertex;
        int cnt;
        public Point(int vertex, int cnt) {
            this.vertex = vertex;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        deque = new ArrayDeque<>();
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        arr = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];
        res = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = true;
            arr[to][from] = true;
        }
        deque.add(new Point(start, 0));
        visited[start] = true;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.vertex == end) {
                res = current.cnt;
                break;
            }
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && arr[current.vertex][i]) {
                    visited[i] = true;
                    deque.add(new Point(i, current.cnt + 1));
                }
            }
        }
        bw.write(res == 0 ? "-1" : Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}