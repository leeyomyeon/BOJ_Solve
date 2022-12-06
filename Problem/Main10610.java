import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main10610 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int[] arr = new int[10];
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - '0']++;
        }
        if(arr[0] == 0) {
            bw.write("-1");
        } else {
            int chkSum = 0;
            StringBuffer sb = new StringBuffer();
            for(int i = 9; i >= 0; i--) {
                for(int j = 0; j < arr[i]; j++) {
                    chkSum += i;
                    sb.append(String.valueOf(i));
                }
            }
            if(chkSum % 3 != 0) {
                bw.write("-1");
            } else {
                bw.write(sb.toString());
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}