package com.burcumirza.HenryChess.Logic;

import java.util.*;

public class TreeNode {
    private Move value;
    private int level;
    private ArrayList<TreeNode> childList = new ArrayList<TreeNode>();

    public TreeNode(Move initValue, int level) {
        value = initValue;
        this.level = level;
    }

    public Move value() {
        return value;
    }

    public int level() {
        return level;
    }

    public int numChildren() {
        return childList.size();
    }

    public void setChild(TreeNode newNode) {
        childList.add(newNode);
    }

    public TreeNode child(int i) {
        return (TreeNode) childList.get(i);
    }

    public ArrayList<Move> children() {
        ArrayList<Move> valueList = new ArrayList<Move>();
        for (int i = 0; i < numChildren(); i++) {
            valueList.add(child(i).value());
        }
        return valueList;
    }

    public void removeChild(int i) {
        childList.remove(i);
    }

    public void removeChildren() {
        childList = null;
    }
}
