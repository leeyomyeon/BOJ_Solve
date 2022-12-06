import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main17081 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 1024 * 64);
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 1024 * 64);
    public static int R, C, startR, startC, K, L, bossR, bossC;
    public static String command;
    public static User user;
    public static char[][] arr;
    public static Monster[][] monsters;
    public static ItemBox[][] items;
    public static class User {
        // 위치 / 레벨 / 최대HP / 현재HP / 공격력 / 방어력 / 경험치 / 무기공격력 / 방어력
        int r, c, level, maxHp, curHp, atk, def, exp, weapon, armor, trap;
        double expMag;  // 경험치획득량
        int atkMag; // 공격력배수
        HashSet<String> acc;
        void setR(int r) {
            this.r = r;
        }
        void setC(int c) {
            this.c = c;
        }
        int getEndAtk() {   // 총 무기공격력 반환
            return this.atk + this.weapon;
        }
        int getEndDef() {
            return this.def + this.armor;
        }
        public User(int r, int c) { // 초기값
            this.r = r;
            this.c = c;
            this.level = 1;
            this.maxHp = 20;
            this.curHp = 20;
            this.atk = 2;
            this.def = 2;
            this.exp = 0;
            this.weapon = 0;
            this.armor = 0;
            this.trap = 5;
            this.expMag = 1.0;  // 경험치배수
            this.atkMag = 1; // 무기배수
            this.acc = new HashSet<>();
        }
        void addExp(int exp) {
            this.exp += (int) Math.floor(exp * this.expMag);    // 1.2배 획득시 소수점 아래 버림
            if(this.exp >= this.level * 5) { // 레벨업하면
                this.level += 1;
                this.exp = 0;
                this.maxHp += 5;
                this.atk += 2;
                this.def += 2;
                this.curHp = this.maxHp;
            }
            if(this.acc.contains("HR")) {
                this.curHp = Math.min(this.curHp + 3, this.maxHp);
            }
        }
        void setWeapon(int weapon) {
            this.weapon = weapon;
        }
        void setArmor(int armor) {
            this.armor = armor;
        }
        void setAcc(String acc) {
            if(this.acc.size() >= 4 || this.acc.contains(acc)) {
                return;
            }
            this.acc.add(acc);
            if(this.acc.contains("EX")) {
                this.expMag = 1.2;
            } else {
                this.expMag = 1.0;
            }
            if(this.acc.contains("CO")) {
                if(this.acc.contains("DX")) {
                    this.atkMag = 3;
                } else {
                    this.atkMag = 2;
                }
            } else {
                this.atkMag = 1;
            }
            if(this.acc.contains("DX")) {
                if(this.acc.contains("CO")) {
                    this.atkMag = 3;
                }
                this.trap = 1;
            } else {
                this.trap = 5;
            }
        }
        void setFullHp() {
            this.curHp = this.maxHp;
        }
        int attackedByMonster(int damage, char target) { // 피해입을시 target M = 몬스터, T = 함정
            this.curHp -= target == 'M' ? Math.max(1, damage - getEndDef()) : this.trap;
            if(this.curHp <= 0) {
                if(this.acc.contains("RE")) {
                    this.r = startR;
                    this.c = startC;
                    this.curHp = this.maxHp;
                    this.acc.remove("RE");
                    return 1;
                } else {
                    this.curHp = 0;
                    return -1;
                }
            }
            return 0;
        }
    }
    public static class Monster {
        String name;
        char type;
        int atk, def, hp, exp, maxHp;
        public Monster(String name, int atk, int def, int hp, int exp, char type) {
            this.name = name;
            this.atk = atk;
            this.def = def;
            this.hp = hp;
            this.maxHp = hp;
            this.exp = exp;
            this.type = type; // B - 보스, N - 일반
        }
        void setMaxHp() {
            this.hp = this.maxHp;
        }
        boolean attackedByUser(int turn) {
            this.hp -= turn == 0 ? (Math.max(1, user.getEndAtk() * user.atkMag - this.def)) : Math.max(1, user.getEndAtk() - this.def);
            return this.hp <= 0;
        }
    }
    public static class ItemBox {
        char info;
        String acc;
        int n;
        public ItemBox(char info, String acc, int n) {
            this.info = info;
            if(info == 'O') {
                this.acc = acc;
            }
            this.n = n;
        }
    }
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R + 1][C + 1];
        monsters = new Monster[R + 1][C + 1];
        items = new ItemBox[R + 1][C + 1];
        K = 0; L = 0;
        for(int i = 1; i <= R; i++) {
            String str = br.readLine();
            for(int j = 1; j <= C; j++) {
                arr[i][j] = str.charAt(j - 1);
                if(arr[i][j] == '&') {
                    K++;
                } else if(arr[i][j] == 'M') {
                    bossR = i;
                    bossC = j;
                    K++;
                } else if(arr[i][j] == 'B') {
                    L++;
                } else if(arr[i][j] == '@') {
                    arr[i][j] = '.';
                    startR = i;
                    startC = j;
                    user = new User(i, j);
                }
            }
        }
        command = br.readLine();
        for(int i = 0; i < K; i++) { // 몬스터 정보
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            String s = st.nextToken();                  // 이름
            int w = Integer.parseInt(st.nextToken());   // 공
            int a = Integer.parseInt(st.nextToken());   // 방
            int h = Integer.parseInt(st.nextToken());   // 체
            int e = Integer.parseInt(st.nextToken());   // 경
            // String name, int atk, int def, int hp, int exp, char type(B - 보스, N - 일반)
            monsters[r][c] = new Monster(s, w, a, h, e, arr[r][c] == '&' ? 'N' : 'B');
        }
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            char info = st.nextToken().charAt(0);
            // char info, String acc, int n
            if(info == 'O') {
                String acc = st.nextToken();
                items[r][c] = new ItemBox(info, acc, 0);
            } else {
                int n = Integer.parseInt(st.nextToken());
                items[r][c] = new ItemBox(info, null, n);
            }
        }
        execute();
    }
    public static void execute() throws Exception {
        int lastTurn = 0;
        boolean isDead = false;
        String deadMessage = "Press any key to continue.";
        Loop1:
        for(int turn = 1; turn <= command.length(); turn++) {
            lastTurn = turn;
            int d;
            if(command.charAt(turn - 1) == 'L') { d = 0; }
            else if(command.charAt(turn - 1) == 'R') { d = 1; }
            else if(command.charAt(turn - 1) == 'U') { d = 2; }
            else { d = 3; }
            int nr = user.r + dr[d];
            int nc = user.c + dc[d];
            if(nr >= 1 && nr <= R && nc >= 1 && nc <= C) {
                if((arr[nr][nc] == '&' || arr[nr][nc] == 'M') && monsters[nr][nc] != null) { // 일반몬스터
                    int time = 0;
                    while(true) { // 수행
                        if(monsters[nr][nc].type == 'B' && user.acc.contains("HU") && time == 0) {
                            user.setFullHp();
                        }
                        if(monsters[nr][nc].attackedByUser(time)) { // 쓰러트렸을시
                            user.addExp(monsters[nr][nc].exp);
                            arr[nr][nc] = '.';
                            move(nr, nc);
                            if(monsters[nr][nc].type == 'B') {
                                deadMessage = "YOU WIN!";
                                break Loop1;
                            } else {
                                break;
                            }
                        }
                        if(monsters[nr][nc].type == 'B' && user.acc.contains("HU") && time == 0) {
                            time++;
                            continue;
                        }
                        int res = user.attackedByMonster(monsters[nr][nc].atk, 'M');
                        if(res == 1) { // 몬스터한테 죽었는데 부활할 시
                            monsters[nr][nc].setMaxHp();
                            break;
                        } else if(res == - 1) { // 죽었을 시 게임 끝
                            deadMessage = "YOU HAVE BEEN KILLED BY " + monsters[nr][nc].name + "..";
                            isDead = true;
                            break Loop1;
                        }
                        time++;
                    }
                } else if(arr[nr][nc] == '^') {
                    int res = user.attackedByMonster(0, 'T');
                    if(res == -1) {
                        deadMessage = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
                        isDead = true;
                        break;
                    } else if(res == 1) {
                        continue;
                    }
                    move(nr, nc);
                } else if(arr[nr][nc] == '.') {
                    move(nr, nc);
                } else if(arr[nr][nc] == 'B' && items[nr][nc] != null) {
                    if(items[nr][nc].info == 'W') {
                        user.setWeapon(items[nr][nc].n);
                    } else if(items[nr][nc].info == 'A') {
                        user.setArmor(items[nr][nc].n);
                    } else if(items[nr][nc].info == 'O') {
                        user.setAcc(items[nr][nc].acc);
                    }
                    arr[nr][nc] = '.';
                    move(nr,nc);
                } else if(arr[nr][nc] == '#') {
                    if(arr[user.r][user.c] == '^') {
                        if(user.attackedByMonster(0, 'T') == -1) {
                            deadMessage = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
                            isDead = true;
                            break;
                        }
                    }
                }
            } else {
                if(arr[user.r][user.c] == '^') {
                    if(user.attackedByMonster(0, 'T') == -1) {
                        deadMessage = "YOU HAVE BEEN KILLED BY SPIKE TRAP..";
                        isDead = true;
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if(!isDead) {
            arr[user.r][user.c] = '@';
        }
        for(int i = 1; i <= R; i++) {
            for(int j = 1; j <= C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        sb.append("Passed Turns : ").append(lastTurn).append("\n");
        sb.append("LV : ").append(user.level).append("\n");
        sb.append("HP : ").append(user.curHp).append("/").append(user.maxHp).append("\n");
        sb.append("ATT : ").append(user.atk).append("+").append(user.weapon).append("\n");
        sb.append("DEF : ").append(user.def).append("+").append(user.armor).append("\n");
        sb.append("EXP : ").append(user.exp).append("/").append(user.level * 5).append("\n");
        sb.append(deadMessage);
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }
    public static void move(int nr, int nc) {
        user.setR(nr);
        user.setC(nc);
    }
    // L R U D
    public static int[] dr = {0, 0, -1, 1};
    public static int[] dc = {-1, 1, 0, 0};
}