import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main26071 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, MIN;
    public static HashSet<Integer> setR, setC;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        int minR = N;
        int minC = N;
        int maxR = 0;
        int maxC = 0;
        setR = new HashSet<>(); // 가로칸에 몇개씩 차지하는지
        setC = new HashSet<>(); // 세로칸에 몇개씩 차지하는지
        int num = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int c = fr.nextCharToInt();
                if(c == 6) {
                    num++;
                    setR.add(i);
                    setC.add(j);
                    minR = Math.min(i, minR);
                    minC = Math.min(j, minC);
                    maxR = Math.max(i, maxR);
                    maxC = Math.max(j, maxC);
                }
            }
        }
        if(num == 1) {
            bw.write("0");
        } else {
            MIN = Integer.MAX_VALUE;
            for(int k = 0; k < 4; k++) {// 일단 한쪽방향으로 다 모아보기
                int cnt = 0;
                // k = 0 위로 maxR 만큼 이동 필수
                // k = 1 오른쪽 N - minC - 1 만큼 이동 필수
                // k = 2 아래 N - minR 만큼 이동 필수
                // k = 3 왼쪽 maxC 만큼 이동 필수
                if(k == 0) {
                    cnt += maxR;
                } else if(k == 1) {
                    cnt += (N - minC - 1);
                } else if(k == 2) {
                    cnt += (N - minR - 1);
                } else {
                    cnt += maxC;
                }
                if(k % 2 == 0) {
                    // 위 또는 아래로 다 밀었을 때 setC에 들어있는 행의 개수만큼 남음
                    // ..G...G.G.... 오른쪽으로 이동 시 10 N - 1 - minC
                    // ..G...G.G.... 왼쪽으로 이동 시 8칸 maxC
                    if(setC.size() != 1) {
                        // 일렬로 되어 있으면 더할 필요 없음. 이미 위/아래로 이동 시 하나로 합쳐졌기 때문
                        cnt += Math.min(maxC, N - 1 - minC);
                    }
                } else {
                    // 왼 또는 오른으로 다 밀었을 때 setR에 들어있는 행의 개수만큼 남음
                    if(setR.size() != 1) {
                        cnt += Math.min(maxR, N - 1 - minR);
                    }
                }
                MIN = Math.min(cnt, MIN);
            }
            bw.write(Integer.toString(MIN));
        }
        bw.flush();
        bw.close();
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

        public int nextCharToInt() throws IOException {
            byte c = read();
            if (c == '\n') {
                c = read();
            }
            return c - 'A';
        }
    }
}