package epCOO;

import java.util.Random;
/**
 * Classe que contém informações das cartas
 */
public class Card {
    /**
     * Construtor que define os principais atributos de uma cara
     * @param name Nome da carta
     * @param color Cor da carta
     * @param positions Todas as posições relativas de movimento
     */
    String name;
    Color color;
    Position positions[] = new Position[5];
    boolean cartaDaMesa;

    public static Card tigre;
    public static Card sapo;
    public static Card carangueijo;
    public static Card ganso;
	public static Card dragao;
	public static Card coelho;
	public static Card elefante;
	public static Card galo;

    public Card(String name, Color color, Position[] positions) {
        this.name = name;
        this.color = color;

        for (int i=0; i<positions.length; i++)
            this.positions[i] = positions[i];
            
    }

    /**
     * Método que devolve o nome da carta
     * @return String que contém o nome da carta
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método que devolve a cor da carta
     * @return Enum Color que contém a cor da carta
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Método que devolve todas as possíveis posições relativas de movimento.
     * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as possíveis posições de movimento em relação ao ponto de origem.
     * @return Um array de Position contendo todas as possíveis posições de movimento em relação ao ponto de origem
     */
    public Position[] getPositions() {
        return this.positions;
    }

    public boolean cartaDaMesa(){
        return this.cartaDaMesa;
    }

    public void setcartaDaMesa(boolean cartaDaMesa){
        this.cartaDaMesa = cartaDaMesa;
    }
    /**
     * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que serão utilizadas na partida.
     * @return Vetor de cartas com todas as cartas do jogo
     */

    private Card[] embaralhar(Card[] cartas){
        Random aleatorio = new Random();
        Card[] cartasJogo = new Card[5];

        for (int i = 0; i <cartas.length; i++){
            
            int indiceAleatorio = aleatorio.nextInt(i+1);

           
            Card aux = cartas[indiceAleatorio];
            cartas[indiceAleatorio] = cartas[i];
            cartas[i] = aux;
            
        }

        for (int j = 0; j< cartasJogo.length; j++) 
            cartasJogo[j] = cartas[j];
        
        return cartasJogo;
    }


    public Card[] createCards() {
        Position[] posicoes_tigre = {new Position(-2, 0), new Position(1, 0)};
        tigre = new Card("tigre", Color.BLUE, posicoes_tigre);

        Position[] posicoes_sapo = {new Position(0, -2), new Position(-1, -1), new Position(1, 1)};
        sapo = new Card("sapo", Color.RED, posicoes_sapo);

        Position[] posicoes_carangueijo = {new Position(0, -2), new Position(-1, 0), new Position(0, 2)};
        carangueijo = new Card("carangueijo", Color.BLUE, posicoes_carangueijo);

        Position[] posicoes_ganso = {new Position(-1, -1), new Position(0, -1), new Position(0, 1), new Position(1, 1)};
        ganso = new Card("ganso", Color.BLUE, posicoes_ganso);

        Position[] posicoes_dragao = {new Position(-1, -2), new Position(1, -1), new Position(1, 1), new Position(-1, 2)};
        dragao = new Card("dragao", Color.RED, posicoes_dragao);

        Position[] posicoes_coelho = {new Position(1, -1), new Position(-1, 1), new Position(0, 2)};
        coelho = new Card("coelho", Color.BLUE, posicoes_coelho);

        Position[] posicoes_elefante = {new Position(-1, -1), new Position(0, -1), new Position(-1, 1), new Position(0, 1)};
        elefante = new Card("elefante", Color.RED, posicoes_elefante);

        Position[] posicoes_galo = {new Position(0, -1), new Position(1, -1), new Position(0, 1), new Position(-1, 1)};
        galo = new Card("galo", Color.RED, posicoes_galo);

        Card[] cartas_completas = {tigre, sapo, carangueijo, ganso, dragao, coelho, elefante, galo};

        Card[] cartas = embaralhar(cartas_completas);

        return cartas;
    }

}
