import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16234 {
    public static int N;
    public static int MIN;
    public static int MAX;
    public static int[][] arr;
    public static boolean[][] visited;
    public static LinkedList<Point> list;
    public static boolean chk;
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        MIN = Integer.parseInt(st.nextToken());
        MAX = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        chk = true;
        while(chk) {
            chk = false;
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        chk(i, j);
                    }
                }
            }
            if(chk) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};

    public static void chk(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        list = new LinkedList<>();
        queue.add(new Point(r, c));
        list.add(new Point(r, c));
        visited[r][c] = true;
        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && Math.abs(arr[current.r][current.c] - arr[nr][nc]) >= MIN && Math.abs(arr[current.r][current.c] - arr[nr][nc]) <= MAX) {
                    list.add(new Point(nr, nc));
                    queue.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                    chk = true;
                }
            }
        }
        calc();
    }
    
    public static void calc() {
        if(list.size() == 0) {
            return;
        }
        int sum = 0;
        for (Point cur : list) {
            sum += arr[cur.r][cur.c];
        }
        sum /= list.size();
        for (Point cur : list) {
            arr[cur.r][cur.c] = sum;
        }
        list.clear();
    }
}
/*
5 1 5
88 27 34 84 9
40 91 11 30 81
2 88 65 26 75
75 66 16 14 28
89 10 5 30 75
ans = 1

4 1 9
96 93 74 30
60 90 65 96
5 27 17 98
10 41 46 20
ans = 1

2 10 20
0 30
50 10

0 20
50 20

10 10
50 20

10 15
50 15

*/