import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main16978 {
    // 세그먼트 트리, 오프라인 쿼리
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M;
    public static int[] arr;
    public static long[] segTree;
    public static ArrayList<Query> updateList;
    public static ArrayList<Query> queryList;
    public static class Query {
        int num, a, b, c;
        long res;
        public Query(int num, int a, int b, int c) {
            this.num = num; // 계산 순서
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public void setRes(long res) {
            this.res = res;
        }
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        segTree = new long[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
        }
        makeSegTree(1, N, 1);
        M = fr.nextInt();
        updateList = new ArrayList<>();
        queryList = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            int command = fr.nextInt();
            if(command == 1) {
                updateList.add(new Query(i + 1, fr.nextInt(), fr.nextInt(), -1));
            } else {
                queryList.add(new Query(i + 1, fr.nextInt(), fr.nextInt(), fr.nextInt()));
            }
        }

        queryList.sort(Comparator.comparingInt(o -> o.a));
        int idx = 0;
        for (Query query : queryList) {
            if(query.a > idx) {
                for (int j = idx; j < query.a; j++) {
                    Query updateQuery = updateList.get(j);
                    update(1, N, 1, updateQuery.a, updateQuery.b - arr[updateQuery.a]);
                    arr[updateQuery.a] = updateQuery.b;
                }
                idx = query.a;
            }
            long res = find(1, N, 1, query.b, query.c);
            query.setRes(res);
        }
        queryList.sort(Comparator.comparingInt(o -> o.num));
        for(Query query : queryList) {
            bw.write(query.res + "\n");
        }
        bw.flush();
        bw.close();
    }
    /*
5
1 2 3 4 5
7
1 2 5
2 0 1 3
2 1 1 3
1 4 2
2 0 2 5
2 1 2 5
2 2 2 5

6
9
14
17
15
     */
    public static long makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[start];
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        long left = makeSegTree(start, mid, idx * 2);
        long right = makeSegTree(mid + 1, end, idx * 2 + 1);
        return segTree[idx] = left + right;
    }   // 세그먼트 트리 초기값 생성
    public static void update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return;
        }
        segTree[idx] += value;
        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, target, value);
        update(mid + 1, end, idx * 2 + 1, target, value);
    }
    public static long find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 16;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // input line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
    }
}