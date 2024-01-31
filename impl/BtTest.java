package de.hawhamburg.hamann.ad.trees.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BtTest {

    private BT<Integer> tree;

    @Before
    public void setUp() {
        tree = new BT<>();
    }

    @Test
    public void testInsertAndRetrieveData() {
        tree.setData(10);
        assertEquals(Integer.valueOf(10), tree.getData());

        tree.setData(5);
        assertEquals(Integer.valueOf(5), tree.getLeftNode().getData());

        tree.setData(15);
        assertEquals(Integer.valueOf(15), tree.getRightNode().getData());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicateThrowsException() {
        tree.setData(10);
        tree.setData(10); // Dies sollte eine IllegalArgumentException auslösen
    }

    @Test
    public void testIsLeaf() {
        tree.setData(10);
        assertTrue(tree.isLeaf());

        tree.setData(5);
        assertFalse(tree.isLeaf());
    }

    @Test
    public void testVisitPreOrder() {
        tree.setData(1);
        tree.setData(2);
        tree.setData(3);
        tree.setData(4);
        tree.setData(5);
        tree.setData(6);
        tree.setData(7);
        tree.setData(8);
        tree.setData(9);
        tree.setData(10);

        assertEquals("1\n" +
                "└── 2\n" +
                "    └── 3\n" +
                "        └── 4\n" +
                "            └── 5\n" +
                "                └── 6\n" +
                "                    └── 7\n" +
                "                        └── 8\n" +
                "                            └── 9\n" +
                "                                └── 10\n",tree.toString());
    }

    @Test
    public void testVisitInOrder() {
        tree.setData(5);
        tree.setData(2);
        tree.setData(11);
        tree.setData(4);
        tree.setData(23);
        tree.setData(6);
        tree.setData(7);
        tree.setData(8);
        tree.setData(9);
        tree.setData(10);


        assertEquals("5\n" +
                                "├── 2\n" +
                                "│   └── 4\n" +
                                "└── 11\n" +
                                "    ├── 6\n" +
                                "    │   └── 7\n" +
                                "    │       └── 8\n" +
                                "    │           └── 9\n" +
                                "    │               └── 10\n" +
                                "    └── 23\n",tree.toString());
    }

    @Test
    public void testVisitPostOrder() {
        tree.setData(5);
        tree.setData(2);
        tree.setData(11);
        tree.setData(4);
        tree.setData(23);
        tree.setData(6);
        tree.setData(7);
        tree.setData(8);
        tree.setData(9);
        tree.setData(10);


        assertEquals("5\n" +
                "├── 2\n" +
                "│   └── 4\n" +
                "└── 11\n" +
                "    ├── 6\n" +
                "    │   └── 7\n" +
                "    │       └── 8\n" +
                "    │           └── 9\n" +
                "    │               └── 10\n" +
                "    └── 23\n",tree.toString());
    }
}
