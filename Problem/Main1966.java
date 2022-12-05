import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1966 {
    public static class Print {
        int num;
        int priority;

        public Print(int num, int priority) {
            this.num = num;
            this.priority = priority;
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 문서의 개수
            int m = Integer.parseInt(st.nextToken()); // 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수

            LinkedList<Print> list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                list.add(new Print(i, Integer.parseInt(st.nextToken())));
            }

            Print target = list.get(m);
            int idx = 0;
            while(true) {
                // 현재 문서 다음에 중요한 문서가 있는 경우
                Print current = list.poll();
                boolean isPrint = true;
                for(int i = 0; i < list.size(); i++) {
                    if(current.priority < list.get(i).priority) {
                        list.add(current);
                        isPrint = false;
                        break;
                    } 
                    
                }
                if(isPrint) {
                    idx++;
                    if(current.num == target.num && current.priority == target.priority) {
                        break;
                    }
                }
            }
            System.out.println(idx);
        }
        
    }
}