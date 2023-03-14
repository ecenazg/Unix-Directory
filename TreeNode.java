//-----------------------------------------------------
// Title: Tree Node class
// Author: Ecenaz Güngör
//-----------------------------------------------------


import java.util.LinkedList;
import java.util.List;

// The file structure does not have to be a binary tree
// Each Parent file may have more than 2 children.
// I used List to store children.
// Removing and adding operation in LinkedList are more efficient than ArrayList
public class TreeNode {
	private UNIX currentNode;
	// 1 to many relation. Each parent may have lots of children
	private List<TreeNode> children;

	public TreeNode(UNIX currentNode) {
		this.currentNode = currentNode;
		this.children = new LinkedList<TreeNode>();
	}

	public UNIX getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(UNIX currentNode) {
		this.currentNode = currentNode;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}



