import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16212 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[4000001];
        while(st.hasMoreTokens()) {
            arr[Integer.parseInt(st.nextToken()) + 2000000]++;
        }

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) {
                for(int j = 0; j < arr[i]; j++) {
                    bw.write((i - 2000000)+" ");
                }
            }
        }
        bw.write("");
        br.close();
        bw.flush();
        bw.close();
    }
}