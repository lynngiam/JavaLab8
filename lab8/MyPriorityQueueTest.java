import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Test;

public class MyPriorityQueueTest {

    @Test
    public void testMyPriorityQueue() {
	fail("Not yet implemented");
    }

    @Test
    public void testAdd() {
	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
	PriorityQueue<String> standard = new PriorityQueue<String>(10, Collections.reverseOrder());
	test.add("C");
	test.add("B");
	test.add("A");
	test.add("D");
	test.add("E");
	standard.add("C");
	standard.add("B");
	standard.add("A");
	standard.add("D");
	standard.add("E");
	assertEquals("Size: ", test.size(), standard.size());
	assertEquals("ToString: ", standard.toString(), test.toString());
	
	
    }

    @Test
    public void testRemove() {
	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
	PriorityQueue<String> standard = new PriorityQueue<String>(10, Collections.reverseOrder());
	test.add("C");
	test.add("B");
	test.add("A");
	test.add("D");
	test.add("E");
	standard.add("C");
	standard.add("B");
	standard.add("A");
	standard.add("D");
	standard.add("E");
	while (test.size() != 0) {
		System.out.print(test.size() + " ");
		System.out.print(test.remove() + " ");
		System.out.println();
	}

    }

    @Test
    public void testIsEmpty() {
    	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
    	test.add("C");
    	test.add("B");
    	test.add("A");
    	test.add("D");
    	test.add("E");
    	assertFalse("Priority queue is empty!", test.isEmpty());
    	test.clear();
    	assertTrue("Priority queue is not empty!", test.isEmpty());
    }

    @Test
    public void testSize() {
    	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
    	PriorityQueue<String> standard = new PriorityQueue<String>(10, Collections.reverseOrder());
    	test.add("C");
    	test.add("B");
    	test.add("A");
    	test.add("D");
    	test.add("E");
    	standard.add("C");
    	standard.add("B");
    	standard.add("A");
    	standard.add("D");
    	standard.add("E");
    	assertEquals("The size of the priority queue is: ", standard.size(), test.size());

    }

    @Test
    public void testClear() {
	fail("Not yet implemented");
    }

    @Test
    public void testToString() {
    	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
    	PriorityQueue<String> standard = new PriorityQueue<String>(10, Collections.reverseOrder());
    	test.add("C");
    	test.add("B");
    	test.add("A");
    	test.add("D");
    	test.add("E");
    	standard.add("C");
    	standard.add("B");
    	standard.add("A");
    	standard.add("D");
    	standard.add("E");
    	assertEquals("The size of the priority queue is: ", standard.toString(), test.toString());
    }

}
