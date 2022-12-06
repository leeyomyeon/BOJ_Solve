import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main10867 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<Short> list = new LinkedList<>();
        Short i = 0;
        while(st.hasMoreTokens()) {
            i = Short.parseShort(st.nextToken());
            if(!list.contains(i)) {
                list.add(i);
            }
        }
        Collections.sort(list);
        for(Short k : list) {
            bw.write(k + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}