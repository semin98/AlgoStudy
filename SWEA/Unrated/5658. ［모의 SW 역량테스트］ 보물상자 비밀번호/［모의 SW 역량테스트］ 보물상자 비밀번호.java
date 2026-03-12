import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

	static int N, K;
	static ArrayList<Integer> list;
	static Deque<Character> q;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			list = new ArrayList<>();
			q = new ArrayDeque<>();
			
			String input = br.readLine();
			for(int i = 0; i < N; i++) {
				q.offer(input.charAt(i));
			}
		
			for(int i = 0; i < N; i++) {
				solve();
			}
			
			Collections.sort(list);
			int ans = list.get(list.size() - K);

			System.out.println("#" + t + " " + ans);
		}
		
	}
	
	static void solve() {
		
		int size = N / 4;
		
		for(int i = 0; i < N; i +=3) {
			String result = "";
			for(int j = 0; j < size; j++) {
				result += q.peekFirst();
				q.offerLast(q.pollFirst());
			}
			int tmp = Integer.parseInt(result, 16);
			if(list.contains(tmp)) {
				continue;
			} else {
				list.add(tmp);
			}
		}
		
		q.offerFirst(q.pollLast());
	}
}
