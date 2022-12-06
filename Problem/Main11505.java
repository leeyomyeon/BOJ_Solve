import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/* 세그먼트 트리 구간 곱 구하기 */
public class Main11505 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int N, M, K;
    public static int[] arr;
    public static long[] seg;
    public static int MOD = 1000000007;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 수의 변경이 일어나는 횟수
        K = Integer.parseInt(st.nextToken());   // 구간합을 구하는 횟수
        arr = new int[N + 1];
        seg = new long[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        makeSegment(1, N, 1);
        for(int k = 0; k < M + K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1) {
                // a가 1일땐 b번째 수를 c로 변경
                editSegment(1, N, 1, b, c);
                arr[b] = c;
            } else {
                // a가 2일땐 b부터 c까지의 곱 구함
                sb.append(find(1, N, 1, b, c)).append("\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static long makeSegment(int start, int end, int index) {
        if(start == end) {
            seg[index] = arr[start];
            return seg[index];
        }
        int mid = (start + end) / 2; // 중앙값
        seg[index] = ((makeSegment(start, mid, index * 2) % MOD) * (makeSegment(mid + 1, end, index * 2 + 1) % MOD)) % MOD;
        return seg[index];
    }
    public static long find(int start, int end, int index, int left, int right) {
        if(left > end || right < start) { // 범위 밖을 벗어난 경우
            return 1;
        }
        if(left <= start && right >= end) { // 범위 안에 들어오면
            return seg[index];
        }
        int mid = (start + end) / 2;
        return ((find(start, mid, index * 2, left, right) % MOD) * (find(mid + 1, end, index * 2 + 1, left, right) % MOD)) % MOD;
    }
    public static long editSegment(int start, int end, int index, int target, long value) {
        if(target < start || target > end) { // 범위 밖을 벗어난 경우
            return seg[index];
        }
        // 범위 내에 있으면 value 값만큼 갱신
        if(start == end) {
            seg[index] = value;
            return seg[index];
        }
        int mid = (start + end) / 2;
        seg[index] = (editSegment(start, mid, index * 2, target, value) * editSegment(mid + 1, end, index * 2 + 1, target, value)) % MOD;
        return seg[index];
    }
}