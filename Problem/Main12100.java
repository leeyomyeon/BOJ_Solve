import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main12100 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int N, MAX;
    public static int[][] arr;
    public static int[][] tmp;
    public static ArrayDeque<Integer> deque;
    public static int[] selected;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        deque = new ArrayDeque<>();
        MAX = Integer.MIN_VALUE;
        selected = new int[5];
        permutation(0);
        bw.write(Integer.toString(MAX));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void permutation(int cnt) {
        if(cnt == 5) {
            simulation();
            System.out.println(MAX + " " + Arrays.toString(selected));
            return;
        }

        for(int i = 1; i <= 4; i++) {
            selected[cnt] = i;
            permutation(cnt + 1);
        }
    }

    public static void simulation() {
        tmp = new int[N][N];
        for(int i = 0; i < N; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, N);
        }
        for(int command : selected) {
            if(command == 1) {
                up();
            } else if (command == 2) {
                down();
            } else if (command == 3) {
                left();
            } else if (command == 4) {
                right();
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                MAX = Math.max(MAX, tmp[i][j]);
            }
        }
    }
    public static void up() {
        for(int j = 0; j < N; j++) {
            int startIdx = -1;
            int endIdx = -1;
            for(int i = 0; i < N; i++) {
                // 같은 블록 합침
                if(tmp[i][j] != 0 && startIdx == -1) {
                    startIdx = i;
                } else if(tmp[i][j] != 0 && startIdx != -1) {
                    endIdx = i;
                }
                if(startIdx != -1 && endIdx != -1) {
                    if(tmp[startIdx][j] == tmp[endIdx][j]) {
                        tmp[startIdx][j] += tmp[endIdx][j];
                        tmp[endIdx][j] = 0;
                    } else {
                        i--;
                    }
                    startIdx = -1;
                    endIdx = -1;
                }
            }
            // 블록 전부 위로 올림
            for(int i = 0; i < N; i++) {
                if(tmp[i][j] != 0) {
                    deque.add(tmp[i][j]);
                }
            }
            for(int i = 0; i < N; i++) {
                if(deque.isEmpty()) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = deque.removeFirst();
                }
            }
        }
    }
    public static void down() {
        for(int j = 0; j < N; j++) {
            int startIdx = -1;
            int endIdx = -1;
            for(int i = N - 1; i >= 0; i--) {
                // 같은 블록 합침
                if(tmp[i][j] != 0 && startIdx == -1) {
                    startIdx = i;
                } else if(tmp[i][j] != 0 && startIdx != -1) {
                    endIdx = i;
                }
                if(startIdx != -1 && endIdx != -1) {
                    if(tmp[startIdx][j] == tmp[endIdx][j]) {
                        tmp[startIdx][j] += tmp[endIdx][j];
                        tmp[endIdx][j] = 0;
                    } else {
                        i++;
                    }
                    startIdx = -1;
                    endIdx = -1;
                }
            }
            for(int i = N - 1; i >= 0; i--) {
                if(tmp[i][j] != 0) {
                    deque.add(tmp[i][j]);
                }
            }
            for(int i = N - 1; i >= 0; i--) {
                if(deque.isEmpty()) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = deque.removeFirst();
                }
            }
        }
    }
    public static void left() {
        for(int i = 0; i < N; i++) {
            int startIdx = -1;
            int endIdx = -1;
            for(int j = 0; j < N; j++) {
                // 같은 블록 합침
                if(tmp[i][j] != 0 && startIdx == -1) {
                    startIdx = j;
                } else if(tmp[i][j] != 0 && startIdx != -1) {
                    endIdx = j;
                }
                if(startIdx != -1 && endIdx != -1) {
                    if(tmp[i][startIdx] == tmp[i][endIdx]) {
                        tmp[i][startIdx] += tmp[i][endIdx];
                        tmp[i][endIdx] = 0;
                    } else {
                        j--;
                    }
                    startIdx = -1;
                    endIdx = -1;
                }
            }
            for(int j = 0; j < N; j++) {
                if(tmp[i][j] != 0) {
                    deque.add(tmp[i][j]);
                }
            }
            for(int j = 0; j < N; j++) {
                if(deque.isEmpty()) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = deque.removeFirst();
                }
            }
        }
    }
    public static void right() {
        for(int i = 0; i < N; i++) {
            int startIdx = -1;
            int endIdx = -1;
            for(int j = N - 1; j >= 0; j--) {
                // 같은 블록 합침
                if(tmp[i][j] != 0 && startIdx == -1) {
                    startIdx = j;
                } else if(tmp[i][j] != 0 && startIdx != -1) {
                    endIdx = j;
                }
                if(startIdx != -1 && endIdx != -1) {
                    if(tmp[i][startIdx] == tmp[i][endIdx]) {
                        tmp[i][startIdx] += tmp[i][endIdx];
                        tmp[i][endIdx] = 0;
                    } else {
                        j++;
                    }
                    startIdx = -1;
                    endIdx = -1;
                }
            }
            for(int j = N - 1; j >= 0; j--) {
                if(tmp[i][j] != 0) {
                    deque.add(tmp[i][j]);
                }
            }
            for(int j = N - 1; j >= 0; j--) {
                if(deque.isEmpty()) {
                    tmp[i][j] = 0;
                } else {
                    tmp[i][j] = deque.removeFirst();
                }
            }
        }
    }
}