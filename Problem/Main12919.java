import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main12919 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static String S, T;
    public static void main(String[] args) throws Exception {
        S = br.readLine();
        T = br.readLine();
        boolean res = recursion(T);
        if(res) {
            bw.write("1");
        } else {
            bw.write("0");
        }
        br.close();
        bw.flush();
        bw.close();
    }
    public static boolean recursion(String target) {
        if(target.length() == S.length()) {
            if(S.equals(target)) {
                return true;
            }
            return false;
        }
        if(target.charAt(target.length() - 1) == 'A') {
            if(recursion(target.substring(0, target.length() - 1))) {
                return true;
            }
        }
        if(target.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(target.substring(1)).reverse();
            if(recursion(sb.toString())) {
                return true;
            }
        }
        return false;
    }
}