

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] arr;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt()  throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = nextInt();

        for(int test_case = 1; test_case <= T; test_case++) {

            N = nextInt();
            arr = new int[N];
            for(int i = 0; i < N; i++) {
                arr[i] = nextInt();
            }

            int ans = 0;
            int i = 0;

            while(i < N - 1) {
                int up = 0;
                int down = 0;
                
                while(i < N - 1 && arr[i] < arr[i + 1]) {
                    up++;
                    i++;
                }

                while(i < N - 1 && arr[i] > arr[i + 1]) {
                    down++;
                    i++;
                }

                if(up > 0 && down > 0) {
                    ans += (up * down);
                }

                if(i < N - 1 && arr[i] == arr[i + 1]) {
                    i++;
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }
}