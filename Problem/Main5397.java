import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main5397 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static Stack<Character> left, right;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            left = new Stack<>();
            right = new Stack<>();
            char[] arr = br.readLine().toCharArray();
            for (char c : arr) {
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')) {
                    left.push(c);
                } else if (c == '<' && !left.isEmpty()) {
                    right.push(left.pop());
                } else if (c == '>' && !right.isEmpty()) {
                    left.push(right.pop());
                } else if (c == '-') {
                    if (!left.isEmpty()) {
                        left.pop();
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while(!left.isEmpty()) {
                sb.append(left.pop());
            }
            sb.reverse();
            while(!right.isEmpty()) {
                sb.append(right.pop());
            }
            bw.write(sb.toString());
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}