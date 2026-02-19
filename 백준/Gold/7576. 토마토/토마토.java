

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c, ans;
	static Queue<int[]> q;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		c = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		ans = 0;
		
		map = new int[r][c];
		
		q = new ArrayDeque<>();
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					q.offer(new int[] {i, j, 0});
				}
			}
		}
		
		bfs();
		
		for(int i = 0; i < r; i ++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] == 0) {
					ans = -1;
					break;
				}
			}
		}
		
		System.out.println(ans);
		
	}
	
	static void bfs() {
		
		while(!q.isEmpty()) {
			
			int[] tmp = q.poll();
			int ci = tmp[0];
			int cj = tmp[1];
			int day = tmp[2];
			ans = day;
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(isIn(ni, nj) && map[ni][nj] == 0) {
					map[ni][nj] = 1;
					q.offer(new int[] {ni, nj, day + 1});
				}
				
			}
			
		}
		
	}
	
	static boolean isIn(int si, int sj) {
		return 0 <= si && si < r && 0 <= sj && sj < c;
	}

}
