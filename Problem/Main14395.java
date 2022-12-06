import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main14395 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static StringBuffer sb = new StringBuffer();
    public static long S, T;
    public static HashSet<Long> set;
    public static ArrayDeque<Point> deque;
    public static class Point {
        long n;
        StringBuilder sb;
        public Point(long n, StringBuilder sb) {
            this.n = n;
            this.sb = sb;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        T = Long.parseLong(st.nextToken());
        set = new HashSet<>();
        deque = new ArrayDeque<>();
        set.add(S);
        deque.add(new Point(S, new StringBuilder()));
        int res = -1;
        if(S == T) {
            res = 1;
            bw.write("0");
        }
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();

            if(current.n == T) {
                res = 1;
                bw.write(current.sb.toString());
                break;
            }
            long nn = current.n * current.n;
            if(nn >= 1 && nn <= 1000000000 && !set.contains(nn)) {
                set.add(nn);
                StringBuilder sb = new StringBuilder(current.sb).append("*");
                deque.add(new Point(nn, sb));
            }
            nn = current.n * 2;
            if(nn >= 1 && nn <= 1000000000 && !set.contains(nn)) {
                set.add(nn);
                StringBuilder sb = new StringBuilder(current.sb).append("+");
                deque.add(new Point(nn, sb));
            }
            nn = 1;
            if(!set.contains(nn)) {
                set.add(nn);
                StringBuilder sb = new StringBuilder(current.sb).append("/");
                deque.add(new Point(nn, sb));
            }
        }
        if(res == -1) {
            bw.write("-1");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}