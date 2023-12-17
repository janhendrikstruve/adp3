package de.hawhamburg.hamann.ad.trees.impl;

import de.hawhamburg.hamann.ad.trees.BinarySearchTree;
import de.hawhamburg.hamann.ad.trees.BinaryTree;

import java.util.NoSuchElementException;

public class BSTree<K extends Comparable<K>, E> implements BinarySearchTree<K, E> {

    private K data;
    private BSTree<K> leftNode;
    private BSTree<K> rightNode;

    public BSTree() {
        data = null;
        leftNode = null;
        rightNode = null;
    }

    @Override
    public void insert(K k, E e) {
        if (this.data == null) {
            this.data = k;
        } else {
            if (this.data.compareTo(data) > 0) {
                if (leftNode == null) {
                    leftNode = new BSTree<K, E>();
                }
                leftNode.insert(k, leftNode);
            } else if (this.data.compareTo(data) < 0) {
                if (rightNode == null) {
                    rightNode = new BSTree<K, E>();
                }
                rightNode.insert(k, rightNode);
            } else {
                throw new IllegalArgumentException("Bereits enthalten");
            }
        }
    }

    @Override
    public void remove(K k) throws NoSuchElementException {
        if (this.data.compareTo(k) > 0 && leftNode != null) {
            leftNode.remove(k);
        } else if (this.data.compareTo(data) < 0 && rightNode != null) {
            rightNode.remove(k);
        } else if (this.data.compareTo(k) == 0) {

            if (leftNode != null && rightNode != null) {
                if (data.compareTo((K) leftNode.getData()) < 0) {
                    this.data = (K) leftNode.getData();
                    leftNode = leftNode.getLeftNode();
                    rightNode = leftNode.getRightNode();
                } else {
                    this.data = (K) rightNode.getData();
                    leftNode = rightNode.getLeftNode();
                    rightNode = rightNode.getRightNode();
                }
            } else if (leftNode != null) {
                this.data = (K) leftNode.getData();
                leftNode = leftNode.getLeftNode();
                rightNode = leftNode.getRightNode();
            } else if (rightNode != null) {
                this.data = (K) rightNode.getData();
                leftNode = rightNode.getLeftNode();
                rightNode = rightNode.getRightNode();
            } else {
                throw new NoSuchElementException("No such element");
            }
        }
    }

    @Override
    public E get(K k) throws NoSuchElementException {
        if (data.compareTo(k) == 0) {
            return (E) data;
        }
        if (data.compareTo(k) < 0 && leftNode != null) {
            return (E) leftNode.get(k);
        }
        if (data.compareTo(k) > 0 && rightNode != null) {
            return (E) rightNode.get(k);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        if (leftNode == null && rightNode == null) {
            return 0;
        }
        int i = 0;

        if (leftNode != null) {
            i += 1 + leftNode.size();
        }
        ;
        if (rightNode != null) {
            i += 1 + rightNode.size();
        }
        ;

        return i;
    }

    @Override
    public boolean contains(K k) {
        if (data.compareTo(k) == 0) {
            return true;
        }
        if (data.compareTo(k) < 0 && leftNode != null) {
            return leftNode.contains(k);
        }
        if (data.compareTo(k) > 0 && rightNode != null) {
            return rightNode.contains(k);
        }
        return false;
    }

    public K getData() {
        return data;
    }

    public BSTree<K> getLeftNode() {
        return leftNode;
    }

    public BSTree<K> getRightNode() {
        return rightNode;
    }
}
