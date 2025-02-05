package poo;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;

import java.awt.*;

/**
 * Exemplo de aplicação gráfica com interação do usuário por mouse e teclado
 * 
 * A aplicação desenha um tabuleiro e um círculo que pode ser movido pelo usuário com o mouse
 * O círculo pode ser selecionado ou desmarcado com um clique do mouse
 * A aplicação também captura eventos de teclado
 * Pressionando a tecla 's' é exibida uma mensagem no terminal
 * 
 */
public class App implements DrawListener {
    private final int dimensao;
    private Circulo circulo;
    private Draw draw;
    private boolean teclaPressionada;

    public App(int dimensao) {
        // Criando um círculo na coordenada (0,0)
        circulo = new Circulo(0, 0);
        // Objeto responsável por fazer a área de desenho (canvas)
        draw = new Draw();

        // Adicionando objeto desta classe como o
        // objeto que ficará ouvindo por eventos do mouse ou teclado
        draw.addListener(this);

        // Para saber quando uma tecla não está mais pressionada
        teclaPressionada = false;

        // Definindo a dimensão do tabuleiro
        this.dimensao = dimensao;
        
        // Definindo que a escala de cada eixo será de 0 a dimensao
        draw.setXscale(0, dimensao);
        draw.setYscale(0, dimensao);

        // Habilitando o uso de double buffering para deixar o desenho mais suave
        draw.enableDoubleBuffering();

          
    }

    /**
     * Limpa a tela, desenha o tabuleiro e os círculos
     */
    public void desenharTela(){
        // Limpa a área de desenho
        draw.clear(Color.LIGHT_GRAY);
        // Desenha o tabuleiro
        this.desenharTabuleiro();
        // Desenha o círculo
        this.desenharCirculos();
        draw.setPenColor(Color.BLACK);
        draw.text(4.5, .5, "Pressione a tecla 's' e veja o terminal");
        // Exibe a área de desenho, com todos os objetos desenhados
        draw.show();
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
     * Captura o evento de botão pressionado do mouse
     * @param x coordenada X do cursor do mouse quando o botão foi pressionado
     * @param y coordenada Y do cursor do mouse quando o botão foi pressionado
     */
    public void mousePressed(double x, double y) {
        // Se o usuário clicou dentro da casa do tabuleiro onde está o círculo
        if ((Math.floor(x) == circulo.getX()) && (Math.floor(y) == circulo.getY())){
            // Troca o estado do círculo (selecionado ou não)
            circulo.marcaDesmarca();
        }else{
            // Move o círculo para a nova posição e desmarca o círculo
            circulo.setX(x);
            circulo.setY(y);
            circulo.setSelecionado(false);
        }
        // Limpa a área de desenho, desenha o tabuleiro e os círculos
        desenharTela();
    }

    /**
     * Captura o evento de tecla pressionada
     * @param c caractere da tecla pressionada
     */
    @Override
    public void keyTyped(char c) {
        if ((c == 's') && (!teclaPressionada)){
            System.out.println("apertou a tecla s");
            teclaPressionada = true;
        }
    }

    /**
     * Captura o evento de tecla liberada
     * @param i código da tecla liberada
     */
    @Override
    public void keyReleased(int i) {
        teclaPressionada = false;
    }

    public static void main(String[] args) {

        // Inicializa a aplicação com um tabuleiro 8x8
        App app = new App(8);
        app.desenharTela();
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
