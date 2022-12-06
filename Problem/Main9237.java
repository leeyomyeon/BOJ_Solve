import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main9237 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        br.readLine();
        LinkedList<Integer> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list, (Integer o1, Integer o2) -> {
            return o2 - o1;
        });
        int idx = 2;
        int max = Integer.MIN_VALUE;
        for(int k : list) {
            max = max < (k + idx) ? k + idx : max;
            idx++;
        }
        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
        bw.close();
    }
}