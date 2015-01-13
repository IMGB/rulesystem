package imgb.rulesystem.rulereader.regulation.parser;


import imgb.rulesystem.rulereader.regulation.annotaion.IterableToken;
import imgb.rulesystem.rulereader.regulation.annotaion.TokenRegulation;

import java.util.Set;

/**
 * Created by TT on 2014/10/29.
 */
public class TestA {
    private Integer id;
    private Set<TestB> testBs;

    @TokenRegulation(tokenName = "id")
    public Integer getInteger() {
        return id;
    }

    public void setInteger(Integer id) {
        this.id = id;
    }

    @IterableToken(tokenName ="testB", subSession = true)
    public Set<TestB> getTestBs() {
        return testBs;
    }

    public void setTestBs(Set<TestB> testBs) {
        this.testBs = testBs;
    }

}
