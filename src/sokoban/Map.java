package sokoban;
import java.util.Arrays;
import javax.swing.JFrame;
import java.util.Timer;
import java.util.TimerTask;
public class Map {
    // Initializing Coordinate values for each object
    private final MapElement[][] MyMap = new MapElement[12][12];
    private final int[][] Walls = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11}, {11, 0}, {11, 11}, {1, 11}, {2, 11}, {3, 11}, {4, 11}, {5, 11}, {6, 11}, {7, 11}, {8, 11}, {9, 11}, {10, 11}};
    private final int[][] Diamonds = {{1, 3}, {2, 5}, {4, 7}};
    private final int[][] Boxes = {{3, 2}, {6, 8}};
    private final int[][] TNTs = {{5, 8}, {9, 8}}; 
    private int playerX = 4, playerY = 5;
    public int PlayerPosX = playerX;
    public int PlayerPosY = playerY;
    boolean DiamondCheck = false;
    boolean TNTCheck = false;
    public int DiamondLength = Diamonds.length;
    int DiamondCounter;

    
    Map() 
    {
        // Nested for-loop to traverse the MyMap 2D array
       for (int i = 0; i < MyMap.length; i++)
       {
    for (int j = 0; j < MyMap[i].length; j++) 
    {
        //Allows for i&j to be referenced via lambda 
        final int x = i;
        final int y = j;

               // If the current position in the loop is at the edge of the map or in the walls array, then create a Wall object at that position
                if (i == 0 || j == 0 || i == MyMap.length - 1 || j == MyMap[i].length - 1 || Arrays.asList(Walls).contains(new int[]{i, j})) 
                {
                MyMap[i][j] = new Wall();
                } 
                //creates a stream of array and matches x&y to check position
               else if (Arrays.stream(Diamonds). anyMatch(coords -> coords[0] == x && coords[1] == y)) 
               {
                MyMap[i][j] = new Diamond();
                System.out.println("There are a total of " + DiamondLength + " Diamonds");
                System.out.println("Diamonds Pos [" + i + "][" + j + "]");
                }
               //creates a stream of array and matches x&y to check position
               else if (Arrays.stream(Boxes).anyMatch(coords -> coords[0] == y && coords[1] == x)) 
                {
                MyMap[i][j] = new Boxs();
                System.out.println("Boxes Pos [" +i + "][" + j + "]");
                }
                      else if (Arrays.stream(TNTs).anyMatch(coords -> coords[0] == y && coords[1] == x)) 
                {
                MyMap[i][j] = new TNT();
                System.out.println("Creating TNT [" +i + "][" + j + "]");
                }
                else if (i == playerX && j == playerY) 
                {
                    MyMap[playerX][playerY] = new Player();
                } 
                else 
                {
                    MyMap[i][j] = new Floor();
  
                }
            }
        }

    }

    public MapElement[][] getMyMap()
    {
        return MyMap;
    }

    // This updates and moves the player depending on the Key INT and updates player and Floor
   public void movePlayer(int dir)  throws InterruptedException
   {
      
    int newPlayerX = playerX;
    int newPlayerY = playerY;
    
    int BoxX = - 1;
    int BoxY = - 1;

    //Updates Player and Box Movement Depending on Dir
    if (dir == 1) 
    {
        newPlayerX--;
        BoxX = playerX -2;
        BoxY = playerY;
        System.out.println(playerX +" X "+ playerY +" Y");
        
    }
    //Updates Player and Box Movement Depending on Dir
    else if (dir == 2) 
    {
        newPlayerY--;
        BoxX = playerX;
        BoxY = playerY - 2;
        System.out.println(playerX +" X "+ playerY +" Y");
        
    } 
    //Updates Player and Box Movement Depending on Dir
    else if (dir == 3) 
    {
        newPlayerX++;
        BoxX = playerX + 2;
        BoxY = playerY;
        System.out.println(playerX +" X "+ playerY +" Y");  
        
    } 
    //Updates Player and Box Movement Depending on Dir
    else if (dir == 4) 
    {
        newPlayerY++;
        BoxX = playerX;
        BoxY = playerY + 2;
        System.out.println(playerX +" X "+ playerY +" Y");
       
    }

    // player pos check into wall
    if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("W")) 
    {
        System.out.println("You cannot move a wall");
        return;
    }
    //closes application if player is out of bounds from map level
    else if ( newPlayerX  < 0 || newPlayerX > 11 || newPlayerY < 0 || newPlayerX > 11)
    {
       System.exit(0);
       return;
    }
    
    //Collision detection for player moved box
     boolean BoxMoved = false;
         if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("B")) 
       {
           BoxMoved = true;
       }
           
       if (BoxMoved) 
       {
             if (MyMap[BoxX][BoxY].getSymbol().equals("W")) 
           {
               System.out.println("Box has hit a wall!");
               return;
           }
             else if(BoxX < 0 || BoxX > 11 || BoxY < 0 || BoxY > 11) 
                 System.exit(0);
       }
     //Collects diamonds on collision with player and sets counter  
    if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("X")) 
    {
    System.out.println("Diamond Collected");
    DiamondCheck = true;    
    DiamondCounter++;
    System.out.println("Total diamonds collected: " + DiamondCounter);
     if (DiamondLength == DiamondCounter) 
     {
        //Put next level option
        System.out.println("You've Successfully Collected all diamonds on this level");
        System.exit(0);
    }
}
       if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("T")) 
{
    //Collision for detecting if the player touched TNT
    //Put Death screen
    System.out.println("YOU EXPLODED ' Maybe dont touch a box with high explosives? '");
    TNTCheck = true;   
    if (TNTCheck) 
    {
        try
        {
            //sleeps player
            Thread.sleep(2000);
        }
             catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.exit(0);
    }

}
       //Collision detection with Boxes and TNT
if (MyMap[BoxX][BoxY].getSymbol().equals("T")) 
{
    //Put Death screen
    System.out.println("YOU EXPLODED ' Maybe dont put a box on high explosives? '");
    TNTCheck = true;   
    if (TNTCheck) 
    {
        try
        {
            //sleeps player
            Thread.sleep(2000);
        }
             catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        System.exit(0);
    }}
    
    // Update player and floor position on map
    MyMap[playerX][playerY] = new Floor();
    MyMap[newPlayerX][newPlayerY] = new Player();
    playerX = newPlayerX;
    playerY = newPlayerY;
    
    if (BoxMoved) 
    {
        //This moves the box, floor and player position
        MyMap[BoxX][BoxY] = new Boxs();
        MyMap[playerX][playerY] = new Floor();
        MyMap[newPlayerX][newPlayerY] = new Player();

        }
    }
}

