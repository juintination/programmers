import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> nicknameMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        for (String str : record) {
            StringTokenizer st = new StringTokenizer(str, " ");
            String operation = st.nextToken();
            if (operation.equals("Enter")) {
                String uid = st.nextToken();
                String nickname = st.nextToken();
                nicknameMap.put(uid, nickname);
                list.add(uid + "님이 들어왔습니다.");
            } else if (operation.equals("Leave")) {
                String uid = st.nextToken();
                list.add(uid + "님이 나갔습니다.");
            } else if (operation.equals("Change")) {
                String uid = st.nextToken();
                String nickname = st.nextToken();
                nicknameMap.put(uid, nickname);
            }
        }
        String[] answer = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            int idx = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '님') {
                    idx = j;
                    break;
                }
            }
            String uid = str.substring(0, idx);
            StringBuilder sb = new StringBuilder();
            sb.append(nicknameMap.get(uid));
            sb.append(str.substring(idx));
            answer[i] = sb.toString();
        }
        return answer;
    }
}
