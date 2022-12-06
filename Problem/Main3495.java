import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main3495 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int H, W;
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new char[H][W];
        int startIdx = -1;
        int endIdx = -1;
        int sum = 0;
        for(int i = 0; i < H; i++) {
            arr[i] = br.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                if((arr[i][j] == '\\' || arr[i][j] == '/') && startIdx < 0) {
                    startIdx = j;
                } else if((arr[i][j] == '\\' || arr[i][j] == '/') && startIdx >= 0) {
                    endIdx = j;
                }
                if(startIdx >= 0 && endIdx >= 0) {
                    for(int k = startIdx + 1; k < endIdx; k++) {
                        sum += 1;
                    }
                    sum +=1;
                    startIdx = -1;
                    endIdx = -1;
                }
            }
        }
        bw.write(Integer.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
8 8
.../\...
../..\..
././\.\.
/./..\.\
\.\.././
.\.\/./.
..\../..
...\/...
*/