import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Main1251 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        String str = br.readLine();
        LinkedList<String> list = new LinkedList<>();
        for(int i = 1; i < str.length(); i++) {
            for(int j = i + 1; j < str.length(); j++) {
                StringBuilder sb1 = new StringBuilder(str.substring(0, i));
                StringBuilder sb2 = new StringBuilder(str.substring(i, j));
                StringBuilder sb3 = new StringBuilder(str.substring(j, str.length()));
                sb1.reverse();
                sb2.reverse();
                sb3.reverse();
                sb1.append(sb2).append(sb3);
                list.add(sb1.toString());
            }
        }
        
        Collections.sort(list, Comparator.naturalOrder());
        bw.write(list.get(0));
        br.close();
        bw.flush();
        bw.close();
    }
}