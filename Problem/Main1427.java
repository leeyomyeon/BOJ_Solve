import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Main1427 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        String[] str = br.readLine().split("");
        ArrayList<Integer> list = new ArrayList<>();
        for(String s : str) {
            list.add(Integer.parseInt(s));
        }
        Collections.sort(list, (Integer i1, Integer i2) -> {
            return i2 - i1;
        });
        for(int i : list) {
            bw.write(Integer.toString(i));
        }
        br.close();
        bw.flush();
        bw.close();
    }
}