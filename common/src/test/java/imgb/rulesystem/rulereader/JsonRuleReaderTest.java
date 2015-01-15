package imgb.rulesystem.rulereader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import imgb.rulesystem.rulereader.reader.RuleReader;
import imgb.rulesystem.rulereader.token.RuleToken;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JsonRuleReaderTest {


    private void checkToken(RuleToken token, String name, String value, boolean beEnd) {
        assertEquals(token.getTokenName(), name);
        assertEquals(token.getTokenString(), value);
        assertEquals(token.beSessionEnd(), beEnd);
    }

    @Test
    public void testOneLayer() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }

        assertEquals(ruleTokens.size(), 5);
    }

    @Test
    public void testTreeLayer() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n" +
                "\t\t\t\"号\":13,\n" +
                "\t\t\t\"室\":203\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 14);

        checkToken(ruleTokens.get(0), "", "session begin", false);
        checkToken(ruleTokens.get(1), "起始时间", "2014-10-24", false);
        checkToken(ruleTokens.get(2), "结束时间", "2014-11-25", false);
        checkToken(ruleTokens.get(3), "人数", "32", false);
        checkToken(ruleTokens.get(4), "店址", "session begin", false);
        checkToken(ruleTokens.get(5), "国家", "中国", false);
        checkToken(ruleTokens.get(6), "省", "江苏", false);
        checkToken(ruleTokens.get(7), "街道", "session begin", false);
        checkToken(ruleTokens.get(8), "路名", "南京路", false);
        checkToken(ruleTokens.get(9), "号", "13", false);
        checkToken(ruleTokens.get(10), "室", "203", false);
        checkToken(ruleTokens.get(11), "", "session end", true);
        checkToken(ruleTokens.get(12), "", "session end", true);
        checkToken(ruleTokens.get(13), "", "session end", true);
    }

    @Test
    public void testThreeBlock() throws Exception{
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n" +
                "\t\t\t\"号\":\"13\",\n" +
                "\t\t\t\"室\":\"203\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"菜式\":\"中式\",\n" +
                "\t\"雇员\":{\n" +
                "\t\t\"厨师长\":\"dsa\",\n" +
                "\t\t\"学徒\":\"asd\"\n" +
                "\t}\n" +
                "}";

        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 19);

        checkToken(ruleTokens.get(0), "", "session begin", false);
        checkToken(ruleTokens.get(1), "起始时间", "2014-10-24", false);
        checkToken(ruleTokens.get(2), "结束时间", "2014-11-25", false);
        checkToken(ruleTokens.get(3), "人数", "32", false);
        checkToken(ruleTokens.get(4), "店址", "session begin", false);
        checkToken(ruleTokens.get(5), "国家", "中国", false);
        checkToken(ruleTokens.get(6), "省", "江苏", false);
        checkToken(ruleTokens.get(7), "街道", "session begin", false);
        checkToken(ruleTokens.get(8), "路名", "南京路", false);
        checkToken(ruleTokens.get(9), "号", "13", false);
        checkToken(ruleTokens.get(10), "室", "203", false);
        checkToken(ruleTokens.get(11), "", "session end", true);
        checkToken(ruleTokens.get(12), "", "session end", true);

        checkToken(ruleTokens.get(13), "菜式", "中式", false);
        checkToken(ruleTokens.get(14), "雇员", "session begin", false);
        checkToken(ruleTokens.get(15), "厨师长", "dsa", false);
        checkToken(ruleTokens.get(16), "学徒", "asd", false);
        checkToken(ruleTokens.get(17), "", "session end", true);
        checkToken(ruleTokens.get(18), "", "session end", true);
    }

    @Test
    public void testMissComma() throws Exception{
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\"\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        ArrayList<RuleToken> ruleTokens = new ArrayList<>();
        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 2);
        checkToken(ruleTokens.get(0), "", "session begin", false);
        checkToken(ruleTokens.get(1), "起始时间", "2014-10-24", false);
    }


    @Test(expected = TokenException.class)
    public void testMissQuotation() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25,\n" +
                "\t\"人数\":32\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        ArrayList<RuleToken> ruleTokens = new ArrayList<>();
        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
    }

    @Test
    public void testHalfJson() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n";

        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 4);

        checkToken(ruleTokens.get(0), "", "session begin", false);
        checkToken(ruleTokens.get(1), "起始时间", "2014-10-24", false);
        checkToken(ruleTokens.get(2), "结束时间", "2014-11-25", false);
        checkToken(ruleTokens.get(3), "人数", "32", false);
    }

    @Test
    public void testTwoLayerHalfJson() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n";

        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 9);

        checkToken(ruleTokens.get(0), "", "session begin", false);
        checkToken(ruleTokens.get(1), "起始时间", "2014-10-24", false);
        checkToken(ruleTokens.get(2), "结束时间", "2014-11-25", false);
        checkToken(ruleTokens.get(3), "人数", "32", false);
        checkToken(ruleTokens.get(4), "店址", "session begin", false);
        checkToken(ruleTokens.get(5), "国家", "中国", false);
        checkToken(ruleTokens.get(6), "省", "江苏", false);
        checkToken(ruleTokens.get(7), "街道", "session begin", false);
        checkToken(ruleTokens.get(8), "路名", "南京路", false);


    }

    @Test(expected = TokenException.class)
    public void testTwoLayerHalfJSONAndGetTokenAgain() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n";

        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            ruleTokens.add(ruleReader.getNextToken());
        }
        assertEquals(ruleTokens.size(), 9);

        RuleToken token = ruleReader.getNextToken();

    }

    @Test
    public void testInputEmptyString() throws Exception {
        String jsonStr = "";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        assertFalse(ruleReader.hasNext());
        assertNull(ruleReader.getNextToken());

        assertFalse(ruleReader.hasNext());
        assertFalse(ruleReader.hasNext());
        assertFalse(ruleReader.hasNext());
        assertFalse(ruleReader.hasNext());
        assertFalse(ruleReader.hasNext());

        assertNull(ruleReader.getNextToken());
        assertNull(ruleReader.getNextToken());
        assertNull(ruleReader.getNextToken());
    }

    @Test(expected = NullPointerException.class)
    public void testInputNull() throws Exception {
        String jsonStr = null;
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
    }

    @Test
    public void testGetRuleType() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        assertEquals(ruleReader.getRuleType(), "json_V0.1");
    }

    @Test
    public void testHasNext() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n" +
                "\t\t\t\"号\":13,\n" +
                "\t\t\t\"室\":203\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        RuleReader ruleReader = new JsonRuleReader(jsonStr);

        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            RuleToken token = ruleReader.getNextToken();
            assertNotNull(token);

            ruleTokens.add(token);
        }
        assertEquals(ruleTokens.size(), 14);

        for(int i = 0;i<100;i++) {
            assertFalse(ruleReader.hasNext());
            assertNull(ruleReader.getNextToken());
        }

    }

    @Test
    public void testGetNextToken1() throws Exception {
        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n" +
                "\t\t\t\"号\":13,\n" +
                "\t\t\t\"室\":203\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            for(int i = 0;i<100;i++) {
                assertTrue(ruleReader.hasNext());
            }
            RuleToken token = ruleReader.getNextToken();
            assertNotNull(token);
            ruleTokens.add(token);
        }
        assertEquals(ruleTokens.size(), 14);
    }

    @Test
    public void testGetRuleTypeNotChange() throws Exception {
        testGetRuleType();

        String jsonStr = "{\n" +
                "\t\"起始时间\":\"2014-10-24\",\n" +
                "\t\"结束时间\":\"2014-11-25\",\n" +
                "\t\"人数\":32,\n" +
                "\t\"店址\":{\n" +
                "\t\t\"国家\":\"中国\",\n" +
                "\t\t\"省\":\"江苏\",\n" +
                "\t\t\"街道\":{\n" +
                "\t\t\t\"路名\":\"南京路\",\n" +
                "\t\t\t\"号\":13,\n" +
                "\t\t\t\"室\":203\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        RuleReader ruleReader = new JsonRuleReader(jsonStr);
        ArrayList<RuleToken> ruleTokens = new ArrayList<>();

        while(ruleReader.hasNext()) {
            for(int i = 0;i<100;i++) {
                assertTrue(ruleReader.hasNext());
            }
            RuleToken token = ruleReader.getNextToken();
            assertNotNull(token);
            ruleTokens.add(token);
        }
        assertEquals(ruleTokens.size(), 14);

        testGetRuleType();
    }
}