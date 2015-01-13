package imgb.rulesystem.context.repository.datasource;

import imgb.rulesystem.context.entity.Entity;

import java.util.List;

/**
 * Created by Daniel on 2014/9/14.
 */
public interface DataSource {
    public abstract Entity getObject(Entity entity);
    public List query(String queryStr,Object[] values);
}
