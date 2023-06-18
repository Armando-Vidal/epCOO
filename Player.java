package epCOO;

public class Player
{ 
    
    private Card[] cards = new Card[2];
    private Color color;
    private String name;
 
    public Player(String name, Color pieceColor, Card[] cards)
    {
        this.name = name;
        this.color = pieceColor;
        this.cards = cards;
    }

    
    public Player(String name, Color pieceColor, Card card1, Card card2)
    {
        this.name = name;
        this.color = pieceColor;
        this.cards[0] = card1;
        this.cards[1] = card2;
    }

  
    public String getName()
    {
        return this.name;
    }

    public Color getPieceColor()
    {
        return this.color;
    }

   
    public Card[] getCards()
    {
        return this.cards;
    }

   
    protected void swapCard(Card oldCard, Card newCard) throws InvalidCardException
    {
        
        if(!oldCard.equals(this.cards[0]) && !oldCard.equals(this.cards[1]))
            throw new InvalidCardException("Amigão você não tem essa carta, escolha outra");
        else
            {
                if(oldCard.equals(this.cards[0])){ 
                    this.cards[0] = newCard; 
                    newCard = oldCard; 
                }

                else{
                    this.cards[1] = newCard; 
                    newCard = oldCard; 
                }
            }

}
}