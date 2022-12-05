import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1717 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            arr[i] = i;
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(command == 0) {
                union(a, b);
            } else {
                if(find(a) == find(b)) {
                    bw.write("YES");
                } else {
                    bw.write("NO");
                }
                bw.newLine();
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if( x != y) {
            if(x < y) {
                arr[y] = x;
            } else {
                arr[x] = y;
            }
        }
    }

    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        }
        return arr[target] = find(arr[target]);
    }
}