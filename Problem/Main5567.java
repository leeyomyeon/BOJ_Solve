import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main5567 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[] select;
    public static ArrayList<ArrayList<Point>> list;
    public static class Point {
        int num;
        int depth;
        public Point(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        select = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.get(start).add(new Point(end, 0));
            list.get(end).add(new Point(start, 0));
        }

        int cnt = 0;
        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(1, 0));
        select[1] = true;
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            for(Point next : list.get(current.num)) {
                if(!select[next.num] && current.depth <= 2) {
                    select[next.num] = true;
                    deque.add(new Point(next.num, current.depth + 1));
                    if(current.depth + 1 <= 2) {
                        cnt++;
                    }
                }
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}