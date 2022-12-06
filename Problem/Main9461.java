import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main9461 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long[] arr;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new long[101];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;
        arr[4] = 2;
        arr[5] = 2;
        arr[6] = 3;
        arr[7] = 4;
        arr[8] = 5;
        arr[9] = 7;
        arr[10] = 9;
        for(int i = 11; i <= 100; i++) {
            arr[i] = arr[i - 1] + arr[i - 5];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[Integer.parseInt(br.readLine())]).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
1 1 1 2 2 3 4 5 7 9 12 16 21 28 37

*/