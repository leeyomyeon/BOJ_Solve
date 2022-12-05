import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main1920 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();
        int k;
        while(st.hasMoreTokens()) {
            k = Integer.parseInt(st.nextToken());
            set.add(k);
        }
        br.readLine();
        st = new StringTokenizer(br.readLine());
        int j;
        while(st.hasMoreTokens()) {
            j = Integer.parseInt(st.nextToken());
            if(set.contains(j)) {
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}