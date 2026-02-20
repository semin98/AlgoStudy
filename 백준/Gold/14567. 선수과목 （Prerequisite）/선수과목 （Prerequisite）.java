
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] degree, ans;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		degree = new int[N + 1];
		ans = new int[N + 1];
		list = new ArrayList[N + 1];
		
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			degree[b]++;
		}
		
		solve();
		
		for(int i = 1; i <= N; i++) {
			System.out.print(ans[i] + " ");
		}
				
	}
	
	static void solve() {
		Queue<int[]> q = new ArrayDeque<>();
		
		for(int i = 1; i <= N; i++) {
			if(degree[i] == 0) q.offer(new int[] {i, 1});
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int sub = cur[0];
			int cnt = cur[1];
			ans[sub] = cnt;
			
			for(int next : list[sub]) {
				degree[next]--;
				
				if(degree[next] == 0) {
					ans[next] = cnt + 1;
					q.offer(new int[] {next, cnt + 1});
				}
			}
			
		}
	}
}
