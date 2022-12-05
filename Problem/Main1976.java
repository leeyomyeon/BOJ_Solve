import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1976 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] arr;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int k = Integer.parseInt(st.nextToken());
                if(k == 1) {
                    union(i, j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));
        while(st.hasMoreTokens()) {
            int to = Integer.parseInt(st.nextToken());

            if(start != find(to)) {
                bw.write("NO");
                br.close();
                bw.flush();
                bw.close();
                return;
            }
        }
        bw.write("YES");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void union(int from, int to) {
        int x = find(from);
        int y = find(to);

        if( x != y ) {
            if( x < y ) {
                arr[y] = x;
            } else {
                arr[x] = y;
            }
        }
    }

    public static int find(int target) {
        if(target == arr[target]) {
            return target;
        } else {
            return arr[target] = find(arr[target]);
        }
    }
}