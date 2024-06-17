import java.io.*;
import java.util.*;

public class Main14426 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static class Trie {
        Node rootNode = new Node();

        void insert(String str) {
            Node node = this.rootNode;  // root부터 시작
            for(int i = 0; i < str.length(); i++) {
                if(node.child[str.charAt(i) - 'a'] == null) {
                    node.child[str.charAt(i) - 'a'] = new Node();
                }
                node = node.child[str.charAt(i) - 'a'];
            }
            node.finished = true;   // 맨 마지막 노드
        }
        boolean search(String str) {
            Node node = this.rootNode;  // root부터 시작
            for(int i = 0; i < str.length(); i++) {
                node = node.child[str.charAt(i) - 'a'];
                if(node == null) {  // 못 찾으면
                    return false;
                }
            }
            return true;    // 찾으면
        }
    }
    public static class Node {
        Node[] child;
        boolean finished;
        public Node() {
            this.child = new Node[26];
            this.finished = false;
        }
    }
    public static int N, M;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        M = fr.nextInt();
        Trie trie = new Trie();
        for(int i = 0; i < N; i++) {
            trie.insert(fr.readLine());
        }
        int res = 0;
        for(int i = 0; i < M; i++) {
            res += trie.search(fr.readLine()) ? 1 : 0;
        }
        bw.write(res + "");
        bw.flush();
        bw.close();
    }
    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 21;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[501]; // input line length
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
