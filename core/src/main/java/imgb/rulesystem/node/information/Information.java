package imgb.rulesystem.node.information;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel on 2014/7/26.
 */
public class Information {
    protected Map<String, Object> infoContainer = new HashMap<String, Object>();

    public Information() {

    }

    public void setInfo(String name, Object value) {
        infoContainer.put(name, value);
    }

    public Object getInfo(String key) {
        return infoContainer.get(key);
    }

    public boolean hasInfo(String key) {
        return infoContainer.containsKey(key);
    }
}
