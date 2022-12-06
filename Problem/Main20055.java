import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main20055 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int N, K, cnt;
    public static boolean[] robot;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        robot = new boolean[N * 2 + 1];
        arr = new int[N * 2 + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N * 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        int level = 1;
        while(true) {
            // 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            rotate();
            // put and remove
            downRobot();
            // 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다.
            moveRobot();
            downRobot();
            putRobot();
            if(cnt >= K) {
                break;
            }
            level++;
        }
        bw.write(Integer.toString(level));
        br.close();
        bw.flush();
        bw.close();
    }
    public static void rotate() {
        boolean tmpRobot = robot[2 * N];
        int tmp = arr[2 * N];
        for(int i = 2 * N; i > 1; i--) {
            arr[i] = arr[i - 1];
            robot[i] = robot[i - 1];
        }
        robot[1] = tmpRobot;
        arr[1] = tmp;
    }
    public static void downRobot() {
        if(robot[N]) {
            robot[N] = false;
        }
    }
    public static void moveRobot() {
        for(int i = N - 1; i >= 1; i--) {
            if(robot[i] && !robot[i + 1] && arr[i + 1] >= 1) {
                //이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
                robot[i + 1] = true;
                robot[i] = false;
                arr[i + 1] -= 1;
                if(arr[i + 1] == 0) {
                    cnt++;
                }
            }
        }
    }
    public static void putRobot() {
        if(arr[1] >= 1) {
            robot[1] = true;
            arr[1] -= 1;
            if(arr[1] == 0) {
                cnt++;
            }
        }
    }
}