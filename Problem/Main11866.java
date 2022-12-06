import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11866 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            queue.add(i);
        }
        bw.write("<");
        int count = 1;
        while(true) {
            if(queue.size() == 1) {
                bw.write(queue.poll() + ">");
                break;
            }
            int cur = queue.poll();
            if(count == K) {
                bw.write(cur + ", ");
                count = 1;
            } else {
                queue.add(cur);
                count++;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}