package epCOO;
/*
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class Player
{ 
    
    private Card[] cards = new Card[2];
    private Color color;
    private String name;
    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param cards Cartas na mão do jogador
     */
    public Player(String name, Color pieceColor, Card[] cards)
    {
        this.name = name;
        this.color = pieceColor;
        this.cards = cards;
    }

    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param card1 A primeira carta na mão do jogador
     * @param card2 A segunda carta na mão do jogador
     */
    public Player(String name, Color pieceColor, Card card1, Card card2)
    {
        this.name = name;
        this.color = pieceColor;
        this.cards[0] = card1;
        this.cards[1] = card2;
    }

    /**
     * Método que devolve o nome do jogador(a)
     * @return String com o nome do jogador(a)
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Método que devolve a cor das peças do jogador
     * @return Enum Color com a cor das peças do jogador
     */
    public Color getPieceColor()
    {
        return this.color;
    }

    /**
     * Método que devolve as cartas da mão do jogador
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public Card[] getCards()
    {
        return this.cards;
    }

    /**
     * Método que troca uma carta da mão por outra carta (idealmente da mesa)
     * @param oldCard A carta que será substituída
     * @param newCard A carta que irá substituir
     * @exception InvalidCardException Caso a carta não esteja na mão do jogador e/ou na mesa
     */
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