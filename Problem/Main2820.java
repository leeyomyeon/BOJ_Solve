import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*
방문 순서
            1
      2            7
  3      6       8    10
4  5          9
상사-부하의 관계 Tree
            1
      3            6
  2      5       7    9
4  10          8
월급 Tree
            10
      8            5
  9      6       4    2
7  1          3

p 1 1 p 2 2 p 3 3 p 4 4 p 5 5 연산 후
            10
      9            6
  13    10       5    3
13   7         4
p 6 6 연산 후
            10
      9            6
  13     10     11   9
13   7        10

10 16
10
9 3
8 1
7 2
6 3
5 1
4 6
3 7
2 6
1 2
p 1 1 p 2 2 p 3 3 p 4 4 p 5 5
u 1 u 2 u 3 u 4 u 5
p 6 6
u 6 u 7 u 8 u 9 u 10
 */
public class Main2820 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, num;
    public static int[] origin, arr;
    public static ArrayList<ArrayList<Integer>> list;
    public static int[][] path;
    public static long[] segTree, lazy;

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        arr = new int[N + 1];
        origin = new int[N + 1];
        list = new ArrayList<>();
        path = new int[N + 1][2]; // [n][0] n번의 시작값, [n][1] n번의 끝값
        num = 1;    // 시작인덱스
        origin[1] = 1;
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        arr[1] = fr.nextInt();
        for (int i = 2; i <= N; i++) {
            arr[i] = fr.nextInt();
            int to = fr.nextInt();
            list.get(to).add(i);
        }

        makePath(1);
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        segTree = new long[1 << h];
        lazy = new long[1 << h];
        makeSegTree(1, N, 1);

        for(int q = 0; q < M; q++) {
            char c = fr.nextChar();
            if(c == 'p') {
                int a = fr.nextInt();
                int x = fr.nextInt();
                int l = path[a][0] + 1;
                int r = path[a][1];
                // a의 모든 부하(a 미포함) x만큼 상승/차감 가능
                update(1, N, 1, l, r, x);
            } else {
                int target = fr.nextInt();
                bw.write(find(1, N, 1, path[target][0])  + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
    public static void makePath(int current) {
        path[current][0] = num;
        for(int next : list.get(current)) {
            num++;
            origin[num] = next;
            makePath(next);
        }
        path[current][1] = num;
    }
    public static void makeSegTree(int start, int end, int idx) {
        if(start == end) {
            segTree[idx] = arr[origin[start]];  // 원 배열의 값
            return;
        }
        int mid = (start + end) / 2;
        makeSegTree(start, mid, idx * 2);
        makeSegTree(mid + 1, end, idx * 2 + 1);
    }
    public static void lazyPropagation(int start, int end, int idx) {
        if(lazy[idx] != 0) {
            if(start == end) {  // 구간의 합을 구하는게 아닌 target의 값만 얻으면 되니 맨 아래 노드에만 갱신
                segTree[idx] += lazy[idx];
            }
            if(start != end) {
                lazy[idx * 2] += lazy[idx];
                lazy[idx * 2 + 1] += lazy[idx];
            }
            lazy[idx] = 0;
        }
    }
    public static void update(int start, int end, int idx, int left, int right, int value) {
        lazyPropagation(start, end, idx);
        if(right < start || end < left) {
            return;
        }
        if(left <= start && end <= right) {
            if(start == end) { // target의 값만 얻으면 되니 맨 아래 노드에만 갱신
                segTree[idx] += value;
            }
            if(start != end) {
                lazy[idx * 2] += value;
                lazy[idx * 2 + 1] += value;
            }
            return;
        }
        int mid = (start + end) / 2;
        update(start, mid, idx * 2, left, right, value);
        update(mid + 1, end, idx * 2 + 1, left, right, value);
    }
    public static long find(int start, int end, int idx, int target) {
        lazyPropagation(start, end, idx);
        if(target < start || end < target) {
            return 0;
        }
        if(target <= start && end <= target) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        long l = find(start, mid, idx * 2, target);
        long r = find(mid + 1, end, idx * 2 + 1, target);
        return l + r;
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
        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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
