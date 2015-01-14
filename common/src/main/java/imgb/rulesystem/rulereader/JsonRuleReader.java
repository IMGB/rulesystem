package imgb.rulesystem.rulereader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import imgb.rulesystem.rulereader.reader.RuleReader;
import imgb.rulesystem.rulereader.token.NullToken;
import imgb.rulesystem.rulereader.token.RegulationToken;
import imgb.rulesystem.rulereader.token.RuleToken;

import java.io.IOException;

/**
 * Created by is on 1/14/15.
 */
public class JsonRuleReader extends RuleReader{
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonToken nextToken;

    private JsonParser jsonParser;

    public JsonRuleReader(String jsonStr) {
        try {
            jsonParser = OBJECT_MAPPER.getFactory().createParser(jsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() throws TokenException{
        try {
            if(nextToken == null) {
                nextToken = jsonParser.nextToken();
            }
        } catch (IOException e) {
            return false;
        }
        return (nextToken != null);
    }

    @Override
    public RuleToken getNextToken() throws TokenException{
        nextToken = null;
        return assembleCurrentToken(jsonParser.getCurrentToken());
    }

    private RuleToken assembleCurrentToken(JsonToken jsonToken) throws TokenException{

        if(jsonToken.isStructEnd()) {
            return RuleToken.END_TOKEN;
        }

        if(jsonToken != null) {
            try {
                String name = jsonParser.getCurrentName();
                String value = jsonParser.getValueAsString();
                if (value == null) {
                    value = "session begin";
                }
                return new RegulationToken(name, value, false);
            } catch (IOException e) {
                throw new TokenException(e);
            }
        }

        return null;
    }

    @Override
    public String getRuleType() {
        return null;
    }

}
