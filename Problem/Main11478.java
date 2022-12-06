import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main11478 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        String str = br.readLine();
        for(int i = 0; i < str.length(); i++) {
            for(int j = i + 1; j <= str.length(); j++) {
                set.add(str.substring(i, j));
            }
        }
        bw.write(Integer.toString(set.size()));
        br.close();
        bw.flush();
        bw.close();
    }
}