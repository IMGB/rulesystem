package imgb.rulesystem.rulereader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonRuleReaderTest {

    @Test
    public void testHasNext() throws Exception {

        String jsonStr = "{\n" +
                "    \"glossary\": {\n" +
                "        \"title\": \"example glossary\",\n" +
                "\t\t\"GlossDiv\": {\n" +
                "            \"title\": \"S\",\n" +
                "\t\t\t\"GlossList\": {\n" +
                "                \"GlossEntry\": {\n" +
                "                    \"ID\": \"SGML\",\n" +
                "\t\t\t\t\t\"SortAs\": \"SGML\",\n" +
                "\t\t\t\t\t\"GlossTerm\": \"Standard Generalized Markup Language\",\n" +
                "\t\t\t\t\t\"Acronym\": \"SGML\",\n" +
                "\t\t\t\t\t\"Abbrev\": \"ISO 8879:1986\",\n" +
                "\t\t\t\t\t\"GlossDef\": {\n" +
                "                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\n" +
                "\t\t\t\t\t\t\"GlossSeeAlso\": [\"GML\", \"XML\"]\n" +
                "                    },\n" +
                "\t\t\t\t\t\"GlossSee\": \"markup\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(jsonStr);
            JsonParser jsonParser = mapper.getFactory().createParser(jsonStr);

            while(true) {
                JsonToken token = jsonParser.nextToken();

                if(token == null) {
                    break;
                }

                System.out.print("name is " + jsonParser.getCurrentName() + "\n");
                System.out.print("value is " + jsonParser.getValueAsString() + "\n");
                System.out.print("text is " + jsonParser.getText() + "\n");
                System.out.print("struct begin: " + token.isStructStart()+ "\n");
                System.out.print("struct end: "+ token.isStructEnd()+ "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetNextToken() throws Exception {

    }

    @Test
    public void testGetRuleType() throws Exception {

    }
}