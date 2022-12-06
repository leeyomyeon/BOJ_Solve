import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main4342 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if( a == b && a == 0) {
                break;
            }
            bw.write(gcd(a, b) ? "A wins\n": "B wins\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static boolean gcd(int p, int q) {
        int max = Math.max(p, q);
        int min = Math.min(p, q);

        if(max % min == 0) {
            return true;
        }
        if(max - min < min){
            return !gcd(max % min, min);
        }
        return true;
    }
}