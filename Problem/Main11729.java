import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main11729 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        bw.write(Integer.toString((int) (Math.pow(2, N) - 1)));
        bw.newLine();
        hanoi(N, 1, 2, 3);
        br.close();
        bw.flush();
        bw.close();
    }

    public static void hanoi(int n, int from, int by, int to) throws Exception {
        if(n == 0) {
            return;
        }
        hanoi(n - 1, from, to ,by);
        bw.write(from + " " + to);
        bw.newLine();
        hanoi(n - 1, by, from, to);
    }
}