import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main9372 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[][] arr;
    public static HashSet<Integer> visited;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 국가의 수
            int m = Integer.parseInt(st.nextToken());   // 비행기 종류

            arr = new int[n + 1][n + 1]; 
            
            visited = new HashSet<>(); // 어느 나라를 방문했는지

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                arr[from][to] = 1;
                arr[to][from] = 1;
            }

            int cnt = 0;
            for(int i = 1; i <= n; i++) {
                if(!visited.contains(i)) {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i);
                    while(queue.isEmpty()) {
                        int current = queue.poll();

                        visited.add(current);
                        for(int j = 1; j <= n; j++) {
                            if(arr[current][j] == 1) {
                                queue.add(j);
                            }
                        }
                    }

                    cnt++;
                }
            }

            bw.write((cnt - 1) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}