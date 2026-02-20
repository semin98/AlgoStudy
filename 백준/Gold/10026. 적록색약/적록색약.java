import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int N;
	static int[] di = {1, 0, -1, 0};
	static int[] dj = {0, 1, 0, -1};
	static char[][] map;
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		v = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				map[i][j] = arr[j];
			}
		}
		
		int ans_normal = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!v[i][j]) {
					ans_normal++;
					bfs_normal(i, j);
				}
			}
		}
		
		v = new boolean[N][N];
		
		int ans_rgcw = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!v[i][j]) {
					ans_rgcw++;
					bfs_rgcw(i, j);
				}
			}
		}
		
		System.out.println(ans_normal + " " + ans_rgcw);
		
	}
	
	static void bfs_normal(int si, int sj) {
		
		char tmp = map[si][sj];
		
		Queue<int[]> q = new ArrayDeque<>();
		v[si][sj] = true;
		q.offer(new int[] {si, sj});
		
		while(!q.isEmpty()) {
			
			int[] cur=  q.poll();
			int ci = cur[0];
			int cj = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(isIn(ni, nj) && map[ni][nj] == tmp && !v[ni][nj]) {
					v[ni][nj] = true;
					q.offer(new int[] {ni, nj});
				}
			}
			
		}
		
	}
	
	static void bfs_rgcw(int si, int sj) {
		
		char tmp = map[si][sj];
		
		Queue<int[]> q = new ArrayDeque<>();
		v[si][sj] = true;
		q.offer(new int[] {si, sj});
		
		while(!q.isEmpty()) {
			
			int[] cur=  q.poll();
			int ci = cur[0];
			int cj = cur[1];
			
			for(int d = 0; d < 4; d++) {
				int ni = ci + di[d];
				int nj = cj + dj[d];
				
				if(tmp == 'B') {
					if(isIn(ni, nj) && map[ni][nj] == tmp && !v[ni][nj]) {
						v[ni][nj] = true;
						q.offer(new int[] {ni, nj});
					}
				} else {
					if(isIn(ni, nj) && (map[ni][nj] == 'G' || map[ni][nj] == 'R') && !v[ni][nj]) {
						v[ni][nj] = true;
						q.offer(new int[] {ni, nj});
					}
				}
				
			}
			
		}
		
	}
	
	static boolean isIn(int si, int sj) {
		return 0 <= si && si < N && 0 <= sj && sj < N;
	}

}
