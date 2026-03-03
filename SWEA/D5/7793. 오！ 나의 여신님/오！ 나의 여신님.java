import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, ans;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static Deque<int[]> q;
	static char[][] map;
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			q = new ArrayDeque<>();
			
			ans = 0;
			map = new char[N][M];
			v = new boolean[N][M];
			
			int[] tmp = new int[3];
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j);
					
					if(map[i][j] == '*') {
						q.offer(new int[] {i, j, -1});
					}
					if(map[i][j] == 'S') {
						tmp[0] = i;
						tmp[1] = j;
						tmp[2] = 0;
					}
				}
			}
			q.offer(tmp);
			
			bfs();
			
			if(ans == 0) {
				System.out.println("#" + t + " GAME OVER");
			} else {
				System.out.println("#" + t + " " + ans);
			}
		}
		
	}
	
	static void bfs() {
		
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0];
			int cj = cur[1];
			int cnt = cur[2];
			
			int d_cnt = 0;
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				
				if(cnt == -1) {
					if(isIn(ni, nj) && map[ni][nj] == '.') {
						map[ni][nj] = '*';
						q.offer(new int[] {ni, nj, cnt});
					}
				}
				
				
				if(cnt >= 0) {
					if(isIn(ni, nj) && map[ni][nj] == '.' && !v[ni][nj]) {
						v[ni][nj] = true;
						map[ni][nj] = 'S';
						map[ci][cj] = '.';
						q.offer(new int[] {ni, nj, cnt + 1});
					}else if(isIn(ni, nj) && map[ni][nj] == 'D') {
						ans = cnt + 1;
						return;
					} else if(isIn(ni, nj) && (map[ni][nj] == '*' || map[ni][nj] == 'X')) {
						d_cnt++;
					}
				}
			}
			
			if(d_cnt == 4) {
				
				return;
			}
		}
	}
	
	static boolean isIn(int si, int sj) {
		return 0 <= si && si < N && 0 <= sj && sj < M; 
	}
}
