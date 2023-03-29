import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorIMDB implements ExtratorConteudo {

    public List<Conteudo> extraiConteudos(String json) {
    
        // Extrair só os dados q nos interessam (título, poster, classificação)

        var parser = new JsonParser();

        List<Map<String, String>> listaAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        // Popular a lista de conteudos

        for (Map<String, String> atributos : listaAtributos) {
            
            String titulo = atributos.get("title");

            String urlImagem = atributos.get("image")
                .replaceAll("(@+)(.*).jpg", "$1.jpg");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;


    }
}
