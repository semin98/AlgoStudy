import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, maxVal = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.println(maxVal);
    }

    static void dfs(int cnt, int[][] curBoard) {
        if(cnt == 5) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    maxVal = Math.max(maxVal, curBoard[i][j]);
                }
            }
            return;
        }

        for(int d = 0; d < 4; d++) {
            int[][] nextBoard = move(d, curBoard);
            dfs(cnt + 1, nextBoard);
        }
    }

    static int[][] move(int dir, int[][] board) {
        int[][] next = new int[N][N];

        for(int i = 0; i < N; i++) {
            next[i] = board[i].clone();
        }

        if(dir == 0) {
            for(int c = 0; c < N; c++) {
                Queue<Integer> q = new LinkedList<>();
                for(int r = 0; r < N; r++) {
                    if(next[r][c] !=0) q.offer(next[r][c]);
                    next[r][c] = 0;
                }
                int rIdx = 0;
                while(!q.isEmpty()) {
                    int val = q.poll();
                    if(!q.isEmpty() && q.peek() == val) {
                        next[rIdx++][c] = val * 2;
                        q.poll();
                    } else {
                        next[rIdx++][c] = val;
                    }
                }
            }
        } else if(dir == 1) {
            for(int c = 0; c < N; c++) {
                Queue<Integer> q = new LinkedList<>();
                for(int r = N - 1; r >= 0; r--) {
                    if(next[r][c] != 0) q.offer(next[r][c]);
                    next[r][c] = 0;
                }
                int rIdx = N - 1;
                while(!q.isEmpty()) {
                    int val = q.poll();
                    if(!q.isEmpty() && q.peek() == val) {
                        next[rIdx--][c] = val * 2;
                        q.poll();
                    } else {
                        next[rIdx--][c] = val;
                    }
                }
            }
        } else if(dir == 2) {
            for(int r = 0; r < N; r++) {
                Queue<Integer> q = new LinkedList<>();
                for(int c = 0; c < N; c++) {
                    if(next[r][c] != 0) q.offer(next[r][c]);
                    next[r][c] = 0;
                }
                int cIdx = 0;
                while(!q.isEmpty()) {
                    int val = q.poll();
                    if(!q.isEmpty() && q.peek() == val) {
                        next[r][cIdx++] = val * 2;
                        q.poll();
                    } else {
                        next[r][cIdx++] = val;
                    }
                }
            }
        } else {
            for(int r = 0; r < N; r++) {
                Queue<Integer> q = new LinkedList<>();
                for(int c = N - 1; c >= 0; c--) {
                    if(next[r][c] != 0) q.offer(next[r][c]);
                    next[r][c] = 0;
                }
                int cIdx = N - 1;
                while(!q.isEmpty()) {
                    int val = q.poll();
                    if(!q.isEmpty() && q.peek() == val) {
                        next[r][cIdx--] = val * 2;
                        q.poll();
                    } else {
                        next[r][cIdx--] = val;
                    }
                }
            }
        }
        return next;
    }
}
