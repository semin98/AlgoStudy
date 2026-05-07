import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        
        List<String> uuid_list = new ArrayList<>();
        List<String> cmd_list = new ArrayList<>();
        
        Map<String, String> map = new HashMap<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] tmp = record[i].split(" ");
            String cmd = tmp[0];
            String uuid = tmp[1];
            
            
            if(cmd.equals("Change")) {
                map.put(uuid, tmp[2]);
            } else if(cmd.equals("Enter")) {
                map.put(uuid, tmp[2]);
                uuid_list.add(uuid);
                cmd_list.add(cmd);
            } else {
                uuid_list.add(uuid);
                cmd_list.add(cmd);
            }
        }
        String[] answer = new String[uuid_list.size()];
        for(int i = 0; i < uuid_list.size(); i++) {
            if(cmd_list.get(i).equals("Enter")) {
                answer[i] = map.get(uuid_list.get(i)) + "님이 들어왔습니다.";
            } else if(cmd_list.get(i).equals("Leave")) {
                answer[i] = map.get(uuid_list.get(i)) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}