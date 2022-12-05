import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1043 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, M, T;
    public static boolean[][] trustList;
    public static boolean[] trust;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        trust = new boolean[N + 1];
        trustList = new boolean[N + 1][N + 1];
        for(int i = 0; i < T; i++) {
            int k = Integer.parseInt(st.nextToken());
            trust[k] = true;
        }
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            list.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++) {
                list.get(i).add(Integer.parseInt(st.nextToken()));
                if(j >= 1) {
                    trustList[list.get(i).get(j - 1)][list.get(i).get(j)] = true;
                    trustList[list.get(i).get(j)][list.get(i).get(j - 1)] = true;
                }
            }
        }
        int cnt = 0;
        for(ArrayList<Integer> l : list) {
            boolean flag = true;
            for(int k : l) {
                ArrayDeque<Integer> deque = new ArrayDeque<>();
                boolean[] visited = new boolean[N + 1];
                deque.add(k);
                visited[k] = true;
                while(!deque.isEmpty()) {
                    int current = deque.removeFirst();
                    if(trust[current]) {
                        flag = false;
                        break;
                    }
                    for(int d = 1; d <= N; d++) {
                        if(!visited[d] && trustList[current][d]) {
                            visited[d] = true;
                            deque.add(d);
                        }
                    }
                }
            }
            if(flag) {
                cnt++;
            }
        }

        bw.write(Integer.toString(cnt));
        br.close();
        bw.flush();
        bw.close();
    }
}
