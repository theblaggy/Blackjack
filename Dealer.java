import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a description of class Dealer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dealer
{
    private Card[] hand = new Card[10];
    private int handCards = 0;
    
    private boolean finished = false;
    private boolean busted = false;
    
    private Card[] cards = new Card[208];
    private int cardsNumber = 208;
    
    public Dealer()
    {
        
    }
    
    public void turn()
    {
        int handValue = getHandValue();
        
        if (handValue < 17)
        {
            hit();
            if (handValue < 17)
            {
                stand();
            }
        }
        else
        {
            stand();
        }
    }
    
    public void deal()
    {
        hit();
        hit();
    }
    
    public void hit()
    {
        hand[handCards] = getCard();
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
    
    public Card getCard()
    {
        cardsNumber--;
        return cards[cardsNumber];
    }
    
    public void createCards()
    {
        // Blackjack is played with 4 Decks
        for (int i = 0; i < 4; i++) {
            // Create the 4 Suites
            for (int j = 0; j < 4; j++) {
                // Create The 13 Ranks
                for (int k = 0; k < 13; k++) {
                    cards[i*52 + j*13 + k*1] = new Card(j+1, k+1);
                }
            }
        }
        
        for (int i = 0; i < cards.length; i++)
        {
            int rndm = ThreadLocalRandom.current().nextInt(0, cards.length);
            Card tmp = cards[i];
            cards[i] = cards[rndm];
            cards[rndm] = tmp;
        }
    }
    
    public void flushHand()
    {
        hand = new Card[10];
        handCards = 0;
        finished = false;
        busted = false;
    }
    
    public Card[] getHand()
    {
        return hand;
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

    public int getFirstCardValue()
    {
        return hand[0].getNumber();
    }
    
    public boolean getFinished()
    {
        return finished;
    }
    
    public boolean getBusted()
    {
        return busted;
    }
}
