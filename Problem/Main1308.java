import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main1308 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        int startY = fr.nextInt();
        int startM = fr.nextInt();
        int startD = fr.nextInt();
        int endY = fr.nextInt();
        int endM = fr.nextInt();
        int endD = fr.nextInt();
        if((endY - startY) > 1000 || (endY - startY == 1000 && endM > startM) || (endY - startY == 1000 && endM == startM && endD >= startD)) {
            bw.write("gg");
        } else {
            // 1년 1월 1일부터 startY, M, D 까지의 날짜계산
            int totStartCntDay = countDay(startY, startM, startD);
            int totEndCntDay = countDay(endY, endM, endD);
            bw.write("D-" + (totEndCntDay - totStartCntDay));
        }
        bw.flush();
        bw.close();
    }
    public static int countDay(int targetYear, int targetMonth, int targetDay) {
        boolean isLeap = targetYear % 400 == 0 || (targetYear % 4 == 0 && targetYear % 100 != 0);
        int[] day = {31, isLeap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int totDay = 0;
        for(int i = 1; i < targetYear; i++) {
            totDay += i % 400 == 0 || (i % 4 == 0 && i % 100 != 0) ? 366 : 365;
        }
        for(int i = 0; i < targetMonth - 1; i++) {
            totDay += day[i];
        }
        return totDay + targetDay;
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
