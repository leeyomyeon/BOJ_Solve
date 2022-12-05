import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1022 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int[][] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
        int R = r2 - r1 + 1;
        int C = c2 - c1 + 1;
        arr = new int[R][C];
        int maxLen = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int nr = r1 + i;
                int nc = c1 + j;
                int res;
                if(nr == 0 && nc == 0) {
                    res = 1;
                } else if ((nr < 0 && nr <= nc ) || (nr >= 0 && nr < nc)) {
                    if(Math.abs(nr) < Math.abs(nc)) {
                        res = (int) Math.pow(Math.abs(nc) * 2, 2) + 1 - (Math.abs(nc)*2) - (nr + nc);
                    } else {
                        res = (int) Math.pow(Math.abs(nr) * 2, 2) + 1 - (Math.abs(nr)*2) - (nr + nc);
                    }
                } else {
                    if(Math.abs(nr) < Math.abs(nc)) {
                        res = (int) Math.pow(Math.abs(nc) * 2 + 1, 2) - (2 * Math.abs(nc)) - Math.abs(nr + nc);
                    } else {
                        res = (int) Math.pow(Math.abs(nr) * 2 + 1, 2) - (2 * Math.abs(nr)) + Math.abs(nr + nc);
                    }
                }
                arr[i][j] = res;
                maxLen = Math.max(maxLen, (int)(Math.log10(res)+1));
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int diff = maxLen - (int)(Math.log10(arr[i][j])+1);
                for(int d = 0; d < diff; d++) {
                    sb.append(" ");
                }
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
