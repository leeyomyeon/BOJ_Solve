import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main18111 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, B;
    public static int[][] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());   // 보유중인 블록
        arr = new int[N][M];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(arr[i][j], max);
                min = Math.min(arr[i][j], min);
            }
        }
        /*
        좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다. -> 2초
        인벤토리에서 블록 하나를 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다. -> 1초
        */
        int res = Integer.MAX_VALUE;
        int h = -1;
        for(int m = min; m <= max; m++) {
            int remove = 0;
            int add = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(arr[i][j] < m) {
                        add += (m - arr[i][j]);
                    } else {
                        remove += (arr[i][j] - m);
                    }
                }
            }
            if(add > (B + remove)) {
                continue;
            }
            if(add + (remove * 2) <= res) {
                res = add + (remove * 2);
                h = m;
            }
        }
        bw.write(res + " " + h + "\n");
        br.close();
        bw.flush();
        bw.close();
    }
}