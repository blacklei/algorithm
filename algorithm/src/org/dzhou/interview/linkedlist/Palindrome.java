package org.dzhou.interview.linkedlist;

import java.util.Stack;

/**
 * Practice of "cracking the code interview"
 * 
 * Implement a function to check if a linked list is a palindrome.
 * 
 * Like 0 -> 1 -> 2 -> 1 -> 0
 * 
 * @author DONG ZHOU
 *
 */
public class Palindrome {

	static class HeadAndTail {
		Node head;
		Node tail;

		public HeadAndTail(Node node) {
			this(node, node);
		}

		public HeadAndTail(Node head, Node tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	static HeadAndTail reverse(Node node) {
		if (node.next == null)
			return new HeadAndTail(node);
		HeadAndTail result = reverse(node.next);
		result.tail.next = node;
		result.tail = node;
		return result;
	}

	public static Node getReverse(Node node) {
		HeadAndTail headAndTail = reverse(node);
		headAndTail.tail.next = null;
		return headAndTail.head;
	}

	public static boolean isEqual(Node first, Node second) {
		if (first == null || second == null)
			return false;
		while (first != null && second != null) {
			if (first.data != second.data)
				return false;
			first = first.next;
			second = second.next;
		}
		return true;
	}

	public static boolean isPalindrome(Node node) {
		Node reverse = getReverse(node);
		return isEqual(node, reverse);
	}

	public static boolean isPalindrome2(Node node) {
		Stack<Integer> stack = new Stack<>();
		Node fast = node;
		Node slow = node;
		while (fast != null && fast.next != null) {
			stack.add(slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		if (fast != null)
			slow = slow.next;
		while (slow != null) {
			int top = stack.pop().intValue();
			if (top != slow.data)
				return false;
			slow = slow.next;
		}
		return true;
	}

	public static boolean isPalindrome3(Node node) {
		int length = lengthOfList(node);
		return isPalindromeRecurse(node, length).result;
	}

	static class ResultOfReverse {
		public Node node;
		public boolean result;

		public ResultOfReverse(Node node, boolean result) {
			this.node = node;
			this.result = result;
		}
	}

	static ResultOfReverse isPalindromeRecurse(Node head, int length) {
		if (head == null || length == 0)
			return new ResultOfReverse(head, true);
		else if (length == 1)
			return new ResultOfReverse(head.next, true);
		ResultOfReverse result = isPalindromeRecurse(head.next, length - 2);
		if (result.result == false || result.node == null)
			return result;
		result.result = (result.node.data == head.data);
		result.node = result.node.next;
		return result;
	}

	static int lengthOfList(Node node) {
		int length = 0;
		while (node != null) {
			length++;
			node = node.next;
		}
		return length;
	}

}
