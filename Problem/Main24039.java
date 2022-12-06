import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Main24039 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static boolean[] isPrime;
    public static void main(String[] args) throws Exception {
        isPrime = new boolean[104];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        LinkedList<Integer> list = new LinkedList<>();
        for(int i = 2; i*i <= 10403; i++) {
            if(isPrime[i]) {
                list.add(i);
                int j = 2;
                while(!(j * i >= 103)) {
                    isPrime[j * i] = false;
                    j++;
                }
            }
        }
        list.add(103);
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < list.size() - 1; i++) {
            int k = list.get(i) * list.get(i + 1);
            if(k > N) {
                bw.write(Integer.toString(k));
                break;
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}