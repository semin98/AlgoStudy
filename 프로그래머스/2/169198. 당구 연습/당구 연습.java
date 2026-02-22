

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for(int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int minDistance = Integer.MAX_VALUE;

            if(!(startY == targetY && startX > targetX)) {
                minDistance = Math.min(minDistance, getDistance(startX,startY, -targetX, targetY));
            }

            if (!(startY == targetY && startX < targetX)) {
                minDistance = Math.min(minDistance, getDistance(startX, startY, 2 * m - targetX, targetY));
            }

            if (!(startX == targetX && startY > targetY)) {
                minDistance = Math.min(minDistance, getDistance(startX, startY, targetX, -targetY));
            }

            if (!(startX == targetX && startY < targetY)) {
                minDistance = Math.min(minDistance, getDistance(startX, startY, targetX, 2 * n - targetY));
            }

            answer[i] = minDistance;
        }

        return answer;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow(x1 - x2, 2) + Math.pow(y1 - y2 , 2));
    }
}