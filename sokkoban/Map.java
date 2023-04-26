package com.mycompany.sokkoban;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Map {
    
       private Map_Element [][] myMap = new Map_Element [12][12];
        int playerX = 4, playerY = 5;

       
       
       Map(){
           
       for (int i = 0; i < myMap.length; i++) 
       {
        for (int j = 0; j < myMap.length; j++) 
                {
                myMap[i][j] = new Floor();    
                
                }
           
       
       } 
       myMap[4][5] = new Player(new ImageIcon("graphics/imgP.jpg"));
       
       } 
            
        public Map_Element[][] getmyMap()
       {
       return myMap;
       }
        public void movePlayer (int dir)
        {
        
           switch (dir) {
               case 1 -> 
               {
                   myMap[playerX][playerY] = new Floor();
                   myMap[--playerX][playerY] = new Player();
               }
               case 2 ->
               {
                   myMap[playerX][playerY] = new Floor();
                   myMap[++playerX][playerY] = new Player();
               }
               case 3 -> 
               {
                   myMap[playerX][playerY] = new Floor();
                   myMap[playerX][--playerY] = new Player();
               }
               case 4 -> 
               {
                   myMap[playerX][playerY] = new Floor();
                   myMap[playerX][++playerY] = new Player();
               }
               default -> {
               }
           }
}
}
    

