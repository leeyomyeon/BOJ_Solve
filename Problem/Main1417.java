import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main1417 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int ds = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++) {
            if(i == 0) {
                ds = Integer.parseInt(br.readLine());
            } else {
                int k = Integer.parseInt(br.readLine());
                if(k >= ds) {
                    pq.add(k);
                }
            }
        }
        int cnt = 0;
        while(!pq.isEmpty()) {
            int current = pq.poll();
            if(current >= ds) {
                current -= 1;
                ds += 1;
                cnt++;
                pq.add(current);
            }
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}