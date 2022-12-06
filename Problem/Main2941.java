import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();

        for (String s : croatia) {
            if (str.contains(s)) {
                str = str.replaceAll(s, "1");
            }
        }
        System.out.println(str.length());
    }
}
