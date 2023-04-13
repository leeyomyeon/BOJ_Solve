import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main11085 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, C, V, res;
    public static int[] arr;
    public static ArrayList<Node> list;
    public static class Node implements Comparable<Node> {
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return o.weight - this.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();   // 정점 수
        M = fr.nextInt();   // 경로 수
        C = fr.nextInt();   // b-w 수도
        V = fr.nextInt();   // c-w 수도
        arr = new int[N];
        list = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            arr[i] = i; // 집합 초기화
        }
        for(int i = 0; i < M; i++) {
            int from = fr.nextInt();
            int to = fr.nextInt();
            int weight = fr.nextInt();
            list.add(new Node(from, to, weight));
        }
        /*
            C와 V가 최대 크기로 연결되었을 때 연결된 길 중에서 가장 작은 길을 찾는것
         */
        Collections.sort(list); // 가중치가 큰 것부터 연결
        for(Node current : list) {
            union(current.from, current.to);
            if(find(C) == find(V)) {    // C와 V가 연결되는 순간이 가장 가중치가 작은 길이 있음
                res = current.weight;
                break;
            }
        }
        bw.write(Integer.toString(res));
        bw.flush();
        bw.close();
    }
    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if(x != y) {
            arr[y] = x;
        }
    }
    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
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
