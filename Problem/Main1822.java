import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main1822 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int A, B;
    public static HashSet<Integer> set;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        set = new HashSet<>();
        StringTokenizer a = new StringTokenizer(br.readLine());
        StringTokenizer b = new StringTokenizer(br.readLine());
        for(int i = 0; i < B; i++) {
            set.add(Integer.parseInt(b.nextToken()));
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < A; i++) {
            int k = Integer.parseInt(a.nextToken());
            if(!set.contains(k)) {
                list.add(k);
            }
        }
        Collections.sort(list);
        bw.write(list.size() + "\n");
        for(int k : list) {
            bw.write(Integer.toString(k));
            bw.append(' ');
        }
        br.close();
        bw.flush();
        bw.close();
    }
}