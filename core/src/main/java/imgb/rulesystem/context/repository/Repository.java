package imgb.rulesystem.context.repository;

import imgb.rulesystem.context.repository.datasource.DataSource;
import imgb.rulesystem.context.entity.Entity;

/**
 * Created by ca on 10/14/14.<br>
 */
public abstract class Repository {

    protected DataSource dataSource;
    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract Entity getObject(Entity entity);
}
