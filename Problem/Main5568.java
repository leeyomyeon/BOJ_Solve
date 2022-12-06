import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main5568 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int n, k;
    public static int[] arr;
    public static int[] selected;
    public static boolean[] visited;
    public static Set<Integer> set;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        arr = new int[n];
        visited = new boolean[n];
        selected = new int[k];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine()); 
        }

        permutation(0);
        bw.write(Integer.toString(set.size()));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void permutation(int cnt) {
        if(cnt == k) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < k; i++) {
                sb.append(Integer.toString(arr[selected[i]]));
            }
            set.add(Integer.parseInt(sb.toString()));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) {
                continue;
            }
            selected[cnt] = i;
            visited[i] = true;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}