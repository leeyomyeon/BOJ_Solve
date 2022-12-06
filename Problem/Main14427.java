import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14427 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static Node[] arr, segTree;
    public static class Node {
        int num, idx;
        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new Node[N + 1];
        segTree = new Node[N * 4];
        for(int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = new Node(num, i);
        }
        makeSegTree(1, N, 1);
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if(command == 2) { // find
                Node res = segTree[1];
                sb.append(res.idx).append("\n");
            } else {    // update
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a] = new Node(b, a);
                update(1, N, 1, arr[a]);
            }
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static Node makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        Node left = makeSegTree(start, mid, idx * 2);
        Node right = makeSegTree(mid + 1, end, idx * 2 + 1);
        if(left.num < right.num) {
            segTree[idx] = left;
        } else if(left.num > right.num) {
            segTree[idx] = right;
        } else {
            if(left.idx < right.idx) {
                segTree[idx] = left;
            } else {
                segTree[idx] = right;
            }
        }
        return segTree[idx];
    }
    public static Node update(int start, int end, int idx, Node node) {
        if(node.idx < start || node.idx > end) {
            return segTree[idx];
        }
        if(start == end) {
            segTree[idx] = node;
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        Node left = update(start, mid, idx * 2, node);
        Node right = update(mid + 1, end, idx * 2 + 1, node);
        if(left.num < right.num) {
            segTree[idx] = left;
        } else if(left.num > right.num) {
            segTree[idx] = right;
        } else {
            if(left.idx < right.idx) {
                segTree[idx] = left;
            } else {
                segTree[idx] = right;
            }
        }
        return segTree[idx];
    }
}