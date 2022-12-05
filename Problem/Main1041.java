import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main1041 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static StringBuffer sb = new StringBuffer();
    public static int[] arr;
    public static int N;
    public static int[][] plain2 = {{3,4},{3,0},{3,1},{3,5},
                                    {2,4},{2,0},{2,1},{2,5},
                                    {0,1},{1,5},{5,4},{4,0}};
    public static int[][] plain3 = {{3,5,4},{3,5,1},{3,0,1},{3,0,4},
                                    {2,5,4},{2,5,1},{2,0,1},{2,0,4}};
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[6];
        long min1 = Integer.MAX_VALUE; // 한 면의 최소값
        for(int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            min1 = Math.min(arr[i], min1);
        }
        long res = 0;
        if(N == 1) {
            long min5 = Integer.MAX_VALUE;
            for(int i = 0; i < 6; i++) {
                int sum = 0;
                for(int j = 0; j < 6; j++) {
                    if(j == i) {
                        continue;
                    }
                    sum += arr[j];
                }
                min5 = Math.min(min5, sum);
            }
            res = min5;
        } else {
            // (Math.pow(N - 2, 2) + (N - 2)) * 5 -> 한 면의 개수
            // 4개 -> 3 모서리 개수
            // (2 * N - 3) * 4 -> 2모서리 개수
            long min2 = Integer.MAX_VALUE; // 인접한 두 면의 최소값
            long min3 = Integer.MAX_VALUE; // 인접한 세 면의 최소값

            for (int[] value : plain2) {
                min2 = Math.min(min2, arr[value[0]] + arr[value[1]]);
            }
            for (int[] ints : plain3) {
                min3 = Math.min(min3, arr[ints[0]] + arr[ints[1]] + arr[ints[2]]);
            }
            res += min1 * ((long) Math.pow(N - 2, 2) * 5 + (N - 2) * 4L);
            res += min2 * (2L * N - 3) * 4;
            res += min3 * 4;
        }
        bw.write(Long.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}