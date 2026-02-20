

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static final int SIZE = 100;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		StringTokenizer st;
		
		for(int t = 1; t <= T; t++) {
			
			int test_case = Integer.parseInt(br.readLine());
			
			map = new int[SIZE][SIZE];
			
			int si = 0;
			int sj = 0;
			
			for(int i = 0; i < SIZE; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j = 0; j < SIZE; j++) {
					map[i][j] = tmp[j] - '0';
					if(map[i][j] == 2) {
						si = i;
						sj = j;
					}
				}
			}
			
			System.out.println("#" + t + " " + (bfs(si, sj)));
			
		}
	}
	
	static int bfs(int si, int sj) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {si, sj});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int ci = cur[0];
			int cj = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(isIn(ni, nj) && map[ni][nj] == 0) {
					map[ni][nj] = 1;
					q.offer(new int[] {ni, nj});
				} else if(isIn(ni, nj) && map[ni][nj] == 3) {
					return 1;
				}
				
			}
		}
		
		return 0;
	}
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < SIZE && 0 <= cj && cj < SIZE;
	}
	
}