import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2096 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        int maxLeft = 0, maxMid = 0, maxRight = 0, maxPrevLeft = 0, maxPrevMid = 0, maxPrevRight = 0;
        int minLeft = 0, minMid = 0, minRight = 0, minPrevLeft = 0, minPrevMid = 0, minPrevRight = 0;
        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            maxLeft = Math.max(maxPrevLeft + a, maxPrevMid + a);
            maxMid = Math.max(maxPrevLeft + b, Math.max(maxPrevMid + b, maxPrevRight + b));
            maxRight = Math.max(maxPrevMid + c, maxPrevRight + c);

            minLeft = Math.min(minPrevLeft + a, minPrevMid + a);
            minMid = Math.min(minPrevLeft + b, Math.min(minPrevMid + b, minPrevRight + b));
            minRight = Math.min(minPrevMid + c, minPrevRight + c);

            maxPrevLeft = maxLeft;
            maxPrevMid = maxMid;
            maxPrevRight = maxRight;

            minPrevLeft = minLeft;
            minPrevMid = minMid;
            minPrevRight = minRight;
        }
        int max = Math.max(maxLeft, Math.max(maxMid, maxRight));
        int min = Math.min(minLeft, Math.min(minMid, minRight));
        bw.write(max + " " + min + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}