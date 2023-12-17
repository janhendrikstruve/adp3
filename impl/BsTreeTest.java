package de.hawhamburg.hamann.ad.trees.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class BsTreeTest {

    private BSTree<Integer, String> tree;

    @BeforeEach
    public void setup() {
        tree = new BSTree<>();
    }

    @Test
    public void testInsertAndGet() {
        tree.insert(10, "Ten");
        assertEquals("Ten", tree.get(10), "Get should return the correct value.");
    }

    @Test
    public void testSize() {
        assertEquals(0, tree.size(), "Size of an empty tree should be 0.");
        tree.insert(11, "Ten");
        tree.insert(10, "Ten");

        assertEquals(2, tree.size(), "Size should reflect the number of nodes.");
    }

    @Test
    public void testContains() {
        tree.insert(10, "Ten");
        assertTrue(tree.contains(10), "Tree should contain the key.");
        assertFalse(tree.contains(20), "Tree should not contain a non-existent key.");
    }

    @Test
    public void testRemove() {
        tree.insert(10, "Ten");
        tree.insert(20, "Twenty");
        tree.remove(10);
        assertFalse(tree.contains(10), "Tree should not contain a removed key.");
        assertEquals(1, tree.size(), "Size should decrease after removal.");
    }

    @Test
    public void testRemoveNonExistentKey() {
        assertThrows(NoSuchElementException.class, () -> {
            tree.remove(30);
        }, "Removing a non-existent key should throw NoSuchElementException.");
    }

    @Test
    public void testGetNonExistentKey() {
        assertThrows(NoSuchElementException.class, () -> {
            tree.get(30);
        }, "Getting a non-existent key should throw NoSuchElementException.");
    }
    @Test
    public void testPathsize(){
        assertEquals(0,tree.pathLength(0));
        tree.insert(1,"1");
        tree.insert(2,"2");
        assertEquals(1,tree.pathLength(0));
    }
}
