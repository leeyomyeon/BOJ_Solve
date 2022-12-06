import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15658 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static char[] selectOperation;
    public static int[] arr;
    public static int N, plus, minus, multiple, divide, MAX, MIN;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        selectOperation = new char[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        plus = Integer.parseInt(st.nextToken());
        minus = Integer.parseInt(st.nextToken());
        multiple = Integer.parseInt(st.nextToken());
        divide = Integer.parseInt(st.nextToken());
        permutation(0, plus, minus, multiple, divide);
        bw.write(MAX + "\n" + MIN);
        br.close();
        bw.flush();
        bw.close();
    }
    public static void permutation(int idx, int pl, int mi, int mu, int di) {
        if(idx == N - 1) {
            int res = arr[0];
            for(int i = 1; i < N; i++) {
                if(selectOperation[i - 1] == '+') {
                    res += arr[i];
                } else if(selectOperation[i - 1] == '-') {
                    res -= arr[i];
                } else if(selectOperation[i - 1] == '*') {
                    res *= arr[i];
                } else { // 나누기
                    if(res < 0) {
                        res = Math.abs(res) / arr[i];
                        res *= -1;
                    } else {
                        res /= arr[i];
                    }
                }
            }
            MAX = Math.max(MAX, res);
            MIN = Math.min(MIN, res);
            return;
        }
        if(pl > 0) {
            selectOperation[idx] = '+';
            permutation(idx + 1, pl - 1, mi, mu, di);
        }
        if(mi > 0) {
            selectOperation[idx] = '-';
            permutation(idx + 1, pl, mi - 1, mu, di);
        }
        if(mu > 0) {
            selectOperation[idx] = '*';
            permutation(idx + 1, pl, mi, mu - 1, di);
        }
        if(di > 0) {
            selectOperation[idx] = '/';
            permutation(idx + 1, pl, mi, mu, di - 1);
        }
    }
}