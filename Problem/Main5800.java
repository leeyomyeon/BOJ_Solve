import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main5800 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int K = Integer.parseInt(br.readLine());
        for(int k = 1; k <= K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();
            LinkedList<Integer> list = new LinkedList<>();

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            while(st.hasMoreTokens()) {
                int score = Integer.parseInt(st.nextToken());
                list.add(score);
                min = score < min ? score : min;
                max = score > max ? score : max;
            }
            Collections.sort(list);
            int gapMax = Integer.MIN_VALUE;
            for(int i = 1; i < list.size(); i++ ) {
                int t = list.get(i - 1);
                int r = list.get(i);

                gapMax = (r - t) > gapMax ? (r - t) : gapMax;
            }
            bw.write("Class " + k);
            bw.newLine();
            bw.write("Max " + max + ", Min " + min + ", Largest gap "+ gapMax);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}