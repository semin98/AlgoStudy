import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, si, sj, ei, ej, ans;
	static int[][] customers;
	static boolean[] v;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			
			si = Integer.parseInt(st.nextToken());
			sj = Integer.parseInt(st.nextToken());
			
			ei = Integer.parseInt(st.nextToken());
			ej = Integer.parseInt(st.nextToken());
			
			customers = new int[N][2];
			v = new boolean[N];
			for(int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			findPath(0, si, sj, 0);
			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	static void findPath(int count, int lastX, int lastY, int distSum) {
		
		if(distSum >= ans) return;
		
		if(count == N) {
			int finalDist = distSum + getDist(lastX, lastY, ei, ej);
			ans = Math.min(ans, finalDist);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!v[i]) {
				v[i] = true;
				
				int d = getDist(lastX, lastY, customers[i][0], customers[i][1]);
				findPath(count + 1, customers[i][0], customers[i][1], distSum + d);
				
				v[i] = false;
			}
		}
		
	}
	
	static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
