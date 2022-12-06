import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10709 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int H, W;
    public static int[][] arr;
    public static Queue<Point> queue;
    public static class Point {
        int h;
        int w;
        int cnt;
        public Point(int h, int w, int cnt) {
            this.h = h;
            this.w = w;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
        arr = new int[H][W];

        for(int i = 0; i < H; i++) {
            char[] ch = br.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                if(ch[j] == 'c') {
                    queue.add(new Point(i, j, 0));
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = -1;
                }
            }
        }

        while(!queue.isEmpty()) {
            Point current = queue.poll();
            arr[current.h][current.w] = current.cnt;
            int nw = current.w + 1;
            if(nw < W && arr[current.h][nw] == -1) {
                queue.add(new Point(current.h, nw, current.cnt + 1));
            }
        }
        for(int[] i : arr) {
            for(int j : i) {
                bw.write(j + " ");
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}