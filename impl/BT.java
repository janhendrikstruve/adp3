package de.hawhamburg.hamann.ad.trees.impl;

import de.hawhamburg.hamann.ad.trees.BinaryTree;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

public class BT<Data extends Comparable> implements BinaryTree<Data> {

    private Data data;
    private BinaryTree<Data> leftNode;
    private BinaryTree<Data> rightNode;

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
        visitor.accept(leftNode);
        visitor.accept(rightNode);
    }

    @Override
    public void visitInOrder(Consumer<BinaryTree<Data>> visitor) {
        visitor.accept(leftNode);
        visitor.accept(this);
        visitor.accept(rightNode);
    }

    @Override
    public void visitPostOrder(Consumer<BinaryTree<Data>> visitor) {
        visitor.accept(leftNode);
        visitor.accept(rightNode);
        visitor.accept(this);
    }
}
