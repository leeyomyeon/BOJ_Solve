import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main13913 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static ArrayDeque<Point> deque;
    public static boolean[] visited;
    public static int N, K;
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
        K = Integer.parseInt(st.nextToken());
        deque = new ArrayDeque<>();
        visited = new boolean[100001];
        deque.add(new Point(N, 0));
        int[] trace = new int[100001];
        int[] dn = {0, 1, -1};
        int min = 0;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(current.n == K) {
                min = current.cnt;
                break;
            }
            // 100000 0
            for(int d = 0; d < 3; d++) {
                int nn = dn[d] == 0 ? current.n * 2 : current.n + dn[d];
                if(nn >= 0 && nn <= 100000 && !visited[nn]) {
                    visited[nn] = true;
                    deque.add(new Point(nn, current.cnt + 1));
                    trace[nn] = current.n;
                }
            }
        }
        Stack<Integer> stack = new Stack<>();
        stack.add(K);
        int start = K;
        for(int i = 0; i < min; i++) {
            stack.add(trace[start]);
            start = trace[start];
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        bw.write(Integer.toString(min));
        bw.newLine();
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}