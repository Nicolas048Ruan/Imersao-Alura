import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001b[44m";
    public static final String BOLD = "\u001b[1m";

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        
        API api = API.NASA;

        String url = api.getUrl();

        ExtratorConteudo extrator = api.getExtrator();

        

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradorFigurinhas(); 

        var directory = new File("output/");
        directory.mkdir();

        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();

            String nomeArquivo = "output/" + conteudo.titulo() + ".png";

            // geradora.criar(inputStream, nomeArquivo, avaliacao);
            gerador.criar(inputStream, nomeArquivo);

            System.out.println("-----------------------------");

            // System.out.println(BOLD + AZUL + "Classificação: " + avaliacao + RESET);

            // for (int star = 1; star <= avaliacao; star++) {
            //     System.out.print("⭐");
            // }
            // System.out.println();

            System.out.println(BOLD + AZUL + "Titulo: " + RESET + conteudo.titulo());

            System.out.println(BOLD + AZUL + "Imagem: " + RESET + conteudo.urlImagem());

            System.out.println("-----------------------------");
        }

    }
}
