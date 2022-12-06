import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main17281 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] visited;
    public static int[] selected;
    public static int N, max, next;
    public static int[][] inning;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        visited = new boolean[9];
        selected = new int[9];
        inning = new int[N][10];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++) {
                inning[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        selected[3] = 1;
        visited[3] = true;
        permutation(2);
        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void permutation(int idx) {
        if(idx == 10) {
            exec();
            return;
        }

        for(int i = 0; i < 9; i++) {
            if(visited[i]) {
                continue;
            }
            selected[i] = idx;
            visited[i] = true;
            permutation(idx + 1);
            visited[i] = false;
        }
    }

    public static void exec() {
        next = 0;
        int score = 0;
        for(int i = 0; i < N; i++) {
            int out = 0;
            Deque<Integer> deque = new ArrayDeque<>();
            while(true) {
                int command = inning[i][selected[next]];
                if(command == 1) {
                    deque.add(selected[next]);
                } else if (command == 2) {
                    deque.add(selected[next]);
                    deque.add(-1);
                } else if (command == 3) {
                    deque.add(selected[next]);
                    deque.add(-1);
                    deque.add(-1);
                } else if (command == 4) {
                    int cnt = 0;
                    while(!deque.isEmpty()) {
                        int c = deque.poll();
                        if(c != -1) {
                            cnt++;
                        }
                    }
                    score += (cnt + 1);
                } else if (command == 0) {
                    out++;
                }
                next = (next + 1) % 9;
                if(out == 3) {
                    break;
                }
                while(deque.size() >= 4) {
                    if(deque.poll() != -1) {
                        score++;
                    }
                }
            }
        }

        max = Math.max(score, max);
    }
}