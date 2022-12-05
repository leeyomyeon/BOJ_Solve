import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1063 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int N;
    public static HashMap<String, int[]> map;
    public static class Point {
        int r;
        int c;
        public void setR(int r) {
            this.r = r;
        }
        public void setC(int c) {
            this.c = c;
        }
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        init();
        String K = st.nextToken();
        Point locK = new Point(K.charAt(0) - 'A' + 1, K.charAt(1) - '0');
        String S = st.nextToken();
        Point locS = new Point(S.charAt(0) - 'A' + 1, S.charAt(1) - '0');
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            String command = br.readLine();
            int[] move = map.get(command);
            int knr = locK.r + move[0];
            int knc = locK.c + move[1];
            if(knr >= 1 && knr <= 8 && knc >= 1 && knc <= 8) {  // 체스판 내부 이동
               if(knr == locS.r && knc == locS.c) { // 이동하려는 위치에 돌이 있을때
                   int snr = locS.r + move[0];
                   int snc = locS.c + move[1];
                   if(snr >= 1 && snr <= 8 && snc >= 1 && snc <= 8) {
                       // 돌이 경계면 벗어나지 않으면 돌 이동
                       locS.setR(snr);
                       locS.setC(snc);
                   } else {
                       // 돌이 경계면 이동하면 해당 커맨드 무시
                       continue;
                   }
               }
               locK.setR(knr);
               locK.setC(knc);
            }
        }

        sb.append((char) (locK.r - 1 + 'A')).append(locK.c).append("\n");
        sb.append((char) (locS.r - 1 + 'A')).append(locS.c).append("\n");
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void init() {
        map = new HashMap<>();
        int[][] tmp = {{1, 0}, {-1, 0}, {0, -1}, {0, 1},
                       {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        String[] cmd = {"R", "L", "B", "T", "RT", "LT", "RB", "LB"};
        for(int i = 0; i < 8; i++) {
            map.put(cmd[i], tmp[i]);
        }
    }
}