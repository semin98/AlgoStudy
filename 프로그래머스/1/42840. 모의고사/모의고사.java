class Solution {
    public int[] solution(int[] answers) {
        int[] scores = new int[3];
        
        int[] num1 = {1, 2, 3, 4, 5};
        int[] num2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] num3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int len1 = num1.length;
        int len2 = num2.length;
        int len3 = num3.length;
        
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == num1[i % len1]) scores[0]++;  
            
            if(answers[i] == num2[i % len2]) scores[1]++;  
            
            if(answers[i] == num3[i % len3]) scores[2]++;  
            
        }
        
        int max = Math.max(scores[0], Math.max(scores[1], scores[2]));
        
        int tot = 0;
        
        for(int tmp : scores) {
            if(tmp == max) tot++;
        }
        
        int[] answer = new int[tot];
        System.out.println(max);
        int idx = 0;
        
        for(int i = 0; i < 3; i++) {
            if(scores[i] == max) {
                answer[idx] = i + 1;
                idx++;
            }
        }
        return answer;
    }
}