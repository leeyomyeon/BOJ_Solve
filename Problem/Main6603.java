import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6603 {
    public static String[] lotto;
    public static boolean[] visited;
    public static int K;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) {
                return;
            }
            lotto = new String[K];
            visited = new boolean[K];
            for(int i = 0; i < K; i++) {
                lotto[i] = st.nextToken();
            }

            printLotto(0, 0);
            System.out.println();
        }
    }

    public static void printLotto(int idx, int selected) {
        if(idx == K) {
            if(selected == 6) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < K; i++) {
                    if(visited[i]) {
                        sb.append(lotto[i] + " ");
                    }
                }
                System.out.println(sb.toString());
            }
            return;
        }
        
        visited[idx] = true;
        printLotto(idx + 1, selected + 1);
        visited[idx] = false;
        printLotto(idx + 1, selected);
    }
}