import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
// LCS(Longest Common Subsequence) 최장 공통 부분 수열
public class Main9252 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr;
    public static int max;
    public static void main(String[] args) throws Exception {
        String str1 = br.readLine();    // 행
        String str2 = br.readLine();    // 열
        max = 0;

        arr = new int[str2.length() + 1][str1.length() + 1];
        for(int i = 1; i <= str2.length(); i++) {
            char a = str2.charAt(i - 1);
            for(int j = 1; j <= str1.length(); j++) {
                if(a == str1.charAt(j - 1)) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                    max = Math.max(arr[i][j], max);
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int nr = str2.length();
        int nc = str1.length();
        while(true) {
            if(arr[nr][nc] == 0) {
                break;
            }

            if(arr[nr - 1][nc] == arr[nr][nc]) {
                nr -= 1;
                continue;
            } else if (arr[nr][nc - 1] == arr[nr][nc]) {
                nc -= 1;
                continue;
            } else {
                sb.append(str2.charAt(nr - 1));
                nr -= 1;
                nc -= 1;
            }
        }
        bw.write(Integer.toString(max));
        if(sb.length() != 0) {
            bw.newLine();
            bw.write(sb.reverse().toString());
        }
        br.close();
        bw.flush();
        bw.close();
    }
}