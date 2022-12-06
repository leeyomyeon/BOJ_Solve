import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main11053 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int[] lis;
    public static int N;
    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());
        lis = new int[N];
        int idx = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            if(idx == 0) {
                lis[idx] = k;
                idx++;
            } else {
                if(lis[idx - 1] < k) {
                    lis[idx] = k;
                    idx++;
                } else {
                    int chgIdx = find(0, idx, k);
                    lis[chgIdx] = k;
                }
            }
        }
        bw.write(Integer.toString(idx));
        br.close();
        bw.flush();
        bw.close();
    }

    public static int find(int start, int end, int target) {
        int mid;
        while(start < end) {
            mid = (start + end) / 2;
            if(lis[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }


}