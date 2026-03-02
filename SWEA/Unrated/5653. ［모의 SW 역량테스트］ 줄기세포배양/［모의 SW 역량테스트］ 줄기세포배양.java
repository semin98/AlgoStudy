
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

class Solution {

    static class Cell implements Comparable<Cell> {
        int r;
        int c;
        int x;
        int time;
        int status; // 0 : 비활성화, 1 : 활성화, 2 : 죽음..

        Cell(int r, int c, int x) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.time = x;
            this.status = 0;
        }
        @Override
        public int compareTo(Cell o) {
            return o.x - this.x;
        }
    }

    static int N, M, K;
    static int[] di = {1, 0, -1, 0};
    static int[] dj = {0, 1, 0, -1};
    static List<Cell> cells;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N +  K + 100][M + K + 100];
            cells = new ArrayList<>();

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M;j ++) {
                    int x = Integer.parseInt(st.nextToken());
                    if(x > 0) {
                        int ni = i + K / 2 + 50;
                        int nj = j + K / 2 + 50;
                        map[ni][nj] = 1;
                        cells.add(new Cell(ni, nj, x));
                    }
                }
            }

            for(int time = 0; time < K; time++) {
                simulate();
            }

            int ans = 0;
            for(Cell c : cells) {
                if(c.status != 2) ans++;
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static void simulate() {
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        List<Cell> nextCells = new ArrayList<>();

        for(Cell c : cells) {
            if(c.status == 2) continue;

            if(c.status == 0) {
                c.time--;
                if(c.time == 0) c.status = 1;
            } else if(c.status == 1) {
                if(c.time == 0) {
                    for(int d = 0; d < 4; d++) {
                        int ni = c.r + di[d];
                        int nj = c.c + dj[d];
                        if(map[ni][nj] == 0) {
                            pq.offer(new Cell(ni, nj, c.x));
                        }
                    }
                }
                c.time++;
                if(c.time == c.x) c.status = 2;
            }
        }

        while(!pq.isEmpty()) {
            Cell cell = pq.poll();
            if(map[cell.r][cell.c] == 0) {
                map[cell.r][cell.c] = 1;
                nextCells.add(cell);
            }
        }

        cells.addAll(nextCells);
    }

}