import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1074 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, R, C, cnt;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cnt = 0;
        dnq(0, 0, (int) Math.pow(2, N));

    }

    public static void dnq(int r, int c, int dist) throws Exception {
        if(dist == 2) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    if(r + i == R && c + j == C) {
                        bw.write(Integer.toString(cnt));
                        br.close();
                        bw.flush();
                        bw.close();
                        System.exit(0);
                    }
                    cnt++;
                }
            }
            return;
        }
        if(C >= c && C < c + dist && R >= r && R < r + dist) {
            int newDist = dist >> 1;
            dnq(r, c, newDist);
            dnq(r, c + newDist, newDist);
            dnq(r + newDist, c, newDist);
            dnq(r + newDist, c + newDist, newDist);
        } else {
            cnt += dist * dist;
            return;
        }
    }
}