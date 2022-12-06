import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17470 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int R, C, K, rotate;
    public static class Point {
        int r;
        int c;
        public Point(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static int[][] arr;
    public static Point[][] move;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rotate = 0;
        arr = new int[R][C];
        move = new Point[4][4];
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        setMove();
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; k++) {
            int command = Integer.parseInt(st.nextToken());
            if(command == 1) {
                moveTopDown();
            } else if(command == 2) {
                moveLeftRight();
            } else if(command == 3) {
                rotate++;
                moveRotate(1);
            } else if(command == 4) {
                rotate++;
                moveRotate(-1);
            } else if(command == 5) {
                movePartRotate(1);
            } else if(command == 6) {
                movePartRotate(-1);
            }
        }
        printArr();
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    public static void setMove() {
        move[0][0] = new Point(0, 0);
        move[0][1] = new Point(0, C/2-1);
        move[0][2] = new Point(0, C/2);
        move[0][3] = new Point(0, C-1);
        move[1][0] = new Point(R/2-1, 0);
        move[1][1] = new Point(R/2-1, C/2-1);
        move[1][2] = new Point(R/2-1, C/2);
        move[1][3] = new Point(R/2-1, C-1);
        move[2][0] = new Point(R/2, 0);
        move[2][1] = new Point(R/2, C/2-1);
        move[2][2] = new Point(R/2, C/2);
        move[2][3] = new Point(R/2, C-1);
        move[3][0] = new Point(R-1, 0);
        move[3][1] = new Point(R-1, C/2-1);
        move[3][2] = new Point(R-1, C/2);
        move[3][3] = new Point(R-1, C-1);
    }
    public static void moveTopDown() {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
                Point tmp = move[1 - i][j];
                move[1 - i][j] = move[2 + i][j];
                move[2 + i][j] = tmp;
            }
        }
    }
    public static void moveLeftRight() {
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 4; j++) {
                Point tmp = move[j][1 - i];
                move[j][1 - i] = move[j][2 + i];
                move[j][2 + i] = tmp;
            }
        }
    }
    public static void moveRotate(int r) {
        Point[][] tmpArr = new Point[4][4];
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                tmpArr[i][j] = r == 1 ? move[3 - j][i] : move[j][3 - i];
            }
        }
        move = tmpArr;
    }
    public static void movePartRotate(int r) {
        Point[][] tmp = new Point[2][2];
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                tmp[i][j] = move[i][j];
            }
        }
        if(r == 1) {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    move[i][j] = move[2 + i][j];
                }
            }// 4 -> 1
            for(int i = 2; i < 4; i++) {
                for(int j = 0; j < 2; j++) {
                    move[i][j] = move[i][2 + j];
                }
            }// 3 -> 4
            for(int i = 2; i < 4; i++) {
                for(int j = 2; j < 4; j++) {
                    move[i][j] = move[i - 2][j];
                }
            }// 2 -> 3
            for(int i = 0; i < 2; i++) {
                for(int j = 2; j < 4; j++) {
                    move[i][j] = tmp[i][j - 2];
                }
            }// 3 -> 1
        } else {
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 2; j++) {
                    move[i][j] = move[i][j + 2];
                }
            }// 2 -> 1
            for(int i = 0; i < 2; i++) {
                for(int j = 2; j < 4; j++) {
                    move[i][j] = move[2 + i][j];
                }
            }// 3 -> 2
            for(int i = 2; i < 4; i++) {
                for(int j = 2; j < 4; j++) {
                    move[i][j] = move[i][j - 2];
                }
            }// 4 -> 3
            for(int i = 2; i < 4; i++) {
                for(int j = 0; j < 2; j++) {
                    move[i][j] = tmp[i - 2][j];
                }
            }// 1 -> 4
        }
    }
    public static void printArr() {
        for(int i = 0; i < 4; i += 2) {
            int startR1 = move[i][0].r;
            int startC1 = move[i][0].c;
            int endR1 = move[i + 1][1].r;
            int endC1 = move[i + 1][1].c;
            int startR2 = move[i][2].r;
            int startC2 = move[i][2].c;
            int endR2 = move[i + 1][3].r;
            int endC2 = move[i + 1][3].c;
            int dr1, dc1, dr2, dc2;
            if(startR1 > endR1) { dr1 = -1; }
            else { dr1 = 1; }
            if(startC1 > endC1) { dc1 = -1; }
            else { dc1 = 1; }
            if(startR2 > endR2) { dr2 = -1; }
            else { dr2 = 1; }
            if(startC2 > endC2) { dc2 = -1; }
            else { dc2 = 1; }
            int r1 = startR1;
            int r2 = startR2;
            int c1 = startC1;
            int c2 = startC2;
            int cnt = 0;
            if(rotate % 2 == 0) {
                while(cnt <= Math.abs(startR1 - endR1)) {
                    for(int c = startC1; c != endC1 + dc1; c += dc1) {
                        sb.append(arr[r1][c]).append(" ");
                    }
                    for(int c = startC2; c != endC2 + dc2; c += dc2) {
                        sb.append(arr[r2][c]).append(" ");
                    }
                    sb.append("\n");
                    r1 += dr1;
                    r2 += dr2;
                    cnt++;
                }
            } else {
                while(cnt <= Math.abs(startC1 - endC1)) {
                    for(int r = startR1; r != endR1 + dr1; r += dr1) {
                        sb.append(arr[r][c1]).append(" ");
                    }
                    for(int r = startR2; r != endR2 + dr2; r += dr2) {
                        sb.append(arr[r][c2]).append(" ");
                    }
                    sb.append("\n");
                    c1 += dc1;
                    c2 += dc2;
                    cnt++;
                }
            }
        }
    }
}