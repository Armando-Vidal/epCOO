package epCOO;

public class Piece {

    Color color = Color.NONE;
    boolean isMaster;
    boolean isDead = false;

    public Piece(Color color, boolean isMaster) {
        this.color = color;
        this.isMaster = isMaster;
    }

 
    public Color getColor() {
        return this.color;
    }

  
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
