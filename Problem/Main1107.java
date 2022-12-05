import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1107 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static HashSet<Integer> set;
    public static PriorityQueue<Integer> queue;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        if(M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
        }
        int minCnt = Math.abs(N - 100);
        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for(int i = 0; i <= 1000000; i++) {
            String num = Integer.toString(i);
            boolean isNum = true;
            for(int j = 0; j < num.length(); j++) {
                if(set.contains(num.charAt(j) - '0')) {
                    isNum = false;
                    break;
                }
            }

            if(isNum) {
                cnt = num.length() + Math.abs(N - i);
                min = Math.min(cnt, min);
            }

        }

        bw.write(Integer.toString(Math.min(minCnt, min)));
        br.close();
        bw.flush();
        bw.close();
    }
}