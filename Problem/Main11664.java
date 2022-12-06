import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main11664 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        double Ax = Integer.parseInt(st.nextToken());
        double Ay = Integer.parseInt(st.nextToken());
        double Az = Integer.parseInt(st.nextToken());
        double Bx = Integer.parseInt(st.nextToken());
        double By = Integer.parseInt(st.nextToken());
        double Bz = Integer.parseInt(st.nextToken());
        double Cx = Integer.parseInt(st.nextToken());
        double Cy = Integer.parseInt(st.nextToken());
        double Cz = Integer.parseInt(st.nextToken());
        double res = Double.MAX_VALUE;
        while(true) {
            double Mx = (Ax + Bx) / 2;
            double My = (Ay + By) / 2;
            double Mz = (Az + Bz) / 2;
            double start = dist(Ax, Cx, Ay, Cy, Az, Cz);
            double mid = dist(Mx, Cx, My, Cy, Mz, Cz);
            double end = dist(Bx, Cx, By, Cy, Bz, Cz);
            if(Math.abs(res - mid) <= 1e-6) {
                res = mid;
                break;
            }
            res = Math.min(res, mid);
            if(start > end) {
                Ax = Mx;
                Ay = My;
                Az = Mz;
            } else {
                Bx = Mx;
                By = My;
                Bz = Mz;
            }
        }
        bw.write(String.format("%.10f", res));
        br.close();
        bw.flush();
        bw.close();
    }

    public static double dist(double x1, double x2, double y1, double y2, double z1, double z2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }
}