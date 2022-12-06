import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15904 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        char[] valid = {'U', 'C', 'P', 'C'};
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == valid[idx]) {
                idx++;
                sb.append(str.charAt(i));
            }
            if(idx == valid.length) {
                break;
            }
        }
        if(sb.toString().equals("UCPC")) {
            bw.write("I love UCPC");
        } else {
            bw.write("I hate UCPC");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}