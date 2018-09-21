import java.util.concurrent.ThreadLocalRandom;

/**
 * Dealer extends from Player and also creates the playing card deck
 */
public class Dealer extends Player
{
    private Card[] cards = new Card[208];
    private int cardsNumber = 208;
    
    /**
     * Sets chips and bet to -1 because the dealer has endless chips and won´t place a bet
     */
    public Dealer()
    {
        super(null);
        
        chips = -1;
        bet = -1;
        dealer = this;
    }
    
    /**
     * Dealer draws on 16 stands on all 17 
     */
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
    
    /**
     * Overwritten to set bet to -1, so the dealer doesn´t have to place a bet
     */
    @Override
    public void flushHand()
    {
        super.flushHand();
        bet = -1;
    }
    
    /**
     * Returns a card from the stack
     */
    public Card getCard()
    {
        cardsNumber--;
        return cards[cardsNumber];
    }
    
    /**
     * Creates 4 decks á 52 cards
     */
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
}
