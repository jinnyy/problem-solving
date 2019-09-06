/*
 * [프로그래머스][2017 카카오코드 예선] 카카오프렌즈 컬러링북
 * 14.38ms, 56.9MB
 */
 package dfs_bfs

class KakaoFriendsColoringBook {
    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};
    static int M, N, size, maxSizeOfOneArea, map[][];
    
    
    public int[] solution(int m, int n, int[][] picture) {
    	int numberOfArea = 0;
    	maxSizeOfOneArea = 0;
    	M = m;
    	N = n;
    	map = picture;
    	
    	boolean[][] visited = new boolean[m][n];
    	for(int i=0; i<m; i++){
    		for(int j=0; j<n; j++){
    			if(!visited[i][j] && picture[i][j]!=0){
    				numberOfArea++;
    				size=0;
    				dfs(i, j, visited);
    			}
    		}
    	}
    	
    	int[] answer = new int[2];
    	answer[0] = numberOfArea;
    	answer[1] = maxSizeOfOneArea;
    	return answer;
    }
    
    static void dfs(int cy, int cx, boolean[][] visited) {
        visited[cy][cx] = true;
        size++;
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, size);
        
        for(int i=0; i<4; i++){
            int y = cy + dy[i];
            int x = cx + dx[i];
            if(y<0 || x<0 || y>=M || x>=N) continue;
            if(!visited[y][x] && map[y][x]==map[cy][cx]) dfs(y, x, visited);
        }
    }
}
