import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1764 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<String> nSet = new HashSet<>();
        HashSet<String> mSet = new HashSet<>();
        for(int i = 0; i < N; i++) {
            nSet.add(br.readLine());
        }
        for(int i = 0; i < M; i++) {
            mSet.add(br.readLine());
        }
        LinkedList<String> list = new LinkedList<>();

        for(String s : nSet) {
            if(mSet.contains(s)) {
                list.add(s);
            }
        }

        Collections.sort(list);
        bw.write(list.size() + "\n");
        for(String s : list) {
            bw.write(s);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}