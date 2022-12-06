import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main7662 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static TreeMap<Integer, Integer> map;
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            map = new TreeMap<>();
            for(int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());
                if(c == 'I') {
                    map.put(n, map.getOrDefault(n, 0) + 1);
                } else if(c == 'D') {
                    if(map.isEmpty()) {  // 비어있을 시 건너뜀
                        continue;
                    }
                    if(n == -1) {   // 최소값 삭제
                        int p = map.get(map.firstKey());
                        if(p <= 1) {
                            map.remove(map.firstKey());
                        } else {
                            map.put(map.firstKey(), p - 1);
                        }
                    } else if(n == 1) { // 최대값 삭제
                        int p = map.get(map.lastKey());
                        if(p <= 1) {
                            map.remove(map.lastKey());
                        } else {
                            map.put(map.lastKey(), p - 1);
                        }
                    }
                }
            }

            if(map.isEmpty()) {
                bw.write("EMPTY");
            } else {
                bw.write(map.lastKey() + " " + map.firstKey());
            }
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}