import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main13306 {
    // 오프라인 쿼리, Union-Find
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, Q;
    public static int[] arr, parent, result;
    public static ArrayList<ArrayList<Integer>> list;
    public static ArrayList<Query> queryList;
    public static class Query {
        int command, from, to;

        public Query(int command, int from, int to) {
            this.command = command;
            this.from = from;
            this.to = to;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        Q = fr.nextInt();
        arr = new int[N + 1];
        list = new ArrayList<>();
        result = new int[Q];
        for(int i = 0; i <= N; i++) {
            arr[i] = i;
            list.add(new ArrayList<>());
        }
        parent = new int[N + 1];
        for(int i = 2; i <= N; i++) {
            int from = fr.nextInt();    // 부모의 정점
            list.get(from).add(i);
            list.get(i).add(from);
            parent[i] = from;
        }
        queryList = new ArrayList<>();
        for(int i = 1; i <= Q + N - 1; i++) {
            int command = fr.nextInt();
            if(command == 1) {  // from과 to가 연결되어 있는지 확인
                int from = fr.nextInt();
                int to = fr.nextInt();
                Query q = new Query(command, from, to);
                queryList.add(q);
            } else {    // target의 부모와 target을 제거함
                int target = fr.nextInt();
                int p = parent[target];
                Query q = new Query(command, p, target);
                queryList.add(q);
            }
        }
        int idx = Q - 1;
        // result[idx]
        for(int i = queryList.size() - 1; i >= 0; i--) { // 쿼리를 거꾸로 수행
            Query current = queryList.get(i);
            if(current.command == 1) {
                int x = find(current.from);
                int y = find(current.to);
                result[idx--] = x == y ? 1 : 0;
            } else {
                int x = find(current.from);
                int y = find(current.to);
                if(x != y) {
                    union(current.from, current.to);
                }
            }
        }
        for(int k : result) {
            bw.write(k == 1 ? "YES" : "NO");
            bw.newLine();
        }
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
            return arr[target];
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