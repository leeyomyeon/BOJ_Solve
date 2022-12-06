import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main4659 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        while(true) {
            StringBuilder sb = new StringBuilder(br.readLine());
            if(sb.toString().equals("end")) {
                break;
            }
            boolean cond1 = false;
            boolean cond2 = false;
            boolean cond3 = false;
            if(sb.indexOf("a") >= 0 || sb.indexOf("e") >= 0 || sb.indexOf("i") >= 0 || sb.indexOf("o") >= 0 || sb.indexOf("u") >= 0) {
                cond1 = true;
            }

            String str = sb.toString();

            if(str.length() >= 3) {
                for(int i = 0; i < str.length() - 2; i++) {
                    if(
                        ( isCheck(str.charAt(i)) && isCheck(str.charAt(i + 1)) && isCheck(str.charAt(i + 2)) ) ||
                        ( !isCheck(str.charAt(i)) && !isCheck(str.charAt(i + 1)) && !isCheck(str.charAt(i + 2)) )
                    ) {
                        cond2 = false;
                        break;
                    } else {
                        cond2 = true;
                    }
                }
            } else {
                cond2 = true;
            }

            if(str.length() >= 2) {
                for(int i = 0; i < str.length() - 1; i++) {
                    if(str.charAt(i) == str.charAt(i + 1) && !(str.charAt(i) == 'e' || str.charAt(i) == 'o')) {
                        cond3 = false;
                        break;
                    } else {
                        cond3 = true;
                    }
                }
            } else {
                cond3 = true;
            }


            if(cond1 && cond2 && cond3) {
                bw.write("<" + str + "> is acceptable.");
            } else {
                bw.write("<" + str + "> is not acceptable.");
            }
            bw.newLine();

        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isCheck(char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        } else {
            return false;
        }
    }
}