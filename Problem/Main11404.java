import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// 플로이드 와샬
public class Main11404 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[][] arr;
    public static int N, M;
    public static final int MAX = 99999999;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        // 초기화
        for(int i = 0; i <= N; i++) {
            Arrays.fill(arr[i], MAX);
            arr[i][i] = 0;
        }
        // cost 입력
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(arr[from][to] > weight) {
                arr[from][to] = weight;
            }
        }
        // 정점 탐색
        for(int k = 1; k <= N; k++) {   // 경유지
            for(int i = 1; i <= N; i++) {   // 시작점
                for(int j = 1; j <= N; j++) {   // 도착점
                        // 시작점부터 도착점까지 가는 거리가 경유지를 거쳐갈때보다 크면 업데이트
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int res = 0;
                if(i != j && arr[i][j] != MAX) {
                    res = arr[i][j];
                }
                bw.write(res + " ");
            }   
            bw.newLine();
        }
        bw.write("");
        br.close();
        bw.flush();
        bw.close();
    }
}