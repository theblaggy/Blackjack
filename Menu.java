import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Form for the main menu of the game
 * 
 * @author Filip
 */
public class Menu extends javax.swing.JPanel
{
    private Image background;
    private Application parent;
    
    /**
     * Sets parent and window size and calls the components initializer
     */
    public Menu(Application application) {
        this.parent = application;
        
        ImageIcon iiBackground = new ImageIcon("src/resources/background.png");
        background = iiBackground.getImage();
        int w = background.getWidth(this);
        int h = background.getHeight(this);
        setPreferredSize(new Dimension(w, h));
        setLayout(null);
        
        initComponents();
    }

    /**
    * Creates all components
    */
    private void initComponents()
    {
        // Creates start button
        JButton buttonStart = new JButton("Start");
        buttonStart.setBounds(120,400,130,50);
        buttonStart.setFocusPainted(false);
        buttonStart.setForeground(Color.WHITE);
        buttonStart.setBackground(new Color(189, 15, 15));
        buttonStart.setFont(new Font("sansserif",0,12));
        add(buttonStart);
        
        buttonStart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                parent.changeCard();
            }
        }
        );
        
        // Creates info label
        JLabel labelStart = new JLabel("Press Start to Continue");
        labelStart.setBounds(119,430,150,50);
        add(labelStart);
        
        // Creates the logo
        JLabel labelLogo = new JLabel(new ImageIcon("src/resources/logo.png"));
        labelLogo.setBounds(5,25,375, 475);
        add(labelLogo);
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
