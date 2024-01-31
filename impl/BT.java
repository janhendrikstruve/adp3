package de.hawhamburg.hamann.ad.trees.impl;

import de.hawhamburg.hamann.ad.trees.BinaryTree;

import java.util.function.Consumer;

public class BT<Data extends Comparable<Data>> implements BinaryTree<Data> {

    private Data data;
    private BT<Data> leftNode;
    private BT<Data> rightNode;

    public BT(){
        data = null;
        leftNode=null;
        rightNode=null;
    }
    @Override
    public Data getData() {
        return data;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        printTree(this, sb, "", "");
        return sb.toString();
    }

    private void printTree(BT<Data> node, StringBuilder sb, String prefix, String childrenPrefix) {
        if(node != null) {
            sb.append(prefix);
            sb.append(node.data);
            sb.append('\n');
            if (node.leftNode != null)
                printTree(node.leftNode, sb, childrenPrefix + "├── ", childrenPrefix + "│   ");
            if (node.rightNode != null)
                printTree(node.rightNode, sb, childrenPrefix + "└── ", childrenPrefix + "    ");
        }
    }

    @Override
    public void setData(Data data) throws IllegalArgumentException {
        if(this.data==null){
            this.data=data;
        } else{
            if(this.data.compareTo(data)>0){
                if(leftNode==null){leftNode = new BT<Data>();}
                leftNode.setData(data);
            }
            else if(this.data.compareTo(data)<0)
            {
                if(rightNode==null){rightNode = new BT<Data>();}
                rightNode.setData(data);
            } else{
                throw new IllegalArgumentException("Bereits enthalten");
            }
        }
    }

    @Override
    public BinaryTree<Data> getLeftNode() {
        return leftNode;
    }

    @Override
    public BinaryTree<Data> getRightNode() {
        return rightNode;
    }

    @Override
    public boolean isLeaf() {
        return leftNode == null && rightNode == null;
    }

    @Override
    public void visitPreOrder(Consumer<BinaryTree<Data>> visitor) {
        visitor.accept(this);

        if (leftNode != null)
            leftNode.visitPreOrder(visitor);

        if (rightNode != null)
            rightNode.visitPreOrder(visitor);

    }

    @Override
    public void visitInOrder(Consumer<BinaryTree<Data>> visitor) {
        if (leftNode!= null)
            leftNode.visitInOrder(visitor);

        visitor.accept(this);

        if (rightNode!= null)
            rightNode.visitInOrder(visitor);
    }

    @Override
    public void visitPostOrder(Consumer<BinaryTree<Data>> visitor) {
        if (leftNode != null)
            visitor.accept(leftNode);
        if (rightNode != null)
            visitor.accept(rightNode);
        visitor.accept(this);
    }
}
