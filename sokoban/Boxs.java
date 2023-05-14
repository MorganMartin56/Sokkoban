package sokoban;
public class Boxs extends MapElement 
{ 
      public boolean diamondDropped;
      
      
      public Boxs deepCopy()
      {
          Boxs Copy = new Boxs();
          Copy.diamondDropped = this.diamondDropped;
          return Copy;
      
      }
      
      
    Boxs()
    {
    diamondDropped = false;
    setSymbol ("B");
    setImgFileName("/images/box.jpg");
    
    }
}
