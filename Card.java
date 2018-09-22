import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * Implements a playing card with suit and rank
 * rank and suite are handled iternaly as integers
 */
public class Card 
{
    private int suite; 
    private int rank;
    private ImageIcon icon;
    private boolean flagLower = false;
    
    /**
     * Sets suite, rank and icon
     */
    public Card(int pSuite, int pRank)
    {
        suite = pSuite;
        rank = pRank;
        icon = new ImageIcon(new ImageIcon("src/resources/Playing_card_" + getSuite() + "_" + getRank() + ".png").getImage().getScaledInstance(120, 180, Image.SCALE_SMOOTH));
    }
    
    /**
     * Returns suite
     */
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
    
    /**
     * Returns rank
     */
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
    
    /**
     * Returns rank as integer
     */
    public int getNumber()
    {
        if (rank > 10)
        {
            return 10;
        }
        if (rank == 1 && !flagLower)
        {
            return 11;
        }
        return rank;
    }
    
    /**
     * Returns icon
     */
    public ImageIcon getIcon()
    {
        return icon;
    }
    
    /**
     * Sets Ace from 11 to 1
     */
    public void setLower()
    {
        flagLower = true;
    }
}
