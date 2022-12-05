import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main1316 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int res = 0;
        for(int i = 0; i < n; i++) {
            char[] cArr = br.readLine().toCharArray();
            Set<Character> set = new HashSet<>();

            if(cArr.length == 1) {
                res++;
                continue;
            }
            boolean f = true;
            for(int j = 0; j < cArr.length; j++) {
                if(j == cArr.length - 1) {
                    if(set.contains(cArr[j])) {
                        f = false;
                        break;
                    } 
                } else {
                    if(cArr[j] != cArr[j + 1]) {
                        if(set.contains(cArr[j])) {
                            f = false;
                            break;
                        }
                        set.add(cArr[j]);
                    }
                }
            }
            if(f) {
                res++;
            }
        }

        System.out.println(res);
    }
}