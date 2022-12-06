import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main5555 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        String findStr = br.readLine();
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            sb.append(sb);
            if(sb.indexOf(findStr) >= 0) {
                cnt++;
            }
        }
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}