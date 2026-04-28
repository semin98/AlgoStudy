
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        String[] tmp = video_len.split(":");
        int total = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);

        tmp = pos.split(":");
        int now_time = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);

        tmp = op_start.split(":");
        int ops = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);

        tmp = op_end.split(":");
        int ope = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);

        for(int i = 0; i < commands.length; i++) {
            String cmd = commands[i];

            if(now_time >= ops && now_time <= ope) now_time = ope;

            if(cmd.equals("prev")) {
                now_time = Math.max(0, now_time - 10);
            } else if(cmd.equals("next")) {
                now_time = Math.min(total, now_time + 10);
            }

            if(now_time >= ops && now_time <= ope) now_time = ope;
        }

        return String.format("%02d:%02d", now_time / 60, now_time % 60);
    }
}