import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main2239 {
    public static int[][] arr;
    public static boolean[][] visited;
    public static LinkedList<Point> list;
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        arr = new int[9][9];
        visited = new boolean[9][9];

        list = new LinkedList<Point>();

        for(int i = 0; i < 9; i++) {
            String[] str = br.readLine().split("");
            for(int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(str[j]);
                if(arr[i][j] == 0) {
                    list.add(new Point(i, j));
                }
            }
        }

        backTrack(0);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void backTrack(int idx) throws Exception {
        if(idx == list.size()) {
            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    bw.write(Integer.toString(arr[i][j]));
                }
                bw.newLine();
            }
            br.close();
            bw.flush();
            bw.close();
            System.exit(0);
        }
        Point current = list.get(idx);
        // ------------  3*3 가로 세로 체크
        int startR = 0;
        int startC = 0;
        if(current.r >= 3 && current.r < 6) {
            startR = 3;
        } else if (current.r >= 6 && current.r < 9) {
            startR = 6;
        }
        if(current.c >= 3 && current.c < 6) {
            startC = 3;
        } else if (current.c >= 6 && current.c < 9) {
            startC = 6;
        }

        boolean[] visited = new boolean[10];
        Arrays.fill(visited, true);
        for(int i = startR; i < startR + 3; i++) {
            for(int j = startC; j < startC + 3; j++) {
                visited[arr[i][j]] = false;
            }
        }
        for(int i = 0; i < 9; i++) {
            visited[arr[i][current.c]] = false;
            visited[arr[current.r][i]] = false;
        }

        for(int i = 1; i <= 9; i++) {
            if(visited[i]) {
                arr[current.r][current.c] = i;
                backTrack(idx + 1);
                arr[current.r][current.c] = 0;
            }
        }
    }
}