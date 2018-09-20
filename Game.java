
<<<<<<< HEAD

=======
>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
 * @author Lucas
 * @version 01.09.2018
=======
 * @author Lucas & Filip
 * @version 18.09.2018
>>>>>>> frehburg-patch-1
 */

public class Game extends JPanel
{    
    private Dealer dealer = new Dealer();
    private Player player = new Player(dealer);
<<<<<<< HEAD
    
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
=======

    int chipHeight=87;
    int chipWidth=87;
    int buttonHeight=78;
    int buttonWidth=78;
    private JLabel labelChips;
    private JLabel labelBetCoin;
    private JLabel labelBetText;

    private int state = 1; // 1 = Bet; 2 = Players Turn; 3 = Win; 4 = Dealers Turn

    private Image background;

>>>>>>> frehburg-patch-1
    public Game()
    {   
        dealer.createCards();
        game();
<<<<<<< HEAD
        
        
        initComponents();
    }
    
=======

        initComponents();
    }

>>>>>>> frehburg-patch-1
    /**
     * Implements a mouse handling method
     */
    private void game()
    {
<<<<<<< HEAD
        
        ImageIcon iiBackground = new ImageIcon("src/resources/background4.png");
        background = iiBackground.getImage();
        
=======

        ImageIcon iiBackground = new ImageIcon("src/resources/background.png");
        background = iiBackground.getImage();

>>>>>>> frehburg-patch-1
        int w = background.getWidth(this);
        int h = background.getHeight(this);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);
<<<<<<< HEAD
        
=======

>>>>>>> frehburg-patch-1
        drawChips();
        if (player.getBet() != 0)
        {
            drawBet();
        }
<<<<<<< HEAD
        
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
        
             
=======

        add(new Automation(this));
    }

    private JButton buttonCreator(int x, int y, String src, boolean isButton){
        int h;
        int w;
        if(isButton){
            h=buttonHeight;
            w=buttonWidth;
        } else{
            h=chipHeight;
            w=chipWidth; 
        }
        JButton button=new JButton();
        button.setBounds(x, y, w, h);
        ImageIcon iicon = new ImageIcon(new ImageIcon("src/resources/" + src).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        button.setIcon(iicon);
        button.setVisible(true);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        add(button);
        return button;
    }

    private void initComponents(){
        int chipValue[]=new int[4];
        String chipIcon[]=new String[4];
        JButton buttonReset;
        JButton buttonAllIn;
        JButton buttonDeal;
        JButton button1;
        JButton button2;
        JButton button3;
        JButton button4;
        JLabel lChipBar;
        JLabel bet;

        buttonReset=buttonCreator(30, 430, "button_Reset.png", true);
        buttonReset.setBounds(30, 430, 95, 95);
        buttonReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.reset();
                    gameloop(-1);
                }
            });

        buttonAllIn=buttonCreator(140,470, "button_all_in.png", true);
        buttonAllIn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.allIn();
                    gameloop(-1);
                }
            });

        buttonDeal=buttonCreator(250,430,"button_deal.png", true);
        buttonDeal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {

                    if (player.deal() == 0)
                    {
                        removeAll();
                        dealer.deal();
                        drawCards();
                        initComponents2();
                        state = 2;
                    }
                    gameloop(-1);
                }
            });

        int i=0;
        if(player.getChips()>=5000 && i<4){
            chipValue[i]=5000;
            chipIcon[i]= "chip5000.png";
            i++;
        }
        if(player.getChips()>=1000 && i<4){
            chipValue[i]=1000;
            chipIcon[i]= "chip1000.png";
            i++;
        }
        if(player.getChips()>=500 && i<4){
            chipValue[i]=500;
            chipIcon[i]= "chip500.png";
            i++;
        }
        if(player.getChips()>=100 && i<4){
            chipValue[i]=100;
            chipIcon[i]= "chip100.png";
            i++;
        }
        if(player.getChips()>=25 && i<4){
            chipValue[i]=25;
            chipIcon[i]= "chip25.png";
            i++;
        }
        if(player.getChips()>=5 && i<4){
            chipValue[i]=5;
            chipIcon[i]= "chip5.png";
            i++;
        }
        if(player.getChips()>=1 && i<4){
            chipValue[i]=1;
            chipIcon[i]= "chip1.png";
            i++;
        }
        int chipX=17;
        int xPadding=85;
        int chipY=565;
        button1=buttonCreator(chipX,chipY,chipIcon[0], false);
        button1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.bet(chipValue[0]);
                    gameloop(-1);
                }
            });

        button2=buttonCreator(chipX+xPadding-1,chipY,chipIcon[1], false);
        button2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.bet(chipValue[1]);
                    gameloop(-1);
                }
            });

        button3=buttonCreator(chipX+(2*xPadding),chipY,chipIcon[2], false);
        button3.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.bet(chipValue[2]);
                    gameloop(-1);
                }
            });

        button4=buttonCreator(chipX+(3*xPadding),chipY,chipIcon[3], false);
        button4.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.bet(chipValue[3]);
                    gameloop(-1);
                }
            });

        lChipBar= new JLabel();
        lChipBar.setBounds(5,561,375,105);
        ImageIcon iiChipBar = new ImageIcon("src/resources/chipBar.png");

        lChipBar.setIcon(iiChipBar);
        add(lChipBar);

    }

    public void initComponents2(){
        Image white;
        Image iDouble;
        Image iHit;
        Image iStand;
        JButton buttonHit;
        JButton buttonStand;
        JButton buttonDouble;
        JLabel box1;
        JLabel box2; 
        Image box;
        JPanel cardGame;
        JPanel cardMenu;
        JPanel cards;

        buttonHit=buttonCreator(35, 495, "button_hit.png", true);
        buttonHit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.hit();
                }
            });

        buttonStand= buttonCreator(140,565,"button_stand.png", true);
        buttonStand.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.stand();
                }
            });

        buttonDouble=buttonCreator(275,495,"button_double.png", true);
        buttonDouble.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    player.doubble();
                }
            });

        box1= new JLabel();
        box1.setLocation(170,60);
        ImageIcon iibox = new ImageIcon("src/resources/box.png");
        box= iibox.getImage();
        box1.setIcon(iibox);

        box2= new JLabel();
        box2.setLocation(170,315);
        box2.setIcon(iibox);
    }

    public void gameloop(int element)
    {
        try{//updated non-static Elements
            remove(labelChips);
            remove(labelBetCoin);
            remove(labelBetText);
        } catch(Exception e){//if a label hasn´t been added
        }
>>>>>>> frehburg-patch-1
        if (state == 3)
        {
            state = 1;
        }
        if (!player.getFinished())
        {
            if (state == 1)
            {
<<<<<<< HEAD
                
=======

>>>>>>> frehburg-patch-1
                if (element == 2)
                {
                    if (player.deal() == 0)
                    {
                        dealer.deal();
                        drawCards();
                        state = 2;
                    }
                }
<<<<<<< HEAD
                
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
                
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
        
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
    
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
            
=======

>>>>>>> frehburg-patch-1
            JLabel label = new JLabel("PUSH");
            label.setFont(new Font("Calibri",Font.BOLD , 24));
            label.setForeground(Color.white);
            add(label);
            label.setBounds(300, 2, 150, 50);
        }
<<<<<<< HEAD
        
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
    
=======

        player.writeChips();

        player.flushHand();
        dealer.flushHand();
    }

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
            
=======

>>>>>>> frehburg-patch-1
            int c1 = 0;
            for (int j = hand.length-1; j >= 0; j--)
            {
                if (hand[j] != null)
                {
                    c1++;
                }
            }
<<<<<<< HEAD
            
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
    
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
    
=======

    public void drawChips()
    {
        labelChips = new JLabel("$" + Integer.toString(player.getChips()));
        labelChips.setFont(new Font("Calibri",Font.BOLD , 18));
        labelChips.setForeground(Color.white);
        add(labelChips);
        if (state == 1)
        {
            labelChips.setBounds(45, 520, 250, 50);
        }
        if (state == 2 || state == 3 || state == 4)
        {
            labelChips.setBounds(40, 615, 250, 50);
        }
    }

>>>>>>> frehburg-patch-1
    public void drawWin()
    {
        JLabel label = new JLabel("$" + Integer.toString(player.getBet()*2));
        label.setFont(new Font("Calibri",Font.BOLD , 42));
        label.setForeground(Color.white);
        add(label);
        label.setBounds(130, 120, 250, 50);
    }
<<<<<<< HEAD
    
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
    
=======

    public void drawBet()
    {
        labelBetText = new JLabel("$" + Integer.toString(player.getBet()));
        labelBetText.setFont(new Font("Calibri",Font.BOLD , 18));
        labelBetText.setForeground(Color.white);
        add(labelBetText);

        ImageIcon iicon;
        if (player.getBet() - 5000 >= 0){iicon =new ImageIcon("src/resources/chip5000.png");}
        else if (player.getBet() - 500  >= 0){iicon = new ImageIcon("src/resources/chip500.png");}           
        else if (player.getBet() - 100  >= 0){iicon = new ImageIcon("src/resources/chip100.png");}
        else if (player.getBet() - 10   >= 0){iicon = new ImageIcon("src/resources/chip25.png");}
        else {iicon = new ImageIcon("");}
        labelBetCoin=new JLabel(iicon);
        add(labelBetCoin);

        if (state == 1)
        {
            labelBetText.setBounds(160, 425, 250, 50);
            labelBetCoin.setBounds(148, 350, iicon.getIconWidth(), iicon.getIconHeight());
        }
        if (state == 2 || state == 4)
        {
            labelBetText.setBounds(20, 230, 250, 50);
            labelBetCoin.setBounds(8, 152, 78, 78);
        }
    }

>>>>>>> frehburg-patch-1
    public void drawPlayersHandValue()
    {
        JLabel label = new JLabel(Integer.toString(player.getHandValue()));
        label.setFont(new Font("Calibri",Font.BOLD , 16));
        label.setForeground(Color.black);
        add(label);
        label.setBounds(158, 289, 50, 50);
    }
<<<<<<< HEAD
    
=======

>>>>>>> frehburg-patch-1
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
<<<<<<< HEAD
    
=======

>>>>>>> frehburg-patch-1
    /**
     * Overwritten to draw the background pictures
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
<<<<<<< HEAD
        
            g.drawImage(background, 0, 0, null);
        
        
=======

        g.drawImage(background, 0, 0, null);

>>>>>>> frehburg-patch-1
        Toolkit.getDefaultToolkit().sync();
    }
}
