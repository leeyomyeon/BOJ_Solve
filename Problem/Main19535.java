import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main19535 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N;
    public static long G, D;
    public static boolean[] visited;
    public static ArrayList<ArrayList<Integer>> list;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }
        G = 0;
        D = 0;
        visited = new boolean[N + 1];
        visited[1] = true;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        while(!deque.isEmpty()) {
            int current = deque.removeFirst();
            int c = list.get(current).size();
            if(c >= 3) {
                G += ((long) c * (c - 1) * (c - 2) / 6);
            }
            for(int k : list.get(current)) {
                if(!visited[k]) {
                    int s = list.get(k).size();
                    D += ((long) c - 1) * (s - 1);
                    visited[k] = true;
                    deque.add(k);
                }
            }
        }
        if(D == G * 3) {
            bw.write("DUDUDUNGA");
        } else if (D > G * 3) {
            bw.write("D");
        } else if(D < G * 3) {
            bw.write("G");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}