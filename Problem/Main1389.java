import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main1389 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[][] list;
    public static boolean[] visited;
    public static class Point {
        int num;
        int depth;
        public Point(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new boolean[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list[start][end] = true;
            list[end][start] = true;
        }
        int cnt = Integer.MAX_VALUE;
        int num = N + 1;
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            int tmpSum = 0;
            ArrayDeque<Point> deque = new ArrayDeque<>();
            deque.add(new Point(i, 0));
            visited[i] = true;
            while(!deque.isEmpty()) {
                Point current = deque.removeFirst();

                for(int next = 1; next <= N; next++) {
                    if(!visited[next] && list[current.num][next]) {
                        visited[next] = true;
                        deque.add(new Point(next, current.depth + 1));
                        tmpSum += (current.depth + 1);
                    }
                }
            }
            if(tmpSum < cnt) {
                cnt = tmpSum;
                num = i;
            } else if(tmpSum == cnt) {
                num = Math.min(i, num);
            }
        }
        bw.write(Integer.toString(num));
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
1--3--2
|／
4--5
 */