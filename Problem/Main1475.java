import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1475 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {

        int[] arr = new int[10];

        String[] str = br.readLine().split("");
        for(String s : str) {
            arr[Integer.parseInt(s)]++;
        }
        arr[6] += (arr[9] + 1);
        arr[6] /= 2;
        arr[9] = 0;
        int max = Integer.MIN_VALUE;
        for(int i : arr) {
            max = i > max ? i : max;
        }
        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
        bw.close();
    }
}