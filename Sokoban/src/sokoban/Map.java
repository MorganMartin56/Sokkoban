package sokoban;
import java.util.Arrays;
public class Map 
{
    // Initializing Coordinate values
    private MapElement[][] MyMap = new MapElement[12][12];
    private int playerX = 4, playerY = 5;
    private int[][] Walls = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, {0, 9}, {0, 10}, {0, 11}, {11, 0}, {11, 11}, {1, 11}, {2, 11}, {3, 11}, {4, 11}, {5, 11}, {6, 11}, {7, 11}, {8, 11}, {9, 11}, {10, 11}};
    private int[][] Diamonds = {{1, 3}, {2, 5}, {4, 7}};
    private int[][] Boxs = {{3, 2}, {6, 8}};
    
    Map() 
    {
        // Nested for-loop to traverse the MyMap 2D array
        for (int i = 0; i < MyMap.length; i++)
        {
            for (int j = 0; j < MyMap[i].length; j++) 
            {
                // If the current index is on the border or in the wallCoords array, then create a Wall object at the current index
                if (i == 0 || j == 0 || i == MyMap.length - 1 || j == MyMap[i].length - 1 || Arrays.asList(Walls).contains(new int[]{i, j})) 
                {
                    MyMap[i][j] = new Wall();
                } 
                else if (Arrays.asList(Diamonds).contains(new int[]{i, j})) 
                {
                    MyMap[i][j] = new Diamond();
                } 
                else if (Arrays.asList(Boxs).contains(new int[]{i, j})) 
                {
                    MyMap[i][j] = new Boxs();
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
    public void movePlayer(int dir) 
    {
        if (dir == 1) {
            MyMap[playerX][playerY] = new Floor();
            MyMap[--playerX][playerY] = new Player();
        } 
        else if (dir == 2) 
        {
            MyMap[playerX][playerY] = new Floor();
            MyMap[playerX][--playerY] = new Player();
        } 
        else if (dir == 3)
        {
            MyMap[playerX][playerY] = new Floor();
            MyMap[++playerX][playerY] = new Player();
        } 
        else if (dir == 4) 
        {
            MyMap[playerX][playerY] = new Floor();
            MyMap[playerX][++playerY] = new Player();
        }
    }
}
