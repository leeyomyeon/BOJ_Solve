import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
// 클래스 사용
public class Main11286 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static PriorityQueue<Number> pq;
    public static int N;
    public static class Number implements Comparable<Number>{
        int origin;
        int abs;
        public Number(int origin, int abs) {
            this.origin = origin;
            this.abs = abs;
        }
        @Override
        public int compareTo(Number o) {
            if(o.abs == this.abs) {
                return this.origin - o.origin;
            }
            return this.abs - o.abs;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                if(pq.size() == 0) {
                    sb.append("0");
                } else {
                    sb.append(pq.poll().origin);
                }
                sb.append("\n");
            } else {
                pq.add(new Number(num, Math.abs(num)));
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}