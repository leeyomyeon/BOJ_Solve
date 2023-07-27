import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Stack;

public class Main9019 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static boolean[] visited;
    public static Node[] track;
    public static Stack<Character> stack;
    public static final int MOD = 10000;
    public static ArrayDeque<Integer> deque;
    public static class Node {
        int next;
        char c;

        public Node(int next, char c) {
            this.next = next;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int T = fr.nextInt();
        for(int tc = 0; tc < T; tc++) {
            int start = fr.nextInt();
            int end = fr.nextInt();
            deque = new ArrayDeque<>();
            visited = new boolean[10000];
            track = new Node[10000];
            deque.add(start);
            while(!deque.isEmpty()) {
                int current = deque.removeFirst();
                if(current == end) {
                    stack = new Stack<>();
                    tracking(start, end);
                    while(!stack.isEmpty()) {
                        bw.write(stack.pop());
                    }
                    bw.newLine();
                    break;
                }
                // D
                int nk = (current << 1) % MOD;
                if(!visited[nk]) {
                    visited[nk] = true;
                    track[nk] = new Node(current, 'D');
                    deque.add(nk);
                }
                // S
                nk = (current + MOD - 1) % MOD;
                if(!visited[nk]) {
                    visited[nk] = true;
                    track[nk] = new Node(current, 'S');
                    deque.add(nk);
                }
                // L
                nk = (current % 1000) * 10 + (current / 1000);
                if(!visited[nk]) {
                    visited[nk] = true;
                    track[nk] = new Node(current, 'L');
                    deque.add(nk);
                }
                // R
                nk = (current % 10) * 1000 + (current / 10);
                if(!visited[nk]) {
                    visited[nk] = true;
                    track[nk] = new Node(current, 'R');
                    deque.add(nk);
                }
            }
        }
        bw.write("");
        bw.flush();
        bw.close();
    }
    public static void tracking(int value, int target) {
        if(target == value) {
            return;
        }
        stack.add(track[target].c);
        tracking(value, track[target].next);
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