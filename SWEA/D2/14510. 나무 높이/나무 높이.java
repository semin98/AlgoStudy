
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[] trees = new int[N];
            int max_height = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                max_height = Math.max(max_height, trees[i]);
            }

            int odd = 0;
            int even = 0;
            
            for(int i = 0; i < N; i++) {
                int diff = max_height - trees[i];
                if (diff == 0) continue;
                
                even += diff / 2;
                odd += diff % 2;
            }
            
            if(even > odd) {
                while(Math.abs(even - odd) > 1) {
                    even--;
                    odd += 2;
                }
            }
            
            int ans = 0;
            if (odd > even) {
                ans = odd * 2 - 1;
            } else if(even > odd) {
                ans =even * 2;
            } else {
                ans = odd + even;
            }

            System.out.println("#" + t + " " + ans);
        }

    }
}