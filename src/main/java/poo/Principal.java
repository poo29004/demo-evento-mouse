package poo;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;

import java.awt.*;

/**
 * Pequeno exemplo para ilustrar a captura
 * de evento de clique do mouse
 */
public class Principal implements DrawListener {
    private final int dimensao;
    private Circulo circulo;
    private Draw draw;
    private boolean teclaPressionada;

    public Principal(int dimensao) {
        // Criando um círculo na coordenada (0,0)
        circulo = new Circulo(0, 0);
        // Objeto responsável por fazer a área de desenho (canvas)
        draw = new Draw();

        // Adicionando objeto desta classe como o
        // objeto que ficará ouvindo por eventos do mouse ou teclado
        draw.addListener(this);

        // Para saber quando uma tecla não está mais pressionada
        teclaPressionada = false;

        // Indicando o número de casas da área de desenho
        this.dimensao = dimensao;
        draw.setXscale(0, dimensao);
        draw.setYscale(0, dimensao);
        draw.enableDoubleBuffering();

        // Desenhando os objetos na tela
        desenharTela();        
    }

    /**
     * Desenha a grade do tabuleiro
     */
    public void desenharTabuleiro(){
        draw.setPenColor(Color.BLACK);
        for (int i = 0; i <= dimensao; i++) draw.line(i, 0, i, dimensao);
        for (int j = 0; j <= dimensao; j++) draw.line(0, j, dimensao, j);
    }

    /**
     * Faz o objeto círculo se desenhar na tela
     */
    public void desenharCirculos(){
        circulo.desenhar(draw);
    }

    /**
     * Limpa a tela, desenha o tabuleiro e os círculos
     */
    public void desenharTela(){
        draw.clear(Color.LIGHT_GRAY);
        this.desenharTabuleiro();
        this.desenharCirculos();
        draw.setPenColor(Color.BLACK);
        draw.text(4.5, .5, "Pressione a tecla 's' e veja o terminal");
        draw.show();
    }

    /**
     * Captura o evento de botão pressionado do mouse
     * @param x coordenada X do cursor do mouse quando o botão foi pressionado
     * @param y coordenada Y do cursor do mouse quando o botão foi pressionado
     */
    public void mousePressed(double x, double y) {
        // Se o usuário clicou dentro da casa do tabuleiro onde está o círculo
        if ((Math.floor(x) == circulo.getX()) && (Math.floor(y) == circulo.getY())){
            circulo.marcaDesmarca();
        }else{
            circulo.setX(x);
            circulo.setY(y);
            circulo.setSelecionado(false);
        }
        // Limpa a tela e desenha tudo novamente (tabuleiro e círculo)
        desenharTela();
    }

    @Override
    public void keyTyped(char c) {
        if ((c == 's') && (!teclaPressionada)){
            System.out.println("apertou a tecla s");
            teclaPressionada = true;
        }
    }
    @Override
    public void keyReleased(int i) {
        teclaPressionada = false;
    }


    public static void main(String[] args) {
        Principal p = new Principal(8);
    }

    // Métodos da interface DrawListener que não serão usados neste exemplo
    @Override
    public void mouseDragged(double v, double v1) {}
    @Override
    public void mouseReleased(double v, double v1) {}
    @Override
    public void mouseClicked(double v, double v1) {}    
    @Override
    public void keyPressed(int i) {}
}
