

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Handles the whole Game:
 *  Draws the UI
 *  Calculates the game
 *  Manages mouse clicks
 *
 * @author Lucas
 * @version 01.09.2018
 */

public class Game extends JPanel
{    
    private Dealer dealer = new Dealer();
    private Player player = new Player(dealer);
    
    //                              All In      Reset       Deal        5000        500         100         10
    private int[][] elements1 = {{185, 250}, {65, 385}, {305, 385}, {55, 610}, {140, 610}, {225, 610}, {310, 610}};
    
    //                              Hit         Stand           Double
    private int[][] elements2 = {{100, 560}, {185, 560}, {270, 560}};
    
    private int state = 1; // 1 = Bet; 2 = Players Turn; 3 = Win; 4 = Dealers Turn
    
    private Image background;
    
    private JButton buttonReset;
    private JButton buttonAllIn;
    private JButton buttonDeal;
    private JButton button25;
    private JButton button100;
    private JButton button500;
    private JButton button1000;
    private Image iReset;
    private Image iAllIn;
    private Image iDeal;
    private Image chip25;
    private Image chip100;
    private Image chip500;
    private Image chip1000;
    private Image chip5000;
    private Image chipBar;
    private JLabel lChipBar;
    private JLabel bet;
    
    JPanel cardGame;
    JPanel cardMenu;
    JPanel cards;
    public Game()
    {   
        dealer.createCards();
        game();
        
        
        initComponents();
    }
    
    /**
     * Implements a mouse handling method
     */
    private void game()
    {
        
        ImageIcon iiBackground = new ImageIcon("src/resources/background4.png");
        background = iiBackground.getImage();
        
        int w = background.getWidth(this);
        int h = background.getHeight(this);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);
        
        drawChips();
        if (player.getBet() != 0)
        {
            drawBet();
        }
        
        add(new Automation(this));
    }
    private void initComponents(){
        buttonReset=new JButton();
        buttonReset.setBounds(30,380,95,95);
        ImageIcon iiReset = new ImageIcon("src/resources/button_Reset.png");
        iReset= iiReset.getImage();
        buttonReset.setIcon(iiReset);
        buttonReset.setVisible(true);
        buttonReset.setOpaque(false);
        buttonReset.setContentAreaFilled(false);
        buttonReset.setBorderPainted(false);
        add(buttonReset);
        buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.reset();
			    gameloop(-1);
			}
		});
        
        buttonAllIn=new JButton();
        buttonAllIn.setBounds(140,420,95,95);
        ImageIcon iiAllIn = new ImageIcon("src/resources/button_all_in.png");
        iAllIn= iiAllIn.getImage();
        buttonAllIn.setIcon(iiAllIn);
        buttonAllIn.setVisible(true);
        buttonAllIn.setOpaque(false);
        buttonAllIn.setContentAreaFilled(false);
        buttonAllIn.setBorderPainted(false);
        add(buttonAllIn);
        buttonAllIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.allIn();
			    gameloop(-1);
			}
		});
        
        buttonDeal=new JButton();
        buttonDeal.setBounds(250,380,95,95);
        ImageIcon iiDeal = new ImageIcon("src/resources/button_deal.png");
        iDeal= iiDeal.getImage();
        buttonDeal.setIcon(iiDeal);
        buttonDeal.setVisible(true);
        buttonDeal.setOpaque(false);
        buttonDeal.setContentAreaFilled(false);
        buttonDeal.setBorderPainted(false);
        add(buttonDeal);
        buttonDeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    
			    if (player.deal() == 0)
                            {
                                removeAll();
                                dealer.deal();
                                drawCards();
                                state = 2;
                            }
			    gameloop(-1);
			}
		});
        
        button25= new JButton();
        button25.setBounds(10,558,95,95);
        ImageIcon iiChip25 = new ImageIcon("src/resources/chip25.png");
        chip25= iiChip25.getImage();
        button25.setIcon(iiChip25);
        button25.setOpaque(false);
        button25.setContentAreaFilled(false);
        button25.setBorderPainted(false);
        add(button25);
        button25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.bet(25);
			    gameloop(-1);
			}
		});
		
	button100= new JButton();
        button100.setBounds(97,560,95,95);
        ImageIcon iiChip100 = new ImageIcon("src/resources/chip100.png");
        chip100= iiChip100.getImage();
        button100.setIcon(iiChip100);
        button100.setOpaque(false);
        button100.setContentAreaFilled(false);
        button100.setBorderPainted(false);
        add(button100);
        button100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.bet(100);
			    gameloop(-1);
			}
		});
		
	button500= new JButton();
        button500.setBounds(181,559,95,95);
        ImageIcon iiChip500 = new ImageIcon("src/resources/chip500.png");
        chip500= iiChip500.getImage();
        button500.setIcon(iiChip500);
        button500.setOpaque(false);
        button500.setContentAreaFilled(false);
        button500.setBorderPainted(false);
        add(button500);
        button500.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.bet(500);
			    gameloop(-1);
			}
		});
		
	button1000= new JButton();
        button1000.setBounds(266,559,95,95);
        ImageIcon iiChip1000 = new ImageIcon("src/resources/chip1000.png");
        chip1000= iiChip1000.getImage();
        button1000.setIcon(iiChip1000);
        button1000.setOpaque(false);
        button1000.setContentAreaFilled(false);
        button1000.setBorderPainted(false);
        add(button1000);
        button1000.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			    player.bet(1000);
			    gameloop(-1);
			}
		});
        
        lChipBar= new JLabel();
        lChipBar.setBounds(5,561,375,105);
        ImageIcon iiChipBar = new ImageIcon("src/resources/chipBar.png");
        chipBar= iiChipBar.getImage();
        lChipBar.setIcon(iiChipBar);
        add(lChipBar);
        
        
    }
    public void gameloop(int element)
    {
        
             
        if (state == 3)
        {
            state = 1;
        }
        if (!player.getFinished())
        {
            if (state == 1)
            {
                
                if (element == 2)
                {
                    if (player.deal() == 0)
                    {
                        dealer.deal();
                        drawCards();
                        state = 2;
                    }
                }
                
            }
            else if (state == 2)
            {
                JLabel label;
                if (element == 0)
                {
                    player.hit();
                    label = new JLabel("HIT");
                }
                if (element == 1)
                {
                    player.stand();
                    label = new JLabel("STAND");
                    state = 4;
                }
                if (element == 2)
                {
                    if (player.doubble() == 0)
                    {
                        label = new JLabel("DOUBLE");
                        state = 4;
                    }
                }
                if (player.getBusted())
                {
                    label = new JLabel("BUST");
                    state = 4;
                }
                else
                {
                    label = new JLabel();
                }
                label.setFont(new Font("Calibri",Font.BOLD , 24));
                label.setForeground(Color.white);
                add(label);
                label.setBounds(300, 2, 150, 50);
                
                if (player.getFinished())
                {
                    state = 4;
                }
            }
        }
        else if (!dealer.getFinished())
        {
            dealer.turn();
        }
        else if (player.getFinished() && dealer.getFinished())
        {
            roundEnd();
        }
        
        // Draw UI
        drawChips();
        if (player.getBet() != 0)
        {
            drawBet();
        }
        if (state == 2 || state == 4)
        {
            drawCards();
            drawPlayersHandValue();
            drawDealersHandValue();
        }
        repaint();
    }
    
    public void roundEnd()
    {
        if ((player.getHandValue() > dealer.getHandValue() && !player.getBusted()) || (!player.getBusted() && dealer.getBusted()))
        {
            player.setChips(player.getBet()*2);
            state = 3;
            drawWin();
        }
        if ((player.getHandValue() < dealer.getHandValue() && !dealer.getBusted()) || (player.getBusted() && !dealer.getBusted()))
        {
            state = 1;
        }
        if (player.getHandValue() == dealer.getHandValue() || (player.getBusted() && dealer.getBusted()))
        {
            player.setChips(player.getBet());
            state = 1;
            
            JLabel label = new JLabel("PUSH");
            label.setFont(new Font("Calibri",Font.BOLD , 24));
            label.setForeground(Color.white);
            add(label);
            label.setBounds(300, 2, 150, 50);
        }
        
        player.writeChips();
        
        player.flushHand();
        dealer.flushHand();
    }
    
    /**
     * Translates the coordinates of the mouse press to a GUI Element
     */
    public int clickedElement(int x, int y)
    {
        if (state == 1)
        {
            for (int i = 0; i < elements1.length; i++)
            {
                int x_diff = x - elements1[i][0];
                int y_diff = y - elements1[i][1];
                
                double d = Math.sqrt(x_diff*x_diff + y_diff*y_diff);
                int r = 40;
                
                if (d < r) {
                    return i;
                }
            }
        }
        if (state == 2)
        {
            for (int i = 0; i < elements2.length; i++)
            {
                int x_diff = x - elements2[i][0];
                int y_diff = y - elements2[i][1];
                
                double d = Math.sqrt(x_diff*x_diff + y_diff*y_diff);
                int r = 40;
                if (d < r) {
                    return i;
                }
            }
        }
        return -1;
    }
    
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
                    JLabel icon = new JLabel(hand[j].getIcon());
                    add(icon);
                    int x = 190 - (60 + c2 * 30) + (((c1 - 1) / 2) * 30);
                    int y;
                    c2++;
                    if (i == 0){y = 340;}
                    else {y = 100;}
                    icon.setBounds(x, y, 120, 180);
                }
            }
        }
    }
    
    public void drawChips()
    {
        JLabel label = new JLabel("$" + Integer.toString(player.getChips()));
        label.setFont(new Font("Calibri",Font.BOLD , 18));
        label.setForeground(Color.white);
        add(label);
        if (state == 1)
        {
            label.setBounds(45, 520, 250, 50);
        }
        if (state == 2 || state == 3 || state == 4)
        {
            label.setBounds(40, 615, 250, 50);
        }
    }
    
    public void drawWin()
    {
        JLabel label = new JLabel("$" + Integer.toString(player.getBet()*2));
        label.setFont(new Font("Calibri",Font.BOLD , 42));
        label.setForeground(Color.white);
        add(label);
        label.setBounds(130, 120, 250, 50);
    }
    
    public void drawBet()
    {
        JLabel text = new JLabel("$" + Integer.toString(player.getBet()));
        text.setFont(new Font("Calibri",Font.BOLD , 18));
        text.setForeground(Color.white);
        add(text);
        
        JLabel icon;
        if (player.getBet() - 5000 >= 0){icon = new JLabel(new ImageIcon("src/resources/chip5000.png"));}
        else if (player.getBet() - 500  >= 0){icon = new JLabel(new ImageIcon("src/resources/chip500.png"));}           
        else if (player.getBet() - 100  >= 0){icon = new JLabel(new ImageIcon("src/resources/chip100.png"));}
        else if (player.getBet() - 10   >= 0){icon = new JLabel(new ImageIcon("src/resources/chip10.png"));}
        else {icon = new JLabel();}
        add(icon);
        
        if (state == 1)
        {
            text.setBounds(160, 425, 250, 50);
            icon.setBounds(148, 350, 78, 78);
        }
        if (state == 2 || state == 4)
        {
            text.setBounds(20, 230, 250, 50);
            icon.setBounds(8, 152, 78, 78);
        }
    }
    
    public void drawPlayersHandValue()
    {
        JLabel label = new JLabel(Integer.toString(player.getHandValue()));
        label.setFont(new Font("Calibri",Font.BOLD , 16));
        label.setForeground(Color.black);
        add(label);
        label.setBounds(158, 289, 50, 50);
    }
    
    public void drawDealersHandValue()
    {
        JLabel label;
        if (player.getFinished()){label = new JLabel(Integer.toString(dealer.getHandValue()));}
        else {label = new JLabel(Integer.toString(dealer.getFirstCardValue()));}
        label.setFont(new Font("Calibri",Font.BOLD , 18));
        label.setForeground(Color.black);
        add(label);
        label.setBounds(158, 30, 50, 50);
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
