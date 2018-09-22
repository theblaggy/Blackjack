import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

/**
 * Creates JFrame and adds Menu & Game
 */
public class Application extends JFrame
{
    JPanel cards;
    JPanel cardMenu;
    JPanel cardGame;
    
    /**
     * Creates the Menu and Game card and sets attributes
     */
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
    
    /**
     * Changes the Menu card to the Game card
     */
    public void changeCard() {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "GAME");
        pack();
        setLocationRelativeTo(null);
    }
}
