import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    // 맨 왼쪽 수부터 소수여야지 시작이 가능(2, 3, 5, 7)
    // 뒤에 숫자를 붙여가면서 소수이면 계속 가고 아니면 후진
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }

    // 작은 수부터 시작하니 자동으로 오름차순이 됨
    static void dfs(int num, int depth) {

        if(depth == N) {
            System.out.println(num);
            return;
        }

        for(int i = 1; i <= 9; i++) {
            int next = num * 10 + i;

            if(isPrime(next)) {
                dfs(next, depth + 1);
            }
        }
    }

    static boolean isPrime(int n) {

        if(n < 2) return false;

        for(int i = 2; i  * i <= n; i++) {
            if(n % i == 0) return false;
        }

        return true;
    }
}