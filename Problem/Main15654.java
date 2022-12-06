import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main15654 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static ArrayList<Integer> list;
    public static int[] arr;
    public static boolean[] visited;
    public static int N, M;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        visited = new boolean[N];
        arr = new int[M];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        backTrack(0);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void backTrack(int idx) throws Exception {
        if(idx == M) {
            for(int k : arr) {
                bw.write(k + " ");
            }
            bw.newLine();
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                arr[idx] = list.get(i);
                visited[i] = true;
                backTrack(idx + 1);
                visited[i] = false;
            }
        }
    }

}