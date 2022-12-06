import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2578 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] isBingo;
    public static int[] bingo;
    public static void main(String[] args) throws Exception {
        isBingo = new boolean[26];
        bingo = new int[26];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 5; j++) {
                bingo[Integer.parseInt(st.nextToken())] = (i * 5) + j;
            }
        }

        int res = 0;
        Loop1:
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 5; j++) {
                isBingo[bingo[Integer.parseInt(st.nextToken())]] = true;
                if(countBingo() >= 3) {
                    res = (i * 5) + j;
                    break Loop1;      
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }

    public static int countBingo() {
        int cnt = 0;
        for(int i = 0; i <= 4; i++) {
            if(isBingo[(i * 5) + 1] && isBingo[(i * 5) + 2] && isBingo[(i * 5) + 3] && isBingo[(i * 5) + 4] && isBingo[(i * 5) + 5]) {
                cnt++;
            }
            if(isBingo[(1 + i)] && isBingo[(1 + i) + 5] && isBingo[(1 + i) + 10] && isBingo[(1 + i) + 15] && isBingo[(1 + i) + 20]) {
                cnt++;
            }
        }
        if(isBingo[1] && isBingo[7] && isBingo[13] && isBingo[19] && isBingo[25]) {
            cnt++;
        }
        if(isBingo[5] && isBingo[9] && isBingo[13] && isBingo[17] && isBingo[21]) {
            cnt++;
        }
        return cnt;
    }
}