package imgb.rulesystem.context.whiteboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by is on 1/14/15.
 */
public class RecordBoard {
    Map<String, Object> recordsMap = new HashMap<String, Object>();

    public RecordBoard(Map<String, Object> recordsMap) {
        this.recordsMap = recordsMap;
    }

    public RecordBoard() {

    }

    /**
     * @param entity
     * @param alias
     */
    public void setRecord(Object entity, String alias) {
        recordsMap.put(alias, entity);
    }


    /**
     * @param alias
     */
    public void removeRecord(String alias) {
        recordsMap.remove(alias);
    }

    /**
     * @param alias
     * @return
     */
    public Object getRecord(String alias) {
        return recordsMap.get(alias);
    }

    public void clear() {
        recordsMap.clear();
    }
}
