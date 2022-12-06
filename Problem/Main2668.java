import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Main2668 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static int[] arr;
    public static boolean[] visited;
    public static LinkedList<Integer> list;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        arr = new int[N + 1];
        
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs(i, i);
        }

        Collections.sort(list);
        bw.write(list.size() + "\n");
        for(int k : list) {
            bw.write(k + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int startNum, int currentNum) {
        if(!visited[currentNum]) {
            visited[currentNum] = true;
            dfs(startNum, arr[currentNum]);
        } else {
            if(startNum == currentNum) {
                list.add(startNum);
            }
        }
    }
}