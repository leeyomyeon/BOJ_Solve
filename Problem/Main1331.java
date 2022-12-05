import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main1331 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] dr = {1, 2, 2, 1, -1, -2, -2, -1};
    public static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};
    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        HashSet<String> set = new HashSet<>();
        boolean isValid = false;
        Point[] list = new Point[36];
        for(int i = 0; i < 36; i++) {
            String s = br.readLine();
            if(set.contains(s)) {
                isValid = true;
            } else {
                set.add(s);
            }
            list[i] = new Point(s.charAt(0) - 'A', s.charAt(1) - '1');
            if(i >= 1 && !validation(list[i - 1], list[i])) {
                isValid = true;
            }
        }
        if(!isValid && validation(list[0], list[35])) {
            bw.write("Valid");
        } else {
            bw.write("Invalid");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean validation(Point o1, Point o2) {
        for(int i = 0; i < 8; i++) {
            int newR = o1.r + dr[i];
            int newC = o1.c + dc[i];
            if(newR == o2.r && newC == o2.c) {
                return true;
            }
        }
        return false;
    }
} 