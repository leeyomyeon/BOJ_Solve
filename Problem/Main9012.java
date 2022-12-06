import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main9012 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        Loop1:
        for(int tc = 1; tc <= T; tc++) {
            int count = 0;
            char[] c = br.readLine().toCharArray();
            for(int i = 0; i < c.length; i++) {
                if(c[i] == '(') {
                    count += 1;
                } else {
                    count -= 1;
                }
                if(count < 0) {
                    bw.write("NO");
                    bw.newLine();
                    continue Loop1;
                }
            }
            if(count == 0) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
} 