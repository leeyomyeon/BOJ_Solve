import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main4577 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, startR, startC, ansCnt, boxCnt;
    public static char[][] arr;
    public static void main(String[] args) throws Exception {
        int tc = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(R == 0 && C == 0) {
                break;
            }
            arr = new char[R][C];
            ansCnt = 0;
            boxCnt = 0;
            for(int i = 0; i < R; i++) {
                String str = br.readLine();
                for(int j = 0; j < C; j++) {
                    arr[i][j] = str.charAt(j);
                    if(arr[i][j] == 'w' || arr[i][j] == 'W') {
                        if(arr[i][j] == 'w') {
                            arr[i][j] = '.';
                        } else {
                            arr[i][j] = '+';
                        }
                        startR = i;
                        startC = j;
                    }
                    if(arr[i][j] == '+') {
                        ansCnt += 1;
                    }
                    if(arr[i][j] == 'B') {
                        boxCnt += 1;
                        ansCnt += 1;
                    }
                }
            }
            String str = br.readLine();
            for(int i = 0; i < str.length(); i++) {
                char command = str.charAt(i);
                int d = -1;
                if(command == 'U') { d = 0; }
                else if (command == 'D') { d = 1; }
                else if (command == 'L') { d = 2; }
                else if (command == 'R') { d = 3; }
                if(ansCnt == boxCnt) {
                    break;
                }
                move(d);
            }
            if(arr[startR][startC] == '.') {
                arr[startR][startC] = 'w';
            } else if (arr[startR][startC] == '+') {
                arr[startR][startC] = 'W';
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(arr[i][j]);
                }
                sb.append("\n");
            }
            if(ansCnt == boxCnt) {
                bw.write("Game " + tc + ": complete\n");
            } else {
                bw.write("Game " + tc + ": incomplete\n");
            }
            bw.write(sb.toString());
            tc++;
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static void move(int d) {
        // 바라보는 앞칸
        int nr = startR + dr[d];
        int nc = startC + dc[d];
        if(arr[nr][nc] == '.' || arr[nr][nc] == '+') {
            // 빈칸이면 이동
            startR = nr;
            startC = nc;
        } else if(arr[nr][nc] == 'b' || arr[nr][nc] == 'B') {
            // 박스가 있는지?
            int boxR = startR + dr[d] * 2;  // 박스 앞 좌료
            int boxC = startC + dc[d] * 2;  // 박스 앞 좌표
            if(arr[boxR][boxC] == '.' || arr[boxR][boxC] == '+') {
                // 빈 칸이면 한칸 이동함
                if(arr[nr][nc] == 'b') {
                    arr[nr][nc] = '.';
                } else if(arr[nr][nc] == 'B') {
                    boxCnt -= 1;
                    arr[nr][nc] = '+';
                }
                if(arr[boxR][boxC] == '.') {
                    arr[boxR][boxC] = 'b';
                } else {
                    boxCnt += 1;
                    arr[boxR][boxC] = 'B';
                }
                startR = nr;
                startC = nc;
            }
        }
    }
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

}