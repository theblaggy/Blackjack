<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
>>>>>>> frehburg-patch-1

/**
 * Write a description of class Card here.
 *
<<<<<<< HEAD
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    private int suite;
=======
 * @author Lucas & Filip
 * @version 18.09.
 */
public class Card 
{
    private int suite; 
>>>>>>> frehburg-patch-1
    private int rank;
    private ImageIcon icon;
    private boolean flag_lower = false;
    
    public Card(int pSuite, int pRank)
    {
        suite = pSuite;
        rank = pRank;
<<<<<<< HEAD
        icon = new ImageIcon("src/resources/Playing_card_" + getSuite() + "_" + getRank() + ".png");
=======
        icon = new ImageIcon(new ImageIcon("src/resources/Playing_card_" + getSuite() + "_" + getRank() + ".png").getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH));
>>>>>>> frehburg-patch-1
    }
    
    public String getSuite()
    {
        switch(suite) {
            case 1:
                return "club";
            case 2:
                return "diamond";
            case 3:
                return "heart";
            case 4:
                return "spade";
        }
        return "";
    }
    
    public String getRank()
    {
        switch(rank) {
            case 1:
                return "A";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            case 6:
                return "6";
            case 7:
                return "7";
            case 8:
                return "8";  
            case 9:
                return "9";
            case 10:
                return "10";
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
        }
        return "";
    }
    
    public int getNumber()
    {
        if (rank > 10)
        {
            return 10;
        }
        if (rank == 1 && !flag_lower)
        {
            return 11;
        }
        return rank;
    }
    
    public ImageIcon getIcon()
    {
        return icon;
    }
    
    public void setLower()
    {
        flag_lower = true;
    }
}
