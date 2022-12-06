import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main10866 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        Deque<Integer> deque = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if(command.equals("push_front")) {
                int x = Integer.parseInt(st.nextToken());
                deque.addFirst(x);
                continue;
            } else if (command.equals("push_back")) {
                int x = Integer.parseInt(st.nextToken());
                deque.addLast(x);
                continue;
            } else {
                switch(command) {
                    case "pop_front":
                        if(deque.isEmpty()) {
                            bw.write("-1");
                        } else {
                            bw.write(Integer.toString(deque.pollFirst()));
                        }
                        break;
                    case "pop_back":
                        if(deque.isEmpty()) {
                            bw.write("-1");
                        } else {
                            bw.write(Integer.toString(deque.pollLast()));
                        }
                        break;
                    case "size":
                        bw.write(Integer.toString(deque.size()));
                        break;
                    case "empty":
                        if(deque.isEmpty()) {
                            bw.write("1");
                        } else {
                            bw.write("0");
                        }
                        break;
                    case "front":
                        if(deque.isEmpty()) {
                            bw.write("-1");
                        } else {
                            bw.write(Integer.toString(deque.getFirst()));
                        }
                        break;
                    case "back":
                        if(deque.isEmpty()) {
                            bw.write("-1");
                        } else {
                            bw.write(Integer.toString(deque.getLast()));
                        }
                        break;
               }
            }
            bw.newLine();
        }
 
        br.close();
        bw.flush();
        bw.close();
    }
}