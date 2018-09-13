import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
/**
 * Creates JFrame and adds the Game
 */
public class Application extends JFrame
{
    JPanel cards;
    JPanel cardMenu;
    JPanel cardGame;
    public Application()
    {
        cardMenu = new Menu(this);
        cardGame = new Game();
        
        cards = new JPanel(new PageViewer());
        cards.add(cardMenu, "MENU");
        cards.add(cardGame, "GAME");
        
        getContentPane().add(cards);
        
        pack();
        setResizable(false);
        
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void changeCard() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "GAME");
        pack();
        setLocationRelativeTo(null);
    }
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Application app = new Application();
                app.setVisible(true);
            }
        }
        );
    }
}
