import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main11657 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int N, M, MAX;
    public static LinkedList<Point>[] list;
    public static long[] cost;
    public static class Point {
        int to;
        int vertex;
        public Point(int to, int vertex) {
            this.to = to;
            this.vertex = vertex;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAX = 987654321;
        list = new LinkedList[N + 1];
        for(int i = 0; i <= N; i++) {
            list[i] = new LinkedList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int vertex = Integer.parseInt(st.nextToken());
            list[from].add(new Point(to, vertex));
        }
        cost = new long[N + 1];
        Arrays.fill(cost, MAX);
        cost[1] = 0;
        boolean isCycle = false;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(Point current : list[j]) {
                    if(cost[j] != MAX && cost[current.to] > current.vertex + cost[j]) {
                        cost[current.to] = current.vertex + cost[j];
                        if(i == N) {
                            isCycle = true;
                        }
                    }
                }
            }
        }
        if(isCycle) {
            bw.write("-1");
        } else {
            StringBuffer sb = new StringBuffer();
            for(int i = 2; i <= N; i++) {
                sb.append(cost[i] == MAX ? "-1" : cost[i]).append("\n");
            }
            bw.write(sb.toString());
        }
        br.close();
        bw.flush();
        bw.close();
    }
}