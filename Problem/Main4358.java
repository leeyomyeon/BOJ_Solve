import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class Main4358 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static HashMap<String, Integer> map;
    public static void main(String[] args) throws Exception {
        String str;
        map = new HashMap<>();
        int n = 0;
        while((str = br.readLine()) != null) {
            n++;
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        Set<String> set = map.keySet();
        PriorityQueue<String> pq = new PriorityQueue<>();
        pq.addAll(set);
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            String next = pq.poll();
            int k = map.get(next);
            double res = ((double) k / (double) n) * 100;
            sb.append(next).append(" ").append(String.format("%.4f", res)).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}