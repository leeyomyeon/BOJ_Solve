import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
// KMP
public class Main1786 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static char[] text, pattern;
    public static int[] pi;
    public static LinkedList<Integer> list;
    public static int M, N;
    public static void main(String[] args) throws Exception {
        text = br.readLine().toCharArray();
        pattern = br.readLine().toCharArray();
        M = pattern.length;
        N = text.length;
        pi = new int[M];
        makePiArr();
        
        list = new LinkedList<>();

        int j = 0;
        for(int i = 0; i < N; i++) {
            while(j > 0 && text[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if(pattern[j] == text[i]) {
                if(j == M - 1) {
                    list.add(i - M + 2);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        bw.write(list.size() + "\n");
        for(int k : list) {
            bw.write(k + " ");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static void makePiArr() {
        for(int i = 1, j = 0; i < M; i++) {
            while(j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if(pattern[i] == pattern[j]) {
                pi[i] = ++j;
            } 
        }
    }
}