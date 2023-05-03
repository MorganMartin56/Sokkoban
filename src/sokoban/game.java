package sokoban;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
    
public class game extends javax.swing.JFrame implements KeyListener 
{

    private JLabel [][] MyElements = new JLabel [12][12];
    private Map TmpMap; 
    public game() 
    {
        initComponents();
        //nested for loop to generate level
        for (int i = 0; i < MyElements.length; i++) 
        {
            for (int j = 0; j < MyElements.length; j++) 
            {
                MyElements [i][j] = new JLabel();
                MyElements[i][j] .setText(".");
                pnl_game.add(MyElements[i][j]);
            }
        }


        //Attaches key listener to form and checks if window is foucused
        this.addKeyListener(this);
        setFocusable(true);
                
                
        TmpMap = new Map();
        DrawMap();   
    }
    //This method draws the map and sets each object icon
  private void DrawMap() {
        try {
            for (int i = 0; i < MyElements.length; i++) 
            {
                for (int j = 0; j < MyElements.length; j++) 
                {
                    MapElement element = TmpMap.getMyMap()[i][j];
                    MyElements[i][j].setText(element.getSymbol());

                    if (element instanceof Boxs) 
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/box.jpg")));
                    } 
                    else if (element instanceof Diamond) 
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/diamond.jpg")));
                    } 
                     else if (element instanceof TNT) 
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/tnt.jpg")));
                    } 
                    else if (element instanceof Wall) 
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/wall.jpg")));
                        
                    } 
                    else if (element instanceof Player)
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/player.jpg")));
                    } 
                    else 
                    {
                        MyElements[i][j].setIcon(new ImageIcon(getClass().getResource("/images/floor.jpg")));
                    }
                }
            }
        } 
        //error handling
        catch (Exception e)
        {
            System.err.println("An error while drawing the map.");
            e.printStackTrace();
        }
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_status = new javax.swing.JPanel();
        lbl_output = new javax.swing.JLabel();
        DiamondCounter = new javax.swing.JProgressBar();
        pnl_game = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnl_status.setPreferredSize(new java.awt.Dimension(400, 30));

        lbl_output.setText("jLabel1");

        javax.swing.GroupLayout pnl_statusLayout = new javax.swing.GroupLayout(pnl_status);
        pnl_status.setLayout(pnl_statusLayout);
        pnl_statusLayout.setHorizontalGroup(
            pnl_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_statusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_output)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addComponent(DiamondCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnl_statusLayout.setVerticalGroup(
            pnl_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_statusLayout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(pnl_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DiamondCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_output))
                .addContainerGap())
        );

        getContentPane().add(pnl_status, java.awt.BorderLayout.PAGE_END);

        pnl_game.setPreferredSize(new java.awt.Dimension(400, 430));
        pnl_game.setLayout(new java.awt.GridLayout(12, 12));
        getContentPane().add(pnl_game, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new game().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar DiamondCounter;
    private javax.swing.JLabel lbl_output;
    private javax.swing.JPanel pnl_game;
    private javax.swing.JPanel pnl_status;
    // End of variables declaration//GEN-END:variables

    
    
    @Override
    public void keyTyped(KeyEvent e) {}
//Binds Each key to a specififc event that interacts with Label and outputs it to UI & It updates map with int value to translate onto Map
    @Override
    public void keyPressed(KeyEvent e) 
    {
            if(e.getKeyChar() == 'w' ||  e.getKeyCode() == KeyEvent.VK_UP)
            {
            try 
            {
            TmpMap.movePlayer(1);
            } 
            catch (InterruptedException ex) 
            {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbl_output.setText("You Pressed Up");
            }
           
            else if (e.getKeyChar() == 'a' ||  e.getKeyCode() == KeyEvent.VK_LEFT)
            {
            try 
            {
                TmpMap.movePlayer(2);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbl_output.setText("You Pressed Left");
            }
           
            else if (e.getKeyChar() == 's' ||  e.getKeyCode() == KeyEvent.VK_DOWN)
           {
            try 
            {
                TmpMap.movePlayer(3);
            } 
            catch (InterruptedException ex) 
            {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }
            lbl_output.setText("You Pressed Down");
            }
            
            else if (e.getKeyChar() == 'd' ||  e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
            try 
            {
                TmpMap.movePlayer(4);
            } 
            catch (InterruptedException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        lbl_output.setText("You Pressed Right");
        }
        //this redraws the map after each action
        DrawMap();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
