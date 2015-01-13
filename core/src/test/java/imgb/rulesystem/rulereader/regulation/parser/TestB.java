package imgb.rulesystem.rulereader.regulation.parser;

import imgb.rulesystem.rulereader.regulation.annotaion.TokenRegulation;

/**
 * Created by TT on 2014/10/29.
 */
public class TestB {
    private Integer id;
    private String str;

    @TokenRegulation(tokenName = "str")
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @TokenRegulation(tokenName = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
