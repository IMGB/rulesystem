package imgb.rulesystem.rulecontainer.data;

import imgb.rulesystem.node.list.UserForTest;
import org.junit.Before;
import org.junit.Test;
import imgb.rulesystem.node.list.NodeListImp;

import static org.junit.Assert.*;

public class ArrayDataTest {
    ArrayData arrayData = new ArrayData();
    @Before
    public void before(){
        UserForTest userForTest = new UserForTest("10");
        arrayData.insert(Long.valueOf(10),new NodeListImp(userForTest));
    }

    @Test
    public void testInsert() throws Exception {
        UserForTest userForTest = new UserForTest("12");
        arrayData.insert(Long.valueOf(12),new NodeListImp(userForTest));
        System.out.println(arrayData.search(Long.valueOf(12)));
        assertNotNull(arrayData.search(Long.valueOf(12)));
    }

    @Test
    public void testClear() throws Exception {
        arrayData.clear();
        System.out.println(arrayData);
        assertNull(arrayData.search(Long.valueOf(10)));
    }

    @Test
    public void testSearch() throws Exception {
        assertNotNull(arrayData.search(Long.valueOf(10)));
    }
}