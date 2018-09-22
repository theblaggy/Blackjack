import java.util.Scanner;
import java.io.*;

/**
 * Implements the Player
 */
public class Player
{
    protected Card[] hand = new Card[10];
    protected int handCards = 0;
    
    protected boolean finished = false;
    protected boolean busted = false;
    
    protected int chips;
    protected int bet;
    
    protected Dealer dealer;
    
    /**
     * Sets dealer to get cards and read chips from file
     */
    public Player(Dealer dealer)
    {
        this.dealer = dealer;
        readChips();
    }
    
    /**
     * Hits two cards if bet isn´t zero
     * Returns 1 if bet is zero else 0
     */
    public int deal()
    {
        if (bet == 0){return 1;}
        
        hit();
        hit();
        return 0;
    }
    
    /**
     * Places all chips of the player
     */
    public void allIn()
    {
        bet += chips;
        chips = 0;
    }
    
    /**
     * Sets bet to zero
     */
    public void reset()
    {
        chips += bet;
        bet = 0;
    }
    
    /**
     * Bet the given amount of chips
     */
    public void bet(int pBet)
    {
        if (pBet > chips){return;}
        
        chips -= pBet;
        bet += pBet;
    }
    
    /**
     * Draws a card and sets Ace to 1 if needed
     * Checks if player has busted
     */
    public void hit()
    {
        hand[handCards] = dealer.getCard();
        handCards++;
        
        if (getHandValue() > 21)
        {
            for (int i = 0; i < handCards; i++)
            {
                if (hand[i].getNumber() == 11)
                {
                    hand[i].setLower();
                    if (getHandValue() < 21)
                    {
                        break;
                    }
                }
            }
        }
        if (getHandValue() >= 21)
        {
            if (getHandValue() > 21)
            {
                busted = true;
            }
            stand();
        }
    }
    
    /**
     * Sets finished to true
     */
    public void stand()
    {
        finished = true;
    }
    
    /**
     * Double the bet if possible and get one last card
     */
    public int doubble()
    {
        if (bet > chips) {return 1;}
        
        chips -= bet;
        bet *= 2;
        hit();
        stand();
        return 0;
    }
    
    /**
     * Resets all hand associated attributes
     */
    public void flushHand()
    {
        hand = new Card[10];
        handCards = 0;
        finished = false;
        busted = false;
        bet = 0;
    }
    
    /**
     * Returns player hand
     */
    public Card[] getHand()
    {
        return hand;
    }
    
    /**
     * Returns chips
     */
    public int getChips()
    {
        return chips;
    }
    
    /**
     * Adds chips to player chips
     */
    public void setChips(int chips)
    {
        this.chips += chips;
    }
    
    /**
     * Returns placed bet
     */
    public int getBet()
    {
        return bet;
    }
    
    /**
     * Returns value of all hand cards
     */
    public int getHandValue()
    {
        int value = 0;
        for (int i = 0; i < handCards; i++)
        {
            value += hand[i].getNumber();
        }
        return value;
    }
    
    /**
     * Returns finished
     */
    public boolean getFinished()
    {
        return finished;
    }
    
    /**
     * Returns busted
     */
    public boolean getBusted()
    {
        return busted;
    }
    
    /**
     * Writes Chips to file
     */
    public void writeChips()
    {
        // The name of the file to open.
        String fileName = "chips.txt";

        try
        {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            bufferedWriter.write(Integer.toString(chips));

            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex)
        {
            System.out.println("Error writing to file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
    
    /**
     * Reads chips from file
     */
    public void readChips()
    {
        // The name of the file to open.
        String fileName = "chips.txt";

        // This will reference one line at a time
        String line = null;

        try
        {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                chips = Integer.parseInt(line);
                if (chips == 0) {chips = 1000;}
            }

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file '" + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}
