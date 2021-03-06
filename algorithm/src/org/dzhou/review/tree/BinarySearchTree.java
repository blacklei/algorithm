package org.dzhou.review.tree;

public class BinarySearchTree<T extends Comparable<T>> {

	class Node {
		T object;
		Node left;
		Node right;

		public Node(T object) {
			this.object = object;
		}
	}

	Node root;

	public T searchNode(T object) {
		Node current = root;
		while (current != null) {
			int compare = current.object.compareTo(object);
			if (compare == 0)
				return current.object;
			else if (compare < 0)
				current = current.left;
			else
				current = current.right;
		}
		return null;
	}

	public void insert(T object) {
		if (root == null) {
			root = new Node(object);
			return;
		}
		Node current = root;
		Node parent = null;
		int compare = 0;
		while (current != null) {
			compare = current.object.compareTo(object);
			if (compare == 0)
				throw new IllegalArgumentException();
			parent = current;
			if (compare < 0)
				current = current.left;
			else
				current = current.right;
		}
		if (compare < 0)
			parent.left = new Node(object);
		else
			parent.right = new Node(object);
	}

	public void remove(T object) {
		if (root == null)
			throw new NullPointerException();
		Node current = root;
		Node parent = null;
		boolean isLeft = false;
		while (current != null) {
			int compare = current.object.compareTo(object);
			if (compare == 0)
				break;
			parent = current;
			if (compare < 0) {
				current = current.left;
				isLeft = true;
			} else {
				current = current.right;
				isLeft = false;
			}
		}
		if (current == null)
			throw new IllegalArgumentException();
		int childrenNumber = countChildren(current);
		if (parent != null && childrenNumber == 0) {
			removeLeaf(parent, isLeft);
		} else if (parent != null && childrenNumber == 1) {
			removeOneChildNode(parent, current, isLeft);
		} else {
			current.object = findAndRmoveBiggestChild(current, current.left);
		}
	}

	private int countChildren(Node current) {
		if (current.left == null && current.right == null)
			return 0;
		if (current.left != null && current.right != null)
			return 2;
		return 1;
	}

	private void removeLeaf(Node parent, boolean isLeft) {
		if (isLeft)
			parent.left = null;
		else
			parent.right = null;
	}

	private void removeOneChildNode(Node parent, Node current, boolean isLeft) {
		if (isLeft)
			parent.left = getOnlyChild(current);
		else
			parent.right = getOnlyChild(current);
	}

	private Node getOnlyChild(Node node) {
		return node.left != null ? node.left : node.right;
	}

	private T findAndRmoveBiggestChild(Node parent, Node node) {
		Node current = node;
		while (current.right != null) {
			parent = current;
			current = current.right;
		}
		if (current == node)
			parent.left = current.left;
		else
			parent.right = current.left;
		return current.object;
	}

}
