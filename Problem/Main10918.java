import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

/*
a     b     c     d     e     f
##### ##### ##### ##### ##### #####
..#.. #...# ###.. ##### ..### #.#.#
#...# #.#.# ###.# ##### #.### ....#
##### ....# ###.. ..### #.... #.#.#
##### ##### ##### #.### ##### #####
g     h     i     j     kq    l
###.# ##### #.#.# ##### ###.# #####
##..# #...# #...# ##### ###.# #...#
##.## #.#.# ##### ##### ###.# ##.##
##..# #...# ##### ###.. #...# #...#
###.# ###.# ##### ###.# #.### ##.##
m     n     o     p     z     r
##### ##### ##### ###.# ##### #####
##### #.... ..### ###.. ....# #.#.#
#...# #.#.# #.### ##### ###.# #....
..#.. #...# ..### ##### ###.. #.#.#
##### ##### ##### ##### ##### #####
s     t     uv    w     x     y
#.### #.### ##### #.### ###.# ##.##
#..## #...# ##### ..### #...# #...#
##.## #.#.# ##### ##### #.### ##.##
#..## #...# #...# ##### #.### #...#
#.### ##### #.#.# ##### #.### #####
1     2     3     4     _     _
##### ##### ##### ##### ..... .....
#.#.# #...# #...# #...# ..... .....
#.#.# #.### #.#.# ###.# ..... .....
#...# #...# #.#.# #...# ..... .....
##### ##### ##### ##### ..... .....




3 4
#####.#####.#####......
#.#.#...#...#####......
#.###.#...#.#####......
#...#.#####.#...#......
#####.#####.#.#.#......
.......................
#####.......#####.#####
###.........#...#.#.#.#
###.#.......#.#.#.#####
###.........#.#.#.#.#.#
#####.......#####.#####
.......................
#####.#####.......###.#
#####.#...#.......###.#
#####.#.#.#.......###.#
..###.....#.......#...#
#.###.#####.......#.###

3 a uv _
c _ 3  10
d b _  kq

_10kq
uv3_
a_b
3cd

#####.#####.#####.#####.#####.#####
..#...#...#.###...#####...###.#.#.#
#...#.#.#.#.###.#.#####.#.###.....#
#####.....#.###.....###.#.....#.#.#
#####.#####.#####.#.###.#####.#####
...................................
###.#.#####.#.#.#.#####.###.#.#####
##..#.#...#.#...#.#####.###.#.#...#
##.##.#.#.#.#####.#####.###.#.##.##
##..#.#...#.#####.###...#...#.#...#
###.#.###.#.#####.###.#.#.###.##.##
...................................
#####.#####.#####.###.#.#####.#####
#####.#.......###.###.......#.#.#.#
#...#.#.#.#.#.###.#####.###.#.#....
..#...#...#...###.#####.###...#.#.#
#####.#####.#####.#####.#####.#####
...................................
#.###.#.###.#####.#.###.###.#.##.##
#..##.#...#.#####...###.#...#.#...#
##.##.#.#.#.#####.#####.#.###.##.##
#..##.#...#.#...#.#####.#.###.#...#
#.###.#####.#.#.#.#####.#.###.#####
...................................
#####.#####.#####.#####............
#.#.#.#...#.#...#.#...#............
#.#.#.#.###.#.#.#.###.#............
#...#.#...#.#.#.#.#...#............
#####.#####.#####.#####............
 */
public class Main10918 {
    public static char[][] arr;
    public static String[][] code;
    public static int R, C;
    public static HashMap<Integer, String> map;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader();
        map = new HashMap<>();
        init();
        R = fr.nextInt();
        C = fr.nextInt();

        // 입력
        arr = new char[6 * R - 1][6 * C - 1];
        for(int i = 0; i < 6 * R - 1; i++) {
            for(int j = 0; j < 6 * C - 1; j++) {
                arr[i][j] = fr.nextChar();
            }
        }
        code = new String[R][C];
        int one = 33216063;
        int two = 33087039;
        int three = 33085119;
        int four = 33093183;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int idx = 24;
                int k = 0;
                for(int startR = 6 * i; startR < 6 * i + 5; startR++) {
                    for(int startC = 6 * j; startC < 6 * j + 5; startC++) {
                        if(arr[startR][startC] == '#') {
                            k |= 1 << idx;
                        }
                        idx--;
                    }
                }
                if(map.containsKey(k)) {
                    code[i][j] = map.get(k);
                } else {
                    int res = 0;
                    if(k == (k | one)) res += 1;
                    if(k == (k | two)) res += 2;
                    if(k == (k | three)) res += 3;
                    if(k == (k | four)) res += 4;
                    code[i][j] = Integer.toString(res);
                }
            }
        }
        for(int j = C - 1; j >= 0; j--) {
            for(int i = 0; i < R; i++) {
                bw.write(code[i][j]);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
    public static void init() {
        R = 5;
        C = 6;
        code = new String[][] {
            {"a", "b", "c", "d", "e", "f"},
            {"g", "h", "i", "j", "kq", "l"},
            {"m", "n", "o", "p", "z", "r"},
            {"s", "t", "uv", "w", "x", "y"},
            {"1", "2", "3", "4", "_", ""}
        };
        arr = new char[6 * R - 1][6 * C - 1];
        String initStr = new StringBuilder("#####.#####.#####.#####.#####.#####")
                .append("..#...#...#.###...#####...###.#.#.#")
                .append("#...#.#.#.#.###.#.#####.#.###.....#")
                .append("#####.....#.###.....###.#.....#.#.#")
                .append("#####.#####.#####.#.###.#####.#####")
                .append("...................................")
                .append("###.#.#####.#.#.#.#####.###.#.#####")
                .append("##..#.#...#.#...#.#####.###.#.#...#")
                .append("##.##.#.#.#.#####.#####.###.#.##.##")
                .append("##..#.#...#.#####.###...#...#.#...#")
                .append("###.#.###.#.#####.###.#.#.###.##.##")
                .append("...................................")
                .append("#####.#####.#####.###.#.#####.#####")
                .append("#####.#.......###.###.......#.#.#.#")
                .append("#...#.#.#.#.#.###.#####.###.#.#....")
                .append("..#...#...#...###.#####.###...#.#.#")
                .append("#####.#####.#####.#####.#####.#####")
                .append("...................................")
                .append("#.###.#.###.#####.#.###.###.#.##.##")
                .append("#..##.#...#.#####...###.#...#.#...#")
                .append("##.##.#.#.#.#####.#####.#.###.##.##")
                .append("#..##.#...#.#...#.#####.#.###.#...#")
                .append("#.###.#####.#.#.#.#####.#.###.#####")
                .append("...................................")
                .append("#####.#####.#####.#####.......#####")
                .append("#.#.#.#...#.#...#.#...#.......#####")
                .append("#.#.#.#.###.#.#.#.###.#.......#####")
                .append("#...#.#...#.#.#.#.#...#.......#####")
                .append("#####.#####.#####.#####.......#####").toString();
        int ci = 0;
        for(int i = 0; i < 6 * R - 1; i++) {
            for(int j = 0; j < 6 * C - 1; j++) {
                char c = initStr.charAt(ci);
                arr[i][j] = c;
                ci++;
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                int idx = 24;
                int k = 0;
                for(int startR = 6 * i; startR < 6 * i + 5; startR++) {
                    for(int startC = 6 * j; startC < 6 * j + 5; startC++) {
                        if(arr[startR][startC] == '#') {
                            k |= 1 << idx;
                        }
                        idx--;
                    }
                }
                map.put(k, code[i][j]);
            }
        }
    }

    public static class FastReader {
        private final DataInputStream din;
        private final int BUFFER_SIZE = 1 << 16;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // input line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            } while ((c = read()) > 32);

            return neg ? ~ret + 1 : ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = (ret << 3) + (ret << 1) + (c & 15);
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        public char nextChar() throws IOException {
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            return (char) c;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }
    }
}
