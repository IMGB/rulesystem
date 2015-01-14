package imgb.rulesystem.context.whiteboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ca on 8/21/14.<br>
 */
public class WhiteBoard {

    public enum Scope {
        LOCAL_SCOPE("LOCAL_SCOPE"), GLOBAL_SCOPE("GLOBAL_SCOPE"), TREE_SCOPE("TREE_SCOPE");

        private String key;

        Scope(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    private Map<Scope,RecordBoard> scopeBoardMap;

    public WhiteBoard() {
        scopeBoardMap = new HashMap<>();
        scopeBoardMap.put(Scope.GLOBAL_SCOPE, new RecordBoard());
        scopeBoardMap.put(Scope.TREE_SCOPE, new RecordBoard());
        scopeBoardMap.put(Scope.LOCAL_SCOPE, new RecordBoard());

    }

    /**
     * @param entity
     * @param alias
     */
    public void setRecord(Object entity, String alias) {
        RecordBoard recordBoard = scopeBoardMap.get(Scope.LOCAL_SCOPE);
        recordBoard.setRecord(entity, alias);
    }

    /**
     * @param entity
     * @param alias
     */
    public void setRecord(Object entity, String alias, Scope scope) {
        RecordBoard recordBoard = scopeBoardMap.get(Scope.LOCAL_SCOPE);
        if(recordBoard == null) {
            return;
        }
        recordBoard.setRecord(entity, alias);
    }

    public void RemoveRecord(Scope scope) {
        RecordBoard recordBoard = scopeBoardMap.get(Scope.LOCAL_SCOPE);
        if(recordBoard != null) {
            recordBoard.clear();
        }
    }

    public void RemoveRecord(Scope scope, String alias) {
        RecordBoard recordBoard = scopeBoardMap.get(Scope.LOCAL_SCOPE);
        if(recordBoard != null) {
            recordBoard.removeRecord(alias);
        }
    }

    /**
     * @param alias
     * @return
     */
    public Object getRecord(String alias) {

        Object returnObject = null;

        returnObject = this.getRecord(Scope.LOCAL_SCOPE, alias);
        if(returnObject != null) {
            return returnObject;
        }

        returnObject = this.getRecord(Scope.TREE_SCOPE, alias);
        if(returnObject != null) {
            return returnObject;
        }

        returnObject = this.getRecord(Scope.GLOBAL_SCOPE, alias);
        if(returnObject != null) {
            return returnObject;
        }

        return null;
    }

    public Object getRecord(Scope scope, String alias) {
        RecordBoard recordBoard = scopeBoardMap.get(Scope.LOCAL_SCOPE);

        if(recordBoard != null){
            return recordBoard.getRecord(alias);
        }
        return null;
    }

}
