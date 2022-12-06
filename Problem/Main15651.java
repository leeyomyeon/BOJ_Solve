import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15651 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, M;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[M];
        중복조합(0);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void 중복조합(int cnt) throws Exception{
        if(cnt == M) {
            for(int r : arr) {
                bw.write(r + " ");
            }
            bw.newLine();
            return;
        }

        for(int i = 1; i <= N; i++) {
            arr[cnt] = i;
            중복조합(cnt + 1);
        }
    }
}