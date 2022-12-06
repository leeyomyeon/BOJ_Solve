import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2174 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static Robot[] robot;
    public static int R, C, N, M;
    public static String res;
    public static int[][] arr;
    public static class Robot {
        int r, c, dir;
        public void setR(int r) {
            this.r = r;
        }
        public void setC(int c) {
            this.c = c;
        }
        public void setDir(int dir) {
            this.dir = dir;
        }
        public Robot(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[R + 1][C + 1];
        robot = new Robot[N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            int k = -1;
            // N E S W
            if(d == 'N') k = 0;
            else if(d == 'E') k = 1;
            else if(d == 'S') k = 2;
            else if(d == 'W') k = 3;
            robot[i] = new Robot(r, c, k);
            arr[r][c] = i;
        }
        res = "OK";
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int n = Integer.parseInt(st.nextToken());
            boolean res = move(idx, c, n);
            if(res) {
                break;
            }
        }
        bw.write(res);
        br.close();
        bw.flush();
        bw.close();
    }
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1 ,0};
    public static boolean move(int idx, char command, int n) {
        if(command == 'L') {
            robot[idx].setDir((robot[idx].dir + 4 + (-1 * n % 4)) % 4);
        } else if (command == 'R') {
            robot[idx].setDir((robot[idx].dir + (n % 4)) % 4);
        } else { // F
            int nr = -1;
            int nc = -1;
            for(int i = 1; i <= n; i++) {
                nr = robot[idx].r + dr[robot[idx].dir] * i;
                nc = robot[idx].c + dc[robot[idx].dir] * i;
                if(nr >= 1 && nr <= R && nc >= 1 && nc <= C) {
                    if(arr[nr][nc] > 0) {
                        res = "Robot " + idx + " crashes into robot " + arr[nr][nc];
                        return true;
                    }
                } else {
                    res = "Robot " + idx + " crashes into the wall";
                    return true;
                }
            }
            arr[robot[idx].r][robot[idx].c] = 0;
            robot[idx].setR(nr);
            robot[idx].setC(nc);
        }
        return false;
    }

}