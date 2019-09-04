/*
 * [프로그래머스][dfs/bfs][level3] 여행경로
 * https://programmers.co.kr/learn/courses/30/lessons/43164
 */
package dfs_bfs;
import java.util.HashMap;
import java.util.ArrayList;


public class TravelRoute {
	static Airport[] gRoute;
	
	public String[] solution(String[][] tickets) {
		HashMap<String, Airport> airports = new HashMap<String, Airport>();
		Airport start = null;
		for(String[] ticket : tickets) {
			Airport from, to;
			if(airports.containsKey(ticket[0])) from = airports.get(ticket[0]);
			else from = new Airport(ticket[0]);
			if(airports.containsKey(ticket[1])) to = airports.get(ticket[1]);
			else to = new Airport(ticket[1]);
			if(from.name.equals("ICN")) start = from;
			
			from.addDest(to);
			airports.put(from.name, from);
			airports.put(to.name, to);
		}
		
		// dfs
		Airport[] path = new Airport[tickets.length+1];
		path[0] = start;
		dfs(start, 1, path);
		
		String[] answer = new String[path.length];
		for(int i=0; i<path.length; i++) {
			answer[i] = gRoute[i].name;
		}
		return answer;
	}
	
	static void dfs(Airport cur, int depth, Airport[] path) {
		if(depth==path.length) {
			// path끼리 대소 비교해서 업데이트
			if(gRoute==null) {
				gRoute = new Airport[path.length];
				for(int i=0; i<path.length; i++) {
					gRoute[i] = path[i];
				}
			} else {
				for(int i=0; i<path.length; i++) {
					int comp = gRoute[i].compareTo(path[i]);
					// 현재 path가 더 작으면 바꾼다
					if(comp==0) continue;
					else {
						if(comp<0) {
							for(int idx=0; idx<path.length; idx++) {
								gRoute[idx] = path[idx];
							}
						}
						break;
					}
				}
			}
			return;
		}
		for(Airport dest : cur.dests) {
			if(!cur.isVisitable(dest)) continue;
			path[depth] = dest;
			cur.visit(dest);
			dfs(dest, depth+1, path);
			path[depth] = null;
			cur.reset(dest);
		}
		
	}
}


class Airport implements Comparable<Airport> {
	String name;
	ArrayList<Airport> dests;
	HashMap<Airport, Integer> visitable;
	
	Airport(String name) {
		this.name = name;
		this.dests = new ArrayList<Airport>();
		this.visitable = new HashMap<Airport, Integer>();
	}
	
	void addDest(Airport airport) {
		this.dests.add(airport);
		int cnt = 1;
		if(this.visitable.containsKey(airport))
			cnt += this.visitable.get(airport);
		this.visitable.put(airport, cnt);
	}
	
	void visit(Airport dest) {
		this.visitable.put(dest, this.visitable.get(dest)-1);
	}
	
	void reset(Airport dest) {
		this.visitable.put(dest, this.visitable.get(dest)+1);
	}
	
	boolean isVisitable(Airport dest) {
		return this.visitable.get(dest)>0;
	}
	
	@Override
	public int compareTo(Airport B) {
		for(int i=0; i<this.name.length(); i++) {
			char cur = this.name.charAt(i);
			char b = B.name.charAt(i);
			if(cur == b) continue;
			if(cur < b) return 1;
			else return -1;
		}
		return 0;
	}
	
	// 디버깅용
	@Override
	public String toString() {
		return this.name;
	}
}
