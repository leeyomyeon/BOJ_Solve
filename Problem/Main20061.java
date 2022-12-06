import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main20061 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static boolean[][] green, blue;
    public static int N;
    public static int cnt;
    public static ArrayDeque<Boolean> deque = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        green = new boolean[6][4];
        blue = new boolean[4][6];
        cnt = 0;
        N = Integer.parseInt(br.readLine());
        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            addGreen(t, c);
            addBlue(t, r);
        }
        int sum = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 6; j++) {
                if(blue[i][j]) {
                    sum++;
                }
                if(green[j][i]) {
                    sum++;
                }
            }
        }
        bw.write(Integer.toString(cnt));
        bw.newLine();
        bw.write(Integer.toString(sum));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void addGreen(int t, int c) {
        // 블록쌓고
        if(t == 1 || t == 3) {
            // c만 봄
            for(int i = 1; i <= 5; i++) {
                if(i <= 4 && green[i + 1][c]) {
                    if(t == 3) { green[i - 1][c] = true; }
                    green[i][c] = true;
                    break;
                } else if(i == 5) {
                    if(t == 3) { green[i - 1][c] = true; }
                    green[i][c] = true;
                }
            }
        } else if(t == 2) {
            for(int i = 0; i <= 5; i++) {
                if(i <= 4 && (green[i + 1][c] || green[i + 1][c + 1])) {
                    green[i][c] = true;
                    green[i][c + 1] = true;
                    break;
                } else if(i == 5) {
                    green[i][c] = true;
                    green[i][c + 1] = true;
                }
            }
        }
        // 터지고
        for(int i = 2; i <= 5; i++) {
            if(green[i][0] && green[i][1] && green[i][2] && green[i][3]) {
                setFalse('G', i);
                cnt++;
                for(int k = i; k >= 0; k--) { // 내리고
                    if(k == 0) {
                        setFalse('G', k);
                    } else {
                        move('G', k);
                    }
                }
            }
        }
        // 연한부분 검사
        if(green[1][0] || green[1][1] || green[1][2] || green[1][3]) {
            int a = 0;
            if(green[0][0] || green[0][1] || green[0][2] || green[0][3]) {
                // 두 줄 있는경우
                a++;
                setFalse('G', 4);
            }
            setFalse('G', 5);
            // 블록 아래로 내리고
            for(int g = 0; g <= a; g++) {
                for (int i = 5; i >= 0; i--) {
                    if (i == 0) {
                        setFalse('G', i);
                    } else {
                        move('G', i);
                    }
                }
            }
        }
    }
    public static void addBlue(int t, int r) {
        if(t == 1 || t == 2) {
            for(int i = 1; i <= 5; i++) {
                if(i <= 4 && blue[r][i + 1]) {
                    if(t == 2) { blue[r][i - 1] = true; }
                    blue[r][i] = true;
                    break;
                } else if(i == 5) {
                    if(t == 2) { blue[r][i - 1] = true; }
                    blue[r][i] = true;
                }
            }
        } else {
            for(int i = 0; i <= 5; i++) {
                if(i <= 4 && (blue[r][i + 1] || blue[r + 1][i + 1])) {
                    blue[r][i] = true;
                    blue[r + 1][i] = true;
                    break;
                } else if(i == 5) {
                    blue[r][i] = true;
                    blue[r + 1][i] = true;
                }
            }
        }
        // 터지고
        for(int i = 2; i <= 5; i++) {
            if(blue[0][i] && blue[1][i] && blue[2][i] && blue[3][i]) {
                setFalse('B', i);
                cnt++;
                for(int k = i; k >= 0; k--) { // 내리고
                    if(k == 0) {
                        setFalse('B', k);
                    } else {
                        move('B', k);
                    }
                }
            }
        }
        // 연한부분 검사
        if(blue[0][1] || blue[1][1] || blue[2][1] || blue[3][1]) {
            int a = 0;
            if(blue[0][0] || blue[1][0] || blue[2][0] || blue[3][0]) {
                // 두 줄 있는경우
                a++;
                setFalse('B', 4);
            }
            setFalse('B', 5);
            // 블록 아래로 내리고
            for(int g = 0; g <= a; g++) {
                for(int i = 5; i >= 0; i--) {
                    if(i == 0) {
                        setFalse('B', i);
                    } else {
                        move('B', i);
                    }
                }
            }
        }
    }
    public static void move(char color, int k) {
        if(color == 'G') {
            green[k][0] = green[k - 1][0];
            green[k][1] = green[k - 1][1];
            green[k][2] = green[k - 1][2];
            green[k][3] = green[k - 1][3];
        } else {
            blue[0][k] = blue[0][k - 1];
            blue[1][k] = blue[1][k - 1];
            blue[2][k] = blue[2][k - 1];
            blue[3][k] = blue[3][k - 1];
        }
    }
    public static void setFalse(char color, int k) {
        if(color == 'G') {
            green[k][0] = false;
            green[k][1] = false;
            green[k][2] = false;
            green[k][3] = false;
        } else {
            blue[0][k] = false;
            blue[1][k] = false;
            blue[2][k] = false;
            blue[3][k] = false;
        }
    }

}