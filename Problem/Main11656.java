import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main11656 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static PriorityQueue<String> queue;
    public static void main(String[] args) throws Exception {
        queue = new PriorityQueue<>();
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            queue.add(str.substring(i, str.length()));
        }
        while(!queue.isEmpty()) {
            bw.write(queue.poll());
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}