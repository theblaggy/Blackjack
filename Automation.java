import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Automation is a support class to refresh the panel Game automatically
 * 
 * @author Lucas
 */
public class Automation extends JPanel implements ActionListener
{
    private Game game;
    public Automation(Game game)
    {
        this.game = game;
        new javax.swing.Timer(2000, this).start();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        game.gamelogic();
        game.redrawUI();
    }
}
