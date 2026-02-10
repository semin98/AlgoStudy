import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N, K, max, ans;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> list;
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 배열 크기
            K = Integer.parseInt(st.nextToken()); // 팔 수 있는 최대 깊이
            map = new int[N][N];
            visited = new boolean[N][N];
            max = 0;
            ans = 0;
            list = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > max) {
                        max = map[i][j];
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == max) {
                        list.add(new int[] {i, j});
                    }
                }
            }

            for (int[] start : list) {
                visited[start[0]][start[1]] = true;
                dfs(start[0], start[1], 1, false);
                visited[start[0]][start[1]] = false;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

    static void dfs(int si, int sj, int length, boolean bomb) {

        ans = Math.max(ans, length);

        for(int d = 0; d < 4; d++) {
            int ni = si + di[d];
            int nj = sj + dj[d];

            if(!isIn(ni, nj) || visited[ni][nj]) continue;;

            if(map[ni][nj] < map[si][sj]) {
                visited[ni][nj] = true;
                dfs(ni, nj, length + 1, bomb);
                visited[ni][nj] = false;
            } else if(!bomb) {
                if(map[ni][nj] - K < map[si][sj]) {
                    int height = map[ni][nj];
                    map[ni][nj] = map[si][sj] - 1;
                    visited[ni][nj] = true;
                    dfs(ni, nj, length + 1, true);
                    visited[ni][nj] = false;
                    map[ni][nj] = height;
                }
            }
        }
    }

    static boolean isIn(int ci, int cj) {

        if(0 > ci || 0 > cj || ci >= N || cj >= N) return false;
        return true;
    }
}