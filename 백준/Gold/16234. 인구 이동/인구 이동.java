import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] map;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;

        while(true) {
            visited = new boolean[N][N];
            boolean isMoved = false;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        if(bfs(i, j)) isMoved = true;
                    }
                }
            }
            
            if(!isMoved) break;
            day++;
        }

        System.out.println(day);

    }

    static boolean bfs(int si, int sj) {
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> union = new ArrayList<>();
        
        q.offer(new int[] {si, sj});
        union.add(new int[] {si, sj});
        visited[si][sj] = true;
        
        int sum = map[si][sj];

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int ci = cur[0];
            int cj = cur[1];

            for(int d = 0; d < 4; d++) {
                int ni = ci + di[d];
                int nj = cj + dj[d];

                if(isIn(ni, nj) && !visited[ni][nj]) {
                    int diff = Math.abs(map[ni][nj] - map[ci][cj]);
                    if(diff >= L && diff <= R) {
                        visited[ni][nj] = true;
                        q.offer(new int[] {ni, nj});
                        union.add(new int[] {ni, nj});
                        sum += map[ni][nj];
                    }
                }
            }
        }

        if(union.size() > 1) {
            int renew = sum / union.size();
            for(int[] cur : union) {
                map[cur[0]][cur[1]] = renew;
            }
            return true;
        }
        return false;
    }

    static boolean isIn(int ci, int cj) {
        return 0 <= ci && ci < N && 0 <= cj && cj < N;
    }

}
