import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1958 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][][] lcs;
    public static int max;
    public static void main(String[] args) throws Exception {
        String str1 = br.readLine();    // 행
        String str2 = br.readLine();    // 열
        String str3 = br.readLine();    // 새로운 열
        max = 0;
        lcs = new int[str1.length() + 1][str2.length() + 1][str3.length() + 1];
        for(int i = 1; i <= str1.length(); i++) {
            char a = str1.charAt(i - 1);
            for(int j = 1; j <= str2.length(); j++) {
                char b = str2.charAt(j - 1);
                for(int k = 1; k <= str3.length(); k++) {
                    char c = str3.charAt(k - 1);
                    if(a == b && b == c) {
                        lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
                        max = Math.max(lcs[i][j][k], max);
                    } else {
                        lcs[i][j][k] = Math.max(Math.max(lcs[i - 1][j][k], lcs[i][j - 1][k]), lcs[i][j][k - 1]);
                    }
                }
            }
        }


        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
        bw.close();
    }
}