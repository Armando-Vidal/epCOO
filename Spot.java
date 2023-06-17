package epCOO;
/**
 * Classe contendo ações e informações sobre cada espaço (quadrado) no tabuleiro
 */

public class Spot {
    /**
     * Construtor para espaços com peça e com cor
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos Posição do espaço no tabuleiro
     * @param color Cor do espaço no tabuleiro (Templo)
    */
    public Piece piece;
    public Position pos;
    public Color color;
    public boolean havePiece = false;
    
    public Spot(Piece piece, Position pos, Color color) {
        this.piece = piece;
        havePiece = true;
        this.pos = pos;
        this.color = color;
    }

    /**
     * Construtor para espaços com peça e sem cor
     * @param piece Peça que inicia nesse espaço do tabuleiro
     * @param pos Posição do espaço no tabuleiro
     */
    public Spot(Piece piece, Position pos) {
        this.piece = piece;
        havePiece = true;
        this.pos = pos;
    }

    /**
     * Construtor para espaços sem peça e sem cor
     * @param pos Posição do espaço no tabuleiro
     */
    public Spot(Position pos) {
        this.pos = pos;
    }

    /**
     * Método que devolve a posição (coordenadas) do espaço
     * @return Objeto Position contendo a posição (coordenadas) do espaço
     */
    public Position getPosition() {
        return this.pos;
    }

    /**
     * Método que devolve a peça contida neste espaço
     * @return Objeto Piece caso tenha uma peça ou null caso o espaço esteja vazio
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Método que devolve a cor do espaço
     * @return Enum Color com a cor do espaço. Caso o espaço não tenha cor, o valor do enum será NONE
     */
    public Color getColor() {
        return this.color;
    }

    public boolean havePiece(){
        return this.havePiece;
    }

    /**
     * Método que ocupa o espaço atual com a peça passada
     * @param piece A peça para ocupar este espaço
     * @exception IllegalMovementException Caso o espaço já esteja ocupado por uma peça da mesma cor
     */
    protected void occupySpot(Piece piece) throws IllegalMovementException {
        if (this.havePiece)
            if(this.piece.getColor().equals(piece.getColor())){
                throw new IllegalMovementException("Esse lugar está ocupado por um aliado! Escolha outro!");
        }else{
                this.piece = piece;
                this.havePiece = true;
            }
        }

    /**
     * Método que "libera" o espaço atual, ou seja, deixa-o vazio
     */
    protected void releaseSpot() {
        this.havePiece = false;
    }
}