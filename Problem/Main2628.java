import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2628 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int R, C;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> verList = new LinkedList<>(Arrays.asList(0, C));
        LinkedList<Integer> horList = new LinkedList<>(Arrays.asList(0, R));
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int gbn = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if(gbn == 0) { // 가로
                horList.add(k);
            } else if (gbn == 1) { // 세로
                verList.add(k);
            }
        }
        Collections.sort(verList);
        Collections.sort(horList);
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < horList.size(); i++) {       // R 세로
            for(int j = 1; j < verList.size(); j++) {   // C 가로
                int ver = verList.get(j) - verList.get(j - 1);
                int hor = horList.get(i) - horList.get(i - 1);
                int size = ver * hor;
                max = max < size ? size : max;
            }
        }

        bw.write(Integer.toString(max));
        br.close();
        bw.flush();
        bw.close();
    }
}