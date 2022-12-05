import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main1181 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        HashSet<String> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        
        ArrayList<String> list = new ArrayList<>(set);
        Collections.sort(list, (String s1, String s2) -> {
            if(s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            return s1.length() - s2.length();
        });

        for(int i = 0; i < list.size(); i++) {
            bw.write(list.get(i));
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }
}