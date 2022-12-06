import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main6118 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static boolean[] visited;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(new Point(end, 0));
            list.get(end).add(new Point(start, 0));
        }

        ArrayDeque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(1, 0));
        visited[1] = true;
        int maxDepth = 0;
        int minNum = N;
        HashSet<Integer> set = new HashSet<>();

        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            for(Point next : list.get(current.num)) {
                int nextDepth = current.depth + 1;
                if(!visited[next.num]) {
                    visited[next.num] = true;
                    deque.add(new Point(next.num, nextDepth));
                    if(maxDepth < nextDepth) {
                        maxDepth = nextDepth;
                        minNum = N;
                        set.clear();
                    }
                    if(nextDepth == maxDepth) {
                        minNum = Math.min(minNum, next.num);
                        set.add(next.num);
                    }
                }
            }
        }
        bw.write(minNum + " " + maxDepth + " " + set.size() + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}