import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main2931 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static char[][] arr;
    public static char[] blockChar = {'|', '-', '+', '1', '2', '3', '4'};
    public static boolean[][] visited;
    public static int R, C;
    public static Queue<Point> queue;
    public static HashMap<Character, int[][]> map;
    public static Point start, end;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        initMap();
        arr = new char[R + 2][C + 2];
        visited = new boolean[R + 2][C + 2];
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
                if(arr[i][j] == 'M') {
                    start = new Point(i, j);
                }
                if(arr[i][j] == 'Z') {
                    end = new Point(i, j);
                }
            }
        }
        queue = new LinkedList<>();
        visited[start.r][start.c] = true;
        queue.add(start);
        Point block = null;
        Loop1:
        while(!queue.isEmpty()) {   // 누출된부분 찾기
            Point current = queue.poll();
            char c = arr[current.r][current.c];
            int[][] drc = null;
            if(c == 'M') {
                drc = map.get('+');
            } else {
                drc = map.get(c);
            }
            for(int d = 0; d < drc.length; d++) {
                int nr = current.r + drc[d][0];
                int nc = current.c + drc[d][1];
                if(nr >= 1 && nr <= R && nc >= 1 && nc <= C) {
                    if(arr[nr][nc] == '.' && c != 'M') {
                        block = new Point(nr, nc);
                        break Loop1;
                    }
                    if(!visited[nr][nc] && arr[nr][nc] != '.' && arr[nr][nc] != 'Z') {
                        queue.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        // 블록의 위치를 찾으면 상하좌우가 어떤블록이 들어갈 수 있는지 검사
        char res = find(block);
        bw.write(block.r + " " + block.c + " " + res);
        br.close();
        bw.flush();
        bw.close();
    }

    public static char find(Point p) {
        Set<Character>[] set = new HashSet[4];
        set[0] = new HashSet<>(Arrays.asList('|', '+', '1', '4'));
        set[1] = new HashSet<>(Arrays.asList('-', '+', '3', '4'));
        set[2] = new HashSet<>(Arrays.asList('|', '+', '2', '3'));
        set[3] = new HashSet<>(Arrays.asList('-', '+', '1', '2'));
        /*
        상 arr[p.r - 1][p.c]
        하 arr[p.r + 1][p.c]
        좌 arr[p.r][p.c - 1]
        우 arr[p.r][p.c + 1]
        */
        if(set[0].contains(arr[p.r - 1][p.c]) &&
           set[1].contains(arr[p.r][p.c + 1]) &&
           set[2].contains(arr[p.r + 1][p.c]) &&
           set[3].contains(arr[p.r][p.c - 1])) {
           return '+';
        }
        if(set[0].contains(arr[p.r - 1][p.c]) && set[1].contains(arr[p.r][p.c + 1])) {
            // 상우
            return '2';
        }
        if(set[0].contains(arr[p.r - 1][p.c]) && set[2].contains(arr[p.r + 1][p.c])) {
            // 상하
            return '|';
        }
        if(set[0].contains(arr[p.r - 1][p.c]) && set[3].contains(arr[p.r][p.c - 1])) {
            // 상좌
            return '3';
        }
        if(set[1].contains(arr[p.r][p.c + 1]) && set[2].contains(arr[p.r + 1][p.c])) {
            // 우하
            return '1';
        }
        if(set[1].contains(arr[p.r][p.c + 1]) && set[3].contains(arr[p.r][p.c - 1])) {
            // 우좌
            return '-';
        }
        if(set[2].contains(arr[p.r + 1][p.c]) && set[3].contains(arr[p.r][p.c - 1])) {
            // 하좌
            return '4';
        }
        return '+';
    }

    public static void initMap() {
        map = new HashMap<>();
        int[][][] arr = {
                            {{-1, 0}, {1, 0}},  // 상하
                            {{0, -1}, {0, 1}},  // 좌우
                            {{-1, 0}, {1, 0}, 
                             {0, -1}, {0, 1}},  // 상하좌우
                            {{1, 0}, {0, 1}},   // 하우
                            {{-1, 0}, {0, 1}},  // 상우
                            {{-1, 0}, {0, -1}}, // 상좌
                            {{1, 0}, {0, -1}}   // 하좌
                        };
        
        for(int i = 0; i < blockChar.length; i++) {
            map.put(blockChar[i], arr[i]);
        }
    }
}