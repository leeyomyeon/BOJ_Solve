import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main9019 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final int MOD = 10000;
    public static boolean[] visited;
    public static int from, to;
    public static Queue<Point> queue;
    public static class Point {
        int k;
        StringBuilder sb;

        public Point(int k, StringBuilder sb) {
            this.k = k;
            this.sb = sb;
        }
    }
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            Point start = new Point(from, new StringBuilder());
            queue = new LinkedList<>();
            visited = new boolean[10000];
            visited[from] = true;
            queue.add(start);
            to = Integer.parseInt(st.nextToken());

            while(!queue.isEmpty()) {
                Point current = queue.poll();
                if(current.k == to) {
                    bw.write(current.sb.toString());
                    bw.newLine();
                    break;
                }
                // D
                int nk = (current.k << 1) % MOD;
                if(!visited[nk]) {
                    visited[nk] = true;
                    queue.add(new Point(nk, new StringBuilder(current.sb).append("D")));
                }
                // S
                nk = (current.k + MOD - 1) % MOD;
                if(!visited[nk]) {
                    visited[nk] = true;
                    queue.add(new Point(nk, new StringBuilder(current.sb).append("S")));
                }
                // L
                nk = (current.k % 1000) * 10 + (current.k / 1000);
                if(!visited[nk]) {
                    visited[nk] = true;
                    queue.add(new Point(nk, new StringBuilder(current.sb).append("L")));
                }
                // R
                nk = (current.k % 10) * 1000 + (current.k / 10);
                if(!visited[nk]) {
                    visited[nk] = true;
                    queue.add(new Point(nk, new StringBuilder(current.sb).append("R")));
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}