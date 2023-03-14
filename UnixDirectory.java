//-----------------------------------------------------
// Title: Unix Directory class
// Author: Ecenaz Güngör
// Description: This class 
//-----------------------------------------------------


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class UnixDirectory {
	// Parent of all directories
	TreeNode root;
	
	// To calculate size of a directory, I declared a global variable
	int totalSize;
	
	// To print sub directories, I stored all them in this variable.
	// HW say, you should sort them according to their name, ascending alphabetically
	List<String> listOfSubDirectories;
	
	// HW say, to create first directory, arguments will be always "root"
	public UnixDirectory() {
		root = new TreeNode(new UNIX("", "root", 0, 0));
	}
	
	// Create Directory method signature
	public void CreateDirectory(String parentName, String childName, int 
			parentSize, int childSize) {
		UNIX newUnix = new UNIX(parentName, childName, parentSize, childSize);
		
		// I need to hold (store) root value
		TreeNode current = root;
		// Traverse over tree to add new directory
		CreateDirectory(newUnix, current);
	}
	
	// Helper function of create directory method. 
	// If input's parent name is equal to current nodes (child) name
	// Then add them to children list
	// Else control all children to find that a node whose name is same as input's name
	private void CreateDirectory(UNIX newUnix, TreeNode current) {
		if(current==null)
			return;
		if(newUnix.getParentName().equals(current.getCurrentNode().getChildName())) {
			current.getChildren().add(new TreeNode(newUnix));
			current.getCurrentNode().setParentSize(newUnix.getParentSize());
		} else {
			for(TreeNode node:current.getChildren()) {
				CreateDirectory(newUnix, node);
			}
		}
	}
	
	// Remove Directory method signature
	public void removeDirectory(String dirName) {
		// I need to hold (store) root value
		TreeNode current = root;
		removeDirectory(dirName, current);
	}
	
	// Traverse over all children of current and
	// if input dirName is same as one of the children name, remove it from list
	// Else continue this operation to all children 
	private void removeDirectory(String dirName, TreeNode current) {
		if(current==null)
			return;
		// Special case for root.
		// If user want to remove root, then assign base case of root
		if(current.getCurrentNode().getChildName().equalsIgnoreCase(dirName)
				&& current == root) {
			root = new TreeNode(new UNIX("", "root", 0, 0));
			return;
		}
		for(TreeNode node:current.getChildren()) {
			if(node.getCurrentNode().getChildName().equalsIgnoreCase(dirName)) {
				current.getChildren().remove(node);
				return;
			}
			removeDirectory(dirName, node);
		}
		
	}
	
	// Tree Traversal
	// Method signature of listDirectory
	// Collect all sub nodes and sort them.
	// Since end of the sample outputs are not comma
	// I put last element manually.
	public void listDirectory(String dirName) {
		listOfSubDirectories = new ArrayList<String>();
		
		// I need to hold (store) root value
		TreeNode current = root;
		listDirectory(current, dirName);
		
		// String sort
		Collections.sort(listOfSubDirectories);
		
		// Commas are between inputs
		for(int i=0;i<listOfSubDirectories.size()-1;i++) {
			System.out.print(listOfSubDirectories.get(i)+", ");
		}
		// Last element
		if(listOfSubDirectories.size()>0)
			System.out.println(listOfSubDirectories.get(listOfSubDirectories.size()-1));
	}
	
	
	private void listDirectory(TreeNode current, String dirName) {
		if(current == null)
			return;
		
		// Found the directory, add all subtrees to list
		if(dirName.equalsIgnoreCase(current.getCurrentNode().getChildName())) {
			addAllSubTrees(current);
			return;
		}
		
		// No found, try children
		for(TreeNode node:current.getChildren()) {
			listDirectory(node, dirName);
		}
		
	}
	 
	// Helper function, current is desired root
	// Add children to list
	private void addAllSubTrees(TreeNode current) {
		if(current==null) {
			return;
		}
		for(TreeNode node:current.getChildren()) {
			listOfSubDirectories.add(node.getCurrentNode().getChildName());
			
			//addAllSubTrees(node);
		}
	}
	
	
	// Method signature of listDirectorySize
	public void listDirectorySize(String dirName) {
		TreeNode current= root;
		listDirectorySize(current, dirName);
		
	}
	
	// Initially traverse over all tree and find dirName node
	private void listDirectorySize(TreeNode current, String dirName){  
		if(current == null)
			return;
		if(dirName.equalsIgnoreCase(current.getCurrentNode().getChildName())) {
			totalSize = 0;
			// Calculate subtrees size
			calculateSubTreeSize(current);
			System.out.println(totalSize);
		}
		
		// Check all children of current node
		for(TreeNode node:current.getChildren()) {
			listDirectorySize(node,dirName);		
		}
	}  
	
	// Current is desired node
	// Recursively calculate all sub trees
	private void calculateSubTreeSize(TreeNode current) {
		if(current == null)
			return;
		totalSize+=current.getCurrentNode().getChildSize();
		for(TreeNode node:current.getChildren()) {
			calculateSubTreeSize(node);
		}
	}

	// Driver method
	public static void main(String[] args) {

		UnixDirectory ud= new UnixDirectory();
		System.out.println("Enter Operation Name");
		Scanner scanner = new Scanner(System.in);
		String exit = "quit";
		String op = "";
		String line;
		String parentName;
		String childName; 
		int parentSize; 
		int childSize;
		String dirName;
		String[] inp = new String[5];
		while(!op.equalsIgnoreCase(exit)) {
			line = scanner.nextLine().trim();
			inp = line.split(" ");
			op=inp[0];
			if(op.equalsIgnoreCase("mkdir")) {
				parentName=inp[1];
				parentSize=Integer.parseInt(inp[2]);
				childName=inp[3];
				childSize=Integer.parseInt(inp[4]);
				if(parentName.equalsIgnoreCase("root")) {
					// Set roots size first
					ud.root.getCurrentNode().setChildSize(parentSize);
				}
				ud.CreateDirectory(parentName, childName, parentSize, childSize);
			}
			else if(op.equalsIgnoreCase("rmdir")) {
				dirName=inp[1];
				ud.removeDirectory(dirName);
			}
			else if(op.equalsIgnoreCase("ls")) {
				dirName=inp[1];
				ud.listDirectory(dirName);
			}
			else if(op.equalsIgnoreCase("sizels")) {
				dirName=inp[1];
				ud.listDirectorySize(dirName);
			}
		}
		scanner.close();
	}

}

