import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main1059 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static void main(String[] args) throws Exception {
        int L = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        while(st.hasMoreTokens()) {
            int k = Integer.parseInt(st.nextToken());
            list.add(k);
            set.add(k);
        }
        int N = Integer.parseInt(br.readLine());
        if(set.contains(N)) {
            bw.write("0");
        } else {
            list.add(N);
            Collections.sort(list);
            int idx = -1;
            for(int i = 0; i < list.size(); i++) {
                if(list.get(i) == N) {
                    idx = i;
                    break;
                }
            }
            int start;
            if(idx == 0) {
                start = 1;
            } else {
                start = list.get(idx - 1) + 1;
            }
            int end = list.get(idx + 1) - 1;
            int res = (N - start) * (end - N) + end - start;
            bw.write(Integer.toString(res));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}