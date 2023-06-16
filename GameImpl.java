package epCOO;

public class GameImpl implements Game {
    /**
     * Método que devolve a cor da posição do tabuleiro. Se possui uma cor, significa que é um templo. Caso contrário, é um espaço normal
     * @param position Posição do tabuleiro
     * @return O enum Color que representa a cor da posição
     */


    public final int LENGTH = 5;

    Position[][] posicoes = new Position[LENGTH][LENGTH]; //valor -2
    Spot[][] spots = new Spot[LENGTH][LENGTH]; //valor -2
    Piece[] pecas = new Piece[10];

    String nomeAzul = new String();
    String nomeVermelho = new String();
    Cards [] novoDeck = new Card(); // indices 0 e 1 correspondem às cartas do azul, 2 e 3 às do vermelho e índice 4 é a carta da mesa
    Card cartaDaMesa = novoDeck[4];
    Color turno = novoDeck[4].getColor();

    Player bluePlayer = new Player("Jogador Azul", Color.BLUE, novoDeck[0], novoDeck[1]);
    Player redPlayer = new Player ("Jogador Vermelho", Color.RED, novoDeck[2], novoDeck[3]);

    //construtores

    public GameImpl(){}

    public GameImpl(String nomeAzul, String nomeVermelho){
        this.nomeAzul = nomeAzul;
        this.nomeVermelho = nomeVermelho;

    }
    public GameImpl(String nomeAzul, String nomeVermelho, Cards[] novoDeck){
        this.nomeAzul = nomeAzul;
        this.nomeVermelho = nomeVermelho;
        for (int i =0; i <novoDeck.length; i++)
            this.novoDeck[i] = novoDeck[i];
    }


    public void init(){
    
    //todas as casas

     for (int i=0; i < LENGTH; i++){
        for (int j=0; j < LENGTH; j++){
            posicoes [i][j] = new Position(i-2, j-2);
        }
    }
    
    //peças azuis

        for (int i=0; i<5; i++){
            if (i == 2) pecas[i] = new Piece(Color.BLUE, true);
            else pecas[i] = new Piece(Color.BLUE, false);
    }

    //peças vermelhas

    for (int i=5; i<10; i++){
        if (i == 7) pecas[i] = new Piece(Color.RED, true);
        else pecas[i] = new Piece(Color.RED, false);
    }

    //todos os spots 
    int index =0;
    for (int i=0; i < LENGTH; i++){
        for (int j=0; j < LENGTH; j++){
            if(i ==0) {
                spots[i][j] = new Spot(pecas[index], posicoes[i][j]);//spots com peças azuis
                index++;
            }
            else if(i == 4){
                spots[i][j] = new Spot(pecas[index], posicoes[i][j]);//spots com peças vermelhas
                index++;
            }
            else
                spots [i][j] = new Spot(posicoes[i][j]);
        }
    }
    spots [0][2] = new Spot(pecas[2], posicoes[0][2], Color.BLUE); // templo azul
    spots [4][2] = new Spot(pecas[7], posicoes[4][2], Color.RED);  // templo vermelho

    


}


    public Color getSpotColor(Position position){
        int a = position.getRow();
        int b = position.getCol();
        Color color = spots[a+2][b+2].getColor();
        return color;
    }

    /**
     * Método que devolve a peça que está na posição do tabuleiro
     * @param position Posição do tabuleiro
     * @return Um objeto Piece que representa a peça na posição indicada. Se não tiver peça, devolve null
     */
    public Piece getPiece(Position position){
        int a = position.getRow();
        int b = position.getCol();
        Piece piece = spots[a+2][b+2].getPiece();
        return piece;
    }

    /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     * @return Um objeto Card que representa a carta na mesa
     */
    public Card getTableCard(){
        return this.cartaDaMesa;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     * @return Um objeto Player que representa o jogador vermelho
     */
    public Player getRedPlayer(){
        return this.redPlayer;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     * @return Um objeto Player que representa o jogador azul
     */
    public Player getBluePlayer(){
        return this.bluePlayer;
    }
    public void moveAux (Position cardMove, Position currentPos){
        Position movimento = new Position (currentPos.getRow() + cardMove.getRow(), currentPos.getCol()+currentPos.getCol());
        spots[cardMove.getRow()+2][cardMove.getCol()+2] = new Spot(piecetoMove, movimento);
    }
    /**
     * Método que move uma peça
     * @param card A carta de movimento que será usada
     * @param position A posição da carta para onde a peça irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    public void makeMove(Card card, Position cardMove, Position currentPos) throws 
    IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException{

        // Position [] cardPositions = card.getPositions(); //exception
        
        
        if (currentPos.getRow() + cardMove.getRow() > 2 || currentPos.getRow() + cardMove.getRow() < -2)
            throw new IllegalMovementException ("A posição excede a linha");
        else if (currentPos.getCol() + cardMove.getCol() > 2 || currentPos.getCol() + cardMove.getCol() < -2)
            throw new IllegalMovementException ("A posição excede a coluna");
        else{
            Piece pieceToMove = spots[currentPos.getRow()+2][currentPos.getCol()+2].getPiece();
            Spot spotToGo = spots[cardMove.getRow()+2][cardMove.getCol()+2];
            if (spotToGo.getPosition().getRow() > 2 || spotToGo.getPosition().getRow() < -2)
                throw new InvalidPieceException("Essa peça não está no tabuleiro");
            if (spotToGo.getPosition().getCol() > 2 || spotToGo.getPosition().getCol() < -2)
                throw new InvalidPieceException("Essa peça não está no tabuleiro");
            else {
                if (spotToGo.getPiece().getColor().equals(pieceToMove.getColor()))
                    throw new IllegalMovementException("Já tem uma peça dessa cor nessa posição");
                else { 
                
                    if (pieceToMove.getColor().equals(Color.BLUE) && turno.equals(Color.BLUE)){
                        if (bluePlayer.getCards()[0] != card && bluePlayer.getCards()[1] != card)
                            throw new InvalidCardException("Você não tem a carta na sua mão");
                        else {
                            spotToGo.getPiece().kill();
                            moveAux(cardMove, currentPos);

                            bluePlayer.swapCard(card, cartaDaMesa);
                            turno = Color.RED;
                    }
            }
                    if (pieceToMove.getColor().equals(Color.RED) && turno.equals(Color.RED)){
                        if (redPlayer.getCards()[0] != card && redPlayer.getCards()[1] != card)
                            throw new InvalidCardException("Você não tem a carta na sua mão");
                        else {
                            spotToGo.getPiece().kill();
                            moveAux(cardMove, currentPos);
                            
                            redPlayer.swapCard(card, cartaDaMesa);
                            turno = Color.BLUE;
                        }
            }
            else if (pieceToMove.getColor().equals(Color.BLUE) && turno.equals(Color.RED))
                throw new IncorrectTurnOrderException ("Não é o turno do azul");
            else if (pieceToMove.getColor().equals(Color.RED) && turno.equals(Color.BLUE))
                throw new IncorrectTurnOrderException ("Não é o turno do vermelho");
                }
            }
        }

        //TODO
    }

    /**
     * Método que confere se um jogador de uma determinada cor venceu o jogo. Critérios de vitória:
     * — Derrotou a peça de mestre adversária
     * — Posicionou o seu mestre na posição da base adversária
     * @param color Cor das peças do jogador que confere a condição de vitória
     * @return Um booleano true para caso esteja em condições de vencer e false caso contrário
     */

     // LEMBRETE : POSIÇOES DE CARTAS DEVEM SER SOMADAS AS POSICOES ATUAIS 

    public boolean checkVictory(Color color){
        return false;
    }

    /**
     * Método que imprime o tabuleiro no seu estado atual
     * OBS: Esse método é opcional não será utilizado na correção, mas serve para acompanhar os resultados parciais do jogo
     */
    public void printBoard(){}
}
