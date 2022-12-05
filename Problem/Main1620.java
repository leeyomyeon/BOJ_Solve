import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main1620 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        HashMap<String,Integer> map = new HashMap<>();
        HashMap<Integer,String> map2 = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            String name = br.readLine();
            map.put(name, i);
            map2.put(i, name);

        }
        for(int i = 0; i < M; i++) {
            String key = br.readLine();
            boolean isNum = key.matches("[0-9]+[\\.]?[0-9]*");
            if(isNum) {
                bw.write(map2.get(Integer.parseInt(key)));
            } else {
                bw.write(Integer.toString(map.get(key)));
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}