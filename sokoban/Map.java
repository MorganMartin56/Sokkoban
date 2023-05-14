package sokoban;
public class Map {
    // Initializing Coordinate values for each object
    private MapElement[][] MyMap;
    int newPlayerX;
    int newPlayerY;
    int MoveCounter = 0;
    int Moves = 40;
    int mapNumber;
    public void SetPlayerCoords(int x, int y) {
        newPlayerX = x;
        newPlayerY = y;
    }
    boolean DiamondCheck = false;
    boolean TNTCheck = false;
    boolean LevelFinish =false;
    boolean IfTouch = false;

   private int DiamondCounter = 0;
   private int DiamondsMax = 0;

   public void setDiamondMax(int num){
       DiamondsMax = num;
   }

    public Map(int mapNumber) {
        ReadFileToArray reader = new ReadFileToArray();
        this.MyMap = reader.ConstructorArray(mapNumber);
        this.DiamondsMax = reader.getDiamondCounter();
        this.mapNumber = mapNumber;    
    }

    public MapElement[][] getMyMap() {
        return MyMap;
    }
    
    
    // This updates and moves the player depending on the Key INT and updates player and Floor
    public void movePlayer(int dir) throws InterruptedException {
        int prevPlayerX = newPlayerX;
        int prevPlayerY = newPlayerY;

        int BoxX = -1;
        int BoxY = -1;

        //Updates Player and Box Movement Depending on Dir
        if (dir == 1) {
            newPlayerX--;
            MoveCounter++;
            BoxX = newPlayerX - 1;
            BoxY = newPlayerY;
            System.out.println(newPlayerX + " X " + newPlayerY + " Y");

        }
        //Updates Player and Box Movement Depending on Dir
        else if (dir == 2) {
            newPlayerY--;
            MoveCounter++;
            BoxX = newPlayerX;
            BoxY = newPlayerY - 1;
            System.out.println(newPlayerX + " X " + newPlayerY + " Y");

        }
        //Updates Player and Box Movement Depending on Dir
        else if (dir == 3) {
            newPlayerX++;
            MoveCounter++;
            BoxX = newPlayerX + 1;
            BoxY = newPlayerY;
            System.out.println(newPlayerX + " X " + newPlayerY + " Y");

        }
        //Updates Player and Box Movement Depending on Dir
        else if (dir == 4) {
            newPlayerY++;
            MoveCounter++;
            BoxX = newPlayerX;
            BoxY = newPlayerY + 1;
            System.out.println(newPlayerX + " X " + newPlayerY + " Y");

        }

        // player pos check into wall
        if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("W")) {
            System.out.println("You cannot move a wall");
            return;
        }
        //closes application if player is out of bounds from map level
        else if (newPlayerX < 0 || newPlayerX > 11 || newPlayerY < 0 || newPlayerX > 11) {
            System.exit(0);
            return;
        }

        //Collision detection for player moved box
        boolean BoxMoved = false;
        if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("B")) {
            BoxMoved = true;
            IfTouch = true;
        }

        if (BoxMoved) 
        {
            if (MyMap[BoxX][BoxY].getSymbol().equals("W")) {
                System.out.println("Box has hit a wall!");
                return;
            } 
            else if (BoxX < 0 || BoxX > 11 || BoxY < 0 || BoxY > 11)
                System.exit(0);
        }
        //Collects diamonds on collision with player and sets counter  
        if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("X")) {
            System.out.println("Diamond Collected");
            DiamondCheck = true;
            DiamondCounter++;
            System.out.println("Total diamonds collected: " + DiamondCounter);
            if (DiamondCounter == DiamondsMax) 
            {
                LevelFinish = true;
                System.out.println("Level Complete");
            }
        }
        
        if (MyMap[newPlayerX][newPlayerY].getSymbol().equals("T")) {
            //Collision for detecting if the player touched TNT
            //Put Death screen
            System.out.println("YOU EXPLODED ' Maybe dont touch high explosives? '");
            TNTCheck = true;
            if (TNTCheck) {
                try {
                    //sleeps player
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }

        }
        // Update player and floor position on map
        MyMap[prevPlayerX][prevPlayerY] = new Floor();
        if (MyMap[newPlayerX][newPlayerY] instanceof Boxs) 
        {
            Boxs newBox =  (Boxs) MyMap[newPlayerX][newPlayerY];
            MyMap[BoxX][BoxY] = newBox.deepCopy();
        }
        MyMap[newPlayerX][newPlayerY] = new Player();
     

        if (BoxMoved) {
            //This moves the box, floor and player position
            MyMap[prevPlayerX][prevPlayerY] = new Floor();
            MyMap[newPlayerX][newPlayerY] = new Floor();
            MyMap[prevPlayerX][prevPlayerY] = new Player();
            
            if (MyMap[BoxX][BoxY] instanceof Boxs) 
            {
                Boxs Box = (Boxs) MyMap[BoxX][BoxY];
                if (Box.diamondDropped) 
                {
                    MyMap[newPlayerX][newPlayerY] = new Floor();
                }
                 else 
                    {
                    MyMap[newPlayerX][newPlayerY] = new Diamond();
                    Box.diamondDropped = true;
                    }
            }
          
            
        }
        if (MoveCounter >= Moves) {
            System.out.println("You've used all of your moves");
            try 
            {
                //sleeps player
                Thread.sleep(2500);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}