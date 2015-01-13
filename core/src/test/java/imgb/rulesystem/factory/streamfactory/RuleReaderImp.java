package imgb.rulesystem.factory.streamfactory;

import imgb.rulesystem.rulereader.reader.RuleReader;
import imgb.rulesystem.rulereader.token.RegulationToken;
import imgb.rulesystem.rulereader.token.RuleToken;

/**
 * Created by Gaoshuai on 2014/10/29.
 */
public class RuleReaderImp extends RuleReader {

    private  int size = 0;

    public  boolean hasNext(){
        if(size<3){
            size++;
            return true;
        }
        return false;
    }

    public RuleToken getNextToken(){
        RegulationToken regulationToken = new RegulationToken("STREAM_FACTORY_PRIORITY_KEY","1",true);
//        RegulationToken regulationToken = new RegulationToken("user","1",true);
        return regulationToken;
    }

    public  String getRuleType(){
        return  null;
    }
}
