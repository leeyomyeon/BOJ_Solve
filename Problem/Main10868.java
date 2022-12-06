import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 세그먼트 트리 최소값
public class Main10868 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, segTree;
    public static int MAX = 1000000001;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        segTree = new int[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        makeSegTree(1, N, 1);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int res = find(1, N, 1, a, b);
            bw.write(res + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {  // 최하단 노드에 도착했을 때는 배열값
            segTree[idx] = arr[start];
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        // 자식 노드의 최소값을 부모 노드가 저장
        segTree[idx] = Math.min(makeSegTree(start, mid, idx * 2), makeSegTree(mid + 1, end, idx * 2 + 1));
        return segTree[idx];
    }

    public static int find(int start, int end, int idx, int left, int right) {
        if(left > end || right < start) {
            // 범위 밖을 벗어날 땐 입력의 최대값 + 1
            return MAX;
        }
        if(left <= start && end <= right) {
            // 범위 내에 있을 땐 바로 최소값
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return Math.min(find(start, mid, idx * 2, left, right), find(mid + 1, end, idx * 2 + 1, left, right));
    }
}