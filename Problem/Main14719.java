import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14719 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int H, W;
    public static int[][] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; i++) {
            int k = Integer.parseInt(st.nextToken());
            for(int j = (H - 1); j >= (H - k); j--) {
                arr[j][i] = 1;
            }
        }

        int res = 0;
        for(int i = (H - 1); i >= 0; i--) {
            int startIdx = -1;
            int endIdx = -1;
            for(int j = 0; j < (W - 1); j++) {
                if(arr[i][j] == 1 && arr[i][j + 1] == 0) {
                    startIdx = j;
                }
                if(arr[i][j] == 0 && arr[i][j + 1] == 1 && startIdx >= 0) {
                    endIdx = j + 1;
                }
                if(startIdx >= 0 && endIdx >= 0) {
                    int cnt = (endIdx - startIdx - 1);
                    startIdx = -1;
                    endIdx = -1;
                    if(cnt > 0) {
                        res += cnt;
                    }
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}