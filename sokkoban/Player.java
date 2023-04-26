
package com.mycompany.sokkoban;
import javax.swing.ImageIcon;

public class Player extends Map_Element
{
    private ImageIcon PlayerIcon;
    public Player() 
    {
        setSymbol("P");
        setImgFileName("graphics/imgP.jpg");
        PlayerIcon = new ImageIcon(getImageFileName());
        
    }

    public Player(ImageIcon PlayerIcon)
    {
    this.PlayerIcon = PlayerIcon;
    }
    pulbic ImageIcon getPlayerIcon()
    {
    return PlayerIcon;
    
    }
    public void setPlayerIcon(ImageIcon PlayerIcon)
    {
    this.PlayerIcon = PlayerIcon;
    }

}




