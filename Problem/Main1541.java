import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main1541 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static String str;
    public static String[] tmpArr;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        str = br.readLine();
        ArrayList<Character> oper = new ArrayList<>();
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '+' || str.charAt(i) == '-') {
                oper.add(str.charAt(i));
            }
        }
        tmpArr = str.split("[+-]");
        arr = new int[tmpArr.length];
        for(int i = 0; i < tmpArr.length; i++) {
            arr[i] = Integer.parseInt(tmpArr[i]);
        }
        int res = arr[0];
        boolean isMinus = false;
        for(int i = 0; i < oper.size(); i++) {
            if(!isMinus && oper.get(i) == '-') {
                isMinus = true;
            }
            if(isMinus) {
                res += (arr[i + 1] * -1);
            } else {
                res += arr[i + 1];
            }
        }

        bw.write(Integer.toString(res));
        br.close();
        bw.flush();
        bw.close();
    }
}