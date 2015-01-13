package imgb.rulesystem.common;

import imgb.rulesystem.common.jsontransform.JsonSerializableImp;
import imgb.rulesystem.common.jsontransform.TestCustomer;
import org.codehaus.jackson.JsonNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonSerializableTest {
    TestCustomer test = new TestCustomer("1312");
    JsonSerializableImp imp = new JsonSerializableImp(test);

    @Test
    public void testSerlizationToJson() throws Exception {
        JsonNode jsonNode = imp.serlizationToJson();
        System.out.println(jsonNode);
        assertNotNull(jsonNode);
    }

    @Test
    public void testDeserializeFromJson() throws Exception {
        Object object = imp.deserializeFromJson(imp.serlizationToJson());
        System.out.println(object);
        assertNotNull(object);
    }
}