import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class Main2635 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int maxIdx = 0;
        int maxLen = 0;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            list.add(n);
            list.add(i);
            for(int j = 0; j < list.size() - 1; j++) {
                int k = (list.get(j) - list.get(j + 1));
                if(k >= 0) {
                    list.add(k);
                } else {
                    if(maxLen < list.size()) {
                        maxIdx = i;
                        maxLen = list.size();
                    }
                }
            }
            list.clear();
        }
        bw.write(maxLen + "\n");
        list.add(n);
        list.add(maxIdx);
        for(int j = 0; j < list.size() - 1; j++) {
            int k = (list.get(j) - list.get(j + 1));
            if(k >= 0) {
                list.add(k);
            } else {
                break;
            }
        }
        for(int i : list) {
            bw.write(i + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}