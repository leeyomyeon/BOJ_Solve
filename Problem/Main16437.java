import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main16437 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static long[] arr;
    public static boolean[] visited;
    public static int N;
    public static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        arr = new long[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 2; i <= N; i++) {
            char c = fr.nextChar();
            int w = fr.nextInt();
            int next = fr.nextInt();
            arr[i] = c == 'W' ? w * -1 : w;
            list.get(next).add(i);
        }
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1);
        bw.write(Long.toString(arr[1]));
        bw.flush();
        bw.close();
    }
    public static long dfs(int current) {
        for(int next : list.get(current)) {
            if(!visited[next]) {
                visited[next] = true;
                if(list.get(next).size() == 0) {
                    arr[current] += Math.max(arr[next], 0);
                } else {
                    arr[current] += dfs(next);
                }
            }
        }
        return Math.max(arr[current], 0);
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
        private char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
        }
    }
}