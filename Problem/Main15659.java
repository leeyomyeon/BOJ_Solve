import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main15659 {
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
            Stack<Integer> calc = new Stack<>();
            Stack<Character> oper = new Stack<>();
            calc.add(arr[0]);
            for(int i = 1; i < N; i++) {
                if(selectOperation[i - 1] == '*') {
                    int a = calc.pop();
                    a *= arr[i];
                    calc.add(a);
                } else if(selectOperation[i - 1] == '/') {
                    int a = calc.pop();
                    if(a < 0) {
                        a = Math.abs(a) / arr[i];
                        a *= -1;
                    } else {
                        a /= arr[i];
                    }
                    calc.add(a);
                } else {
                    oper.add(selectOperation[i - 1]);
                    calc.add(arr[i]);
                }
            }
            int res = 0;
            while(!oper.isEmpty()) {
                char o = oper.pop();
                int next = calc.pop();
                if(o == '+') {
                    res += next;
                } else {
                    res -= next;
                }
            }
            res += calc.pop();
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