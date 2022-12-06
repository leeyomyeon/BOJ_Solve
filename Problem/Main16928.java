import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main16928 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[] visited;
    public static ArrayDeque<Point> deque;
    public static HashMap<Integer, Integer> map;
    public static class Point {
        int n;
        int cnt;
        public Point(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map.put(from, to);
        }
        visited = new boolean[101];
        deque = new ArrayDeque<>();
        visited[1] = true;
        deque.add(new Point(1, 0));
        int res = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.n == 100) {
                res = current.cnt;
                break;
            }

            for(int d = 1; d <= 6; d++) {
                int nn = current.n + d;
                if(nn <= 100 && !visited[nn]) {
                    visited[nn] = true;
                    if(map.containsKey(nn)) {
                        nn = map.get(nn);
                    }
                    deque.add(new Point(nn, current.cnt + 1));
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}