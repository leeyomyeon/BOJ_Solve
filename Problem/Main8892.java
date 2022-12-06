import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main8892 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int tc = Integer.parseInt(br.readLine());
        Loop1:
        for(int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] str = new String[n];
            for(int i = 0; i < n; i++) {
                str[i] = br.readLine();
            }

            for(int i = 0; i < n - 1; i++) {
                for(int j = i + 1; j < n; j++) {
                    StringBuilder sb1 = new StringBuilder(str[i]).append(str[j]);
                    StringBuilder sb2 = new StringBuilder(str[j]).append(str[i]);
                    if(isPalindrome(sb1)) {
                        bw.write(sb1.toString());
                        bw.newLine();
                        continue Loop1;
                    } else if(isPalindrome(sb2)) {
                        bw.write(sb2.toString());
                        bw.newLine();
                        continue Loop1;
                    }
                }
            }
            bw.write("0");
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isPalindrome(StringBuilder sb) {
        for(int i = 0; i < sb.length() / 2; i++) {
            if(sb.charAt(i) != sb.charAt(sb.length() - (i + 1))) {
                return false;
            }
        }
        return true;
    }
}