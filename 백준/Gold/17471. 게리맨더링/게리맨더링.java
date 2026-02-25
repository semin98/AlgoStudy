
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, ans;
	static int[] population;
	static boolean[] chk;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		ans = Integer.MAX_VALUE;
		
		population = new int[N + 1];
		list = new ArrayList[N + 1];
		chk = new boolean[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				
				list[i].add(tmp);
				list[tmp].add(i);
			}
		}
		
		comb(1);
		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
		
	}
	
	static void comb(int cnt) {
		
		if(cnt == N + 1) {
			find();
			return;
		}
		
		chk[cnt] = true;
		comb(cnt + 1);
		
		chk[cnt] = false;
		comb(cnt + 1);
		
	}
	
	static void find() {
		ArrayList<Integer> red = new ArrayList<>();
		ArrayList<Integer> blue = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			if(chk[i]) red.add(i);
			else blue.add(i);
		}
		
		if(red.isEmpty() || blue.isEmpty()) return;
		
		if(isConnected(red) && isConnected(blue)) {
			getDiff();
		}
	}
	
	static boolean isConnected(ArrayList<Integer> group) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] v = new boolean[N + 1];
		
		q.offer(group.get(0));
		v[group.get(0)] = true;
		
		int count = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next : list[cur]) {
				if(chk[next] == chk[group.get(0)] && !v[next]) {
					v[next] = true;
					q.offer(next);
					count++;
				}
			}
		}
		
		return count == group.size();
	}
	
	static void getDiff() {
		int cnt_red = 0;
		int cnt_blue = 0;
		
		for(int i = 1; i <= N; i++) {
			if(chk[i]) {
				cnt_red += population[i];
			} else {
				cnt_blue += population[i];
			}
		}
		
		ans = Math.min(ans, Math.abs(cnt_red - cnt_blue));
	}

}
