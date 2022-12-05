import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1030 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int S, N, K, R1, R2, C1, C2, width;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
        width = (int) Math.pow(N, S);   // 가로길이
        for(int i = R1; i <= R2; i++) {
            for(int j = C1; j <= C2; j++) {
                bw.write(dnq(width, i, j) ? 49 : 48);
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean dnq(int w, int r, int c) {
        if(w == 1) {
            return false;
        }
        int next = w / N;
        if(r >= next * (N - K) / 2 && r < next * (N + K) / 2 && c >= next * (N - K) / 2 && c < next * (N + K) / 2) {
            return true;
        }
        return dnq(next, r % next, c % next);
    }
}