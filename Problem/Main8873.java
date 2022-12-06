import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main8873 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int H, W;
    public static int[][] R, G, B;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        R = new int[H][W];
        G = new int[H][W];
        B = new int[H][W];
        int rSum = 0;
        int gSum = 0;
        int bSum = 0;
        // R
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
                rSum += R[i][j];
            }
        }
        // G
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                G[i][j] = Integer.parseInt(st.nextToken());
                gSum += G[i][j];
            }
        }
        // B
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                B[i][j] = Integer.parseInt(st.nextToken());
                bSum += B[i][j];
            }
        }
        long sum = 0;
        for(int i = 0; i < H - 1; i++) {
            for(int j = 0; j < W - 1; j++) {
                sum += Math.abs(R[i + 1][j] - R[i][j]);
                sum += Math.abs(R[i][j + 1] - R[i][j]);
                sum += Math.abs(G[i + 1][j] - G[i][j]);
                sum += Math.abs(G[i][j + 1] - G[i][j]);
                sum += Math.abs(B[i + 1][j] - B[i][j]);
                sum += Math.abs(B[i][j + 1] - B[i][j]);
            }
        }
        sum /= (long) H * W * 2;
        if(sum < 9) {
            bw.write("4");
        } else if(sum < 23) {
            bw.write("1");
        } else if(sum < 54) {
            bw.write("2");
        } else {
            bw.write("3");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}