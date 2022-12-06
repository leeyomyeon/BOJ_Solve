import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14438 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr, segTree;
    public static int MAX = 1000000001;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        segTree = new int[N * 4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        makeSegTree(1, N, 1);
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(command == 1) { // update
                arr[a] = b;
                updateSegTree(1, N, 1, a, b);
            } else {    // find
                int res = find(1, N, 1, a, b);
                sb.append(res).append("\n");
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static int makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return segTree[idx];
        }

        int mid = (start + end) / 2;
        segTree[idx] = Math.min(makeSegTree(start, mid, idx * 2), makeSegTree(mid + 1, end, idx * 2 + 1));
        return segTree[idx];
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(end < left || right < start) {
            return MAX;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return Math.min(find(start, mid, idx * 2, left, right), find(mid + 1, end, idx * 2 + 1, left, right));
    }
    public static int updateSegTree(int start, int end, int idx, int target, int value) {
        if (target < start || target > end) {
            return segTree[idx];
        }
        if(start == end) {
            segTree[idx] = value;
            return segTree[idx];
        }

        int mid = (start + end) / 2;
        segTree[idx] = Math.min(updateSegTree(start, mid, idx * 2, target, value), updateSegTree(mid + 1, end, idx * 2 + 1, target, value));
        return segTree[idx];
    }
}