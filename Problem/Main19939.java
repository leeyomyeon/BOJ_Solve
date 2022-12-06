import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main19939 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int sum = K * (K + 1) / 2;
        if(N < sum){
            bw.write("-1");
        }else if((N - sum) % K == 0) {
            bw.write(Integer.toString(K - 1));
        }else {
            bw.write(Integer.toString(K));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
/*
10
45 123456789 10 -> 9
46 123456789 11
47 12345678 10 11
48 1234567 9 10 11
49 123456 8 9 10 11
50 12345 7 8 9 10 11
51 1234 6 7 8 9 10 11
52 123 5 6 7 8 9 10 11
53 12 4 5 6 7 8 9 10 11  -> 10
54 1 3 4 5 6 7 8 9 10 11 -> 10
55 2 3 4 5 6 7 8 9 10 11 -> 9

45 55 65 75 85 95 ...
4
10 1234 -> 3
11 1235 -> 4
12 1245 -> 4
13 1345 -> 4
14 2345 -> 3
15 2346 -> 4
16 2356 -> 4
17 2456 -> 4
18 3456 -> 3
10 14 18 22 26 ...

2
2 12
*/