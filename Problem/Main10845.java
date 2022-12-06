import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main10845 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        Queue<Integer> queue = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        int last = 0;
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int x = 0;
            if(command.equals("push")) {
                x = Integer.parseInt(st.nextToken());
                last = x;
                queue.add(x);
            } else {
                switch(command){
                    case "pop":
                        if(queue.isEmpty()) {
                            bw.write("-1");
                        } else {
                            bw.write(Integer.toString(queue.poll()));
                        }
                        break;
                    case "size":
                        bw.write(Integer.toString(queue.size()));
                        break;
                    case "empty":
                        if(queue.isEmpty()) {
                            bw.write("1");
                        } else {
                            bw.write("0");
                        }
                        break;
                    default:
                        if(queue.isEmpty()) {
                            bw.write("-1");
                        } else {
                            if(command.equals("front")) {
                                bw.write(Integer.toString(queue.peek()));
                            } else {
                                bw.write(Integer.toString(last));
                            }
                        }
                }
                bw.newLine();
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}