package imgb.rulesystem.rulecontainer.data;

import imgb.rulesystem.node.BaseNode;

import java.util.ArrayList;

/**
 * Created by ca on 10/20/14.<br>
 */
public class ArrayData extends NodesData<BaseNode> {
    private ArrayList<NodeContainer> array = new ArrayList<>();


    @Override
    public boolean insert(Long keyvalue, BaseNode node) {
        return array.add(new NodeContainer<>(node, keyvalue));
    }

    public boolean clear() {
        array = new ArrayList<>();
        return true;
    }

    @Override
    public BaseNode search(Long keyValue) {
        if (array.size() == 0) {
            return null;
        }

        if(keyValue < array.get(0).getKeyValue()) {
            return null;
        }

        int leftBound = 0;
        int rightBound = array.size();
        int index = getValue(leftBound, rightBound);
        while (rightBound - leftBound > 1) {
            long indexValue = array.get(index).getKeyValue();
            if (indexValue <= keyValue) {
                leftBound = index;
            } else {
                rightBound = index;
            }
            index = getValue(leftBound, rightBound);
        }
        return array.get(leftBound).getNode();
    }

    private int getValue(int leftBound, int rightBound) {
        if (rightBound >= leftBound) {
            return (rightBound + leftBound) / 2;
        }
        return 0;
    }
}
