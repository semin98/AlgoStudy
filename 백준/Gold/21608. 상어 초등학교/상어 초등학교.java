import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] order;
	static int[] di = {-1, 0, 0, 1};
	static int[] dj = {0, -1, 1, 0};
	static int[][] map, preferences;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		order = new int[N * N];
		map = new int[N][N];
		preferences = new int[N * N + 1][4];
		
		for(int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			order[i] = student;
			
			for(int j = 0; j < 4; j++) {
				preferences[student][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N * N; i++) {
			placeStudent(order[i]);
		}
		
		System.out.println(calculateSatisfaction());
		
	}
	
	static void placeStudent(int student) {
		int targetI = -1, targetJ = -1;
		int maxLike = -1, maxEmpty = -1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != 0) continue;
				
				int likeCnt = 0;
				int emptyCnt = 0;
				
				for(int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					
					if(ni >= 0 && ni < N && nj >=0 && nj < N) {
						if(map[ni][nj] == 0) {
							emptyCnt ++;
						} else {
							for(int friend : preferences[student]) {
								if(map[ni][nj] == friend) {
									likeCnt++;
									break;
								}
							}
						}
					}
				}
				
				if(likeCnt > maxLike) {
					maxLike = likeCnt;
					maxEmpty = emptyCnt;
					targetI = i;
					targetJ = j;
				} else if(likeCnt == maxLike) {
					if(emptyCnt > maxEmpty) {
						maxEmpty = emptyCnt;
						targetI = i;
						targetJ = j;
					}
				}
			}
		}
		map[targetI][targetJ] = student;
	}
	
	static int calculateSatisfaction() {
		int total = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int student = map[i][j];
				int count = 0;
				
				for(int d = 0; d < 4; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					
					if(ni >= 0 && ni < N && nj >= 0 && nj < N) {
						for(int friend : preferences[student]) {
							if(map[ni][nj] == friend) {
								count++;
								break;
							}
						}
					}
				}
				
				if(count > 0) {
					total += (int) Math.pow(10,  count - 1);
				}
			}
		}
		
		return total;
	}
}
