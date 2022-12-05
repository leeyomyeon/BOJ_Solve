import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main1927 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws Exception {
        pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x == 0) {
                if(pq.isEmpty()) {
                    bw.write("0");
                    bw.newLine();
                } else {
                    bw.write(Integer.toString(pq.poll()));
                    bw.newLine();
                }
            } else {
                pq.add(x);
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}