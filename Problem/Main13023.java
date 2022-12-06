import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main13023 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static ArrayList<ArrayList<Integer>> list;
    public static boolean[] visited;
    public static int N, M, res;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        visited = new boolean[N];
        for(int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }
        for(int i = 0; i < N; i++) {
            visited[i] = true;
            bfs(i, 1);
            visited[i] = false;
        }
        bw.write("0");
        br.close();
        bw.flush();
        bw.close();
    }
    public static void bfs(int idx, int depth) throws Exception{
        for(int k : list.get(idx)) {
            if(!visited[k]) {
                visited[k] = true;
                if(depth == 4) {
                    bw.write("1");
                    br.close();
                    bw.flush();
                    bw.close();
                    System.exit(0);
                }
                bfs(k, depth + 1);
                visited[k] = false;
            }
        }
    }
}