import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main7785 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        TreeSet<String> set = new TreeSet<>();
        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String command = st.nextToken();
            switch(command) {
                case "enter":
                    set.add(name);
                    break;
                case "leave":
                    set.remove(name);
                    break;
            }
        }

        for(Iterator<String> itr = set.descendingIterator(); itr.hasNext();) {
            bw.write(itr.next());
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}