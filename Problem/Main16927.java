import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16927 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, R;
    public static int[][] arr, resArr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        resArr = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        char c = 'N';
        if(N % 2 == 1 && M % 2 == 0) {
            c = 'M';
        }
        rotate(c);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(resArr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void rotate(char dir) {
        // (0, 0)부터 r과 c에 1씩 더하면서 N 또는 M이 짝수일 때 절반인 곳 까지 가면 종료
        int rot = (dir == 'N' ? Math.min(N, M) : M) / 2;
        for(int k = 0; k < rot; k++) {
            int nCnt = N - (2 * k) - 1;
            int mCnt = M - (2 * k) - 1;
            int rotNum = (nCnt + mCnt) * 2;   // 현재 회전시킬 개수가 몇개 있는지
            // k ~ N - k까지
            int rotCnt = R % rotNum; // (k, k)부터 몇 칸 이동 가능?
            int r = k;
            int c = k;
            rotCnt = (rotNum - rotCnt) % rotNum;
            for(; r < N - 1 - k; r++) {
                int[] start = getStartRC(rotNum, rotCnt, nCnt, mCnt, k);
                resArr[r][c] = arr[start[0]][start[1]];
                rotCnt++;
                if(rotCnt == rotNum) rotCnt %= rotNum;
            }
            for(; c < M - 1 - k; c++) {
                int[] start = getStartRC(rotNum, rotCnt, nCnt, mCnt, k);
                resArr[r][c] = arr[start[0]][start[1]];
                rotCnt++;
                if(rotCnt == rotNum) rotCnt %= rotNum;
            }
            for(; r > k ; r--) {
                int[] start = getStartRC(rotNum, rotCnt, nCnt, mCnt, k);
                resArr[r][c] = arr[start[0]][start[1]];
                rotCnt++;
                if(rotCnt == rotNum) rotCnt %= rotNum;
            }
            for(; c > k; c--) {
                int[] start = getStartRC(rotNum, rotCnt, nCnt, mCnt, k);
                resArr[r][c] = arr[start[0]][start[1]];
                rotCnt++;
                if(rotCnt == rotNum) rotCnt %= rotNum;
            }
        }
    }
    public static int[] getStartRC(int rotNum, int rotCnt, int nCnt, int mCnt, int k) {
        int[] tmp = new int[2];
        if(rotCnt <= nCnt) {
            tmp[0] = k + rotCnt;
            tmp[1] = k;
        } else if(rotCnt <= nCnt + mCnt) {
            tmp[0] = N - 1 - k;
            tmp[1] = rotCnt - nCnt + k;
        } else if(rotCnt <= nCnt * 2 + mCnt) {
            tmp[0] = N - 1 - k - (rotCnt - nCnt - mCnt);
            tmp[1] = M - 1 - k;
        } else {
            tmp[0] = k;
            tmp[1] = k + rotNum - rotCnt;
        }
        return tmp;
    }
}