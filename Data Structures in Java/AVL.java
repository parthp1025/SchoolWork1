import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * Parth Patel
 * CS 1332 Fall 2013
 * AVL Tree
 * 
 * In this class, you will program an AVL Tree (Adelson Veskii-Landis Tree).
 * This is like a better version of a binary search tree in that it 
 * tries to fill out every level of the tree as much as possible. It
 * accomplishes this by keeping track of each node's height and balance
 * factor. As you recurse back up from operations that modify the tree
 * (like add or remove), you will update the height and balance factor
 * of the current node, and perform a rotation on the current node if 
 * necessary. Keeping this in mind, let's get started!
 * 
 * **************************NOTE*************************************
 * please please please  treat null as positive infinity!!!!!!!!
 * PLEASE TREAT NULL AS POSITIVE INFINITY!!!!
 * *************************NOTE**************************************
 * 
 * I STRONLY RECOMMEND THAT YOU IMPLEMENT THIS DATA STRUCTURE RECURSIVELY!
 * 
 * Please make any new internal classes, instance data, and methods private!!
 * 
 * DO NOT CHANGE ANY OF THE PUBLIC METHOD HEADERS
 */
public class AVL<T extends Comparable<T>> {
	
	private AVLNode<T> root;
	private int size;
	
	/**
	 * I promise you, this is just like the add() method you coded
	 * in the BST part of the homework! You will start off at the
	 * root and find the proper place to add the data. As you 
	 * recurse back up the tree, you will have to update the
	 * heights and balance factors of each node that you visited
	 * while reaching the proper place to add your data. Immediately
	 * before you return out of each recursive step, you should update
	 * the height and balance factor of the current node and then
	 * call rotate on the current node. You will then return the node
	 * that comes from the rotate(). This way, the re-balanced subtrees
	 * will properly be added back to the whole tree. Also, don't forget
	 * to update the size of the tree as a whole.
	 * 
	 * PLEASE TREAT NULL AS POSITIVE INFINITY!!!!
	 * 
	 * @param data The data do be added to the tree.
	 */
	public void add(T data) {
        AVLNode<T> newNode = new AVLNode<T>(data);
        if(size == 0){
            root = newNode;
        }else if (data == null) {
            AVLNode<T> node = new AVLNode<T>(data);
            AVLNode<T> newnode = root;

            while (newnode.getRight() != null) {
                newnode = newnode.getRight();
            }
            newnode.setRight(node);

        }
        else if(root != null){
            secadd(root, data);
        }
        size++;
    }
    
    private AVLNode<T> secadd(AVLNode<T> node, T data) {
        if(data == null){
            throw new NullPointerException();
        }
        if (node == null) {
            return new AVLNode<T>(data);
        }else if(node.getData() == null){
            node.setLeft(secadd(node.getLeft(), data));
            updateHeightAndBF(node);
            rotate(node);
        }else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(secadd(node.getLeft(), data));
            updateHeightAndBF(node);
            rotate(node);
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(secadd(node.getRight(), data));
            updateHeightAndBF(node);
            rotate(node);
        }
        return node;
    }
	
	
	/**
	 * This is a pretty simple method. All you need to do is to get
	 * every element in the collection that is passed in into the tree.
	 * 
	 * Try to think about how you can combine a for-each loop and your
	 * add method to accomplish this.
	 * 
	 * @param c A collection of elements to be added to the tree.
	 */
	public void addAll(Collection<? extends T> c){
		for (Iterator<? extends T> t = c.iterator(); t.hasNext();) {
			add(t.next());
			size++;
		}
	}
	
	/**
	 * All right, now for the remove method. Just like in the vanilla BST, you
	 * will have to traverse to find the data the user is trying to remove. 
	 * 
	 * You will have three cases:
	 * 
	 * 1. Node to remove has zero children.
	 * 2. Node to remove has one child.
	 * 3. Node to remove has two children.
	 * 
	 * For the first case, you simply return null up the tree. For the second case,
	 * you return the non-null child up the tree. 
	 * 
	 * Just as in add, you'll have to updateHeightAndBF() as well as rotate() just before
	 * you return out of each recursive step.
	 * 
	 * FOR THE THIRD CASE USE THE PREDECESSOR OR YOU WILL LOSE POINTS
	 * 
	 * @param data The data to search in the tree.
	 * @return The data that was removed from the tree.
	 */
	public T remove(T data) {

		if( data == null) {
			AVLNode<T> newnode = root;
			AVLNode<T> node = root;
			while(newnode.getRight() != null){	
				node = newnode;
				newnode = newnode.getRight();
				
			}
			if(newnode.getData() == null) {
			node.setRight(newnode.getLeft());
			newnode = newnode.getLeft();
			updateHeightAndBF(newnode);
			rotate(newnode);
			newnode = root;
			
			while (newnode.getRight() != null){
				updateHeightAndBF(newnode);
				rotate(newnode);
				newnode = newnode.getRight();
			}
			size--;
			
			
		}
		return null;
	}
		AVLNode<T> newnode = root;
		AVLNode<T> gen = root;
		AVLNode<T> prev = root;
		
		while(gen != null){
			if(data.compareTo(gen.getData()) == 0){
				newnode = gen;
				
				if(newnode.getRight() == null && newnode.getLeft() == null){
					prev.setLeft(null);
					prev.setRight(null);
					gen = gen.getLeft();
					size--;
				}
				
				else if(newnode.getLeft() == null && newnode.getRight() != null){
					remove(newnode.getRight().getData());
					newnode.setData(newnode.getRight().getData());
					updateHeightAndBF(newnode);
					rotate(newnode);
				}
				
				else if(newnode.getLeft() != null && newnode.getRight() == null){
					while(newnode.getLeft().getRight() != null){
						newnode.setLeft(newnode.getRight());
					}
					
					remove(newnode.getLeft().getData());
					newnode.setData(newnode.getLeft().getData());
					updateHeightAndBF(newnode);
					rotate(newnode);
				}
			} else if (data.compareTo(gen.getData()) > 0){
				prev = gen;
				gen = gen.getRight();
			} else if (data.compareTo(gen.getData()) < 0){
				prev = gen;
				gen = gen.getLeft();
			}
	}
		return null;
	}
	

	
	/**
	 * This method should be pretty simple, all you have to do is recurse
	 * to the left or to the right and see if the tree contains the data.
	 * 
	 * @param data The data to search for in the tree.
	 * @return The boolean flag that indicates if the data was found in the tree or not.
	 */
	public boolean contains(T data) {
    	if (isEmpty()) {
			return false;
		}
		List<T> tree = inOrder();
		int treedata = tree.indexOf(data);
		return treedata >= 0;
    }
	
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

	private void inOrderhelper(AVLNode<T> node, List<T> inlist) {

		if (node == null) {
			return;
		}
		inOrderhelper(node.getLeft(), inlist);
		inlist.add(node.getData());
		inOrderhelper(node.getRight(), inlist);
	}
	/**
	 * Again, simply recurse through the tree and find the data that is passed in.
	 * 
	 * @param data The data to fetch from the tree.
	 * @return The data that the user wants from the tree. Return null if not found.
	 */
	public T get(T data) {
		AVLNode<T> newnode = root;
		AVLNode<T> enode = new AVLNode<T>(data);
		AVLNode<T> lnode = newnode.getLeft();
		AVLNode<T> rnode = newnode.getRight();

			
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
		
		private T gethelper(AVLNode<T> node, T data){
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
	 * Test to see if the tree is empty.
	 * 
	 * @return A boolean flag that is true if the tree is empty.
	 */
	public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
	
	/**
	 * Return the number of data in the tree.
	 * 
	 * @return The number of data in the tree.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Reset the tree to its original state. Get rid of every element in the tree.
	 */
	public void clear() {
        root = null;
        size = 0;
    }
	
	// The below methods are all private, so we will not be directly grading them,
	// however we strongly recommend you not change them, and make use of them.
	
	
	/**
	 * Use this method to update the height and balance factor for a node.
	 * 
	 * @param node The node whose height and balance factor need to be updated.
	 */
	private void updateHeightAndBF(AVLNode<T> node) {
		int orig = 0;
		int height = 0;
		int height2 = 0;
		if(node == null){
			return;
		}
		if(node.getLeft() != null){
			height = node.getLeft().getHeight();
		}
		if(node.getRight() != null){
			height2 = node.getRight().getHeight();
		}
		if(height > height2){
			orig = height + 1;
		} else if(height2 > height || height2 == height){
			orig = height2 + 1;
		}
		node.setBF(height - height2);
		node.setHeight(orig);
	}
	
	/**
	 * In this method, you will check the balance factor of the node that is passed in and
	 * decide whether or not to perform a rotation. If you need to perform a rotation, simply
	 * call the rotation and return the new root of the balanced subtree. If there is no need
	 * for a rotation, simply return the node that was passed in.
	 * 
	 * @param node - a potentially unbalanced node
	 * @return The new root of the balanced subtree.
	 */
	private AVLNode<T> rotate(AVLNode<T> node) {
		AVLNode<T> newnode = node;
		int bf = node.getBf();
		int rbf = node.getRight().getBf();
		int lbf = node.getLeft().getBf();
		if(bf == -2){
			if(rbf == 1){
				newnode = rightLeftRotate(node);
			} else if (rbf <= 0){
				newnode = leftRotate(node);
			}
			
		}
		else if(bf == 2){
			if(lbf == -1){
				newnode = leftRightRotate(node);
			} else if(lbf >= 0){
				newnode = rightRotate(node);
			}
		}
		return newnode;
	}
	
	/**
	 * In this method, you will perform a left rotation. Remember, you perform a 
	 * LEFT rotation when the sub-tree is RIGHT heavy. This moves more nodes over to
	 * the LEFT side of the node that is passed in so that the height differences
	 * between the LEFT and RIGHT subtrees differ by at most one.
	 * 
	 * HINT: DO NOT FORGET TO RE-CALCULATE THE HEIGHT OF THE NODES
	 * WHOSE CHILDREN HAVE CHANGED! YES, THIS DOES MAKE A DIFFERENCE!
	 * 
	 * @param node - the current root of the subtree to rotate.
	 * @return The new root of the subtree
	 */
	private AVLNode<T> leftRotate(AVLNode<T> node) {
		AVLNode<T> newnode = node.getLeft();
		node.setLeft(newnode.getRight());
		newnode.setRight(node);
		updateHeightAndBF(node);
		updateHeightAndBF(newnode);
		return newnode;
	}
	
	/**
	 * In this method, you will perform a right rotation. Remember, you perform a
	 * RIGHT rotation when the sub-tree is LEFT heavy. THis moves more nodes over to
	 * the RIGHT side of the node that is passed in so that the height differences
	 * between the LEFT and RIGHT subtrees differ by at most one.
	 * 
	 * HINT: DO NOT FORGET TO RE-CALCULATE THE HEIGHT OF THE NODES
	 * WHOSE CHILDREN HAVE CHANGED! YES, THIS DOES MAKE A DIFFERENCE!
	 * 
	 * @param node - The current root of the subtree to rotate.
	 * @return The new root of the rotated subtree.
	 */
	private AVLNode<T> rightRotate(AVLNode<T> node) {
		AVLNode<T> newnode = node.getRight();
		node.setRight(newnode.getLeft());
		newnode.setLeft(node);
		updateHeightAndBF(node);
		updateHeightAndBF(newnode);
		return newnode;
	}
	
	/**
	 * In this method, you will perform a left-right rotation. You can simply use
	 * the left and right rotation methods on the node and the node's child. Remember
	 * that you must perform the rotation on the node's child first, otherwise you will
	 * end up with a mangled tree (sad face). After rotating the child, remember to link up
	 * the new root of the that first rotation with the node that was passed in.
	 * 
	 * The whole point of heterogeneous rotations is to transform the node's 
	 * subtree into one of the cases handled by the left and right rotations.
	 * 
	 * @param node
	 * @return The new root of the subtree.
	 */
	private AVLNode<T> leftRightRotate(AVLNode<T> node) {
		AVLNode<T> right = null;
		AVLNode<T> left = node.getLeft();
		left = leftRotate(left);
		right = rightRotate(node);
		return right;
	}
	
	/**
	 * In this method, you will perform a right-left rotation. You can simply use your
	 * right and left rotation methods on the node and the node's child. Remember
	 * that you must perform the rotation on the node's child first, otherwise
	 * you will end up with a mangled tree (super sad face). After rotating the node's child,
	 * remember to link up the new root of that first rotation with the node that was
	 * passed in.
	 * 
	 * Again, the whole point of the heterogeneous rotations is to first transform the
	 * node's subtree into one of the cases handled by the left and right rotations.
	 * 
	 * @param node
	 * @return The new root of the subtree.
	 */
	private AVLNode<T> rightLeftRotate(AVLNode<T> node) {
		AVLNode<T> right = node.getRight();
		AVLNode<T> left = null;
		right = rightRotate(right);
		left = leftRotate(node);
		return left;
	}

}
