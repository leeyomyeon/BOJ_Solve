import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main16939 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][][] arr, tmp;
    public static void main(String[] args) throws Exception {
        input();
        int res = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j <= 1; j++) {
                copyArr();
                rotateFront(i, j);
                rotateSide(i, j);
                if(validate()) {
                    res = 1;
                }
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void copyArr() {
        tmp = new int[6][2][2];
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 2; j++) {
                System.arraycopy(arr[i][j], 0, tmp[i][j], 0, 2);
            }
        }
    }
    public static void input() throws Exception{
        arr = new int[6][2][2];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; i++) {
            if(i == 2) {
                for(int j = 1; j >= 0; j--) {
                    for(int k = 1; k >= 0; k--) {
                        arr[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
            } else {
                for(int j = 0; j < 2; j++) {
                    for(int k = 0; k < 2; k++) {
                        arr[i][j][k] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }
    }
    public static boolean validate() {
        boolean isOk = true;
        for(int i = 0; i < 6; i++) {
            if(tmp[i][0][0] == tmp[i][0][1] && tmp[i][0][1] == tmp[i][1][0] && tmp[i][1][0] == tmp[i][1][1]) {
            } else {
                isOk = false;
                break;
            }
        }
        return isOk;
    }
    public static void rotateFront(int idx, int dir) {
        int t = tmp[idx][0][0];
        if(dir == 0) { // 시계
            tmp[idx][0][0] = tmp[idx][1][0];
            tmp[idx][1][0] = tmp[idx][1][1];
            tmp[idx][1][1] = tmp[idx][0][1];
            tmp[idx][0][1] = t;
        } else { // 반시계
            tmp[idx][0][0] = tmp[idx][0][1];
            tmp[idx][0][1] = tmp[idx][1][1];
            tmp[idx][1][1] = tmp[idx][1][0];
            tmp[idx][1][0] = t;
        }
    }
    public static void rotateSide(int idx, int dir) {
        int[][] t = new int[4][2];
        t[0][0] = tmp[order[idx][dir][0]][surface[idx][dir][0][0][0]][surface[idx][dir][0][0][1]];
        t[0][1] = tmp[order[idx][dir][0]][surface[idx][dir][0][1][0]][surface[idx][dir][0][1][1]];
        t[1][0] = tmp[order[idx][dir][1]][surface[idx][dir][1][0][0]][surface[idx][dir][1][0][1]];
        t[1][1] = tmp[order[idx][dir][1]][surface[idx][dir][1][1][0]][surface[idx][dir][1][1][1]];
        t[2][0] = tmp[order[idx][dir][2]][surface[idx][dir][2][0][0]][surface[idx][dir][2][0][1]];
        t[2][1] = tmp[order[idx][dir][2]][surface[idx][dir][2][1][0]][surface[idx][dir][2][1][1]];
        t[3][0] = tmp[order[idx][dir][3]][surface[idx][dir][3][0][0]][surface[idx][dir][3][0][1]];
        t[3][1] = tmp[order[idx][dir][3]][surface[idx][dir][3][1][0]][surface[idx][dir][3][1][1]];
        tmp[order[idx][dir][0]][surface[idx][dir][0][0][0]][surface[idx][dir][0][0][1]] = t[3][0];
        tmp[order[idx][dir][0]][surface[idx][dir][0][1][0]][surface[idx][dir][0][1][1]] = t[3][1];
        tmp[order[idx][dir][1]][surface[idx][dir][1][0][0]][surface[idx][dir][1][0][1]] = t[0][0];
        tmp[order[idx][dir][1]][surface[idx][dir][1][1][0]][surface[idx][dir][1][1][1]] = t[0][1];
        tmp[order[idx][dir][2]][surface[idx][dir][2][0][0]][surface[idx][dir][2][0][1]] = t[1][0];
        tmp[order[idx][dir][2]][surface[idx][dir][2][1][0]][surface[idx][dir][2][1][1]] = t[1][1];
        tmp[order[idx][dir][3]][surface[idx][dir][3][0][0]][surface[idx][dir][3][0][1]] = t[2][0];
        tmp[order[idx][dir][3]][surface[idx][dir][3][1][0]][surface[idx][dir][3][1][1]] = t[2][1];

    }
    public static int[][][] order = {
        {{5, 4, 1, 3}, {5, 3, 1, 4}},   // 0 시계, 반시계
        {{0, 4, 2, 3}, {0, 3, 2, 4}},   // 1 시계, 반시계
        {{5, 3, 1, 4}, {5, 4, 1, 3}},   // 2 시계, 반시계
        {{0, 1, 2, 5}, {0, 5, 2, 1}},   // 3 시계, 반시계
        {{0, 5, 2, 1}, {0, 1, 2, 5}},   // 4 시계, 반시계
        {{0, 3, 2, 4}, {0, 4, 2, 3}}    // 5 시계, 반시계
    };
    public static int[][][][][] surface = {
        {   // {{5, 4, 1, 3}, {5, 3, 1, 4}},   // 0 시계, 반시계
            {{{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}},
            {{{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}, {{0, 1}, {0, 0}}}
        },
        {   // {{0, 4, 2, 3}, {0, 3, 2, 4}},   // 1 시계, 반시계
            {{{1, 0}, {1, 1}}, {{0, 0}, {1, 0}}, {{1, 0}, {1, 1}}, {{1, 1}, {0, 1}}},
            {{{1, 0}, {1, 1}}, {{1, 1}, {0, 1}}, {{1, 0}, {1, 1}}, {{0, 0}, {1, 0}}}
        },
        {   // {{5, 3, 1, 4}, {5, 4, 1, 3}},   // 2 시계, 반시계
            {{{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}},
            {{{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}, {{1, 1}, {1, 0}}}
        },
        {   // {{0, 1, 2, 5}, {0, 5, 2, 1}},   // 3 시계, 반시계
            {{{0, 0}, {1, 0}}, {{0, 0}, {1, 0}}, {{1, 1}, {0, 1}}, {{1, 1}, {0, 1}}},
            {{{0, 0}, {1, 0}}, {{1, 1}, {0, 1}}, {{1, 1}, {0, 1}}, {{0, 0}, {1, 0}}}
        },
        {   // {{0, 5, 2, 1}, {0, 1, 2, 5}},   // 4 시계, 반시계
            {{{1, 1}, {0, 1}}, {{0, 0}, {1, 0}}, {{0, 0}, {1, 0}}, {{1, 1}, {0, 1}}},
            {{{1, 1}, {0, 1}}, {{1, 1}, {0, 1}}, {{0, 0}, {1, 0}}, {{0, 0}, {1, 0}}}
        },
        {   // {{0, 3, 2, 4}, {0, 4, 2, 3}}    // 5 시계, 반시계
            {{{0, 0}, {0, 1}}, {{1, 0}, {0, 0}}, {{0, 0}, {0, 1}}, {{0, 1}, {1, 1}}},
            {{{0, 0}, {0, 1}}, {{0, 1}, {1, 1}}, {{0, 0}, {0, 1}}, {{1, 0}, {0, 0}}}
        },
    };
}