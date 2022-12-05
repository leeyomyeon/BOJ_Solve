import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1343 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '.') {
                if(cnt > 0) {
                    if(cnt % 2 != 0) {
                        bw.write("-1");
                        br.close();
                        bw.flush();
                        bw.close();
                        return;
                    }
                    for(int j = 0; j < cnt / 4; j++) {
                        sb.append("AAAA");
                    }
                    if(cnt % 4 == 2) {
                        sb.append("BB");
                    }
                    cnt = 0;
                } 
                sb.append(".");
            } else {
                cnt++;
                if(i == str.length() - 1) {
                    if(cnt % 2 != 0) {
                        bw.write("-1");
                        br.close();
                        bw.flush();
                        bw.close();
                        return;
                    }
                    for(int j = 0; j < cnt / 4; j++) {
                        sb.append("AAAA");
                    }
                    if(cnt % 4 == 2) {
                        sb.append("BB");
                    }
                }
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
/* 
..XXXXXX..XX
*/