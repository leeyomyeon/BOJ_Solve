import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1011 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            if(y - x <= 2) {
                bw.write((y - x) + "\n");
            } else {
                bw.write(Integer.toString((int) Math.sqrt(4 * (y - x) - 1)));
                bw.newLine();
            }
        }
        bw.write("");
        br.close();
        bw.flush();
        bw.close();
    }
}
/*

1 ~ 2N까지 2의 배수 합 = N * (N + 1)
1(1) 1
2(2) 1 1
-- N = 1 f(N) = 2
3(3) 1 1 1
4(3) 1 2 1
5(4) 1 2 1 1
6(4) 1 2 2 1
-- N = 2 f(N) = 6
7(5) 1 2 2 1 1
8(5) 1 2 2 2 1
9(5) 1 2 3 2 1
10(6) 1 2 3 2 1 1
11(6) 1 2 3 2 2 1
12(6) 1 2 3 3 2 1
-- N = 3 f(N) = 12
13(7) 1 2 3 3 2 1 1
14(7) 1 2 3 3 2 2 1
15(7) 1 2 3 3 3 2 1
16(7) 1 2 3 4 3 2 1
17(8) 1 2 3 4 3 2 1 1
18(8) 1 2 3 4 3 2 2 1
19(8) 1 2 3 4 3 3 2 1
20(8) 1 2 3 4 4 3 2 1
-- N = 4 f(N) = 20
21(9) 1 2 3 4 4 3 2 1 1
22(9) 1 2 3 4 4 3 2 2 1
23(9) 1 2 3 4 4 3 3 2 1
24(9) 1 2 3 4 4 4 3 2 1
25(9) 1 2 3 4 5 4 3 2 1
26
27
28
29
30
-- N = 5 f(N) = 30
-- N = 6 f(N) = 42
*/
