import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main5904 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static char res;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        int next = 0;
        for(int i = 0; i <= 27; i++) {
            next = next * 2 + (i + 3);
            if(N <= next) {
                dnq(i, N, next);
                break;
            }
        }
        bw.write(res);
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dnq(int idx, int search, int next) {
        if(idx == 0) {
            res = "moo".charAt(search - 1);
            return;
        }
        int prev = (next - (idx + 3)) / 2;
        if(search < prev) {    // 맨 왼쪽일 경우
            dnq(idx - 1, search, prev);
        } else if(search > prev + (idx + 3)) { // 맨 오른쪽일 경우
            dnq(idx - 1, search - prev - (idx + 3), prev);
        } else {
            if(search - prev == 1) {
                res = 'm';
            } else {
                res = 'o';
            }
        }
    }
}