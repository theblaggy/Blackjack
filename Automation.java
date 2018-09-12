import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Automation extends JPanel implements ActionListener
{
    Game game;
    public Automation(Game game)
    {
        this.game = game;
        new javax.swing.Timer(2000, this).start();
    }
    
    public void actionPerformed(ActionEvent e)
    {
        game.gameloop(-1);
    }
}
