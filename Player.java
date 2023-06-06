package epCOO;
/*
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class Player
{ 
    
    private Card[] cards;
    private Color color;
    private String name;
    private Card card1;
    private Card card2;
    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param cards Cartas na mão do jogador
     */
    public Player(String name, Color pieceColor, Card[] cards)
    {
        this.name = name;
        this.PieceColor = PieceColor;
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
        this.pieceColor = PieceColor;
        this.card1 = card1;
        this.card2 = card2;
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
        return this.Color;
    }

    /**
     * Método que devolve as cartas da mão do jogador
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public Card[] getCards()
    {
        return this.Card[];
    }

    /**
     * Método que troca uma carta da mão por outra carta (idealmente da mesa)
     * @param oldCard A carta que será substituída
     * @param newCard A carta que irá substituir
     * @exception InvalidCardException Caso a carta não esteja na mão do jogador e/ou na mesa
     */
    protected void swapCard(Card oldCard, Card newCard) throws InvalidCardException
    {
        try
        {
            if(!oldCard.equals(card1) && !oldCard.equals(card2))
            {
                throw new InvalidCardException("Amigão você não tem essa carta, escolha outra");
            }else
            {
                if(oldCard.equals(card1))
                    this.card1 = newCard;
                else
                    this.card2 = newCard;
            }
        }catch(IllegalMovementException erro){
            System.out.println(erro.getMessage());
        }

    }
}
