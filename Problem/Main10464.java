import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main10464 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int[] arr;
    public static int S, F;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());
            int res = 0;
            if(S == F) {
                res = S;
            } else {
                arr = new int[4];

                arr[(S + 1) % 4] = S ^ (S + 1);
                arr[(S + 2) % 4] = arr[(S + 1) % 4] ^ (S + 2);
                arr[(S + 3) % 4] = arr[(S + 2) % 4] ^ (S + 3);
                arr[(S + 4) % 4] = arr[(S + 3) % 4] ^ (S + 4);

                if(F % 4 == 0) {
                    res = arr[3] ^ F;
                } else if (F % 4 == 2) {
                    res = arr[1] ^ F;
                } else {
                    res = arr[F % 4];
                }
            }
            sb.append(res).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
 3 ^ 4 = 7  // 0
 4 ^ 5 = 2  // 1
 5 ^ 6 = 4  // 2
 6 ^ 7 = 3  // 3

 7 ^ 8 = 11 // 0
 8 ^ 9 = 2  // 1
 9 ^ 10 = 8 // 2
10 ^ 11 = 3 // 3


13 ^ 14 = 3     // 2
14 ^ 15 = 12    // 3
15 ^ 16 = 28    // 0
16 ^ 17 = 13    // 1

17 ^ 18 = 31
18 ^ 19 = 12
19 ^ 20 = 24

20 ^ 21 = 13
21 ^ 22 = 27
22 ^ 23 = 12
23 ^ 24 = 20

24 ^ 25 = 13
25 ^ 26 = 23
26 ^ 27 = 12
27 ^ 28 = 16

28 ^ 29 = 13
29 ^ 30 = 19
30 ^ 31 = 12
31 ^ 32 = 44

32 ^ 33 = 13
...
41 ^ 42 =
42 ^ 43 = 12


666 ^ 667 = 1       // 667 % 4 = 3
667 ^ 668 = 669     // 668 % 4 = 0
668 ^ 669 = 0       // 669 % 4 = 1
669 ^ 670 = 670     // 670 % 4 = 2

670 ^ 671 = 1       // 3
671 ^ 672 = 673     // 0
672 ^ 673 = 0       // 1
673 ^ 674 = 674     // 2

1 669 0 670

*/