package VogelAlgorithms;


public class CreateBinarySearchTree {

	private TreeNode root;

	public CreateBinarySearchTree(){}

	public CreateBinarySearchTree(int[] a){
		this();
		for(int i:a)
			add(i);
	}

	private static class TreeNode{
		TreeNode left;
		int item;
		TreeNode right;
		TreeNode(TreeNode left, int item, TreeNode right){
			this.left = left;
			this.item = item;
			this.right = right;
		}
	}

	public void add(int item){
		if (root==null){
			root = new TreeNode(null, item, null);
			return;
		}
		TreeNode node = root;
		if (item < node.item) {
			if (node.left == null) {
				node.left = new TreeNode(null, item, null);
				
			}
			node = node.left;
		} else {
			if (node.right == null) {
				node.right = new TreeNode(null, item, null);
//				break;
			}
		}
	}
}
