import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    static int[][][] arr;
    static boolean[][][] chk;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        arr = new int[102][102][102];
        chk = new boolean[102][102][102];

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1 && c == -1) break;
            w(a, b, c);

            System.out.println("w(" + a + ", " + b + ", " + c + ") = " + arr[a + 50][b + 50][c + 50]);
        }
    }

    static int w(int a, int b, int c) {
        if(chk[a + 50][b + 50][c + 50]) {
            return arr[a + 50][b + 50][c + 50];
        }

        if(a <= 0 || b <= 0 || c <= 0) {
            arr[a + 50][b + 50][c + 50] = 1;
            chk[a + 50][b + 50][c + 50] = true;
        } else if( a> 20 || b > 20 || c > 20) {
            arr[a + 50][b + 50][c + 50] = w(20, 20, 20);
            chk[a + 50][b + 50][c + 50] = true;
        } else if(a < b && b < c) {
            arr[a + 50][b + 50][c + 50] = w(a, b, c -1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
            chk[a + 50][b + 50][c + 50] = true;
        } else {
            arr[a + 50][b + 50][c + 50] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1 );
            chk[a + 50][b + 50][c + 50] = true;
        }
        return arr[a + 50][b + 50][c + 50];
    }
}