
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int H, W;
	static char[] cmd;
	static char[][] arr;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static char[] tank = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			arr = new char[H][W];
			
			int si = 0;
			int sj = 0;
			
			for(int h = 0; h < H; h++) {
				char[] tmp = br.readLine().toCharArray();
				for(int w = 0; w < W; w++) {
					arr[h][w] = tmp[w];
					for(char t : tank) {
						if(t == tmp[w]) {
							si = h;
							sj = w;
						}
					}
				}
			}
			
			int cmd_cnt = Integer.parseInt(br.readLine());
			cmd = br.readLine().toCharArray();
			
			
			for(int i = 0; i < cmd_cnt; i++) {
				int[] tmp = opCmd(si, sj, cmd[i]);
				si = tmp[0];
				sj = tmp[1];
			}
		
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			for(int h = 0; h < H; h++) {
				for(int w = 0; w < W; w++) {
					sb.append(arr[h][w]);
				}
				sb.append('\n');
			}
			
			System.out.print(sb);
		}
	}
	
	static int[] opCmd(int si, int sj, char command) {
		int[] result = new int[] {si, sj};
		
		switch(command) {
		
		case 'S' :
			result = destroy(si, sj);
			break;
			
		case 'U' :
			result = go(si, sj, 0);
			break;
			
		case 'D' :
			result = go(si, sj, 1);
			break;
			
		case 'L' :
			result = go(si, sj, 2);
			break;
			
		case 'R' :
			result = go(si, sj, 3);
			break;
		
		}
		
		return result;
	}
	
	static int[] go(int si, int sj, int d) {
		
		int ni = si + di[d];
		int nj = sj + dj[d];
		
		if(isIn(ni, nj)) {
			if(arr[ni][nj] == '.') {
				arr[si][sj] = '.';
				arr[ni][nj] = tank[d];
				return new int[] {ni, nj};
			} else {
				arr[si][sj] = tank[d];
				return new int[] {si, sj};
			}
		} else {
			arr[si][sj] = tank[d];
			return new int[] {si, sj};
		}
		
	}
	
	static int[] destroy(int si, int sj) {
		
		int dir = 0;
		
		for(int d = 0; d < 4; d++) {
			if(arr[si][sj] == tank[d]) {
				dir = d;
			}
		}
		
		int ni = si + di[dir];
		int nj = sj + dj[dir];
		
		while(true) {
			
			if(isIn(ni, nj)) {
				if(arr[ni][nj] == '*') {
					arr[ni][nj] = '.';
					return new int[] {si, sj};
				} else if(arr[ni][nj] == '#') {
					return new int[] {si, sj};
				} else {
					ni += di[dir];
					nj += dj[dir];
				}
				
			} else {
				return new int[] {si, sj};
			}
		}
		
	}
	
	static boolean isIn(int si, int sj) {
		if(0 <= si && si < H && 0 <= sj && sj < W) return true;
		return false;
	}
}
	
