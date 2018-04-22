import java.util.ArrayList;
import java.util.Comparator;

class MyPriorityQueue<E> implements PriorityQueueADT<E> {

    // you can use either of the following declarations for your heap
    ArrayList<E> heap;
    // E[] heap;

    Comparator<E> comparator;

    MyPriorityQueue(Comparator<E> comparator) {
	this.comparator = comparator;
	heap = new ArrayList<E>();
	// or
	// heap = (E[]) new Object[/* initial length */];
    }

    public boolean add(E item) { // do we need to add exception if item==null?
	// TODO: Write this method
	heap.add(item);
	siftUp(heap.size() - 1);
	return true;
    }

    public E remove() {
	// TODO: Write this method
	E rvalue = heap.get(0); // return value is the root which has the largest value
	heap.set(0, heap.get(size() - 1)); // set root item as rightmost node item
	heap.set(heap.size() - 1, rvalue); // set rightmost node item as root item
	heap.remove(heap.size() - 1); // remove the rightmost node; removing at root will destroy the heap structure
	// @@@@@ added if block here.
	if (heap.size() > 0) siftDown(0);
	return rvalue;
    }

    public boolean isEmpty() {
	// TODO: Write this method
	return heap.isEmpty();
    }

    public int size() {
	// TODO: Write this method
	return heap.size();
    }

    public void clear() {
	// TODO: Write this method
	heap.clear();
    }

    public String toString() {
	// TODO: Write this method
	return "" + heap.toString();
    }

    private void siftUp(int pos) {
	// TODO: Write this method
	E lowestNode = heap.get(pos);
	if (comparator.compare(lowestNode, heap.get(0)) != 0) { // if the lowest node is not the root
	    if (comparator.compare(lowestNode, heap.get(parent(pos))) > 0) { // if the lowest node item is larger than
									     // its parent
		heap.set(pos, heap.get(parent(pos)));
		heap.set(parent(pos), lowestNode);
		siftUp(parent(pos));
	    }
	}

    }

    private void siftDown(int pos) {
	// TODO: Write this method
	E root = heap.get(pos);
	if (pos < (heap.size() - 1) / 2) { // to prevent IndexOutOfBoundsException
	    if (heap.get(leftChild(pos)) != null && heap.get(rightChild(pos)) != null) { // if node has no children, it
											 // is a heap
		if (comparator.compare(root, heap.get(leftChild(pos))) < 0
			|| comparator.compare(root, heap.get(rightChild(pos))) < 0) {
		    if (comparator.compare(heap.get(leftChild(pos)), heap.get(rightChild(pos))) > 0) { // compare to get
												       // the bigger
												       // child
			heap.set(pos, heap.get(leftChild(pos))); // swap root item with left child item
			heap.set(leftChild(pos), root);// swap left child item with root item
			siftDown(leftChild(pos));
		    } else {
			heap.set(pos, heap.get(rightChild(pos))); // swap root item with right child item
			heap.set(rightChild(pos), root);// swap right child item with root item
			siftDown(rightChild(pos));
		    }
		}
	    }
	}
    }

    private int parent(int x) {
	// TODO: Write this method
	return (x - 1) / 2;
    }

    private int leftChild(int x) {
	// TODO: Write this method
	return (2 * x) + 1;
    }

    private int rightChild(int x) {
	// TODO: Write this method
	return (2 * x) + 2;
    }

}
