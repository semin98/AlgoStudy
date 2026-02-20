
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int V, E;
	static int[] degree;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		StringTokenizer st;
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[V + 1];
			degree = new int[V + 1];
			
			for(int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[b].add(a);
				degree[b]++;
			}
			System.out.print("#" + t + " ");
			solve();
			System.out.println();
		}
		
	}
	
	static void solve() {
		
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= V; i++) {
			if(degree[i] == 0) q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			
			System.out.print(tmp + " ");
			
			for(int i = 1; i <= V; i++) {
				if(list[i].contains(tmp)) {
					degree[i] -= 1;
					if(degree[i] == 0) {
						q.offer(i);
					}
					
				}

			}
		}
	}
	
}