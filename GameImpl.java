package epCOO;

public class GameImpl implements Game {



    public final int LENGTH = 5;

    Position[][] posicoes;
    Spot[][] spots;
    Piece[] pecas;

    String nomeAzul = new String();
    String nomeVermelho = new String();
    Card [] novoDeck = new Card[20]; // indices 0 e 1 correspondem às cartas do azul, 2 e 3 às do vermelho e índice 4 é a carta da mesa
    Card cartaDaMesa;
    Color turno;

    Player bluePlayer; 
    Player redPlayer; 

    //construtores

    public GameImpl(){}

    public GameImpl(String nomeAzul, String nomeVermelho){
        this.nomeAzul = nomeAzul;
        this.nomeVermelho = nomeVermelho;

    }
    public GameImpl(String nomeAzul, String nomeVermelho, Card[] novoDeck){
        this.nomeAzul = nomeAzul;
        this.nomeVermelho = nomeVermelho;
        for (int i =0; i <novoDeck.length; i++)
            this.novoDeck[i] = novoDeck[i];
        turno = novoDeck[4].getColor();
    }
    

    public void init(){
         
        posicoes = new Position[LENGTH][LENGTH]; //valor -2
        spots  = new Spot[LENGTH][LENGTH]; //valor -2
        pecas = new Piece[10];
        bluePlayer = new Player("Jogador Azul", Color.BLUE, novoDeck[0], novoDeck[1]);
        redPlayer = new Player ("Jogador Vermelho", Color.RED, novoDeck[2], novoDeck[3]);
        cartaDaMesa = novoDeck[4];
        novoDeck[4].setcartaDaMesa(true);
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

    public Piece getPiece(Position position){
        int a = position.getRow();
        int b = position.getCol();
        Piece piece = spots[a+2][b+2].getPiece();
        return piece;
    }

    public Card getTableCard(){
        return this.cartaDaMesa;
    }

    public Player getRedPlayer(){
        return this.redPlayer;
    }

    
    public Player getBluePlayer(){
        return this.bluePlayer;
    }

    public Color getTurno(){
        return this.turno;
    }
  
    public void makeMove(Card card, Position cardMove, Position currentPos) throws 
    IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {

        Piece pieceToMove = spots[currentPos.getRow()+2][currentPos.getCol()+2].getPiece();

            int blueRow, blueCol;

            if(pieceToMove.getColor().equals(Color.BLUE)){
               blueRow = cardMove.getRow()*(-1);
               blueCol = cardMove.getCol()*(-1);
               cardMove.setRowCol(blueRow, blueCol);
            }

        
        if (currentPos.getRow() + cardMove.getRow() > 2 || currentPos.getRow() + cardMove.getRow() < -2)
            throw new IllegalMovementException ("A posição excede a linha");
        else if (currentPos.getCol() + cardMove.getCol() > 2 || currentPos.getCol() + cardMove.getCol() < -2)
            throw new IllegalMovementException ("A posição excede a coluna");
        else{

            

            Spot spotToGo = spots[currentPos.getRow() + cardMove.getRow()+2][currentPos.getCol() + cardMove.getCol()+2];

           

            if (spotToGo.getPosition().getRow() > 2 || spotToGo.getPosition().getRow() < -2)
                throw new InvalidPieceException("Essa peça não está no tabuleiro");
            if (spotToGo.getPosition().getCol() > 2 || spotToGo.getPosition().getCol() < -2)
                throw new InvalidPieceException("Essa peça não está no tabuleiro");
            if (pieceToMove.getColor().equals(Color.BLUE) && this.turno.equals(Color.RED))
                throw new IncorrectTurnOrderException ("Não é o turno do azul");
            if (pieceToMove.getColor().equals(Color.RED) && this.turno.equals(Color.BLUE))
                throw new IncorrectTurnOrderException ("Não é o turno do vermelho");

            else {
                if (spotToGo.havePiece())
                        {
                            if (spotToGo.getPiece().getColor().equals(pieceToMove.getColor()))
                            throw new IllegalMovementException("Já tem uma peça dessa cor nessa posição");
                        }
                        else { 
                
                            if (pieceToMove.getColor().equals(Color.BLUE) && turno.equals(Color.BLUE)){
                                if (bluePlayer.getCards()[0] != card && bluePlayer.getCards()[1] != card)
                                    throw new InvalidCardException("Você não tem a carta na sua mão");
                                else {
                                    if (spotToGo.havePiece()) spotToGo.getPiece().kill();
                                    spotToGo.occupySpot(pieceToMove);
                                    spots[currentPos.getRow()+2][currentPos.getCol()+2].releaseSpot();

                                try{
                                    bluePlayer.swapCard(card, cartaDaMesa);
                                    cartaDaMesa = card;
                                }catch (InvalidCardException e){
                                    System.out.println(e.getMessage());
                                }

                                this.turno = Color.RED;
                }
            }
        }
                    if (pieceToMove.getColor().equals(Color.RED) && turno.equals(Color.RED)){
                        if (redPlayer.getCards()[0] != card && redPlayer.getCards()[1] != card)
                            throw new InvalidCardException("Você não tem a carta na sua mão");
                        else {
                            if (spotToGo.havePiece()) spotToGo.getPiece().kill();

                            spotToGo.occupySpot(pieceToMove);
                            spots[currentPos.getRow()+2][currentPos.getCol()+2].releaseSpot();

                             try{
                                redPlayer.swapCard(card, cartaDaMesa);
                                cartaDaMesa = card;
                            }catch (InvalidCardException e){
                                System.out.println(e.getMessage());
                            }

                            this.turno = Color.BLUE;
                        }
            }
               
            }
        }
    }


    public boolean checkVictory(Color color){
        for (int i = 0; i < pecas.length; i++){
            if (pecas[i].isMaster() && pecas[i].isDead() && !pecas[i].getColor().equals(color))
                return true;
            for (int j = 0; j <LENGTH; j++)
                if (!spots[i][j].getPiece().getColor().equals(spots[i][j].getColor()))
                    return true;
        }
        return false;
    }

    public void printBoard() {
        System.out.println("    0    1    2    3    4");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 5; j++) {
               if (this.spots[i][j].havePiece())
               {
                if(this.spots[i][j].getPiece().getColor() == Color.BLUE) {
                    Piece piece = this.spots[i][j].getPiece();
                    if(piece.isMaster() == true) {
                        System.out.print("BLUE ");
                    }
                    else {
                        System.out.print("blue ");
                    }
                }
                if(this.spots[i][j].getPiece().getColor() == Color.RED) {
                    Piece piece = this.spots[i][j].getPiece();
                    if(piece.isMaster() == true) {
                        System.out.print("RED  ");
                    }
                    else {
                        System.out.print("red  ");
                    }
                }
            }
            else System.out.print("none ");
            }
            System.out.println();
        }

        //Imprime os players
        System.out.print("Player Azul: ");
        System.out.print(bluePlayer.getCards()[0].getName());
        System.out.print(", ");
        System.out.println(bluePlayer.getCards()[1].getName());
        System.out.print("Player Vermelho: ");
        System.out.print(redPlayer.getCards()[0].getName());
        System.out.print(", ");
        System.out.println(redPlayer.getCards()[1].getName());

        //Carta na mesa
        System.out.println("Carta da mesa: " + getTableCard().getName());
        System.out.println("Turno: " + turno);
    }
}
