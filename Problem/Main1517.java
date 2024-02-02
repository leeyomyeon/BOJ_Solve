import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main1517 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static int[] arr, segTree;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new int[N + 1];
        HashMap<Integer, Queue<Integer>> map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            arr[i] = fr.nextInt();
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], new LinkedList<>());
                map.get(arr[i]).add(i);
            } else {
                map.get(arr[i]).add(i);
            }
        }
        segTree = new int[N * 4];
        Arrays.sort(arr);
        long sum = 0;
        for(int i = 1; i <= N; i++) {
            if(map.containsKey(arr[i]) && !map.get(arr[i]).isEmpty()) {
                int idx = map.get(arr[i]).poll();
                sum += find(1, N, 1, idx, N);;
                update(1, N, 1, idx, 1);
            }
        }
        bw.write(Long.toString(sum));
        bw.flush();
        bw.close();
    }
    public static int find(int start, int end, int idx, int left, int right) {
        if(right < start || end < left) {
            return 0;
        }
        if(left <= start && end <= right) {
            return segTree[idx];
        }
        int mid = (start + end) / 2;
        return find(start, mid, idx * 2, left, right) + find(mid + 1, end, idx * 2 + 1, left, right);
    }
    public static void update(int start, int end, int idx, int target, int value) {
        if(target < start || end < target) {
            return;
        }
        if(start == end) {
            segTree[idx] = value;
            return;
        }
        int mid = (start + end) / 2;
        if(target <= mid) {
            update(start, mid, idx * 2, target, value);
        } else {
            update(mid + 1, end, idx * 2 + 1, target, value);
        }
        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1];
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
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
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