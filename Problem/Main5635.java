import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main5635 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static class Birth implements Comparable<Birth> {
        String name;
        int d;
        int m;
        int y;
        
        public Birth(String name, int d, int m, int y) {
            this.name = name;
            this.d = d;
            this.m = m;
            this.y = y;
        }

        @Override
        public int compareTo(Birth o) {
            if(o.y == this.y) {
                if(o.m == this.m) {
                    return o.d - this.d;
                } else {
                    return o.m - this.m;
                }
            } else {
                return o.y - this.y;
            }
        }
        
    }
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        LinkedList<Birth> list = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int d = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Birth(name, d, m, y));
        }

        Collections.sort(list);
        bw.write(list.get(0).name);
        bw.newLine();
        bw.write(list.get(N - 1).name);
        br.close();
        bw.flush();
        bw.close();
    }
}