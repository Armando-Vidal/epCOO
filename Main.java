package epCOO;

public class Main{
    public static void main (String args[]){

        Position[] posicoesrandom = new Position[1];
        Card deck = new Card("teste", Color.NONE, posicoesrandom);

        

        Card [] deckReal = new Card[5];
        
        for (int i =0; i < deckReal.length; i++)
            deckReal[i] = deck.createCards()[i];

        Player playerBlue = new Player ("Armando", Color.BLUE, deckReal[0], deckReal[1]);
        Player playerRed = new Player ("Marcola", Color.RED, deckReal[2], deckReal[3]);

        

        GameImpl jogo1 = new GameImpl(playerBlue.getName(), playerRed.getName(), deckReal);
        jogo1.init();

        jogo1.printBoard();

        Color turno = jogo1.getTableCard().getColor();

        if (turno.equals(Color.BLUE))
            jogo1.makeMove(playerBlue.getCards()[0], playerBlue.getCards()[0].getPositions()[0], new Position(0,2));
        if (turno.equals(Color.RED))
            jogo1.makeMove(playerRed.getCards()[0], playerRed.getCards()[0].getPositions()[0], new Position(0, -2));

        jogo1.printBoard();
        

    }

}