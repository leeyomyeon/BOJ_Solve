import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main16208 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long sum;
    public static int[] list;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        list = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        sum = 0;
        for(int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(list);
        makeTree(0, list.length - 1, 1);
        bw.write(Long.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
    public static long makeTree(int left, int right, int idx) {
        if(left == right) {
            return list[left];
        }

        int mid = (left + right) / 2;
        long leftVal = makeTree(left,  mid, idx * 2);
        long rightVal = makeTree(mid + 1, right, idx * 2 + 1);
        sum += (leftVal * rightVal);
        return leftVal + rightVal;
    }
}