import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1917 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int[][] arr;
    public static void main(String[] args) throws Exception {
        for(int tc = 0; tc < 1; tc++) {
            arr = new int[6][6];
            for(int i = 0; i < 6; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 6; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean res = check();
            bw.write(res ? "yes\n" : "no\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static boolean check() {
        for (int k = 0; k < square.length; k++) {
            for (int r = 0; r <= 3; r++) {
                int[][] chkArr = rotate(square[k], r);
//                for(int[]chk : chkArr) {
//                    System.out.println(Arrays.toString(chk));
//                }
                for (int i = 0; i < 6 - chkArr.length + 1; i++) {
                    Loop1:
                    for (int j = 0; j < 6 - chkArr[0].length + 1; j++) {
                        boolean chk = false;
                        for (int m = 0; m < chkArr.length; m++) {
                            for (int n = 0; n < chkArr[0].length; n++) {
                                if (arr[i + m][j + n] != chkArr[m][n]) {
                                    continue Loop1;
                                }
                                chk = true;
                            }
                        }
                        if (chk) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int[][] rotate(int[][] target, int r) {
        int[][] res;
        int tr = target.length; // 3
        int tc = target[0].length; // 4
        if(r == 1 || r == 3)  { // 90, 270
            res = new int[tc][tr];
            for(int i = 0; i < tc; i++) {
                for(int j = 0; j < tr; j++) {
                    res[i][j] = target[r == 1 ? tr - j - 1 : j][r == 1 ? i : tc - i - 1];
                }
            }
        } else if(r == 2) { // 180
            res = new int[tr][tc];
            for(int i = 0; i < tr; i++) {
                for(int j = 0; j < tc; j++) {
                    res[i][j] = target[tr - 1 - i][tc - 1 - j];
                }
            }
        } else {
            return target;
        }
        return res;
    }
    public static int[][][] square = {
            {{1, 0, 0, 0},
             {1, 1, 1, 1},
             {1, 0, 0, 0}}, // 1
            {{0, 1, 0, 0},
             {1, 1, 1, 1},
             {1, 0, 0, 0}}, // 2
            {{0, 0, 1, 0},
             {1, 1, 1, 1},
             {1, 0, 0, 0}}, // 3
            {{0, 0, 0, 1},
             {1, 1, 1, 1},
             {1, 0, 0, 0}}, // 4
            {{0, 1, 0, 0},
             {1, 1, 1, 1},
             {0, 1, 0, 0}}, // 5
            {{0, 0, 1, 0},
             {1, 1, 1, 1},
             {0, 1, 0, 0}}, // 6
            {{0, 0, 1, 1},
             {0, 1, 1, 0},
             {1, 1, 0, 0}}, // 8
            {{0, 0, 1, 1},
             {1, 1, 1, 0},
             {1, 0, 0, 0}}, // 9
            {{1, 1, 0, 0},
             {0, 1, 1, 1},
             {0, 1, 0, 0}}, // 10
            {{0, 1, 0, 0},
             {1, 1, 1, 0},
             {0, 0, 1, 1}}, // 11
            {{0, 0, 1, 1, 1},
             {1, 1, 1, 0, 0}}, // 7
            //  --------------------------------------- 회전해도 똑같은모양이 나오는건 제외
            {{0, 0, 1, 0},
             {1, 1, 1, 1},
             {0, 0, 0, 1}}, // 2
            {{0, 1, 0, 0},
             {1, 1, 1, 1},
             {0, 0, 0, 1}}, // 3
            {{1, 0, 0, 0},
             {1, 1, 1, 1},
             {0, 0, 0, 1}}, // 4
            {{0, 1, 0, 0},
             {1, 1, 1, 1},
             {0, 0, 1, 0}}, // 6
            {{1, 1, 0, 0},
             {0, 1, 1, 0},
             {0, 0, 1, 1}}, // 8
            {{1, 1, 0, 0},
             {0, 1, 1, 1},
             {0, 0, 0, 1}}, // 9
            {{0, 0, 1, 1},
             {1, 1, 1, 0},
             {0, 0, 1, 0}}, // 10
            {{0, 0, 1, 0},
             {0, 1, 1, 1},
             {1, 1, 0, 0}}, // 11
            {{1, 1, 1, 0, 0},
             {0, 0, 1, 1, 1}} // 7
    };
}