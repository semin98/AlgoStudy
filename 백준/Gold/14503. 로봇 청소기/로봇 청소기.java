import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.InputStreamReader;

public class Main {
	
	static int N, M;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solve(r, c, d));
		
		
	}
	
	static int solve(int r, int c, int d) {
		int count = 0;
		
		while(true) {
			
			if(map[r][c] == 0) {
				map[r][c] = 2;
				count++;
			}
			
			boolean moved = false;
			
			for(int i = 0; i < 4; i++) {
				d = (d + 3) % 4;
				
				int nr = r + di[d];
				int nc = c + dj[d];
				
				if(isIn(nr, nc) && map[nr][nc] == 0) {
					r = nr; 
					c = nc;
					moved = true;
					break;
				}
			}
			
			if(!moved) {
				int back = (d + 2) % 4;
				int nr = r + di[back];
				int nc = c + dj[back];
				
				if(!isIn(nr, nc) || map[nr][nc] == 1) {
					break;
				}
				
				r = nr;
				c = nc;
			}
		}
		
		return count;
	}
	
	static boolean isIn(int ci, int cj) {
		return 0 <= ci && ci < N && 0 <= cj && cj < M;
	}
	
}
