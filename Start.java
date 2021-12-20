import java.util.Scanner;

public class Start {
	
  public static void main(String[] args){

      Scanner input = new Scanner(System.in);
      int m;
      int n;
      int arrows;
      int x;
      //introducing user to program
      System.out.println("Welcome Knight! We rely on you to save our Princess! She has been captured by a demon and is currently trapped in a dungeon.\nWe know that the dungeon's floor map is a grid. Lets start planning by looking at the possibilities!");
      
      //getting number of rows from user
      System.out.println("What if the floor map has M rows?\nEnter value for M.");
      //Integer validation for rows
      while(!input.hasNextInt()) {
        input.next(); 
        System.out.println("Please enter a valid value for M.");
      }
      
      m = input.nextInt();
      //Positive number validation for rows
      while (m<=0) {
        System.out.println("Please enter a valid value for M.");
          m = input.nextInt();
      }


      //getting number of columns from user
      System.out.println("What if the floor map has N columns?\nEnter value for N.");
      //Integer validation for columns
      while(!input.hasNextInt()) {
        input.next(); 
        System.out.println("Please enter a valid value for N.");
      }
        n = input.nextInt();
       //Positive number validation for columns
        while (n<=0) {
        System.out.println("Please enter a valid value for N.");
          n = input.nextInt();
      }
      int[][] floor= new int [m][n];


      //getting number of obstacles from user
      System.out.println("Enter the elements of the floor map row by row.\nEnter 0 for no obstacle node and 1 for obtacle node.");

      for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
         //Validation for obstacles to get only 0 or 1
            while(!input.hasNextInt()) {
              input.next(); 
              System.out.println("Please enter a valid value.");
            }
            x= input.nextInt();
            while (! (x == 0 || x == 1) ) {
              System.out.println("Please enter a valid value.");
              x = input.nextInt();
            }
            floor[i][j]=x;       
        }
      }
	    System.out.println("The floor map looks like this.");
	    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
          System.out.print(floor[i][j]);
        }
        System.out.println();
      }

	    //arrows is the number of obstacles that can be removed (k)
	    System.out.println("How many arrows do you have?");
       //Integer validation for arrows
      while(!(input.hasNextInt())) {
        input.next(); 
        System.out.println("Please enter a valid value for arrows");
      }
      arrows = input.nextInt();
       //Positive number validation for arrows
      while (arrows<0) {
        System.out.println("Please enter a valid value for arrows");
        arrows = input.nextInt();
      }
      System.out.println("\nThe shortest path without using arrows is :");
      PathQueue p = new PathQueue();
      int solution = p.pathSteps(floor, 0);
      
      if (solution == -1) { System.out.println("No solution is found! We need to eliminate more obstacles to find such a walk.");
      System.exit(0);}
      System.out.println("The number of steps to reach the princess without using arrows is "+ solution+".\n\n");
      System.out.println("------------------------------");
      System.out.println("The shortest path using arrows is :");
      PathQueue p1 = new PathQueue();
      int solution1 = p1.pathSteps(floor, arrows);
      System.out.println("The number of steps to reach the princess is when using "+arrows+" arrows is "+ solution1+".");

	}
}
