import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1068 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> list;
    public static int delete, cnt;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        delete = Integer.parseInt(br.readLine());
        int startIdx = 0;
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < N; i++) {
            int from = Integer.parseInt(st.nextToken());
            if(from == -1) {
                startIdx = i;
            } else {
                list.get(from).add(i);
            }
        }
        cnt = 0;
        visited = new boolean[N];
        visited[delete] = true;
        dfs(startIdx);
        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void dfs(int next) {
        if(visited[next]) {
            return;
        }
        visited[next] = true;
        boolean isChild = true;
        for(int current : list.get(next)) {
            if(current == delete) {
                continue;
            }
            isChild = false;
            dfs(current);
        }
        if(isChild) {
            cnt++;
        }
    }
}