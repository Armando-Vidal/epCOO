package epCOO;
/*
 * Classe que contém informações das peças de jogo
 */
public class Piece {
    /**
     * Construtor que define a cor e o tipo da peça
     * @param color Cor da peça
     * @param isMaster Se o tipo da peça é mestre ou não
     */
    Color color = Color.NONE;
    boolean isMaster;
    boolean isDead = false;

    public Piece(Color color, boolean isMaster) {
        this.color = color;
        this.isMaster = isMaster;
    }

    /**
     * Método que devolve a cor da peça
     * @return Enum Color com a cor da peça
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Método que devolve se é um mestre ou não
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public boolean isMaster() {
        return this.isMaster;
    }
    //auxiliar
    public void kill(){
        this.isDead = true;
    }
    //auxiliar
    public boolean isDead(){
        return this.isDead;
    }
}
