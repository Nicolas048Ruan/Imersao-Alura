import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorNasa implements ExtratorConteudo {

    public List<Conteudo> extraiConteudos(String json) {
        
        // Extrair só os dados q nos interessam (título, poster, classificação)

        var parser = new JsonParser();

        List<Map<String, String>> listaAtributos = parser.parse(json);

        return listaAtributos.stream()
            .map(atributos -> 
                new Conteudo(atributos.get("title"), atributos.get("url"))
            ).toList();

    }

}
