package imgb.rulesystem.rulereader.regulation.parser;

import imgb.rulesystem.rulereader.token.RuleToken;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


public class StreamParserTest {

    /**
     * 测试正常情况下能够取出Token
     * @throws Exception
     */
    @Test
    public void testGetNext() throws Exception {
        TestA testA = new TestA();
        StreamParser streamParser = new StreamParser(testA, "id");
        RuleToken ruleToken = streamParser.getNext();
    }

    /**
     * 测试正常情况下能否迭代取出token
     * @throws Exception
     */
    @Test
    public void testHasNext() throws Exception {
        TestA testA = new TestA();
        TestB testB = new TestB();
        Set<TestB> testBs = new HashSet<>();
        testBs.add(testB);
        testA.setTestBs(testBs);
        StreamParser streamParser = new StreamParser(testA, "id");
        while (streamParser.hasNext()) {
            RuleToken ruleToken = streamParser.getNext();
        }
    }

    /**
     * 测试错误情况下
     * @throws Exception
     */
    @Ignore
    public void testHasNextB() throws Exception {
        TestA testA = new TestA();
        StreamParser streamParser = new StreamParser(testA, "id");
        while (streamParser.hasNext()) {
            RuleToken ruleToken = streamParser.getNext();
        }
    }
}