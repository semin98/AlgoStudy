import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int M, A, sum;
	static int[] userA, userB;
	static int[][] posA, posB;
	static BC[] bcList;
	
	static int[] di = {0, -1, 0, 1, 0};
	static int[] dj = {0, 0, 1, 0, -1};
	
	static class BC {
		int r, c, range, power;
		
		public BC(int c, int r, int range, int power) {
			this.r = r; 
			this.c = c;
			this.range = range;
			this.power = power;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			userA = new int[M + 1];
			userB = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) userA[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++) userB[i] = Integer.parseInt(st.nextToken());
			
			bcList = new BC[A];
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				
				bcList[i] = new BC(c, r, range, power);
			}
			
			sum = 0;
			int ar = 1, ac = 1, br_pos = 10, bc_pos = 10;
			
			for(int i = 0; i <= M; i++) {
				ar += di[userA[i]];
				ac += dj[userA[i]];
				
				br_pos += di[userB[i]];
				bc_pos += dj[userB[i]];
				
				sum += getMaxCharge(ar, ac, br_pos, bc_pos);
			}
			
			System.out.println("#" + t + " " + sum);
		}
		
	}
	
	static int getMaxCharge(int ar, int ac, int br, int bc) {
		List<Integer> listA = getAvailableBC(ar, ac);
		List<Integer> listB = getAvailableBC(br, bc);
		
		int max = 0;
		
		if(listA.isEmpty() && listB.isEmpty()) return 0;
		
		if(listB.isEmpty()) {
			for(int aIdx : listA) max = Math.max(max, bcList[aIdx].power);
		} else if(listA.isEmpty()) {
			for(int bIdx : listB) max = Math.max(max,  bcList[bIdx].power);
		} else {
			for(int aIdx : listA) {
				for(int bIdx : listB) {
					int curSum = 0;
					if(aIdx == bIdx) {
						curSum = bcList[aIdx].power;
					} else {
						curSum = bcList[aIdx].power + bcList[bIdx].power;
					}
					max = Math.max(max, curSum);
				}
			}
	
		}
		
		return max;
	}
	
	static List<Integer> getAvailableBC(int r, int c) {
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < A; i++) {
			int dist = Math.abs(bcList[i].r - r) + Math.abs(bcList[i].c - c);
			if(dist <= bcList[i].range) list.add(i);
		}
		return list;
	}
}
