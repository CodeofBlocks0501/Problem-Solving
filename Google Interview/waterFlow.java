class Solution{
	int[][]dir = {{1,0},{-1,0},{0,1},{0,-1}};
	public List<List<Integer>> waterFlow(int[][] matrix){
		List<List<Integer>>res = new ArrayList();
		if(matrix.length == 0 || matrix == null) return res;
		Queue<int[]>pBorder = new LinkedList<>();
		Queue<int[]>aBorder = new LinkedList<>();

		boolean[][] pVisited = new boolean[matrix.length][matrix[0].length];
		boolean[][] aVisited = new boolean[matrix.length][matrix[0].length];

		int row = matrix.length , col = matrix[0].length;

		//fill vertical 
		for(int i = 0; i < row ; i++){
			pBorder.add(new int[] {i,0});
			aBorder.add(new int[] {i,col-1});
			pVisited[i][0] = true;
			aVisited[i][col-1]= true;
		}

		//fill horizontal
		for(int i = 0 ; i < col ; i++){
			pBorder.add(new int[] {0, i});
			aBorder.add(new int[] {row-1,i});
			pVisited[0][i] = true;
			aVisited[row-1][i] = true;
		}

		BFS(matrix,pBorder,pVisited);
		BFS(matrix,aBorder,aVisited);

		for(int i =0 ; i < row ; i++){
			for(int j = 0; j < col ; j++){
				if(pVisited[i][j] && aVisited[i][j]){
					List<Integer> cur = new ArrayList<>();
					cur.add(i);
					cur.add(j);
					res.add(cur);
				}
			}
		}
		return res;
	}


	//BFS for Traverse in Every Direction 
	private void BFS(int[][] matrix, Queue<int[]> queue, boolean[][] visited){
		while(!queue.isEmpty()){
            int[] poll = queue.poll();
			for(int[] d : dir){
				int x = poll[0] + d[0];
				int y = poll[1] + d[1];

				if(x >= 0 && x < matrix.length && y>=0 && y < matrix[0].length 
					&& matrix[x][y] >= matrix[poll[0]][poll[1]] && !visited[x][y]){
					visited[x][y] = true;
				    queue.offer(new int[] {x,y});
				}
			}
		}
	}
}