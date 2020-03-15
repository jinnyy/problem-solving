/*
 * [프로그래머스][Level2][전화번호 목록]
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 */
import java.util.Arrays;
import java.util.HashMap;


class Solution {
    public boolean solution(String[] phone_book) {
        HashMap<String, Boolean> map = new HashMap<>();
        Arrays.sort(phone_book);
        for(String phone : phone_book) {
        	StringBuilder sb = new StringBuilder();
        	for(int i=0; i<phone.length(); i++) {
        		sb.append(phone.charAt(i));
        		if(map.containsKey(sb.toString()))
        			return false;
        	}
        	map.put(phone, true);
        }
        return true;
    }
}
