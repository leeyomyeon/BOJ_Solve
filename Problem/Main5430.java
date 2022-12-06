import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
public class Main5430 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            String func = br.readLine();

            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");

            if(n == 0) {
                if(func.contains("D")) {
                    System.out.println("error");
                } else {
                    System.out.println("[]");
                }
                continue;
            }
            Deque<String> list = new LinkedList<>();
            while(st.hasMoreTokens()) {
                list.add(st.nextToken());
            }

            int reverse = 1;
            boolean chkFlag = false;
            for (int i = 0; i < func.length(); i++) {
                char c = func.charAt(i);
                if(c == 'R') {
                    reverse *= -1;
                } else if(c == 'D') {
                    if(list.size() == 0) {
                        System.out.println("error");
                        chkFlag = true;
                        break;
                    }
                    if(reverse == -1) { // 맨 뒤에거 하나 꺼냄
                        list.pollLast();
                    } else {            // 맨 앞에거 하나 꺼냄
                        list.pollFirst();
                    }
                }
            }
            if(chkFlag) {
                continue;
            }
            
            StringBuilder array = new StringBuilder("[");
            if(reverse == -1) {
                while(!list.isEmpty()) {
                    array.append(list.pollLast()).append(",");
                }
            } else {
                while(!list.isEmpty()) {
                    array.append(list.pollFirst()).append(",");
                }
            }
            int cidx = array.lastIndexOf(",");
            if(cidx >= 0) {
                array.setCharAt(cidx, ']');
            } else {
                array.append(']');
            }

            System.out.println(array);
        }
        
    }
} 