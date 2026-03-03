import java.io.*;
import java.util.*;

public class Main {

	static int[] arr;
	static int[][] person;
	static int N, num;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		num = Integer.parseInt(br.readLine());
		person = new int[num + 1][2];
		for(int i = 1; i <= num; i++) {
			st = new StringTokenizer(br.readLine());
			person[i][0] = Integer.parseInt(st.nextToken());
			person[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= num; i++) {
			if(person[i][0] == 1) {
				male(person[i][1]);
			} else female(person[i][1]);
			
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.print(arr[i] + " ");
			if(i % 20 == 0) System.out.println();
		}
		
		
	}
	
	static void male(int start) {
		for(int i = start; i <= N; i += start) {
			if(i <= N)
			arr[i] = (arr[i] + 1) % 2;
		}
	}
	
	static void female(int start) {
		arr[start] = 1 - arr[start];
		for(int i = 1; i <= N; i++) {
			if(start - i < 1 || start + i > N) break;
			
			if(arr[start - i] == arr[start + i]) {
				arr[start - i] = 1 - arr[start - i];
				arr[start + i] = 1 - arr[start + i];
			} else break;
		}
	}
}
