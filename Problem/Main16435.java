import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
@SuppressWarnings("unused")
public class Main16435 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[10001];
        st = new StringTokenizer(br.readLine());

        while(st.hasMoreTokens()) {
            arr[Integer.parseInt(st.nextToken())]++;
        }
        for(int i = 1; i <= 10000; i++) {
            if(L >= i) {
                L += arr[i];
            } else {
                break;
            }
        }
        bw.write(Integer.toString(L));
        br.close();
        bw.flush();
        bw.close();
    }
}