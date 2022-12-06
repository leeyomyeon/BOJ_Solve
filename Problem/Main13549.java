import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
// 0-1 BFS
public class Main13549 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int from, to;
    public static int[] dist;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        dist = new int[200001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        deque.add(from);
        dist[from] = 0;

        while(!deque.isEmpty()) {
            int current = deque.pollFirst();

            if(current == to) {
                bw.write(Integer.toString(dist[to]));
                break;
            }

            int nc = current * 2;
            if(nc <= 200000 && dist[nc] > dist[current]) {
                deque.addFirst(nc);
                dist[nc] = dist[current];
            }

            nc = current + 1;
            if(nc <= 200000 && dist[nc] > dist[current] + 1) {
                deque.addLast(nc);
                dist[nc] = dist[current] + 1;
            }

            nc = current - 1;
            if(nc >= 0 && dist[nc] > dist[current] + 1) {
                deque.addLast(nc);
                dist[nc] = dist[current] + 1;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}