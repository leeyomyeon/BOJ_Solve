import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2933 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, N;
    public static char[][] arr;
    public static boolean[][] visited;
    public static ArrayDeque<Point> deque;
    public static ArrayList<ArrayList<Point>> downList;
    public static class Point implements Comparable<Point> {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Point o) {
            if(o.r == this.r) {
                return o.c - this.c;
            } else {
                return o.r - this.r;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int target = R - Integer.parseInt(st.nextToken()); // 시작 위치 arr[target][?]
            // i % 2 == 0 왼쪽 else 오른쪽
            int start = i % 2 == 0 ? 0 : C - 1;
            int add = i % 2 == 0 ? 1 : - 1;
            for(int j = 0; j < C; j++) {
                int next = start + (add * j);
                if(arr[target][next] == 'x') { // 미네랄이 있으면 빈칸으로 만듬
                    arr[target][next] = '.';
                    mineral();
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void mineral() { // 공중에 떠있는 미네랄 찾기
        deque = new ArrayDeque<>();
        visited = new boolean[R][C];
        downList = new ArrayList<>();
        int cnt = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(!visited[i][j] && arr[i][j] == 'x') {
                    downList.add(new ArrayList<>());
                    deque.add(new Point(i, j));
                    visited[i][j] = true;
                    boolean flag = false;
                    downList.get(cnt).add(new Point(i, j));
                    while(!deque.isEmpty()) {
                        Point current = deque.removeFirst();
                        for(int d = 0; d < 4; d++) {
                            int nr = current.r + dr[d];
                            int nc = current.c + dc[d];
                            if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && arr[nr][nc] == 'x') {
                                visited[nr][nc] = true;
                                deque.add(new Point(nr, nc));
                                downList.get(cnt).add(new Point(nr, nc));
                                if(nr == R - 1) {
                                    flag = true;
                                }
                            }
                        }
                    }
                    if(flag) {
                        downList.get(cnt).clear();
                    }
                    cnt++;
                }
            }
        }
        for(ArrayList<Point> list : downList) {
            if(!list.isEmpty()) {
                isDown(list);
            }
        }
    }
    public static void isDown(ArrayList<Point> list) {
        Collections.sort(list);
        for(Point p : list) {
            arr[p.r][p.c] = '.';
        }
        int i = 1;
        Loop1:
        while(true) { // 한칸씩 내려가기
            for(Point p : list) {
                if(p.r + i == R || arr[p.r + i][p.c] == 'x') {
                    break Loop1;
                }
            }
            i++;
        }
        for(Point p : list) {
            arr[p.r + i - 1][p.c] = 'x';
        }
    }

    public static int[] dr = {-1, 1, 0 ,0};
    public static int[] dc = {0, 0, -1, 1};
}
