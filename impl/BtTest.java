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
        tree.setData(10);
        tree.setData(5);
        tree.setData(15);

        StringBuilder sb = new StringBuilder();
        tree.visitPreOrder(bt -> {
            if (bt != null) sb.append(bt.getData()).append(" ");
        });

        assertEquals("10 5 15 ", sb.toString());
    }

    @Test
    public void testVisitInOrder() {
        tree.setData(10);
        tree.setData(5);
        tree.setData(15);

        StringBuilder sb = new StringBuilder();
        tree.visitInOrder(bt -> {
            if (bt != null) sb.append(bt.getData()).append(" ");
        });

        assertEquals("5 10 15 ", sb.toString());
    }

    @Test
    public void testVisitPostOrder() {
        tree.setData(10);
        tree.setData(5);
        tree.setData(15);

        StringBuilder sb = new StringBuilder();
        tree.visitPostOrder(bt -> {
            if (bt != null) sb.append(bt.getData()).append(" ");
        });

        assertEquals("5 15 10 ", sb.toString());
    }
}
