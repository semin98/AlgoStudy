
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, ans, day;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			day = 0;
			
			map = new int[N][N];
			v = new boolean[N][N];
			ans = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int d = 1; d <= 100; d++) {
				int cnt = 0;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] > day && !v[i][j]) {
							v[i][j] = true;
							bfs(i, j);
							cnt++;
						}
					}
				}
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						v[i][j] = false;
					}
				}
				ans = Math.max(ans, cnt);
				day++;
			}
			
			System.out.println("#" + t + " " + ans);
			
		}
	}
	
	static void bfs(int si, int sj) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {si, sj});
		
		while(!q.isEmpty()) {
			
			int tmp[] = q.poll();
			int ci = tmp[0];
			int cj = tmp[1];
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(isIn(ni, nj) && map[ni][nj] > day && !v[ni][nj]) {
					v[ni][nj] = true;
					q.offer(new int[] {ni, nj});
				}
			}
			
		}
		
	}
	
	static boolean isIn(int si, int sj) {
		return (0 <= si && si < N && 0 <= sj && sj < N);
	}
}
