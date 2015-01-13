package imgb.rulesystem.context.entity;

import java.io.Serializable;

/**
 * Created by Daniel on 2014/8/23.
 * ID 为获得对象的唯一标示。
 */
public interface Entity<ID extends Serializable> extends Serializable {
    public ID getId();
}
