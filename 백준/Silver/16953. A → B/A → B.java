import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long start = sc.nextInt();
		long end = sc.nextInt();
		long ans = -1;
		
		Queue<long[]> q = new ArrayDeque<>();
		
		q.offer(new long[] {start, 1});
		
		while(!q.isEmpty()) {
			long[] cur = q.poll();
			long val = cur[0];
			long cnt = cur[1];
			
			long mul = val * 2;
			if(mul <= end) {
				if(mul == end) ans = cnt + 1;
				else q.offer(new long[] {mul, cnt + 1});
			}
			
			long plus = val * 10 + 1;
			if(plus <= end) {
				if(plus == end) ans = cnt + 1;
				else q.offer(new long[] {plus, cnt + 1});
			}
			
		}
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

}
