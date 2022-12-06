import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main12852 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static Set<Integer> set;
    public static Queue<Point> queue;
    public static class Point {
        int n;
        StringBuilder sb;
        int cnt;

        public Point(int n, StringBuilder sb, int cnt) {
            this.n = n;
            this.sb = sb;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        set = new HashSet<>();
        queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());

        queue.add(new Point(N, new StringBuilder(Integer.toString(N)), 0));
        while(!queue.isEmpty()) {
            Point current = queue.poll();
            if(current.n == 1) {
                bw.write(Integer.toString(current.cnt));
                bw.newLine();
                bw.write(current.sb.toString());
                break;
            }
            if(current.n % 3 == 0 && !set.contains(current.n / 3)) {
                set.add(current.n / 3);
                queue.add(new Point(current.n / 3, new StringBuilder(current.sb).append(" ").append(current.n / 3), current.cnt + 1));
            }
            if(current.n % 2 == 0 && !set.contains(current.n / 2)) {
                set.add(current.n / 2);
                queue.add(new Point(current.n / 2, new StringBuilder(current.sb).append(" ").append(current.n / 2), current.cnt + 1));
            }
            if(current.n - 1 >= 1 && !set.contains(current.n - 1)) {
                set.add(current.n - 1);
                queue.add(new Point(current.n - 1, new StringBuilder(current.sb).append(" ").append(current.n - 1), current.cnt + 1));
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}