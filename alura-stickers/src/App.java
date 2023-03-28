import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static final String RESET = "\u001B[0m";
    public static final String AZUL = "\u001b[44m";
    public static final String BOLD = "\u001b[1m";

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI endereco = URI.create(url);

        var client = HttpClient.newHttpClient();
        
        var request = HttpRequest.newBuilder(endereco).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();

        // System.out.println(body);

        // Extrair só os dados q nos interessam (título, postel, classificação)

        var parser = new JsonParser();

        List<Map<String, String>> listaFilmes = parser.parse(body);

        // System.out.println(listaFilmes.size());
        // System.out.println(listaFilmes.get(0));

        // Exibir e manipular os dados

        var geradora = new GeradorFigurinhas(); 

        var directory = new File("output/");
        directory.mkdir();

        for (Map<String, String> filme : listaFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "output/" + titulo + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println("-----------------------------");

            var avaliacao = Double.parseDouble(filme.get("imDbRating"));

            System.out.println(BOLD + AZUL + "Classificação: " + avaliacao + RESET);

            for (int star = 1; star <= avaliacao; star++) {
                System.out.print("⭐");
            }
            System.out.println();

            System.out.println(BOLD + "Titulo: " + RESET + titulo);

            System.out.println(BOLD + "Imagem: " + RESET + urlImagem);

            System.out.println("-----------------------------");
        }

    }
}
 