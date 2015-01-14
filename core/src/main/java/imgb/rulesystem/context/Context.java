package imgb.rulesystem.context;

import imgb.rulesystem.context.repository.Repository;
import imgb.rulesystem.context.repository.manager.RepositoryManager;
import imgb.rulesystem.context.entity.Entity;
import imgb.rulesystem.context.whiteboard.WhiteBoard;

/**
 * Created by ca on 8/21/14.<br>
 */
public class Context {

    public static final String LAST_RULE_EXECUTED_NODE = "LAST_RULE_EXECUTED_NODE";

    private RepositoryManager repositoryManager;

    private WhiteBoard whiteBoard;

    /**
     *
     */
    public Context(RepositoryManager sourceManager) {
        this.repositoryManager = sourceManager;
        whiteBoard = new WhiteBoard();
    }

    public Context() {
        whiteBoard = new WhiteBoard();
    }

    /**
     * @param alias
     * @param entityDAO
     */
    public void setRecord(String alias, Object entityDAO) {
        whiteBoard.setRecord(entityDAO, alias);
    }


    /**
     * @param entity
     * @return
     */
    public Entity getRecord(Entity entity) {
        if(repositoryManager == null) {
            return null;
        }
        Repository repository = repositoryManager.getRepository(entity);
        if (repository == null) {
            return null;
        }
        return repository.getObject(entity);
    }

    /**
     * @param alias
     * @return
     */
    public Object getRecord(String alias) {
        return whiteBoard.getRecord(alias);
    }
}
