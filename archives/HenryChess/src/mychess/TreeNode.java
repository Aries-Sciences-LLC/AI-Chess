package mychess;
import java.util.*;

public class TreeNode{
	private Object value;
	private int numChildren, level;
	private ArrayList childList = new ArrayList();

	public TreeNode (Object initValue, int level){
    	value = initValue;
    	this.level = level;
    }

	public Object value (){
    	return value;
	}
	
	public int level(){
		return level;
	}
	
	public int numChildren(){
		return childList.size();
	}
	
	public void setChild(TreeNode newNode){
		childList.add(newNode);
	}
	
	public TreeNode child(int i){
		return (TreeNode)childList.get(i);
	}
	
	public ArrayList children(){
		ArrayList valueList = new ArrayList();
		for (int i=0; i<numChildren(); i++){
			valueList.add(child(i).value());
		}
		return valueList;
	}
	
	public void removeChild(int i){
		childList.remove(i);
	}
	
	public void removeChildren(){
		childList = null;
	}
}

