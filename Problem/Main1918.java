import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
/* 
참고 : https://woongsios.tistory.com/288
*/
public class Main1918 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<Character> stack;
    public static void main(String[] args) throws Exception {
        stack = new Stack<>();
        String str = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);

            if(current >= 'A' && current <= 'Z') {
                sb.append(current);
            } else {
                /* 
                    우선순위  
                    1. ( )
                    2. * /
                    3. + -
                */
                if(current == '(') {
                    stack.add(current);
                } else if(current == ')') {
                    while(stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop(); // 남아있는 ( 제거
                } else if(current == '*' || current == '/') {
                    if(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        // 스택의 맨 끝 * 와 / 는 연산자 우선순위가 같으므로 맨 끝을 붙이고 자신을 스택에 넣는다
                        sb.append(stack.pop());
                    }
                    // 스택이 비어있으면 우선순위 가 없으니 바로 붙인다.
                    stack.add(current);
                } else if(current == '+' || current == '-') {
                    // +와 -가 우선순위가 높은 경우는 괄호가 있을 때이다.
                    while(!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());   
                    }
                    stack.add(current);
                }
            }
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();

    }
}