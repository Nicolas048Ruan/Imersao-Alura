import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
// import java.io.FileInputStream;
import java.io.InputStream;
// import java.net.URL;

import javax.imageio.ImageIO;

public class GeradorFigurinhas {
    
    public void criar(InputStream inputStream, String nomeArquivo, Double rating) throws Exception {

        // Leitura da imagem

        // InputStream inputStream =
        //     new FileInputStream(new File("img/filme.jpg"));

        // InputStream inputStream =
        //     new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg")
        //     .openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Cria nova imagem em memória com transparência e novo tamanho

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();

        int novaAltura = altura + 200;

        // Cria nova imagem com a largura original, nova altura e com fundo transparênte
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar imagem original para a nova imagem (em memória)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();

        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 86);

        graphics.setColor(Color.ORANGE);
        graphics.setFont(fonte);

        // Escrever uma frase na nova imagem

        String texto = "";

        if (rating > 9) {
            texto = "TOPZERA";
        } else if (rating == 9) {
            texto = "MUITO BOM";
        } else {
            texto = "BOM";
        }

        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();

        int posicaoX = (largura - larguraTexto) / 2;

        graphics.drawString(texto, posicaoX, novaAltura - 100);

        // Escrever a nova imagem em um arquivo

        // ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

    // public static void main(String[] args) throws Exception {
    //     var geradora = new GeradorFigurinhas();
    //     geradora.criar();
    // }

}
