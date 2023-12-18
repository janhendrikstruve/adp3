package de.hawhamburg.hamann.ad.trees.impl;

import de.hawhamburg.hamann.ad.trees.BinarySearchTree;

import java.util.NoSuchElementException;

public class BSTree<K extends Comparable<K>, E> implements BinarySearchTree<K, E> {

    private K key;
    private E data;
    private BSTree<K, E> leftNode;
    private BSTree<K, E> rightNode;

    public BSTree() {
        key = null;
        leftNode = null;
        rightNode = null;
        data= null;
    }

    @Override
    public void insert(K k, E e) {
        if (this.key == null) {
            this.key = k;
            this.data = e;
        } else {
            if (this.key.compareTo(k) > 0) {
                if (leftNode == null) {
                    leftNode = new BSTree<K, E>();
                }
                leftNode.insert(k, e);
            } else if (this.key.compareTo(k) < 0) {
                if (rightNode == null) {
                    rightNode = new BSTree<K, E>();
                }
                rightNode.insert(k, e);
            } else {
                throw new IllegalArgumentException("Bereits enthalten");
            }
        }
    }

    @Override
    public void remove(K k) throws NoSuchElementException {

        if (leftNode != null && rightNode != null) {
            K successorVal = findMin(rightNode);
            this.key = successorVal;
            rightNode.remove(successorVal);
        } else if (leftNode != null) {
            this.key = leftNode.getKey();
            leftNode = leftNode.getLeftNode();
            rightNode = leftNode.getRightNode();
        } else if (rightNode != null) {
            this.key = rightNode.getKey();
            leftNode = rightNode.getLeftNode();
            rightNode = rightNode.getRightNode();
        } else if (key != null) {
            if (this.key.compareTo(k) == 0) {
                key = null;

        }
        } else throw new NoSuchElementException("No such element");
    }


    @Override
    public E get(K k) throws NoSuchElementException {
        if(key == null){throw new NoSuchElementException("no such element");}
        if (key.compareTo(k) < 0 && leftNode != null) {
            return  leftNode.get(k);
        }
        if (key.compareTo(k) > 0 && rightNode != null) {
            return  rightNode.get(k);
        }
        else {return data;}
    }

    public int pathLength(int length){
        if (leftNode == null && rightNode == null) {
            return length;
        }
        int i = length;
        if (leftNode != null) {
            i += leftNode.pathLength(length+1);
        }

        if (rightNode != null) {
            i += rightNode.pathLength(length+1);
        }


        return i;
    }

    @Override
    public int size() {
        if (leftNode == null && rightNode == null) {
           if(data != null){return 1;}
            return 0;
        }
        int i = 0;

        if (leftNode != null) {
            i += 1 + leftNode.size();
        }

        if (rightNode != null) {
            i += 1 + rightNode.size();
        }


        return i;
    }

    @Override
    public boolean contains(K k) {
        if (key.compareTo(k) == 0) {
            return true;
        }
        if (key.compareTo(k) < 0 && leftNode != null) {
            return leftNode.contains(k);
        }
        if (key.compareTo(k) > 0 && rightNode != null) {
            return rightNode.contains(k);
        }
        return false;
    }

    public int averagePathLength () {
        return (this.pathLength(0) / this.size()) + 1;
    }


    public K getKey() {
        return key;
    }

    public BSTree<K, E> getLeftNode() {
        return leftNode;
    }

    public BSTree<K, E> getRightNode() {
        return rightNode;
    }

    private K findMin(BSTree<K, E> node) {
        K minv = node.key;
        while (node.leftNode != null) {
            minv = node.leftNode.key;
            node = node.leftNode;
        }
        return minv;
    }
}
