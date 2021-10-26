package ch.zhaw.ads;

import java.util.*;

public class SortedBinaryTree<T extends Comparable<T>> implements Tree<T> {
	protected TreeNode<T> root;

	private TreeNode<T> insertAt(TreeNode<T> node, T x) {
		if (node == null) {
			return new TreeNode<T>(x);
		} else {
			if (x.compareTo(node.getValue()) <= 0) {
				node.left = insertAt(node.left, x);
			} else {
				node.right = insertAt(node.right, x);
			}
			return node;
		}
	}

	public void add(T x) {
		root = insertAt(root, x);
	}

	// find node to replace
	private TreeNode<T> findRepAt(TreeNode<T> node, TreeNode<T> rep) {
		if (node.right != null) {
			node.right = findRepAt(node.right,rep);
		} else {
			rep.values = node.values;
			node = node.left;
		}
		return node;
	}

	// remove node
	private TreeNode<T> removeAt(TreeNode<T> node, T x,TreeNode<T> removed ) {
		if (node == null) {
			return null;
		} else {
			if (x.compareTo(node.getValue()) == 0) {
				// found
				removed.values = node.values;
				if (node.left == null) {
					node = node.right;
				} else if (node.right == null) {
					node = node.left;
				} else {
					node.left = findRepAt(node.left,node);
				}
			} else if (x.compareTo(node.getValue()) < 0) {
				// search left
				node.left = removeAt(node.left, x, removed);
			} else {
				// search right
				node.right = removeAt(node.right, x, removed);
			}
			return node;
		}
	}

	public T remove(T x) {
		TreeNode<T> removed = new TreeNode<T>(null);
		root = removeAt(root, x, removed);
		return removed.getValue();
	}


	public boolean isEmpty() {
		return root == null;
	}

	public Traversal<T> traversal() {

		return new TreeTraversal<T>(root);
	}
	
	protected int calcHeight(TreeNode<T> node) {
		if (root==null) {
			return 0;
		} else {
			int leftHeight = 0;
			int rightHeight = 0;
			if(node.left!= null){
				leftHeight = calcHeight(node.left);
			}
			if(node.right != null){
				rightHeight = calcHeight(node.right);
			}
			int max = (leftHeight > rightHeight) ? leftHeight:rightHeight;
			return (max+1);
		}

	} 
  
	public int height() {

		return calcHeight(root);
	}
  
	protected int calcSize(TreeNode p) {
		if(p == null){
			return 0;
		}else {
			return(calcSize(p.left) + 1 + calcSize(p.right));
		}

	}
	
  	public int size() {

		return calcSize(root);
	}
		
  	public boolean balanced() {
  	    return balanced(root);

	}

	private boolean balanced(TreeNode<T> node) {

		if (node != null) {
			if (Math.abs(calcHeight(node.left) - calcHeight(node.right)) <= 1) {
				return balanced(node.left) && balanced(node.right);
			} else {
				return false;
			}
		}return true;
	}




	// only for testing and debugging: show the structure of the tree
	public String printTree() {
		StringBuilder out = new StringBuilder();
		if (root.right != null) {
			printTree(root.right,out, true, "");
		}
		out.append(root.values+"\n");
		if (root.left != null) {
			printTree(root.left,out, false, "");
		}
		return out.toString();
	}
  
	private void printTree(TreeNode node, StringBuilder out, boolean isRight, String indent) {
		if (node.right != null) {
			printTree(node.right, out, true,
					indent + (isRight ? "        " : " |      "));
		}
		out.append(indent);
		if (isRight) {
			out.append(" /");
		} else {
			out.append(" \\");
		}
		out.append("----- ");
		out.append(node.values+"\n");
		if (node.left != null) {
			printTree(node.left, out, false,
					indent + (isRight ? " |      " : "        "));
		}
	}

}
