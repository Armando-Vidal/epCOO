package epCOO;

public class Main{
    public static void main (String args[]){

        Position[] posicoesrandom = new Position[1];
        Card deck = new Card("teste", Color.NONE, posicoesrandom);

        Card [] deckReal = new Card[5];

        for (int i =0; i < deckReal.length; i++)
            deckReal[i] = deck.createCards()[i];

        GameImpl jogo1 = new GameImpl("Armando", "Marcola", deckReal);
        jogo1.init();

        jogo1.printBoard();
    }

}