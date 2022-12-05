import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/* Segment Tree 구간합 */
public class Main2042 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int N, M, K;
    public static long[] arr, seg;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 수의 변경이 일어나는 횟수
        K = Integer.parseInt(st.nextToken());   // 구간합을 구하는 횟수
        arr = new long[N + 1];
        seg = new long[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        makeSegment(1, N, 1);
        for(int k = 0; k < M + K; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                // a가 1일땐 b번째 수를 c로 변경
                editSegment(1, N, 1, (int) b, c - arr[(int) b]);
                arr[(int) b] = c;
            } else {
                // a가 2일땐 b부터 c까지의 합 구함
                sb.append(find(1, N, 1, (int) b, (int) c)).append("\n");
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
        seg[index] = makeSegment(start, mid, index * 2) + makeSegment(mid + 1, end, index * 2 + 1);
        return seg[index];
    }
    public static long find(int start, int end, int index, int left, int right) {
        if(left > end || right < start) { // 범위 밖을 벗어난 경우
            return 0;
        }
        if(left <= start && right >= end) { // 범위 안에 들어오면
            return seg[index];
        }
        int mid = (start + end) / 2;
        return find(start, mid, index * 2, left, right) + find(mid + 1, end, index * 2 + 1, left, right);
    }
    public static void editSegment(int start, int end, int index, int target, long value) {
        if(target < start || target > end) { // 범위 밖을 벗어난 경우
            return;
        }
        // 범위 내에 있으면 value 값만큼 갱신
        seg[index] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        editSegment(start, mid, index * 2, target, value);// 왼쪽 갱신
        editSegment(mid + 1, end, index * 2 + 1, target, value);// 오른쪽 갱신 갱신
    }
}