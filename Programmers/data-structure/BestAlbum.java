/*
 * [프로그래머스][level3] 베스트앨범
 */
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.LinkedList;


class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Genre> G = new HashMap<String, Genre>();
        for(int i=0; i<genres.length; i++) {
        	Genre cur;
        	if(G.containsKey(genres[i])) cur = G.get(genres[i]);
        	else {
        		cur = new Genre(genres[i]);
        		G.put(cur.name, cur);
        	}
            cur.addMusic(new Music(i, plays[i]));
        }
        PriorityQueue<Genre> maxHeap = new PriorityQueue<Genre>();
        for(String key : G.keySet()) {
        	maxHeap.add(G.get(key));
        }
        LinkedList<Integer> ansList = new LinkedList<Integer>();
        while(!maxHeap.isEmpty()) {
        	Genre g = maxHeap.poll();
        	for(int i=0; i<2; i++) {
        		Music m = g.musics.poll();
        		if(m ==null) break;
        		ansList.add(m.idx);
        	}
        }
        int n = ansList.size();
        int[] answer = new int[n];
        for(int i=0; i<n; i++) {
        	answer[i] = ansList.poll();
        }
        return answer;
    }
}

class Genre implements Comparable<Genre> {
	String name;
	int play;
	PriorityQueue<Music> musics;
	
	Genre(String name) {
		this.name = name;
		this.play = 0;
		this.musics = new PriorityQueue<Music>();
	}
	
	void addMusic(Music music) {
		this.musics.add(music);
		this.play += music.play;
	}
	
	@Override
	public int compareTo(Genre g) {
		return g.play - this.play;
	}
}

class Music implements Comparable<Music> {
	int play, idx;
	
	Music(int idx, int play) {
		this.idx = idx;
		this.play = play;
	}
	
	@Override
	public int compareTo(Music m) {
		if(m.play == this.play) return this.idx - m.idx;
		return m.play - this.play;
	}
}
