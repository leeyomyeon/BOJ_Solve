import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main13460 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, endR, endC, res;
    public static char[][] arr;
    public static boolean[][] redVisited, blueVisited;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int redR, redC, blueR, blueC, dir;

        public void setRedR(int redR) {
            this.redR = redR;
        }
        public void setRedC(int redC) {
            this.redC = redC;
        }
        public void setBlueR(int blueR) {
            this.blueR = blueR;
        }
        public void setBlueC(int blueC) {
            this.blueC = blueC;
        }

        public Point(int redR, int redC, int blueR, int blueC, int dir) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Point start = new Point(0, 0, 0, 0, -1);
        arr = new char[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'O') {
                    arr[i][j] = '.';
                    endR = i;
                    endC = j;
                } else if(arr[i][j] == 'R') {
                    arr[i][j] = '.';
                    start.setRedR(i);
                    start.setRedC(j);
                } else if(arr[i][j] == 'B') {
                    arr[i][j] = '.';
                    start.setBlueR(i);
                    start.setBlueC(j);
                }
            }
        }
        redVisited = new boolean[R][C];
        blueVisited = new boolean[R][C];
        res = -1;
        move(start);
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void move(Point start) {
        // 왔던 방향, 반대방향은 갈 필요 없음
        deque = new ArrayDeque<>();
        deque.add(start);
        redVisited[start.redR][start.redC] = true;
        blueVisited[start.blueR][start.blueC] = true;
        for(int turn = 1; turn <= 10; turn++) {
            int size = deque.size();
            for(int k = 0; k < size; k++) {
                Point current = deque.removeFirst();
                int[] next = current.dir == -1 ? new int[]{0, 1, 2, 3} : nextDir[current.dir % 2];
                for(int d : next) {
                    int redMove = 0;
                    int blueMove = 0;
                    int nRedR = current.redR;
                    int nRedC = current.redC;
                    int nBlueR = current.blueR;
                    int nBlueC = current.blueC;
                    boolean firstRed = (current.redR < current.blueR && d == 0) || (current.redC > current.blueC && d == 1) ||
                            (current.redR > current.blueR && d == 2) || (current.redC < current.blueC && d == 3);   // 우선순위
                    boolean redGoal = false;
                    boolean blueGoal = false;
                    while (redMove != -1 || blueMove != -1) {
                        // 우선순위 정하기
                        if (firstRed) {
                            if (redMove != -1) {
                                redMove++;
                                nRedR = current.redR + dr[d] * redMove;
                                nRedC = current.redC + dc[d] * redMove;
                                if (arr[nRedR][nRedC] == '#') {
                                    redMove = -1;
                                    nRedR += dr[d] * -1;
                                    nRedC += dc[d] * -1;
                                } else if(nRedR == endR && nRedC == endC) {
                                    redMove = -1;
                                    nRedR = 0;
                                    nRedC = 0;
                                    redGoal = true;
                                }
                            }
                            if (blueMove != -1) {
                                blueMove++;
                                nBlueR = current.blueR + dr[d] * blueMove;
                                nBlueC = current.blueC + dc[d] * blueMove;
                                if (arr[nBlueR][nBlueC] == '#' || nBlueR == nRedR && nBlueC == nRedC) {
                                    blueMove = -1;
                                    nBlueR += dr[d] * -1;
                                    nBlueC += dc[d] * -1;
                                } else if(nBlueR == endR && nBlueC == endC) {
                                    blueMove = -1;
                                    nBlueR = 0;
                                    nBlueC = 0;
                                    blueGoal = true;
                                }
                            }
                        } else {
                            if (blueMove != -1) {
                                blueMove++;
                                nBlueR = current.blueR + dr[d] * blueMove;
                                nBlueC = current.blueC + dc[d] * blueMove;
                                if (arr[nBlueR][nBlueC] == '#') {
                                    blueMove = -1;
                                    nBlueR += dr[d] * -1;
                                    nBlueC += dc[d] * -1;
                                } else if(nBlueR == endR && nBlueC == endC) {
                                    nBlueR = 0;
                                    nBlueC = 0;
                                    blueMove = -1;
                                    blueGoal = true;
                                }
                            }
                            if (redMove != -1) {
                                redMove++;
                                nRedR = current.redR + dr[d] * redMove;
                                nRedC = current.redC + dc[d] * redMove;
                                if (arr[nRedR][nRedC] == '#' || nRedR == nBlueR && nRedC == nBlueC) {
                                    redMove = -1;
                                    nRedR += dr[d] * -1;
                                    nRedC += dc[d] * -1;
                                } else if(nRedR == endR && nRedC == endC) {
                                    nRedR = 0;
                                    nRedC = 0;
                                    redMove = -1;
                                    redGoal = true;
                                }
                            }
                        }
                    }
                    if(redGoal && !blueGoal) {
                        res = turn;
                        return;
                    }
                    if((nRedR != current.redR || nRedC != current.redC || nBlueR != current.blueR || nBlueC != current.blueC) && !blueGoal) {
                        deque.add(new Point(nRedR, nRedC, nBlueR, nBlueC, d));
                    }
                }
            }
        }
    }
    // 상 우 하 좌
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    public static int[][] nextDir = {{1, 3}, {0, 2}};
}