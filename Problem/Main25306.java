import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main25306 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static long[] arr;
    public static long S, F;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Long.parseLong(st.nextToken());
        F = Long.parseLong(st.nextToken());
        long res;
        if(S == F) {
            res = S;
        } else {
            arr = new long[4];

            arr[(int) ((S + 1) % 4)] = S ^ (S + 1);
            arr[(int) ((S + 2) % 4)] = arr[(int) ((S + 1) % 4)] ^ (S + 2);
            arr[(int) ((S + 3) % 4)] = arr[(int) ((S + 2) % 4)] ^ (S + 3);
            arr[(int) ((S + 4) % 4)] = arr[(int) ((S + 3) % 4)] ^ (S + 4);

            if(F % 4 == 0) {
                res = arr[3] ^ F;
            } else if (F % 4 == 2) {
                res = arr[1] ^ F;
            } else {
                res = arr[(int) (F % 4)];
            }
        }
        bw.write(Long.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}