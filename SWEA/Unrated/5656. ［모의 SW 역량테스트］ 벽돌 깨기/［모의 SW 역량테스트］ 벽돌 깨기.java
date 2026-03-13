import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int N, W, H, min;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			
			drop(0, map);
			System.out.println("#" + t + " " + min);
		}
		
	}
	
	static void copy(int[][] src, int[][] dest) {
		for(int r = 0; r < H; r++) {
			for(int c = 0; c < W; c++) {
				dest[r][c] = src[r][c];
			}
		}
	}
	
	static int getRemain(int[][] map) {
		int count = 0;
		
		for(int r = 0; r < H; r++) {
			for(int c = 0; c < W; c++) {
				if(map[r][c] > 0) ++count;
			}
		}
		
		return count;
	}
	
	static boolean drop(int count, int[][] map) { // 구슬 떨어트리기
		int remainCount = getRemain(map);
		if(remainCount == 0) {
			min = 0;
			return true; // 이 문제에서 나올수 있는 가장 최적해가 등장
		}
		
		if(count == N) { // 모든 구슬을 다 던졌다면 남은 벽돌개수 최소값 갱신
			min = Math.min(min, remainCount);
			return false;
		}
		
		int[][] newMap = new int[H][W];
		for(int c = 0; c < W; c++) {
			// c열에 구슬 낙하 했을 경우 부서지게 되는 첫 벽돌 찾기
			int r = 0;
			while(r < H && map[r][c] == 0) ++r;
			// 그런 벽돌이 없다면 부서지는 벽돌이 없으므로 다음 열로 시도
			if(r == H) continue;
			
			// 그런 벽돌이 있다면 벽돌 부수기
			copy(map, newMap);
			int brick = map[r][c];
			
			// 연쇄 폭발
			boom(newMap, r, c);
			
			// 벽돌 내리기
			if(brick > 1) down(newMap);
			// 다음 구슬 떨어트리기
			if(drop(count + 1, newMap)) {
				return true;
			}
		}
		
		return false;
	}
	
	static void boom(int[][] map, int r, int c) { // 벽돌 연쇄 폭발, BFS
		
		Queue<Point> q = new ArrayDeque<>();
		if(map[r][c] > 1) q.offer(new Point(r, c, map[r][c]));
		map[r][c] = 0;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int d = 0; d < 4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				
				for(int k = 0; k < cur.no - 1; k++) {
					nr += di[d];
					nc += dj[d];
					
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] != 0) {
						if(map[nr][nc] > 1) q.offer(new Point(nr, nc, map[nr][nc]));
						map[nr][nc] = 0;
					}
				}
			}
		}
		
	}
	
	static void down(int[][] map) { // 벽돌 내리기
		// 모든 열에 대해 처리(열 고정후 처리)
		for(int c = 0; c < W; c++) {
			// 맨아래부터 올라오며 빈칸 찾기
			int er = H - 1;
			while(er >= 0 && map[er][c] != 0) --er;
			
			if(er < 0) continue;
			
			for(int r = er - 1; r >= 0; r--) {
				if(map[r][c] != 0) {
					map[er][c] = map[r][c];
					map[r][c] = 0;
					--er;
				}
			}
		}
	}
	
	static class Point {
		int r, c, no;

		public Point(int r, int c, int no) {
			this.r = r;
			this.c = c;
			this.no = no;
		}
		
		
	}
}
