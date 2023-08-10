import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * CS 1332 Fall 2013
 * Binary Search Tree
 * 
 * In this assignment, you will be coding methods to make a functional
 * binary search tree. If you do this right, you will save a lot of time
 * in the next two assignments (since they are just augmenting the BST to 
 * make it efficient). Let's get started!
 * 
 * **************************NOTE************************
 * YOU WILL HAVE TO HANDLE NULL DATA IN THIS ASSIGNMENT!!
 * PLEASE TREAT NULL AS POSITIVE INFINITY!!!!
 * **************************NOTE************************
 * 
 * DO NOT CHANGE ANY OF THE PUBLIC METHOD HEADERS
 * 
 * Please make any extra inner classes, instance fields, and methods private
 */
public class BST<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size;
	
	/**
	 * Add data to the binary search tree. Remember to adhere to the BST Invariant:
	 * All data to the left of a node must be smaller and all data to the right of
	 * a node must be larger. Don't forget to update the size. 
	 * 
	 * For this method, you will need to traverse the tree and find the appropriate
	 * location of the data. Depending on the data's value, you will either explore
	 * the right subtree or the left subtree. When you reach a dead end (you have 
	 * reached a null value), simply return a new node with the data that was passed
	 * in.
	 * 
	 * PLEASE TREAT NULL DATA AS POSITIVE INFINITY!!!!
	 * 
	 * @param data A comparable object to be added to the tree.
	 */
	public void add(T data) {
		if (size == 0) {
			root = new Node<T>(data);
		} else if (data == null) {
			Node<T> node = new Node<T>(data);
			Node<T> newnode = root;

			while (newnode.getRight() != null) {
				newnode = newnode.getRight();
			}
			newnode.setRight(node);

		} else if (root!= null){
			secadd(root, data);
		}
		size++;
	}
	private Node<T> secadd(Node<T> node, T data) {
		if(data == null){
			throw new NullPointerException();
		}
				if (node == null) {
					node = new Node<T>(data);
				}else if(node.getData() == null){
					node.setLeft(secadd(node.getLeft(), data));
				}else if (data.compareTo(node.getData()) < 0) {
					node.setLeft(secadd(node.getLeft(), data));
				} else if (data.compareTo(node.getData()) > 0) {
					node.setRight(secadd(node.getRight(), data));
				}
				return node;
			}

	
	/**
	 * Add the contents of the collection to the BST. To do this method, notice that
	 * most every collection in the java collections API implements the iterable 
	 * interface. This means that you can iterate through every element in these 
	 * structures with a for-each loop. Don't forget to update the size.
	 * 
	 * @param collection A collection of data to be added to the tree.
	 */
	public void addAll(Collection<? extends T> c) {
		for (Iterator<? extends T> t = c.iterator(); t.hasNext();) {
			add(t.next());
			size++;
		}

	}
	
	/**
	 * Remove the data element from the tree. Use the removeChild helper method.
	 *  
	 * PLEASE TREAT NULL DATA AS POSITIVE INFINITY!
	 *  
	 * @param data The data element to be searched for.
	 * @return retData The data that was removed from the tree. Return null if the
	 * data doesn't exist.
	 */
	public T remove(T data) {
		if (data == null) {
			Node<T> newnode = root;
			Node<T> previousNode = root;
		
			
			while(newnode.getRight() != null){
				previousNode = newnode;
				newnode = newnode.getRight();
			}
			previousNode.setRight(newnode.getLeft());
			size--;
			
		} else  {
		Node<T> daNode = root;

		Node<T> generic = root;

		Node<T> currentNode = root;

		while (currentNode != null) {

			if (data.compareTo(currentNode.getData()) < 0) {
				daNode = currentNode;
				currentNode = currentNode.getLeft();
			} else if (data.compareTo(currentNode.getData()) == 0) {
				generic = currentNode;
				Node<T> datNode = generic.getLeft();
				Node<T> demNode = generic.getRight();

				if (datNode == null && demNode == null) {
					daNode.setLeft(null);
					daNode.setRight(null);
					currentNode = currentNode.getLeft();
					size--;
				}

				else if (datNode != null) {

					Node<T> curr = datNode;
					while (curr.getRight() != null) {
						curr = curr.getRight();
					}

					T alt = curr.getData();
					remove(curr.getData());
					generic.setData(alt);

				}

				else if (datNode == null && demNode != null) {

					T alt2 = demNode.getData();
					remove(alt2);
					generic.setData(alt2);

				}
			} else if (data.compareTo(currentNode.getData()) > 0) {
				daNode = currentNode;
				currentNode = currentNode.getRight();
			}
		}
		}

		return null;
	}

	/**
	 * This is the hlper method we suggest using, you may code remove in other
	 * ways though.
	 * 
	 * There are three cases you have to deal with:
	 * 1. The node to remove has no children
	 * 2. The node to remove has one child
	 * 2. The node to remove has two children
	 * 
	 * In the first case, return null. In the second case, return the non-null 
	 * child. The third case is where things get interesting. Here, you have two 
	 * you will have to find the predecessor and then copy their data 
	 * into the node you want to remove. You will also have to fix the
	 * predecessor's children if necessary. Don't forget to update the size.
	 *
	 * @param Node<T> node The node to be removed
	 * @param Node<T> The new child node
	 */
	private Node<T> removeChild(Node<T> node) {
		//TODO 
		return null;
	}
	
	/**
	 * Get the data from the tree.
	 * 
	 * This method simply returns the data that was stored in the tree.
	 * 
	 * TREAT NULL DATA AS POSITIVE INFINITY!
	 * 
	 * @param data The datum to search for in the tree.
	 * @return The data that was found in the tree. Return null if the data doesn't
	 * exist.
	 */
	public T get(T data) {
	Node<T> newnode = root;
	Node<T> enode = new Node<T>(data);
	Node<T> lnode = newnode.getLeft();
	Node<T> rnode = newnode.getRight();

		
	if( enode != null && enode.getData() == newnode.getData()){	
		return newnode.getData();
	}
	if(enode.getData() == null){
		while(newnode.getRight() != null){
			newnode = newnode.getRight();
		}
			return newnode.getRight().getData();
			
		} else if(root != null){
			gethelper(root, data);
	}
	return data;
	}
	
	private T gethelper(Node<T> node, T data){
		if(data == null){
			throw new NullPointerException();
		}
		
		if(node == null){
			return null;
		} else if(node.getData() == null){
			gethelper(node.getLeft(), data);
		}else if (data.compareTo(node.getData()) < 0) {
			gethelper(node.getLeft(), data);
		}else if (data.compareTo(node.getData()) > 0) {
			gethelper(node.getRight(), data);
		}
		
		return node.getData();
	}
	
	/**
	 * See if the tree contains the data.
	 * 
	 * TREAT NULL DATA AS POSITIVE INFINITY!
	 * 
	 * @param data The data to search for in the tree.
	 * @return Return true if the data is in the tree, false otherwise.
	 */
	public boolean contains(T data) {
		if (isEmpty()) {
			return false;
		}
		List<T> tree = inOrder();
		int treedata = tree.indexOf(data);
		return treedata >= 0;
	}
	
	/**
	 * Linearize the tree using the pre-order traversal.
	 * 
	 * @return A list that contains every element in pre-order.
	 */
	public List<T> preOrder() {
		List<T> prelist = new ArrayList<T>();
		if (size == 0) {
			return prelist;
		}
		if (root != null) {
			preOrderhelper(root, prelist);
		}

		return prelist;
	}

	private void preOrderhelper(Node<T> node, List<T> prelist) {

		if (node == null) {
			return;
		}
		prelist.add(node.getData());

		preOrderhelper(node.getLeft(), prelist);

		preOrderhelper(node.getRight(), prelist);

	}
	
	
	/**
	 * Linearize the tree using the in-order traversal.
	 * 
	 * @return A list that contains every element in-order.
	 */
	public List<T> inOrder() {
		List<T> inlist = new ArrayList<T>();
		if (size == 0) {
			return inlist;
		}
		if (root != null) {
			inOrderhelper(root, inlist);
		}
		return inlist;
	}

	private void inOrderhelper(Node<T> node, List<T> inlist) {

		if (node == null) {
			return;
		}
		inOrderhelper(node.getLeft(), inlist);
		inlist.add(node.getData());
		inOrderhelper(node.getRight(), inlist);
	}
	
	/**
	 * Linearize the tree using the post-order traversal.
	 * 
	 * @return A list that contains every element in post-order.
	 */
	public List<T> postOrder() {
		List<T> postlist = new ArrayList<T>();

		if (size == 0) {
			return postlist;
		}
		if (root != null) {
			postorderhelper(root, postlist);
		}
		return postlist;
	}

	private void postorderhelper(Node<T> node, List<T> postlist) {
		if (node == null) {
			return;
		}

		postorderhelper(node.getLeft(), postlist);
		postorderhelper(node.getRight(), postlist);
		postlist.add(node.getData());

	}
	
	/**
	 * Test to see if the tree is empty.
	 * 
	 * @return Return true if the tree is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * 
	 * @return Return the number of elements in the tree.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Clear the tree. (ie. set root to null and size to 0)
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Clear the existing tree, and rebuilds a unique binary search tree 
	 * with the pre-order and post-order traversals that are passed in.
	 * Draw a tree out on paper and generate the appropriate traversals.
	 * See if you can manipulate these lists to generate the same tree.
	 * 
	 * TL;DR - at the end of this method, the tree better have the same
	 * pre-order and post-order as what was passed in.
	 * 
	 * @param preOrder A list containing the data in a pre-order linearization.
	 * @param postOrder A list containing the data in a post-order linearization.
	 */
	public void reconstruct(List<? extends T> preOrder, List<? extends T> postOrder) {
		if (preOrder == null) {
			return;
		}
		root = null;
		Collection<? extends T> c = preOrder;
		addAll(c);

	}
}
