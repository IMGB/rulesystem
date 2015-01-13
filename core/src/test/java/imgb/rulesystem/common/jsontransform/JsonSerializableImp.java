package imgb.rulesystem.common.jsontransform;

import org.codehaus.jackson.JsonNode;
import imgb.rulesystem.node.JsonSerializable;

import java.io.IOException;

/**
 * Created by is on 15/1/8.
 */
public class JsonSerializableImp implements JsonSerializable {
    private TestCustomer test;
    public JsonSerializableImp(TestCustomer test){
        this.test = test;
    }
    public JsonNode serlizationToJson(){
        JsonNode jsonNode = null;
        try {
            String json = OBJECT_MAPPER.writeValueAsString(test);
            jsonNode = OBJECT_MAPPER.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode;
    }

    public Object deserializeFromJson(JsonNode jsonNode) throws Exception{
        return OBJECT_MAPPER.readValue(jsonNode,Object.class);
    }
}
