import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11725 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<ArrayList<Integer>> list;
    public static int[] ans;
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        ans = new int[N + 1];
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.get(from).add(to);
            list.get(to).add(from);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        ans[1] = -1;
        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(int next : list.get(current)) {
                if(ans[next] == 0) {
                    ans[next] = current;
                    queue.add(next);
                }
            }
        }
        for(int i = 2; i <= N; i++) {
            bw.write(Integer.toString(ans[i]));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}