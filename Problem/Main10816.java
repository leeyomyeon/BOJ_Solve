import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
@SuppressWarnings("unused")
public class Main10816 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        while(st.hasMoreTokens()) {
            String str = st.nextToken();
            if(map.containsKey(str)){
                int k = map.get(str) + 1;
                map.put(str, k);
            } else {
                map.put(str, 1);
            }
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            String str = st.nextToken();
            int k = 0;
            if(map.containsKey(str)) {
                k = map.get(str);
            }
            bw.write(k + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}