import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main1269 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static int N, M;
    public static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int aCnt = 0;
        int bCnt = 0;
        for(int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            if(set.contains(Integer.parseInt(st.nextToken()))) {
                aCnt++;
            } else {
                bCnt++;
            }
        }

        bw.write(Integer.toString(set.size() - aCnt + bCnt));
        br.close();
        bw.flush();
        bw.close();
    }
}