import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main11068 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            String res = "0";
            Loop1:
            for(int j = 2; j <= 64; j++) {
                Deque<Integer> deque = new LinkedList<>();
                int newN = n;
                while(true) {
                    int e = newN % j;   // 나머지
                    newN /= j;   // 몫
                    deque.add(e);
                    if(newN < j) {
                        deque.add(newN);
                        break;
                    }
                }
                while(true) {
                    if(deque.size() <= 1) {
                        res = "1";
                        break Loop1;
                    }
                    int first = deque.pollFirst();
                    int last = deque.pollLast();
                    if(first != last) {
                        break;
                    }
                }
            }
            bw.write(res);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}