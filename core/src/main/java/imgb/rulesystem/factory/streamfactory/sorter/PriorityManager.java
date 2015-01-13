package imgb.rulesystem.factory.streamfactory.sorter;


import imgb.rulesystem.node.BaseNode;
import org.apache.log4j.Logger;
import imgb.rulesystem.factory.streamfactory.StreamFactory;

import java.util.Map;

/**
 * Created by Daniel on 2014/10/11.
 * 优先级管理器，获得节点的优先级值,默认的优先级值为0
 */
public class PriorityManager {
    private static final Logger logger = Logger.getLogger(Logger.class);

    private static final int DEFAULT_PRIORITY = 0;

    private Map<String, Integer> priorityMap = null;


    public PriorityManager(Map<String, Integer> priorityMap) {
        this.priorityMap = priorityMap;
    }

    public PriorityManager() {

    }

    /**
     * 通过key值获得priorityMap 中所对应的key值
     * @param key
     * @return
     */
    public Integer getPriority(String key) {
        if(priorityMap == null) {
            return DEFAULT_PRIORITY;
        }
        Integer returnInteger = priorityMap.get(key);

        if(returnInteger == null) {
            return DEFAULT_PRIORITY;
        }
        return returnInteger;

    }

    /**
     * 通过节点获得当前节点的优先值，
     * @param node 能够返回默认的优先级值，顺序是根据node的StreamFactory.NODE_NAME 信息，
     *             node的StreamFactory.PRIORITY_KEY 如果都没有则返回默认优先值
     * @return
     */
    public Integer getPriority(BaseNode node) {
        if(node == null) {
            return DEFAULT_PRIORITY;
        }
        String name = (String)node.getNodeInfo(StreamFactory.NODE_NAME);
        Integer priority = null;
        if(name != null && priorityMap != null){
            priority = priorityMap.get(name);
        }

        if(priority == null) {
            priority = (Integer) node.getNodeInfo(StreamFactory.PRIORITY_KEY);
        }

        if(priority == null) {
            priority = DEFAULT_PRIORITY;
        }
        return priority;
    }
}
