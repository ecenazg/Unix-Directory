//-----------------------------------------------------
// Title: UNIX class
// Author: Ecenaz Güngör
//-----------------------------------------------------


// Each folder has following 4 properties.
// Child name is not a good choice for this class
// It should be only name. parentName and name would be better. 

public class UNIX {
	private String parentName;
	private String childName;
	private int parentSize;
	private int childSize;
	
	
	public UNIX(String parentName, String childName, int parentSize, int childSize) {
		this.parentName = parentName;
		this.childName = childName;
		this.parentSize = parentSize;
		this.childSize = childSize;
	}


	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	public String getChildName() {
		return childName;
	}


	public void setChildName(String childName) {
		this.childName = childName;
	}


	public int getChildSize() {
		return childSize;
	}


	public void setChildSize(int childSize) {
		this.childSize = childSize;
	}


	public int getParentSize() {
		return parentSize;
	}


	public void setParentSize(int parentSize) {
		this.parentSize = parentSize;
	}
	
}
