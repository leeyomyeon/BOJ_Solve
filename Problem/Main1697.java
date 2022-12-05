import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1697 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int K;
    public static Queue<Point> queue;
    public static class Point {
        int p;
        int cnt;

        public Point(int p, int cnt) {
            this.p = p;
            this.cnt = cnt;
        }
    }
    public static boolean[] visited;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];
        queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        int res = 0;
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            visited[current.p] = true;
            if(current.p == K) {
                res = current.cnt;
                break;
            }

            if(current.p + 1 <= 100000 && !visited[current.p + 1]) {
                visited[current.p + 1] = true;
                queue.add(new Point(current.p + 1, current.cnt + 1));
            }
            if(current.p - 1 >= 0 && !visited[current.p - 1]) {
                visited[current.p - 1] = true;
                queue.add(new Point(current.p - 1, current.cnt + 1));
            }
            if(current.p * 2 <= 100000 && !visited[current.p * 2]) {
                visited[current.p * 2] = true;
                queue.add(new Point(current.p * 2, current.cnt + 1));
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}