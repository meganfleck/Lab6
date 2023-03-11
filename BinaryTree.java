import java.util.ArrayList;

/**
 * A class for BinaryTrees
 * 
 * @author Dylan Nguyen
 * @version 10 March 2023
 * 
 */
public class BinaryTree <E extends Comparable<E>>{
	
	protected Node<E> root;	
	protected boolean addReturn;
	protected E deleteReturn;
	private ArrayList<E> d = new ArrayList<E>();
	
	protected static class Node<E> {
		E data;
		Node <E> left;
		Node <E> right;
	
		// Node contructor
		public Node(E data) {
			this.data = data;
			left = null;
			right = null;			
		}
	}
	
	public BinaryTree() {
		root = null;
	}
	
	/*
	 * A recursive method to find a target Node in a Tree
	 * @author Dylan Nguyen
	 * Order: The order of the find method is O(n).
	 * The reason is because in the worst case as the size of the tree increases
	 * so does the amount of times it will go through the method recursively.
	 */
	public E find(E target) {
		return find(root,target);
	}
	
	private E find(Node<E> localRoot, E target) {
		if (localRoot == null) {
			return null;
		}		
		int compResult = target.compareTo(localRoot.data);
		if (compResult == 0) {
			return localRoot.data;
		}
		else if (compResult < 0) {
			return find(localRoot.left,target); 
		}
		else return find(localRoot.right,target);
	}
	
	/*
	 * A recursive method to add 
	 * @author Dylan Nguyen
	 * Order: The Order of the add method is O(n).
	 * The reason is because in the worst case scenario as the size of the tree increases
	 * so does the amount of recursions of the method to add at the bottom of the tree.
	 */
	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}
	
	private Node<E> add (Node<E> localRoot, E item) {
		
		if (localRoot == null) {
			addReturn = true;
			return new Node<E>(item);
		}
		else if (item.compareTo(localRoot.data) == 0) {
			addReturn = false; // 
			return localRoot; 
		}
		else if (item.compareTo(localRoot.data) < 0) {
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		}
		else {
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}	
	}
	
	/*
	 * A recursive method for deletion/removing
	 * @author Dylan Nguyen
	 * Order: The order of the delete method is O(n)
	 * The reason is because in the worst case scenario there are two children
	 * and you have to find the inorder predecessor by going through the
	 * findLargestChild method recursively n times depending on how many times.
	 */
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
		}
	
	private Node<E> delete(Node<E> localRoot, E item) {
		
		if (localRoot == null) {
			deleteReturn = null;
			return localRoot;
			}
		int compResult = item.compareTo(localRoot.data);
		if (compResult < 0) { 
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		}
		else if (compResult > 0) {
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}
		else {
			if (localRoot.left == null) {
				return localRoot.right;
				}
			else if (localRoot.right == null) {
				return localRoot.left;
				}
			else {
				if (localRoot.left.right == null) {
				localRoot.data = localRoot.left.data;
				localRoot.left = localRoot.left.left;
				return localRoot;
				}
		else {
			localRoot.data = findLargestChild(localRoot.left);
			return localRoot;
				}			
			}
		}
	}
	
	// Find the node that is the inorder predecessor
	// and replace it with its left child (if any).
	// returns: The data value from the ip
	private E findLargestChild(Node<E> parent) {
		if (parent.right.right == null) {
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		}
		else {
			return findLargestChild(parent.right);
		}
	}
	
	/*
	 * Method that gets the parent of the item.
	 * @author Dylan Nguyen
	 * Order: The order of this getParent method is O(1).
	 * The reason is because as the size of the tree increases it will still only
	 * return the parent.
	 */
	public E getParent(Node<E> item, E val, E parent) {
		if (item == null) {
			return null;
		}
		if (item.data == val) {
			System.out.println(parent);
		}
		else {
			getParent(item.left,val,item.data);
			getParent(item.right,val,item.data);
		}
		return parent;
	}
	
	/*
	 * Method that gets all descendants
	 * @author Dylan Nguyen
	 * Order: The order of this getAllDescendant method is O(n).
	 * The reason is because in the worst case scenario as the size of the tree increases
	 * so does the amount of recursions that occur.
	 */
	public ArrayList<E> getAllDescendant (E item) {
		return getAllDescendant(root,item);
		
	}

	private ArrayList<E> getAllDescendant(Node<E> localRoot, E item) {
		
		if (localRoot == null) {
			return null;
		}
		if (localRoot.left != null) {
			d.add((E) localRoot.left.data);
			getAllDescendant(localRoot.left,item);
		}
		if (localRoot.right != null) {
			d.add((E) localRoot.right.data);
			getAllDescendant(localRoot.right,item);
		}
	
		return d;
	}
	
	/*
	 * Method that prints the BinaryTree In-Order.
	 */
	public void inOrderPrint() {
		inOrderPrint(root);
	}
	
	private void inOrderPrint(Node<E> node) {
		if (node == null) return; // base case
			inOrderPrint(node.left); // left
			System.out.print(node.data); // root
			inOrderPrint(node.right); // right
		}

	public static void main(String[] args) {
		
		BinaryTree a = new BinaryTree();
		a.root = new Node(5);
		a.root.left = new Node(3);
		a.root.right = new Node(7);
		System.out.println("In-Order print of starting tree");
		a.inOrderPrint();
		System.out.println();
		System.out.print("In-Order print after adding 2,4,1,and 6");
		System.out.println();
		a.add(2);
		a.add(4);
		a.add(1);
		a.add(6);
		a.inOrderPrint();
		System.out.println();
		System.out.println("In-Order print after deleting 3");
		a.delete(3);
		a.inOrderPrint();
		System.out.println();
		System.out.println("Result of Find 4");
		System.out.println(a.find(4));
		
		BinaryTree b = new BinaryTree();
		b.add(2); //root of 2
		b.add(1);
		b.add(3);
		b.add(4);
		b.add(9);
		System.out.println("Result of getParent on 9");
		b.getParent(b.root, 9, -1);
		System.out.println("result of getAllDescendant of 2");
		System.out.println(b.getAllDescendant(2));

	}
}
