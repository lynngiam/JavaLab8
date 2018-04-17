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
	test.add("A");
	test.add("B");
	test.add("C");
	test.add("D");
	test.add("E");
	standard.add("A");
	standard.add("B");
	standard.add("C");
	standard.add("D");
	standard.add("E");
	assertEquals("Size: ", test.size(), standard.size());
	assertEquals("ToString: ", standard.toString(), test.toString());

    }

    @Test
    public void testRemove() {
	MyPriorityQueue<String> test = new MyPriorityQueue<String>(new StringComparator());
	PriorityQueue<String> standard = new PriorityQueue<String>(10, Collections.reverseOrder());
	test.add("A");
	test.add("B");
	test.add("C");
	test.add("D");
	test.add("E");
	standard.add("A");
	standard.add("B");
	standard.add("C");
	standard.add("D");
	standard.add("E");
	assertEquals("Size: ", test.size(), standard.size());
	for (int i = 0; i < test.size(); i++) {
	    System.out.println(test.remove());
	    // assertEquals("After add: ", standard.poll(), test.remove());
	}

    }

    @Test
    public void testIsEmpty() {
	fail("Not yet implemented");
    }

    @Test
    public void testSize() {
	fail("Not yet implemented");
    }

    @Test
    public void testClear() {
	fail("Not yet implemented");
    }

    @Test
    public void testToString() {
	fail("Not yet implemented");
    }

}
