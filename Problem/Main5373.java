import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main5373 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static char[][][] arr;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            initArr();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                String command = st.nextToken();
                char r = command.charAt(0);
                int idx = -1;
                if(r == 'U') { idx = 0; }
                else if (r == 'F') { idx = 1; }
                else if (r == 'D') { idx = 2; }
                else if (r == 'B') { idx = 3; }
                else if (r == 'L') { idx = 4; }
                else if (r == 'R') { idx = 5; }
                int dir = command.charAt(1) == '+' ? 0 : 1;
                rotate(idx, dir);
            }
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    bw.write(arr[0][i][j]);
                }
                bw.newLine();
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void initArr() {
        char[][][] cube = {
            {{'w','w','w'},    // 윗면 0
             {'w','w','w'}, 
             {'w','w','w'}},
            {{'r','r','r'},    // 앞면 1
             {'r','r','r'}, 
             {'r','r','r'}},
            {{'y','y','y'},    // 밑면 2
             {'y','y','y'}, 
             {'y','y','y'}},
            {{'o','o','o'},    // 뒷면 3
             {'o','o','o'}, 
             {'o','o','o'}},
            {{'g','g','g'},    // 왼쪽 4
             {'g','g','g'}, 
             {'g','g','g'}},
            {{'b','b','b'},    // 오른쪽 5
             {'b','b','b'}, 
             {'b','b','b'}}
        };
        arr = cube;
    }

    public static void rotate(int idx, int dir) {
        rotateArr(idx, dir);
        rotateSurface(idx, dir);
    }

    public static void rotateArr(int idx, int dir) {
        // 바라보는 방향 회전
        /*            시계        반시계
        00 01 02    20 10 00    02 12 22
        10 11 12 -> 21 11 01    01 11 21
        20 21 22    22 12 02    00 10 20
        */
        char[][] tmp = new char[3][3];
        if(dir == 0) {  // 시계
            tmp[0][0] = arr[idx][2][0];
            tmp[0][1] = arr[idx][1][0];
            tmp[0][2] = arr[idx][0][0];
            tmp[1][0] = arr[idx][2][1];
            tmp[1][1] = arr[idx][1][1];
            tmp[1][2] = arr[idx][0][1];
            tmp[2][0] = arr[idx][2][2];
            tmp[2][1] = arr[idx][1][2];
            tmp[2][2] = arr[idx][0][2];
        } else {    // 반시계
            tmp[0][0] = arr[idx][0][2];
            tmp[0][1] = arr[idx][1][2];
            tmp[0][2] = arr[idx][2][2];
            tmp[1][0] = arr[idx][0][1];
            tmp[1][1] = arr[idx][1][1];
            tmp[1][2] = arr[idx][2][1];
            tmp[2][0] = arr[idx][0][0];
            tmp[2][1] = arr[idx][1][0];
            tmp[2][2] = arr[idx][2][0];
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                arr[idx][i][j] = tmp[i][j];
            }
        }
    }
    public static void rotateSurface(int idx, int dir) {
        char[][] tmp = new char[4][3];
        // U 0 / F 1 / D 2 / B 3 / L 4 / R 5
        //[면][방향][회전순서][행][열]
        tmp[0][0] = arr[order[idx][dir][0]][surface[idx][dir][0][0][0]][surface[idx][dir][0][0][1]];
        tmp[0][1] = arr[order[idx][dir][0]][surface[idx][dir][0][1][0]][surface[idx][dir][0][1][1]];
        tmp[0][2] = arr[order[idx][dir][0]][surface[idx][dir][0][2][0]][surface[idx][dir][0][2][1]];

        tmp[1][0] = arr[order[idx][dir][1]][surface[idx][dir][1][0][0]][surface[idx][dir][1][0][1]];
        tmp[1][1] = arr[order[idx][dir][1]][surface[idx][dir][1][1][0]][surface[idx][dir][1][1][1]];
        tmp[1][2] = arr[order[idx][dir][1]][surface[idx][dir][1][2][0]][surface[idx][dir][1][2][1]];

        tmp[2][0] = arr[order[idx][dir][2]][surface[idx][dir][2][0][0]][surface[idx][dir][2][0][1]];
        tmp[2][1] = arr[order[idx][dir][2]][surface[idx][dir][2][1][0]][surface[idx][dir][2][1][1]];
        tmp[2][2] = arr[order[idx][dir][2]][surface[idx][dir][2][2][0]][surface[idx][dir][2][2][1]];

        tmp[3][0] = arr[order[idx][dir][3]][surface[idx][dir][3][0][0]][surface[idx][dir][3][0][1]];
        tmp[3][1] = arr[order[idx][dir][3]][surface[idx][dir][3][1][0]][surface[idx][dir][3][1][1]];
        tmp[3][2] = arr[order[idx][dir][3]][surface[idx][dir][3][2][0]][surface[idx][dir][3][2][1]];

        // 다음 값 저장
        arr[order[idx][dir][0]][surface[idx][dir][0][0][0]][surface[idx][dir][0][0][1]] = tmp[3][0];
        arr[order[idx][dir][0]][surface[idx][dir][0][1][0]][surface[idx][dir][0][1][1]] = tmp[3][1];
        arr[order[idx][dir][0]][surface[idx][dir][0][2][0]][surface[idx][dir][0][2][1]] = tmp[3][2];

        arr[order[idx][dir][1]][surface[idx][dir][1][0][0]][surface[idx][dir][1][0][1]] = tmp[0][0];
        arr[order[idx][dir][1]][surface[idx][dir][1][1][0]][surface[idx][dir][1][1][1]] = tmp[0][1];
        arr[order[idx][dir][1]][surface[idx][dir][1][2][0]][surface[idx][dir][1][2][1]] = tmp[0][2];

        arr[order[idx][dir][2]][surface[idx][dir][2][0][0]][surface[idx][dir][2][0][1]] = tmp[1][0];
        arr[order[idx][dir][2]][surface[idx][dir][2][1][0]][surface[idx][dir][2][1][1]] = tmp[1][1];
        arr[order[idx][dir][2]][surface[idx][dir][2][2][0]][surface[idx][dir][2][2][1]] = tmp[1][2];

        arr[order[idx][dir][3]][surface[idx][dir][3][0][0]][surface[idx][dir][3][0][1]] = tmp[2][0];
        arr[order[idx][dir][3]][surface[idx][dir][3][1][0]][surface[idx][dir][3][1][1]] = tmp[2][1];
        arr[order[idx][dir][3]][surface[idx][dir][3][2][0]][surface[idx][dir][3][2][1]] = tmp[2][2];

    }
    public static int[][][] order = {
        {{1, 4, 3, 5}, 
         {1, 5, 3, 4}},   // 0 윗면
        {{0, 5, 2, 4}, 
         {0, 4, 2, 5}},   // 1 앞면
        {{1, 5, 3, 4}, 
         {1, 4, 3, 5}},   // 2 밑면
        {{0, 4, 2, 5}, 
         {0, 5, 2, 4}},   // 3 뒷면
        {{0, 1, 2, 3}, 
         {0, 3, 2, 1}},   // 4 왼쪽
        {{0, 3, 2, 1}, 
         {0, 1, 2, 3}}    // 5 오른쪽
    };
    public static int[][][][][] surface = {
        {// idx
            {{{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}}, // 시계 dir
            {{{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}, {{0,0}, {0,1}, {0,2}}} // 반시계
            /*
            윗면(0) 시계 
            1[00 01 02]->4[00 01 02] 4[00 01 02]->3[00 01 02] 3[00 01 02]->5[00 01 02] 5[00 01 02]->1[00 01 02]
            윗면(0) 반시계
            1[00 01 02]->5[00 01 02] 5[00 01 02]->3[00 01 02] 3[00 01 02]->4[00 01 02] 4[00 01 02]->1[00 01 02]
            */
        },
        {
            {{{2,0}, {2,1}, {2,2}}, {{0,0}, {1,0}, {2,0}}, {{2,0}, {2,1}, {2,2}}, {{2,2}, {1,2}, {0,2}}}, // 시계
            {{{2,0}, {2,1}, {2,2}}, {{2,2}, {1,2}, {0,2}}, {{2,0}, {2,1}, {2,2}}, {{0,0}, {1,0}, {2,0}}} // 반시계
            /* 
            앞면(1) 시계 
            0[20 21 22]->5[00 10 20] 5[00 10 20]->2[20 21 22] 2[20 21 22]->4[22 12 02] 4[22 12 02]->0[20 21 22]
            앞면(1) 반시계
            0[20 21 22]->4[22 12 02] 4[22 12 02]->2[20 21 22] 2[20 21 22]->5[00 10 20] 5[00 10 20]->0[20 21 22]
            */
        },  
        {
            {{{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}}, // 시계
            {{{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}, {{2,0}, {2,1}, {2,2}}} // 반시계
            /* 
            밑면(2) 시계 
            1[20 21 22]->5[20 21 22] 5[20 21 22]->3[20 21 22] 3[20 21 22]->4[20 21 22] 4[20 21 22]->1[20 21 22]
            밑면(2) 반시계
            1[20 21 22]->4[20 21 22] 4[20 21 22]->3[20 21 22] 3[20 21 22]->5[20 21 22] 5[20 21 22]->1[20 21 22]
            */
        },  
        {
            {{{0,0}, {0,1}, {0,2}}, {{2,0}, {1,0}, {0,0}}, {{0,0}, {0,1}, {0,2}}, {{0,2}, {1,2}, {2,2}}}, // 시계
            {{{0,0}, {0,1}, {0,2}}, {{0,2}, {1,2}, {2,2}}, {{0,0}, {0,1}, {0,2}}, {{2,0}, {1,0}, {0,0}}} // 반시계
            /* 
            뒷면(3) 시계
            0[00 01 02]->4[20 10 00] 4[20 10 00]->2[00 01 02] 2[00 01 02]->5[02 12 22] 5[02 12 22]->0[00 01 02]
            뒷면(3) 반시계
            0[00 01 02]->5[02 12 22] 5[02 12 22]->2[00 01 02] 2[00 01 02]->4[20 10 00] 4[20 10 00]->0[00 01 02]
            */
        },
        {
            {{{0,0}, {1,0}, {2,0}}, {{0,0}, {1,0}, {2,0}}, {{2,2}, {1,2}, {0,2}}, {{2,2}, {1,2}, {0,2}}}, // 시계
            {{{0,0}, {1,0}, {2,0}}, {{2,2}, {1,2}, {0,2}}, {{2,2}, {1,2}, {0,2}}, {{0,0}, {1,0}, {2,0}}} // 반시계
            /* 
            왼쪽(4) 시계
            0[00 10 20]->1[00 10 20] 1[00 10 20]->2[22 12 02] 2[22 12 02]->3[22 12 02] 3[22 12 02]->0[00 10 20]
            왼쪽(4) 반시계
            0[00 10 20]->3[22 12 02] 3[22 12 02]->2[22 12 02] 2[22 12 02]->1[00 10 20] 1[00 10 20]->0[00 10 20]
            */
        },
        {
            {{{2,2}, {1,2}, {0,2}}, {{0,0}, {1,0}, {2,0}}, {{0,0}, {1,0}, {2,0}}, {{2,2}, {1,2}, {0,2}}}, // 시계
            {{{2,2}, {1,2}, {0,2}}, {{2,2}, {1,2}, {0,2}}, {{0,0}, {1,0}, {2,0}}, {{0,0}, {1,0}, {2,0}}} // 반시계
            /*
            오른쪽(5) 시계 
            0[22 12 02]->3[00 10 20] 3[00 10 20]->2[00 10 20] 2[00 10 20]->1[22 12 02] 1[22 12 02]->0[22 12 02]
            오른쪽(5) 반시계
            0[22 12 02]->1[22 12 02] 1[22 12 02]->2[00 10 20] 2[00 10 20]->3[00 10 20] 3[00 10 20]->0[22 12 02]
            */
        }
    };
}