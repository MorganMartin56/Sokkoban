package sokoban;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;


public class ReadFileToArray 
{
    private int diamondCounter;

    public ReadFileToArray(){
    }

    public int getDiamondCounter(){
        return diamondCounter;
    }

    public MapElement[][] ConstructorArray(int mapNumber) {
        MapElement[][] MapArray = new MapElement[12][12];
        try
        {
            // Create a Scanner object to read from the file
            URL resourceURL = ReadFileToArray.class.getResource("Map" + mapNumber + ".txt");
            File file = new File(resourceURL.getPath());
            Scanner scanner = new Scanner(file);

            // Loop through each row of the 2D array
            for (int i = 0; i < MapArray.length; i++) 
            {
                // Read a line from the file
                String line = scanner.nextLine();

                // Loop through each character in the line
                for (int j = 0; j < line.length(); j++) 
                {
                    // Store the character in the 2D array
                    MapArray[i][j] = ElementFinder(line.charAt(j));
                    if (line.charAt(j) == 'X' || line.charAt(j) =='B')
                    {
                        diamondCounter++;
                    }
                }
            }

            // Close the Scanner object
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the exception if the file is not found
            System.out.println("Please check file path");
            e.printStackTrace();
        }
        // Print the diamond count
        System.out.println("There are : " + diamondCounter + " Go Find them!");

        return MapArray;
    }

    public MapElement ElementFinder(char element) {
        switch (element) {
            case 'F':
                return new Floor();

            case 'W':
                return new Wall();

            case 'X':
                return new Diamond();

            case 'P':
                return new Player();

            case 'T':
                return new TNT();

            case 'B':
                return new Boxs();

            default:
                return new Wall();
        }
    }
}