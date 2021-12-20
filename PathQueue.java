
	import java.util.LinkedList;
	import java.util.Queue;
  import java.util.ArrayList;

public class PathQueue {

    //to store the number of steps for solution
    int count = 0;
    int k;//to store number of obstacles we can remove
    int rl;//to go right or left
    int ud;//to go up or down
    //the knight can go left,right,up,down
    int[][] directions ={{0,1},{1,0},{0,-1},{-1,0}};

	  //returns the number of steps to get to the solution node depending on #obstacles
	  public int pathSteps(int[][] floor, int obtacles){
      //the path represented as a queue so we can push and pop frontier and explored set
      Queue<int[]> path  = new LinkedList<>();

      //gets the number of columns and rows so algorithm knows when invalid (hits wall)
      int columns = floor[0].length;
      int rows = floor.length;
	       
      // obstacles+1 to store the number of obstacles still left 
      //boolean array of visited nodes (true if a node was visited)
      boolean[][][] visitedNodes = new boolean [rows][columns][obtacles+1];

      //insert into queue depending on which direction chosen

      //insert into queue first node. Offer method checks queue capacity
      int []firstNode = {0,0,obtacles};
      //returns false if queue full, instead of add() so no exceptions
	    path.offer(firstNode);
      int pathLength;
      int parent[][][][] = new int[rows][columns][obtacles+1][3];
      parent[0][0][obtacles] = null;
      while(!path.isEmpty()){
        pathLength = path.size();
        while(pathLength>0){
          //poll() pops element from queue. if queue empty returns null
          int[] currentNode = path.poll();
          boolean isLastColumn=currentNode[1]==columns-1;
          boolean isLastRow=currentNode[0]==rows-1;
          //checks if we reached last node (bottom right) otherwise loops through all 4 directions
          if(isLastRow&&isLastColumn){  
            ArrayList<int[]> nodes = new ArrayList<>(); //create array list to print the path
            ArrayList<int[]> nodesRemoved = new ArrayList<>();
            while(currentNode!=null&&!(currentNode[0]==0&&currentNode[1]==0)){
              nodes.add(currentNode);
              if(floor[currentNode[0]][currentNode[1]]==1)
                nodesRemoved.add(currentNode);
              currentNode = parent[currentNode[0]][currentNode[1]][currentNode[2]];
              
            }
            nodes.add(currentNode);
            if(nodesRemoved.size()!=0){//print the point shere the arraw is used
              System.out.println("Arrow is used at position: ");
              for(int i = nodesRemoved.size() - 1; i >= 0; i--){
                System.out.print("("+nodesRemoved.get(i)[0]+", "+nodesRemoved.get(i)[1]+")");
                if(i!=0)
                  System.out.print(", ");
              }
            }
            System.out.println();
            for(int i = nodes.size() - 1; i >= 0; i--){
              System.out.print("("+nodes.get(i)[0]+", "+nodes.get(i)[1]+")");
              if(i!=0)
              System.out.print(" -> ");

            }
            System.out.println();


            return count;   
          }else{
            for(int[] direction: directions){
              k = currentNode[2];
              rl = direction[0]+currentNode[0];
              ud = direction[1]+currentNode[1];
              //to check if in grid
              if(ud >=0 && ud <columns){
                if(rl >=0 && rl <rows){
                  //to check if not in visited grid and has no obstacle
                  if((floor[rl][ud]==0)&& visitedNodes[rl][ud][k]==false){
                    int[] gridNode={rl,ud,k};
                    //mark as visited
                    visitedNodes[rl][ud][k]=true;
                    parent[rl][ud][k] = currentNode;
                    //push in queue
                    path.offer(gridNode);
                  }
                  //to check if has obstacle that we can remove
                  else  if((floor[rl][ud]==1)&& k>0&& (visitedNodes[rl][ud][k-1]==false) ){
                    int[] gridNode={rl,ud,k-1};
                    //mark as visited
                    parent[rl][ud][k-1] = currentNode;
                    visitedNodes[rl][ud][k-1]=true;
                    //push in queue
                    path.offer(gridNode);
                  }
                }
              }
	          }
	          pathLength--;
	        }
        }
        ++count;
	        
	  }
    return -1;
	}
}
	
	   

   