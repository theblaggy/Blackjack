import java.util.Scanner;
import java.io.*;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Card[] hand = new Card[10];
    private int handCards = 0;
    
    private boolean finished = false;
    private boolean busted = false;
    
    private int chips;
    private int bet;
    private Dealer dealer;
    
    public Player(Dealer pDealer)
    {
        dealer = pDealer;
        readChips();
    }
    
    public int deal()
    {
        if (bet == 0){return 1;}
        
        hit();
        hit();
        return 0;
    }
    
    public void allIn()
    {
        bet += chips;
        chips = 0;
    }
    
    public void reset()
    {
        chips += bet;
        bet = 0;
    }
    
    public void bet(int pBet)
    {
        if (pBet > chips){return;}
        
        chips -= pBet;
        bet += pBet;
    }
    
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
    
    public void stand()
    {
        finished = true;
    }
    
    public int doubble()
    {
        if (bet > chips) {return 1;}
        
        chips -= bet;
        bet *= 2;
        hit();
        stand();
        return 0;
    }
    
    public void flushHand()
    {
        hand = new Card[10];
        handCards = 0;
        finished = false;
        busted = false;
        bet = 0;
    }
    
    public Card[] getHand()
    {
        return hand;
    }
    
    public int getChips()
    {
        return chips;
    }
    
    public void setChips(int pChips)
    {
        chips += pChips;
    }
    
    public int getBet()
    {
        return bet;
    }
    
    public int getHandValue()
    {
        int value = 0;
        for (int i = 0; i < handCards; i++)
        {
            value += hand[i].getNumber();
        }
        return value;
    }
    
    public boolean getFinished()
    {
        return finished;
    }
    
    public boolean getBusted()
    {
        return busted;
    }
    
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
