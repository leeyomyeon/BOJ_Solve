import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1340 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static HashMap<String, Integer> map;
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        StringTokenizer st = new StringTokenizer(fr.readLine(), ", :");
        String strMonth = st.nextToken();
        int day = Integer.parseInt(st.nextToken()) - 1;
        int year = Integer.parseInt(st.nextToken());
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        boolean is = year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
        map = new HashMap<>();
        map.put("January", 1);
        map.put("February", 2);
        map.put("March", 3);
        map.put("April", 4);
        map.put("May", 5);
        map.put("June", 6);
        map.put("July", 7);
        map.put("August", 8);
        map.put("September", 9);
        map.put("October", 10);
        map.put("November", 11);
        map.put("December", 12);
        int month = map.get(strMonth);
        // 전체 = 일 * 24 * 60
        int totTime = (is ? 366 : 365) * 24 * 60;
        int curTime = 0;
        // 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        if(month >= 2) curTime += 31;
        if(month >= 3) curTime += (is ? 29 : 28);
        if(month >= 4) curTime += 31;
        if(month >= 5) curTime += 30;
        if(month >= 6) curTime += 31;
        if(month >= 7) curTime += 30;
        if(month >= 8) curTime += 31;
        if(month >= 9) curTime += 31;
        if(month >= 10) curTime += 30;
        if(month >= 11) curTime += 31;
        if(month >= 12) curTime += 30;
        curTime += day;
        curTime = curTime * 24 * 60;
        curTime += hour * 60 + minute;

        bw.write(String.format("%.10f", (double) curTime / totTime * 100));
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
