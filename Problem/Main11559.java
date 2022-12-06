import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main11559 {
    public static char[][] arr = new char[12][6];
    public static boolean[][] visited = new boolean[12][6];

    public static class Puyo {
        int r;
        int c;
        char color;

        public Puyo(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int i = 0; i < 12; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int res = 0;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(arr[i][j] == '.') {
                    visited[i][j] = true;
                } 
            }
        }
        while(true) {
            if(checkPuyo()) {
                downPuyo();
                res++;
            } else {
                break;
            }
        }

        System.out.println(res);
    }
    
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};

    public static boolean checkPuyo() { // 뿌요 4개 체크(동시)
        boolean chk = false;
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(arr[i][j] != '.' && !visited[i][j]) {    // 빈칸이 아니고 방문하지 않았으면
                    Queue<Puyo> chkQueue = new LinkedList<>();
                    LinkedList<Puyo> excQueue = new LinkedList<>();
                    chkQueue.add(new Puyo(i, j, arr[i][j]));
                    excQueue.add(new Puyo(i, j, arr[i][j]));
                    int cnt = 1;
                    visited[i][j] = true;
                    while(!chkQueue.isEmpty()) {
                        Puyo current = chkQueue.poll();
                        for(int k = 0; k < 4; k++) {
                            int newR = current.r + dr[k];
                            int newC = current.c + dc[k];
                            if(newR >= 0 && newR < 12 && newC >= 0 && newC < 6 && arr[newR][newC] == arr[current.r][current.c] && !visited[newR][newC]) {
                                chkQueue.add(new Puyo(newR, newC, arr[i][j]));
                                excQueue.add(new Puyo(newR, newC, arr[i][j]));
                                visited[newR][newC] = true;
                                cnt++;
                            }
                        }
                    }

                    if(cnt >= 4) {
                        for(Puyo p : excQueue) { // 터짐
                            arr[p.r][p.c] = '.';
                        }
                        chk = true;
                    } else {
                        while(!excQueue.isEmpty()) {
                            Puyo current = excQueue.poll();
                            visited[current.r][current.c] = false;
                        }
                    }
                }
            }
        }

        return chk;
    }
    public static void downPuyo() {
        for(int j = 0; j < 6; j++) {
            LinkedList<Puyo> list = new LinkedList<>();
            for(int i = 11; i >= 0; i--) {
                if(arr[i][j] != '.') {
                    list.add(new Puyo(i, j, arr[i][j]));
                }
            }
            for(int i = 11; i >= 0; i--) {
                if(list.isEmpty()){
                    arr[i][j] = '.';
                } else {
                    arr[i][j] = list.poll().color;
                } 
            }
        }
        for(int i = 0; i < 12; i++) {
            for(int j = 0; j < 6; j++) {
                if(arr[i][j] == '.') {
                    visited[i][j] = true;
                } else {
                    visited[i][j] = false;
                }
            }
        }
    }
}
/*
......
......
......
......
......
......
......
......
.Y....
.YG...
RRYG..
RRYGG. 

......
......
......
......
......
......
......
......
......
......
......
......

......
......
......
......
..R...
GBR...
RRY...
GRY...
YYR...
GYR...
RRPP..
GRPP..

RRRRRR
RRRRRR
RRRRRR
RRRRRR
RRRRRR
RRBBRR
RRRRRR
RRRRRR
RRRRRR
RRBBRR
RRRRRR
RRRRRR
*/