import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main1436 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int index = 0;
        int res = 0;
        for(int i = 666; i <= Integer.MAX_VALUE; i++) {
            if(Integer.toString(i).contains("666")) {
                index++;
            }
            if(n == index) {
                res = i;
                break;
            }
        }
        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}