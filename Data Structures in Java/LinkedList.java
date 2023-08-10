package cs1338;

import java.util.Collection;
import java.util.Iterator;

/**
 * This is a circular, singly linked list.
 * @author Parth Patel(section a3)
 */
public class LinkedList<E> implements List<E> {

	protected Node<E> head;

	protected int size;

	@Override
	public void add(E e) {

		if (e != null) {

			Node<E> there = new Node<E>(e);
			Node<E> here = head;

			if (size == 0) {
				head = there;
				there.setNext(there);
			}

			else {
				there.setNext(head);

				for (int i = 0; i < size; i++) {

					if (i + 1 == size) {
						here.setNext(there);
					}
					here = here.getNext();
				}
			}

			size++;

		}

	}

	/*
	 * You will want to look at Iterator, Iterable, and how to use a for-each
	 * loop for this method.
	 */
	@Override
	public void addAll(Collection<? extends E> c) {
		for (Iterator<? extends E> t = c.iterator();t.hasNext();) {
			add(t.next());
		}

	}

	@Override
	public void clear() {
		head = null;
		size = 0;

	}

	@Override
	public boolean contains(Object o) {
		if (indexOf(o) != -1)
			return true;
		else
			return false;
	}

	@Override
	public E get(int index) {
		E gold;
		Node<E> t = head;

		if (isEmpty()) {
			return null;
		}

		else if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		else {
			for (int i = 0; i < index; i++) {
				t = t.getNext();
			}
			gold = t.getData();
		}
		return gold;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> tim = head;
		for (int i = 0; i < size; i++) {
			if (o.equals(tim.getData())) {
				return i;
			}
			tim = tim.getNext();
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if (size <= 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public E remove(int index) {
        Node<E> p = null;
        Node<E> t = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0 && size != 1) {
            for (int i = 1; i <= size; i++) {
                if (i == size) {
                    p = t.getNext();
                    t.setNext(t.getNext().getNext());
                    head = t;
                }
                t = t.getNext();

            }
            size--;

        } else if (size == 1) {
            p = head;
            clear();
        } else if (index != 0) {
            for (int i = 1; i <= index; i++) {
                if (i == index) {
                    p = t.getNext();
                    t.setNext(t.getNext().getNext());
                    
                }
                t = t.getNext();
            }
            size--;
        }

        return p.getData();
    }

	@Override
	public E remove(Object o) {
		int gold = indexOf(o);

		return remove(gold);
	}

	@Override
	public E set(int index, E e) {
		E gold = null;
		Node<E> t = head;
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (e == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i <= index; i++) {

			if (i == index) {
				gold = t.getData();
				t.setData(e);

			}
			t = t.getNext();
		}
		return gold;
	}

	@Override
	public int size() {

		return size;
	}

	/*
	 * The following methods are for grading. Do not modify them, and you do not
	 * need to use them.
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}
}
