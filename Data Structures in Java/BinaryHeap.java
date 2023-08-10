import java.util.Comparator;
/**
 * @author Parth Patel
 * 
 * 
 */
/**
 * This is an implementation of a heap that is backed by an array.
 * 
 * This implementation will accept a comparator object that can be used to
 * define an ordering of the items contained in this heap, other than the
 * objects' default compareTo method (if they are comparable). This is useful if
 * you wanted to sort strings by their length rather than their lexicographic
 * ordering. That's just one example.
 * 
 * Null should be treated as positive infinity if no comparator is provided. If
 * a comparator is provided, you should let it handle nulls, which means it
 * could possibly throw a NullPointerException, which in this case would be
 * fine.
 * 
 * If a comparator is provided that should always be what you use to compare
 * objects. If no comparator is provided you may assume the objects are
 * Comparable and cast them to type Comparable<T> for comparisons. If they
 * happen to not be Comparable you do not need to handle anything, and you can
 * just let your cast throw a ClassCastException.
 * 
 * This is a minimum heap, so the smallest item should always be at the root.
 * 
 * @param <T>
 *            The type of objects in this heap
 */
public class BinaryHeap<T> implements Heap<T> {

	/**
	 * The comparator that should be used to order the elements in this heap
	 */
	private Comparator<T> comp;

	/**
	 * The backing array of this heap
	 */
	private T[] data;

	/**
	 * The number of elements that have been added to this heap, this is NOT the
	 * same as data.length
	 */
	private int size;

	/**
	 * Default constructor, this should initialize data to a default size (11 is
	 * normally a good choice)
	 * 
	 * This assumes that the generic objects are Comparable, you will need to
	 * cast them when comparing since there are no bounds on the generic
	 * parameter
	 */
	public BinaryHeap() {
		size = 0;
	    data = (T[]) new Comparable[11];
	    this.comp = new Comparator<T>() {
            public int compare(T a, T b) {
                if (a == null && b == null) {
                    return 0;
                }
                else if (a == null) {
                    return 1;
                } else if (b == null) {
                    return -1;

                } else {
                    return ((Comparable) a).compareTo((Comparable) b);
                }
            }
        };
	}

	/**
	 * Constructor that accepts a comparator to use with this heap. Also
	 * initializes data to a default size.
	 * 
	 * When a comparator is provided it should be preferred over the objects'
	 * compareTo method
	 * 
	 * If the comparator given is null you should attempt to cast the objects to
	 * Comparable as if a comparator were not given
	 * 
	 * @param comp
	 */
	
	public BinaryHeap(Comparator<T> comp) {
		if (comp == null) {
            this.comp = new Comparator<T>() {
                public int compare(T a, T b) {
                    if (a == null && b == null) {
                        return 0;
                    }
                    else if (a == null) {
                        return 1;
                    } else if (b == null) {
                        return -1;

                    } else {
                        return ((Comparable) a).compareTo((Comparable) b);
                    }
                }
            };
        } else {
            this.comp = comp;
        }
        this.data = (T[]) new Comparable[11];
        this.size = 0;
    }
	
	private void growArray(){
		T[] garray;
		garray = (T[]) new Comparable[data.length*2];
		for(int i = 0; i < data.length; i++){
			garray[i] = data[i];
		}
		data = garray;	
	}
	
	private void swap(int a,int b) {
		T temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
	@Override
	public void add(T item) {
		
		if(size+1 == data.length){
			growArray();
		}
		size++;
		int temp = size;
		data[temp] = item;
		addhelper();      
		      
		 }
	// To percolate up
		private void addhelper(){
			int i = size;
			while(i>1 && (comp.compare(data[i/2], data[i]) > 0)){
				swap(i , i / 2);
				i = i / 2;
			}
		}
		 
	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public T peek() {
		return(data[1]);
		
	}

	@Override
	public T remove() {
		T temp = peek();
		data[1] = data[size];
		data[size] = null;
		size--;
		removehelper();
		return temp;
  }
	// To percolate down
	private void removehelper(){
		int i = 1;
		while(i*2 <= size){
			int child = 2 * i;
			if((((i*2) + 1) <= size) && comp.compare(data[2*i], data[((2*i)+1)]) > 0){
				child = ((2*i)+1);	
			}
			if(comp.compare(data[i], data[child]) > 0){
				swap(i, child);
			}else{
				//do nothing
			}
			i = child;
		}
	}
	

	@Override
	public int size() {
		
		return size;
	}
	
	// method to pass the array to test
		// aka useless in here
		public T[] getArray() {

			return data;

			}
}







