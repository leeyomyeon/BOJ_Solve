import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main9328 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static boolean[] key;
    public static char[][] arr;
    public static boolean[][][] visited;
    public static int keyCnt, R, C, res, totDocCnt;
    public static ArrayDeque<Point> deque;
    public static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            keyCnt = 0;
            res = 0;
            key = new boolean[26];
            arr = new char[R + 2][C + 2];
            visited = new boolean[27][R + 2][C + 2];
            totDocCnt = 0;
            for(int i = 0; i <= R + 1; i++) {
                Arrays.fill(arr[i], '.');
                if(i >= 1 && i <= R) {
                    String str = br.readLine();
                    for(int j = 1; j <= C; j++) {
                        arr[i][j] = str.charAt(j - 1);
                        if(arr[i][j] == '$') {
                            totDocCnt++;
                        }
                    }
                }
            }
            String keyStr = br.readLine();
            if(keyStr.charAt(0) != '0') {
                keyCnt = keyStr.length();
                for(int k = 0; k < keyCnt; k++) {
                    key[keyStr.charAt(k) - 'a'] = true;
                }
            }
            bfs();
            bw.write(res + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs() {
        // 0, 0부터 시작
        Point start = new Point(0, 0);
        visited[keyCnt][0][0] = true;
        deque = new ArrayDeque<>();
        deque.add(start);
        while(!deque.isEmpty()) {
            Point current = deque.removeFirst();
            if(res == totDocCnt) {
                break;
            }
            for(int d = 0; d < 4; d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr <= R + 1 && nc >= 0 && nc <= C + 1 && !visited[keyCnt][nr][nc] && arr[nr][nc] != '*') {
                    if(arr[nr][nc] >= 'A' && arr[nr][nc] <= 'Z' && key[arr[nr][nc] - 'A']) {
                        // 문이면서 해당 키가 있는경우 또는 빈 공간인 경우
                        arr[nr][nc] = '.';
                        visited[keyCnt][nr][nc] = true;
                        deque.add(new Point(nr, nc));
                    } else if(arr[nr][nc] >= 'a' && arr[nr][nc] <= 'z') {
                        // 키를 발견한 경우
                        if(!key[arr[nr][nc] - 'a']) {
                            // 키가 없을 경우 키 갯수 증가
                            keyCnt++;
                            visited[keyCnt][nr][nc] = true;
                            key[arr[nr][nc] - 'a'] = true;
                        } else {
                            visited[keyCnt][nr][nc] = true;
                        }
                        deque.add(new Point(nr, nc));
                        arr[nr][nc] = '.';
                    } else if(arr[nr][nc] == '$') {
                        visited[keyCnt][nr][nc] = true;
                        arr[nr][nc] = '.';
                        res++;
                        deque.add(new Point(nr, nc));
                    } else if(arr[nr][nc] == '.') {
                        visited[keyCnt][nr][nc] = true;
                        deque.add(new Point(nr, nc));
                    }
                }
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};
}