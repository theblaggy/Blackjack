import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Handles the whole Game:
 * Draws the UI
 * Calculates the game
 * Manages mouse clicks
 * 
 * @author Lucas & Filip
 */
public class Game extends JPanel
{    
    private Dealer dealer = new Dealer();
    private Player player = new Player(dealer);
    
    private JLabel labelChips;
    private JLabel labelBetCoin;
    private JLabel labelBetText;
    private JLabel labelHandValue;
    private JLabel labelDealersValue;
    private JLabel labelBox1;
    private JLabel labelBox2;
    private JLabel labelAction;
    private JLabel labelHit;
    private JLabel labelStand;
    private JLabel labelDouble;
    private JButton buttonHit;
    private JButton buttonStand;
    private JButton buttonDouble;
    private JLabel[] labelCards = new JLabel[20];
    
    private int state = 1;
    private boolean win = false;
    private boolean finished = false;
    
    private Image background;
    
    public Game()
    {
        ImageIcon iiBackground = new ImageIcon("src/resources/background.png");
        background = iiBackground.getImage();
    
        int w = background.getWidth(this);
        int h = background.getHeight(this);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);
    
        dealer.createCards();
        add(new Automation(this));
        initComponents();
    }
    
    private JButton buttonCreator(int x, int y, String src, boolean isButton)
    {
        int height;
        int width;
        
        if(isButton)
        {
            height = 78;
            width  = 78;
        }
        else
        {
            height = 87;
            width  = 87;
        }
        
        JButton button=new JButton(new ImageIcon(new ImageIcon("src/resources/" + src).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        button.setBounds(x, y, width, height);
        button.setVisible(true);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        add(button);
        return button;
    }
    
    /**
     * Creates all static components of the first screen (where the player places his bet)
     */
    private void initComponents()
    {
        // Removes all (static) components
        removeAll();
        
        // Creates the little icon next to the players chips label
        JLabel littleChip = new JLabel(new ImageIcon("src/resources/littleChip.png"));
        littleChip.setBounds(15,533,25,25);
        add(littleChip);
        
        // Creates the reset button
        JButton buttonReset = buttonCreator(30, 380, "button_Reset.png", true);
        buttonReset.setBorderPainted(false);
        buttonReset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                player.reset();
                redrawUI();
            }
        }
        );
        
        // Creates a text label for reset button
        JLabel labelReset = new JLabel("Reset");
        labelReset.setBounds(50,450, 100, 30);
        labelReset.setForeground(new Color(255,255,255));
        add(labelReset);
        
        // Creates the all-in button
        JButton buttonAllIn = buttonCreator(145,440, "button_all_in.png", true);
        buttonAllIn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                player.allIn();
                redrawUI();
            }
        }
        );
        
        // Creates a text label for all-in button
        JLabel labelAllIn = new JLabel("All In");
        labelAllIn.setBounds(167,515, 100, 30);
        labelAllIn.setForeground(new Color(255,255,255));
        add(labelAllIn);
        
        // Creates the deal button
        JButton buttonDeal = buttonCreator(265,375,"button_deal.png", true);
        buttonDeal.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                if (player.deal() == 0)
                {
                    dealer.deal();
                    initComponents2();
                    state = 2;
                }
                redrawUI();
            }
        }
        );
        
        // Creates a text label for deal button
        JLabel labelDeal= new JLabel("Deal");
        labelDeal.setBounds(290,450, 100, 30);
        labelDeal.setForeground(new Color(255,255,255));
        add(labelDeal);
        
        int chipValues[] = {5000, 1000, 500, 100, 25, 5, 1};
        int counter = 0;
        
        // Compares the amount of chips of the the user with the predefined chip values, to draw only the ones the player can use
        for (int i = 0; i < chipValues.length; i++)
        {
            if (player.getChips() > chipValues[i])
            {
                counter = i;
                break;
            }
        }
        int firstChip = counter; // The variable that defines the first chip to use has to be (effectively) final to be referenced later from an inner class
        
        int chipX=16;
        int chipY=565;
        int xPadding=85;
        
        // Creates the 4 chip buttons at the bottom of the screen
        for (int i = 0; i < 4; i++)
        {
            int offset = i; // The variable to add the offset to the first chip has to be (effectively) final to be referenced later from an inner class
            JButton buttonChip = buttonCreator(chipX + (offset*xPadding), chipY, "chip" + Integer.toString(chipValues[firstChip+offset]) + ".png", false);
            buttonChip.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    player.bet(chipValues[firstChip+offset]);
                    redrawUI();
                }
            }
            );
        }
        
        // Creates a label for the chipbar
        JLabel labelChipBar = new JLabel(new ImageIcon("src/resources/chipBar.png"));
        labelChipBar.setBounds(0,561,375,105);
        add(labelChipBar);
    }
    
    /**
     * Creates components needed for the second screen (where the player actually plays)
     */
    public void initComponents2()
    {
        // Removes all (static) components
        removeAll();
        
        // Creates a text label for the chip icon
        JLabel labelLittleChip = new JLabel();
        labelLittleChip.setBounds(15,628,25,25);
        labelLittleChip.setIcon(new ImageIcon("src/resources/littleChip.png"));
        add(labelLittleChip);
        
        // Creates the hit button
        buttonHit = buttonCreator(15, 465, "button_hit.png", true);
        buttonHit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                player.hit();
                gamelogic();
                redrawUI();
            }
        }
        );
        
        // Creates a text label for hit button
        labelHit = new JLabel("Hit");
        labelHit.setBounds(44,540, 100, 30);
        labelHit.setForeground(new Color(255,255,255));
        add(labelHit);
        
        // Creates the stand button
        buttonStand = buttonCreator(140,525,"button_stand.png", true);
        buttonStand.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                player.stand();
                gamelogic();
                redrawUI();
            }
        }
        );
        
        // Creates a text label for stand button
        labelStand = new JLabel("Stand");
        labelStand.setBounds(160,600, 100, 30);
        labelStand.setForeground(new Color(255,255,255));
        add(labelStand);
        
        // Creates the double button
        buttonDouble = buttonCreator(275,465,"button_double.png", true);
        buttonDouble.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                player.doubble();
                gamelogic();
                redrawUI();
            }
        }
        );
        
        // Creates a text label for double button
        labelDouble = new JLabel("Double");
        labelDouble.setBounds(292,540, 100, 30);
        labelDouble.setForeground(new Color(255,255,255));
        add(labelDouble);
    }
    
    /**
     * Handles order of events
     */
    public void gamelogic()
    {
        if (player.getFinished())
        {
            remove(labelHit);
            remove(labelStand);
            remove(labelDouble);
            remove(buttonHit);
            remove(buttonStand);
            remove(buttonDouble);
            
            if (!dealer.getFinished())
            {
                dealer.turn();
            }
            else
            {
                finished = true;
                roundFinished();
            }
        }
        if (finished)
        {
            if (!win)
            {
                finished = false;
                initComponents();
                redrawUI();
            }
            else
            {
                win = false;
                redrawUI();
            }
        }
    }
    
    /**
     * Calculates who has won and flushes hands
     */
    public void roundFinished()
    {
        if ((player.getHandValue() > dealer.getHandValue() && !player.getBusted()) || (!player.getBusted() && dealer.getBusted()))
        {
            // Player has won
            player.setChips(player.getBet()*2);
            win = true;
            drawWin();
        }
        if ((player.getHandValue() < dealer.getHandValue() && !dealer.getBusted()) || (player.getBusted() && !dealer.getBusted()))
        {
            // Player has lost
        }
        if (player.getHandValue() == dealer.getHandValue() || (player.getBusted() && dealer.getBusted()))
        {
            // Player has pushed
            player.setChips(player.getBet());
            
            /*
            JLabel label = new JLabel("PUSH");
            label.setFont(new Font("Calibri",Font.BOLD , 24));
            label.setForeground(Color.white);
            add(label);
            label.setBounds(300, 2, 150, 50);
            */
        }
        
        state = 1;
        
        player.writeChips();
        
        player.flushHand();
        dealer.flushHand();
    }
    
    /**
     * Removes old components and draws new ones
     */
    public void redrawUI()
    {
        // Removes dynamic components
        if (labelChips != null)         remove(labelChips);
        if (labelBetCoin != null)       remove(labelBetCoin);
        if (labelBetText != null)       remove(labelBetText);
        if (labelHandValue != null)     remove(labelHandValue);
        if (labelDealersValue != null)  remove(labelDealersValue);
        if (labelBox1 != null)          remove(labelBox1);
        if (labelBox2 != null)          remove(labelBox2);
        if (labelAction != null)        remove(labelAction);
        for (int i = 0; i < labelCards.length; i++)
        {
            if (labelCards[i] != null)
            {
                remove(labelCards[i]);
            }
        }
        
        // Draws dynamic components depending on current state
        drawChips();
        if (player.getBet() != 0)
        {
            drawBet();
        }
        if (state == 2)
        {
            drawCards();
            drawPlayersHandValue();
            drawDealersHandValue();
        }
        repaint();
    }
    
    /**
     * Draws the hand cards of player and dealer
     */
    public void drawCards()
    {
        for (int i = 0; i < 2; i++)
        {
            Card[] hand;
            if (i == 0)
            {
                hand = player.getHand();
            }
            else
            {
                hand = dealer.getHand();
            }
            
            int c1 = 0;
            for (int j = hand.length-1; j >= 0; j--)
            {
                if (hand[j] != null)
                {
                    c1++;
                }
            }
            
            int c2 = 0;
            for (int j = hand.length-1; j >= 0; j--)
            {
                if (hand[j] != null)
                {
                    labelCards[j + i*10] = new JLabel(hand[j].getIcon());
                    add(labelCards[j + i*10]);
                    int x = 190 - (60 + c2 * 30) + (((c1 - 1) / 2) * 30);
                    int y;
                    c2++;
                    if (i == 0){y = 340;}
                    else       {y = 100;}
                    labelCards[j + i*10].setBounds(x, y, 120, 180);
                }
            }
        } 
    }
    
    /**
     * Draw the amount of chips the player owns
     */
    public void drawChips()
    {
        labelChips = new JLabel("$" + Integer.toString(player.getChips()));
        labelChips.setFont(new Font("Calibri",Font.BOLD , 18));
        labelChips.setForeground(Color.white);
        add(labelChips);
        if (state == 1)
        {
            labelChips.setBounds(40, 520, 250, 50);
        }
        if (state == 2)
        {
            labelChips.setBounds(40, 615, 250, 50);
        }
    }
    
    /**
     * Draws the value of the win and a new "background" image
     */
    public void drawWin()
    {
        JLabel label = new JLabel("$" + Integer.toString(player.getBet()*2));
        label.setFont(new Font("Calibri",Font.BOLD , 42));
        label.setBounds(130, 120, 250, 50);
        add(label);
        
        drawChips();
        
        JLabel image = new JLabel(new ImageIcon("src/resources/background_win.png"));
        image.setBounds(0, 0, background.getWidth(this), background.getHeight(this));
        add(image);
    }
    
    /**
     * Draws icon and text with the bets value
     */
    public void drawBet()
    {
        // Creates text label
        labelBetText = new JLabel("$" + Integer.toString(player.getBet()));
        labelBetText.setFont(new Font("Calibri",Font.BOLD , 18));
        labelBetText.setForeground(Color.white);
        add(labelBetText);
        
        //Selects the chip icon that equals the value of the bet
        ImageIcon iicon;
        if      (player.getBet() - 5000 >= 0){iicon = new ImageIcon("src/resources/chip5000.png");}
        else if (player.getBet() - 1000 >= 0){iicon = new ImageIcon("src/resources/chip1000.png");}           
        else if (player.getBet() -  500 >= 0){iicon = new ImageIcon("src/resources/chip500.png");}
        else if (player.getBet() -  100 >= 0){iicon = new ImageIcon("src/resources/chip100.png");}
        else if (player.getBet() -   25 >= 0){iicon = new ImageIcon("src/resources/chip25.png");}           
        else if (player.getBet() -    5 >= 0){iicon = new ImageIcon("src/resources/chip5.png");}
        else if (player.getBet() -    1 >= 0){iicon = new ImageIcon("src/resources/chip1.png");}
        else {iicon = new ImageIcon("");}
        labelBetCoin=new JLabel(iicon);
        add(labelBetCoin);
        
        //Sets postion depending on state
        if (state == 1)
        {
            labelBetText.setBounds(160, 370, 250, 50);
            labelBetCoin.setBounds(148, 300, iicon.getIconWidth(), iicon.getIconHeight());
        }
        if (state == 2)
        {
            labelBetText.setBounds(20, 230, 250, 50);
            labelBetCoin.setBounds(8, 160, 78, 78);
        }
    }

    /**
     * Draws white box with the handvalue of the player
     */
    public void drawPlayersHandValue()
    {
        labelHandValue = new JLabel(Integer.toString(player.getHandValue()));
        labelHandValue.setFont(new Font("Calibri",Font.BOLD , 16));
        labelHandValue.setForeground(Color.black);
        add(labelHandValue);
        labelHandValue.setBounds(165, 290, 50, 50);
        
        labelBox1 = new JLabel(new ImageIcon("src/resources/box.png"));
        add(labelBox1);
        labelBox1.setBounds(150, 290, 50, 50);
    }
    
    /**
     * Draws white box with the handvalue of the dealer
     */
    public void drawDealersHandValue()
    {
        labelDealersValue = new JLabel(Integer.toString(dealer.getHandValue()));
        labelDealersValue.setFont(new Font("Calibri",Font.BOLD , 18));
        labelDealersValue.setForeground(Color.black);
        add(labelDealersValue);
        labelDealersValue.setBounds(165, 30, 50, 50);
        
        labelBox2 = new JLabel(new ImageIcon("src/resources/box.png"));
        add(labelBox2);
        labelBox2.setBounds(150, 30, 50, 50);
    }
    
    /**
     * Overwritten to draw the background pictures
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
    }
}
