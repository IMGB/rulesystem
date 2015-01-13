package imgb.rulesystem.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ca on 8/21/14.<br>
 */
public class WhiteBoard {
    Map<String, Object> recordsMap = new HashMap<String, Object>();

    /**
     * @param entityDAO
     * @param alias
     */
    public void setRecord(Object entityDAO, String alias) {
        recordsMap.put(alias, entityDAO);
    }

    /**
     * @param alias
     */
    public void RemoveRecord(String alias) {
        recordsMap.remove(alias);
    }

    /**
     * @param alias
     * @return
     */
    public Object getRecord(String alias) {
        return recordsMap.get(alias);
    }
}
