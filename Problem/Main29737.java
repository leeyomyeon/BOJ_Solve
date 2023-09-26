import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main29737 {
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, R, C;
    public static class Streak implements Comparable<Streak>{
        String name;
        int lgStCnt;  //가장 긴 최장 스트릭
        int lgStFrzeCnt;    //최장 스트릭 내의 더 적은 개수의 스트릭 프리즈를 사용
        int startDay;   // 최장 스트릭을 시작한 날짜가 더 이른
        int failCnt;    // 스트릭 프리즈 사용한 날짜를 제외하고 스트릭 달성에 실패한 날짜 수가 적은
        public Streak(String name) {
            this.name = name;
            this.lgStCnt = Integer.MIN_VALUE;
            this.lgStFrzeCnt = Integer.MAX_VALUE;
            this.startDay = 0;
            this.failCnt = 0;
        }
        public int compareTo(Streak o) {
            if(o.lgStCnt == this.lgStCnt) {
                if(o.lgStFrzeCnt == this.lgStFrzeCnt) {
                    if(this.startDay == o.startDay) {
                        if(this.failCnt == o.failCnt) {
                            return this.name.compareTo(o.name);
                        }
                        return this.failCnt - o.failCnt;
                    }
                    return this.startDay - o.startDay;
                }
                return this.lgStFrzeCnt - o.lgStFrzeCnt;
            }
            return o.lgStCnt - this.lgStCnt;
        }
        public void setLgStCnt(int lgStCnt) {
            // 가장 긴 최장 스트릭
            this.lgStCnt = lgStCnt;
        }
        public void setLgStFrzeCnt(int lgStFrzeCnt) {
            // 최장 스트릭 내의 더 적은 개수의 스트릭 프리즈
            this.lgStFrzeCnt = lgStFrzeCnt;
        }
        public void setStartDay(int startDay) {
            this.startDay = startDay;
        }
        public void addFailCnt() {
            this.failCnt++;
        }
    }
    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        N = fr.nextInt();
        C = fr.nextInt();
        R = 7;
        ArrayList<Streak> list = new ArrayList<>();
        for(int n = 0; n < N; n++) {
            if(n > 0) {
                fr.read();
            }
            String name = fr.readLine();
            Streak streak = new Streak(name);
            int streakCnt = 0;
            int frzeCnt = 0;
            int startDay = Integer.MAX_VALUE;
            boolean isStreak = false;
            int lgStFrzeCnt = 0;
            char[][] streakStatus = new char[R][C];
            for(int r = 0; r < R; r++) {
                for(int c = 0; c < C; c++) {
                    streakStatus[r][c] = fr.nextChar();
                }
            }
            for(int c = 0; c < C; c++) { // 일
                for(int r = 0; r < R; r++) {    // 주
                    char status = streakStatus[r][c];
                    if(status == 'X') {
                        streak.addFailCnt();

                        if(streak.lgStCnt < streakCnt) {
                            streak.setLgStCnt(streakCnt);
                            streak.setLgStFrzeCnt(lgStFrzeCnt);
                            streak.setStartDay(startDay);
                        } else if(streak.lgStCnt == streakCnt && streak.lgStFrzeCnt > lgStFrzeCnt) {
                            streak.setLgStFrzeCnt(lgStFrzeCnt);
                            streak.setStartDay(startDay);
                        }
                        startDay = Integer.MAX_VALUE;
                        streakCnt = 0;
                        lgStFrzeCnt = 0;
                        frzeCnt = 0;
                        isStreak = false;
                    } else if(status == 'O') {
                        if(!isStreak) { // 스트릭 시작
                            isStreak = true;
                        } else {
                            lgStFrzeCnt += frzeCnt;
                            frzeCnt = 0;
                        }
                        if(startDay == Integer.MAX_VALUE) {
                            startDay = c * R + r + 1;
                        }
                        streakCnt++;
                    } else if(status == 'F' && isStreak) {
                        frzeCnt++;
                    }
                }
            }
            /*
            OOOOFFF 인 경우 OOOO도 최장 스트릭이 될 수 있으므로 S.F 미사용
            OFOFOOFFF > OFOFOO가 최장
             */
            if(streak.lgStCnt < streakCnt) {
                streak.setLgStFrzeCnt(lgStFrzeCnt);
                streak.setLgStCnt(streakCnt);
                streak.setStartDay(startDay);
            } else if(streak.lgStCnt == streakCnt && streak.lgStFrzeCnt > lgStFrzeCnt) {
                streak.setLgStFrzeCnt(lgStFrzeCnt);
                streak.setStartDay(startDay);
            }
            list.add(streak);
        }
        Collections.sort(list);
        int rank = 1;
        for(int i = 0; i < list.size(); i++) {
            Streak s = list.get(i);
            if(i >= 1) {
                Streak before = list.get(i - 1);
                if(before.lgStCnt != s.lgStCnt ||
                    before.lgStFrzeCnt != s.lgStFrzeCnt ||
                        before.startDay != s.startDay ||
                        before.failCnt != s.failCnt
                ) {
                    rank++;
                }
            }
            bw.write(Integer.toString(rank));
            bw.write(". ");
            bw.write(s.name);
            bw.newLine();
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
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
        }
        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
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