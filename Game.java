package epCOO;

public interface Game {
 
    Color getSpotColor(Position position);

    Piece getPiece(Position position);

  
    Card getTableCard();


    Player getRedPlayer();

    Player getBluePlayer();

    void makeMove(Card card, Position cardMove, Position currentPos) throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException;

    boolean checkVictory(Color color);

    void printBoard();
}
