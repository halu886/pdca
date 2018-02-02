package com.jxufe.halu.util;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    private  T parentT;
    private T t;
    private List<Tree> childNodes;

    /**
     *  @descript 树构建函数
     */
    public Tree(T t) {
        this.t = t;
        this.childNodes = new ArrayList<Tree>();
    }

    public T getParentT() {
        return parentT;
    }

    public void setParentT(T parentT) {
        this.parentT = parentT;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public List<Tree> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Tree> childNodes) {
        this.childNodes = childNodes;
    }

    public void addChild(Tree<T> child){
        this.getChildNodes().add(child);
    }

    public boolean removeChile(Tree<T> child){
        if(this.childNodes.contains(child)){
            return this.childNodes.remove(child);
        }
        return  false;
    }
}
