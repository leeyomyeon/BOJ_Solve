import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main5014 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Queue<Point> queue;
    public static boolean[] visited;
    public static int F;
    public static int S;
    public static int G;
    public static int U;
    public static int D;
    public static class Point {
        int loc;
        int cnt;

        public Point(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {

        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());   // 총 F층
        S = Integer.parseInt(st.nextToken());   // 현재 있는 층
        G = Integer.parseInt(st.nextToken());   // 목적지 층
        U = Integer.parseInt(st.nextToken());   // 올라가는 층
        D = Integer.parseInt(st.nextToken());   // 내려가는 층

        
        queue = new LinkedList<>();
        visited = new boolean[F + 1];

        queue.add(new Point(S, 0));
        visited[S] = true;

        int min = Integer.MAX_VALUE;

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            if(current.loc == G) {
                min = Integer.min(min, current.cnt);
                continue;
            }
            int nu = current.loc + U;
            int nd = current.loc - D;

            if(nu <= F && !visited[nu]) {
                queue.add(new Point(nu, current.cnt + 1));
                visited[nu] = true;
            }
            if(nd >= 1 && !visited[nd]) {
                queue.add(new Point(nd, current.cnt + 1));
                visited[nd] = true;
            }
        }
        if(min == Integer.MAX_VALUE) {
            bw.write("use the stairs");
        } else {
            bw.write(Integer.toString(min));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}