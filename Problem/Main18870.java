import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main18870 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> originList = new LinkedList<>();
        LinkedList<Integer> sortedList = new LinkedList<>();
        while(st.hasMoreTokens()) {
            int r = Integer.parseInt(st.nextToken());
            originList.add(r);
            sortedList.add(r);
        }
        Collections.sort(sortedList);
        int rank = 0;
        for(int i : sortedList) {
            if(!map.containsKey(i)) {
                map.put(i, rank);
                rank++;
            }
        }
        for(int i : originList) {
            bw.write(map.get(i) + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}