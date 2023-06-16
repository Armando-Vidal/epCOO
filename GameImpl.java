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
                spots[i][j] = new Spot(pecas[index], posicoes[i][j]);//spots com peças
                index++;
            }
            else if(i == 4){
                spots[i][j] = new Spot(pecas[index], posicoes[i][j]);//spots com peças
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
        Color color = spots[a][b].getColor();
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
        Color piece = spots[a][b].getPiece();
        return piece;
    }

    /**
     * Método que devolve a carta que está na mesa, que será substituída após a próxima jogada
     * @return Um objeto Card que representa a carta na mesa
     */
    public Card getTableCard(){
        return null;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças vermelhas
     * @return Um objeto Player que representa o jogador vermelho
     */
    public Player getRedPlayer(){
        return null;
    }

    /**
     * Método que devolve as informações sobre o jogador com as peças azuis
     * @return Um objeto Player que representa o jogador azul
     */
    public Player getBluePlayer(){
        return null;
    }

    /**
     * Método que move uma peça
     * @param piece A peça que irá mover
     * @param card A carta de movimento que será usada
     * @param position A posição da carta para onde a peça irá se mover
     * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador fazer um movimento
     * @exception IllegalMovementException Caso uma peça seja movida para fora do tabuleiro ou para uma posição onde já tem uma peça da mesma cor
     * @exception InvalidCardException Caso uma carta que não está na mão do jogador seja usada
     * @exception InvalidPieceException Caso uma peça que não está no tabuleiro seja usada
     */
    public void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException{}

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
