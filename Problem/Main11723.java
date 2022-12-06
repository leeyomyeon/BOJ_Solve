import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main11723 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new LinkedList<>();
        for(int i = 1; i <= 20; i++) {
            list.add(i);
        }
        for(int tc = 0; tc < N; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int arg = 0;
            if(!(command.equals("all") || command.equals("empty"))) {
                arg = Integer.parseInt(st.nextToken());
            }
            switch(command) {
                case "add":
                    if(!set.contains(arg)) {
                        set.add(arg);
                    }
                    break;
                case "remove":
                    if(set.contains(arg)) {
                        set.remove(arg);
                    }
                    break;
                case "check":
                    if(set.contains(arg)) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                    bw.newLine();
                    break;
                case "toggle":
                    if(set.contains(arg)) {
                        set.remove(arg);
                    } else {
                        set.add(arg);
                    }
                    break;
                case "all":
                    set.addAll(list);
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}