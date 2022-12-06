import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16198 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long MAX;
    public static int[] arr;
    public static boolean[] selected;
    public static int[] select;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        selected = new boolean[N];
        MAX = Long.MIN_VALUE;
        select = new int[N - 2];
        permutation(0);
        bw.write(Long.toString(MAX));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void permutation(int idx) {
        if(idx == N - 2) {
            boolean[] chk = new boolean[N];
            long sum = 0;
            for(int i = 0; i < select.length; i++) {
                chk[select[i]] = true;
                int left = select[i] - 1;
                while (left != 0 && chk[left]) {
                    left--;
                }
                int right = select[i] + 1;
                while(right != N - 1 && chk[right]) {
                    right++;
                }
                sum += ((long) arr[left] * arr[right]);
            }
            MAX = Math.max(sum, MAX);
            return;
        }
        for(int i = 1; i < N - 1; i++) {
            if(!selected[i]) {
                select[idx] = i;
                selected[i] = true;
                permutation(idx + 1);
                selected[i] = false;
            }
        }
    }

}