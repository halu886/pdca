package com.jxufe.halu.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxufe.halu.model.IBaseBean;
import com.jxufe.halu.model.Project;
import java.util.ArrayList;
import java.util.List;

public class Tree < T extends IBaseBean>{
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

    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("text",this.getT().getName());
        JSONArray jsonArray = new JSONArray();
        for(Tree tree:this.getChildNodes()){
            jsonArray.add(tree.toJson());
        }
        object.put("nodes",jsonArray);
        object.put("t",this.getT());
        return  object;
    }
}
