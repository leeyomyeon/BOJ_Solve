import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main15551 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 8);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 8);
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0; i < N - 2; i++){
            sb1.append("\u0001");
            sb2.append("\u0001");
        }
        sb1.append("U]");
        sb2.append("V>");
        bw.write(sb1.toString());
        bw.newLine();
        bw.write(sb2.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}