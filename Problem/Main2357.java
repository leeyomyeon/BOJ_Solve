import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/* minSegment Tree 최대/최소값 */
public class Main2357 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, minSeg, maxSeg;
    public static int MAX = 1000000001;
    public static int MIN = 0;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        minSeg = new int[N * 4];
        maxSeg = new int[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        makeMinSegTree(1, N, 1);
        makeMaxSegTree(1, N, 1);
        // 쿼리 수행
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = minQuery(1, 1, N, a, b);
            int max = maxQuery(1, 1, N, a, b);
            bw.write(min + " " + max + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static int makeMinSegTree(int start, int end, int idx) {
        // 자식 노드들의 최소값을 가지고있음.
        if(start == end) {
            minSeg[idx] = arr[start];
            return minSeg[idx];
        }
        int mid = (start + end) / 2;
        // 현재 노드 기준 왼쪽 노드에서의 최소값, 오른쪽 노드에서의 최소값을 찾음
        // 현재 노드 기준 왼쪽 자식노드는 부모 노드 * 2, 오른쪽 자식 노드는 부모 노드 * 2 + 1임
        minSeg[idx] = Math.min(makeMinSegTree(start, mid, 2 * idx), makeMinSegTree(mid + 1, end, 2 * idx + 1));
        return minSeg[idx];
    }
    public static int makeMaxSegTree(int start, int end, int idx) {
        // 자식 노드들의 최대값 가지고있음.
        if(start == end) {
            maxSeg[idx] = arr[start];
            return maxSeg[idx];
        }
        int mid = (start + end) / 2;
        // 현재 노드 기준 왼쪽 노드에서의 최대값, 오른쪽 노드에서의 최대값을 찾음
        // 현재 노드 기준 왼쪽 자식노드는 부모 노드 * 2, 오른쪽 자식 노드는 부모 노드 * 2 + 1임
        maxSeg[idx] = Math.max(makeMaxSegTree(start, mid, 2 * idx), makeMaxSegTree(mid + 1, end, 2 * idx + 1));
        return maxSeg[idx];
    }
    public static int minQuery(int idx, int start, int end, int left, int right) {
        if(end < left || right < start) {
            // 찾는 구간이 탐색 범위를 넘어설 경우
            return MAX;
        }
        if(left <= start && end <= right) {
            // 찾는 구간 내에 노드가 존재하면
            return minSeg[idx];
        }
        int mid = (start + end) / 2;
        // 왼쪽 구간의 정보와 오른쪽 구간의 정보를 재귀적으로 찾음
        return Math.min(minQuery(2 * idx, start, mid, left, right), minQuery(2 * idx + 1, mid + 1, end, left, right));
    }
    public static int maxQuery(int idx, int start, int end, int left, int right) {
        if(end < left || right < start) {
            // 찾는 구간이 탐색 범위를 넘어설 경우
            return MIN;
        }
        if(left <= start && end <= right) {
            // 찾는 구간 내에 노드가 존재하면
            return maxSeg[idx];
        }
        int mid = (start + end) / 2;
        // 왼쪽 구간의 정보와 오른쪽 구간의 정보를 재귀적으로 찾음
        return Math.max(maxQuery(2 * idx, start, mid, left, right), maxQuery(2 * idx + 1, mid + 1, end, left, right));
    }
}