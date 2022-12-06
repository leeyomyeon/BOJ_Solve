import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main19235 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] green, blue;
    public static int N;
    public static int cnt;
    public static void main(String[] args) throws Exception {
        green = new int[6][4];
        blue = new int[4][6];
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
                if(blue[i][j] > 0) {
                    sum++;
                }
                if(green[j][i] > 0) {
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
                if(i <= 4 && green[i + 1][c] > 0) {
                    if(t == 3) { green[i - 1][c] = t; }
                    green[i][c] = t;
                    break;
                } else if(i == 5) {
                    if(t == 3) { green[i - 1][c] = t; }
                    green[i][c] = t;
                }
            }
        } else if(t == 2) {
            for(int i = 0; i <= 5; i++) {
                if(i <= 4 && (green[i + 1][c] > 0 || green[i + 1][c + 1] > 0)) {
                    green[i][c] = t;
                    green[i][c + 1] = t;
                    break;
                } else if(i == 5) {
                    green[i][c] = t;
                    green[i][c + 1] = t;
                }
            }
        }
        // 터지고
        while(true) {
            boolean isBreak = false;
            for(int i = 2; i <= 5; i++) {
                if(green[i][0] > 0 && green[i][1] > 0 && green[i][2] > 0 && green[i][3] > 0) {
                    setFalse('G', i);
                    cnt++;
                    isBreak = true;
                }
            }
            if(!isBreak) {
                break;
            }
            for(int i = 5; i >= 0; i--) {
                for(int j = 0; j < 4; j++) {
                    if(green[i][j] == 2) {
                        for(int k = i + 1; k <= 5; k++) {
                            if(k == i + 1 && (green[k][j] > 0 || green[k][j + 1] > 0)) {
                                break;
                            }
                            if(green[k][j] > 0 || green[k][j + 1] > 0) {
                                green[k - 1][j] = green[i][j];
                                green[k - 1][j + 1] = green[i][j + 1];
                                green[i][j] = 0;
                                green[i][j + 1] = 0;
                                break;
                            } else if(k == 5) {
                                green[k][j] = green[i][j];
                                green[k][j + 1] = green[i][j + 1];
                                green[i][j] = 0;
                                green[i][j + 1] = 0;
                            }
                        }
                        j++;
                    } else if(green[i][j] > 0 && green[i][j] != 2){
                        for(int k = i + 1; k <= 5; k++) {
                            if(k == i + 1 && green[k][j] > 0) {
                                break;
                            }
                            if(green[k][j] > 0) {
                                green[k - 1][j] = green[i][j];
                                green[i][j] = 0;
                                break;
                            }
                            if(k == 5) {
                                green[k][j] = green[i][j];
                                green[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
        // 연한부분 검사
        if(green[1][0] > 0 || green[1][1] > 0 || green[1][2] > 0 || green[1][3] > 0) {
            int a = 0;
            if(green[0][0] > 0 || green[0][1] > 0 || green[0][2] > 0 || green[0][3] > 0) {
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
                if(i <= 4 && blue[r][i + 1] > 0) {
                    if(t == 2) { blue[r][i - 1] = t; }
                    blue[r][i] = t;
                    break;
                } else if(i == 5) {
                    if(t == 2) { blue[r][i - 1] = t; }
                    blue[r][i] = t;
                }
            }
        } else {
            for(int i = 0; i <= 5; i++) {
                if(i <= 4 && (blue[r][i + 1] > 0 || blue[r + 1][i + 1] > 0)) {
                    blue[r][i] = t;
                    blue[r + 1][i] = t;
                    break;
                } else if(i == 5) {
                    blue[r][i] = t;
                    blue[r + 1][i] = t;
                }
            }
        }
        // 터지고
        while(true) {
            boolean isBreak = false;
            for(int i = 2; i <= 5; i++) {
                if(blue[0][i] > 0 && blue[1][i] > 0 && blue[2][i] > 0 && blue[3][i] > 0) {
                    setFalse('B', i);
                    cnt++;
                    isBreak = true;
                }
            }
            if(!isBreak) {
                break;
            }
            for(int j = 5; j >= 0; j--) {
                for(int i = 0; i < 4; i++) {
                    if(blue[i][j] == 3) {
                        for(int k = j + 1; k <= 5; k++) {
                            if(k == j + 1 && (blue[i][k] > 0 || blue[i + 1][k] > 0)) {
                                break;
                            }
                            if(blue[i][k] > 0 || blue[i + 1][k] > 0) {
                                blue[i][k - 1] = blue[i][j];
                                blue[i + 1][k - 1] = blue[i + 1][j];
                                blue[i][j] = 0;
                                blue[i + 1][j] = 0;
                                break;
                            } else if(k == 5) {
                                blue[i][k] = blue[i][j];
                                blue[i + 1][k] = blue[i + 1][j];
                                blue[i][j] = 0;
                                blue[i + 1][j] = 0;
                            }
                        }
                        i++;
                    } else if(blue[i][j] > 0 && blue[i][j] != 3){
                        for(int k = j + 1; k <= 5; k++) {
                            if(k == j + 1 && blue[i][k] > 0) {
                                break;
                            }
                            if(blue[i][k] > 0) {
                                blue[i][k - 1] = blue[i][j];
                                blue[i][j] = 0;
                                break;
                            }
                            if(k == 5) {
                                blue[i][k] = blue[i][j];
                                blue[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
        // 연한부분 검사
        if(blue[0][1] > 0 || blue[1][1] > 0 || blue[2][1] > 0 || blue[3][1] > 0) {
            int a = 0;
            if(blue[0][0] > 0 || blue[1][0] > 0 || blue[2][0] > 0 || blue[3][0] > 0) {
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
            green[k][0] = 0;
            green[k][1] = 0;
            green[k][2] = 0;
            green[k][3] = 0;
        } else {
            blue[0][k] = 0;
            blue[1][k] = 0;
            blue[2][k] = 0;
            blue[3][k] = 0;
        }
    }

}