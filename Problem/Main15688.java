import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15688 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[2000001];

        for(int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine()) + 1000000]++;
        }
        // -1000000 ~ 1000000
        for(int i = 0; i <= 2000000; i++) {
            if(arr[i] > 0) {
                for(int j = 0; j < arr[i]; j++) {
                    bw.write(Integer.toString(i - 1000000));
                    bw.newLine();
                }
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}