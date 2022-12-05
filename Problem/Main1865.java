import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1865 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M, W;
    public static LinkedList<Point>[] list;
    public static int[] cost;
    public static int MAX = 987654321;
    public static class Point {
        int to;
        int weight;

        public Point(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            list = new LinkedList[N + 1];
            for(int i = 0; i <= N; i++) {
                list[i] = new LinkedList<>();
            }            
            cost = new int[N + 1];
            for(int i = 0; i < M + W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                if(i < M) {
                    list[from].add(new Point(to, weight));
                    list[to].add(new Point(from, weight));
                } else {
                    list[from].add(new Point(to, weight * -1));
                }
            }

            if(bellmanFord()) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static boolean bellmanFord() {
        boolean isOK = false;
        Arrays.fill(cost, MAX);
        cost[1] = 0;
        for(int i = 1; i < N; i++) {
            isOK = false;
            for(int j = 1; j <= N; j++) {
                for(Point current : list[j]) {
                    if(cost[current.to] > cost[j] + current.weight) {
                        cost[current.to] = cost[j] + current.weight;
                        isOK = true;
                    }
                }
            }
            if(!isOK) {
                break;
            }
        }
        if(isOK) {
            for(int i = 1; i <= N; i++) {
                for(Point current : list[i]) {
                    if(cost[current.to] > cost[i] + current.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}