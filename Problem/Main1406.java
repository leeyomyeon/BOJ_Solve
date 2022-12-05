import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main1406 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static Stack<Character> leftStack, rightStack;
    public static void main(String[] args) throws Exception {
        leftStack = new Stack<>();      // 커서 왼쪽
        rightStack = new Stack<>();   // 커서 오른쪽
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            leftStack.add(str.charAt(i));
        }
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("L")) {
                if(!leftStack.isEmpty()) {
                    rightStack.add(leftStack.pop());
                }
            } else if(command.equals("D")) {
                if(!rightStack.isEmpty()) {
                    leftStack.add(rightStack.pop());
                }
            } else if(command.equals("B")) {
                if(!leftStack.isEmpty()) {
                    leftStack.pop();
                }
            } else {
                char target = st.nextToken().charAt(0);
                leftStack.add(target);
            }
        }
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        while(!leftStack.isEmpty()) {
            left.append(leftStack.pop());
        }
        while(!rightStack.isEmpty()) {
            right.append(rightStack.pop());
        }
        bw.write(left.reverse().append(right).toString());
        br.close();
        bw.flush();
        bw.close();
    }
}