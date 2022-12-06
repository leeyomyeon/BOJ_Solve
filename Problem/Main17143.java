import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17143 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static LinkedList<Point>[][] list;
    public static int R, C, M, sum;
    public static class Point {
        int r;
        int c;
        int s;  // 속력
        int d;  // 이동방향
        int z;  // 크기
        public void setR(int r) {
            this.r = r;
        }
        public void setC(int c) {
            this.c = c;
        }
        public void setD(int d) {
            this.d = d;
        }
        public Point(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new LinkedList[R + 1][C + 1];
        for(int i = 0; i <= R; i++) {
            for(int j = 0; j <= C; j++) {
                list[i][j] = new LinkedList<>();
            }
        }
        sum = 0;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list[r][c].add(new Point(r, c, s, d, z));
        }
        // 낚시왕 이동
        for(int j = 1; j <= C; j++) {
            // 가까운 상어 잡고
            for(int i = 1; i <= R; i++) {
                if(list[i][j].size() >= 1) {
                    Point p = list[i][j].poll();
                    sum += p.z;
                    break;
                }
            }
            // 상어 이동
            Queue<Point> queue = new LinkedList<>();
            for(int k = 1; k <= R; k++) {
                for(int l = 1; l <= C; l++) {
                    if(list[k][l].size() >= 1) {
                        Point p = list[k][l].poll();
                        // s 속력 
                        // d 이동방향1 위 2 아래 3 오른쪽 4 왼쪽
                        if(p.d == 1) {
                            int ns = p.s + (R - p.r);
                            int m = ns / (R - 1);
                            int nr = ns % (R - 1);
                            if(m % 2 == 0) {
                                p.setR(R - nr);
                            } else {
                                p.setR(1 + nr);
                                p.setD(2);
                            }
                        } else if (p.d == 2) {
                            int ns = p.s + (p.r - 1);
                            int m = ns / (R - 1);
                            int nr = ns % (R - 1);
                            if(m % 2 == 0) {
                                p.setR(1 + nr);
                            } else {
                                p.setR(R - nr);
                                p.setD(1);
                            }
                        } else if (p.d == 3) {
                            int ns = p.s + (p.c - 1);
                            int m = ns / (C - 1);
                            int nc = ns % (C - 1);
                            if(m % 2 == 0) {
                                p.setC(1 + nc);
                            } else {
                                p.setC(C - nc);
                                p.setD(4);
                            }
                        } else if (p.d == 4) {
                            int ns = p.s + (C - p.c);
                            int m = ns / (C - 1);
                            int nc = ns % (C - 1);
                            if(m % 2 == 0) {
                                p.setC(C - nc);
                            } else {
                                p.setC(1 + nc);
                                p.setD(3);
                            }
                        }
                        queue.add(p);
                    }
                }
            }
            while(!queue.isEmpty()) {
                Point p = queue.poll();
                list[p.r][p.c].add(p);
            }
            // 상어잡아먹기
            for(int k = 1; k <= R; k++) {
                for(int l = 1; l <= C; l++) {
                    if(list[k][l].size() >= 2) {
                        Collections.sort(list[k][l], (Point o1, Point o2) -> {
                            return o2.z - o1.z;
                        });
                        Point p = list[k][l].poll();
                        list[k][l].clear();
                        list[k][l].add(p);
                    }
                }
            }
        }
        bw.write(Integer.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}