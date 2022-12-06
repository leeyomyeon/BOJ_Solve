import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main16936 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static StringBuffer sb = new StringBuffer();
    public static PriorityQueue<Point> pq;
    public static class Point implements Comparable<Point>{
        long n;
        int d3;
        public Point(long n, int d3) {
            this.n = n;
            this.d3 = d3;
        }
        @Override
        public int compareTo(Point o) {
            if(o.d3 == this.d3) {
                if(this.n > o.n) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return o.d3 - this.d3;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            long k = Long.parseLong(st.nextToken());
            int n = 0;
            long t = k;
            while(t % 3 == 0) {
                n++;
                t = t / 3;
            }
            pq.add(new Point(k, n));
        }
        while(!pq.isEmpty()) {
            sb.append(pq.poll().n).append(" ");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}