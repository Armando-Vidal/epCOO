package epCOO;


public class Spot {
   
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

    public Spot(Piece piece, Position pos) {
        this.piece = piece;
        this.havePiece = true;
        this.pos = pos;
    }

    public Spot(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        return this.pos;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean havePiece(){
        return this.havePiece;
    }

    protected void occupySpot(Piece piece) throws IllegalMovementException {
        if (this.havePiece){
            if(this.piece.getColor().equals(piece.getColor()))
                throw new IllegalMovementException("Esse lugar está ocupado por um aliado! Escolha outro!");
        }else{
                this.piece = piece;
                this.havePiece = true;
            }
        }

    protected void releaseSpot() {
        this.havePiece = false;
    }
}