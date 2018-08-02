import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.util.LinkedList;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
	
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	@Test
	public void testIsEmpty() {
		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		assertEquals(true, listDeque.isEmpty());
		listDeque.addFirst(1);
		assertEquals(false, listDeque.isEmpty());
		listDeque.removeFirst();
		assertEquals(true, listDeque.isEmpty());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExpectedFail() {
		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		listDeque.addLast(0);
		listDeque.addLast(1);
		listDeque.addLast(2);
		listDeque.addLast(3);
		listDeque.get(200);
	}

	@Test
	public void testGet() {
		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		listDeque.addLast(0);
		listDeque.addLast(1);
		listDeque.addLast(2);
		listDeque.addLast(3);
		listDeque.addLast(4);
		listDeque.addLast(5);
		assertEquals(0, listDeque.get(0).intValue());
		assertEquals(2, listDeque.get(2).intValue());
		assertEquals(5, listDeque.get(5).intValue());
	}


	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetRecursiveExpectedFail() {
		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		listDeque.addLast(0);
		listDeque.addLast(1);
		listDeque.addLast(2);
		listDeque.addLast(3);
		listDeque.getRecursive(200);
	}

	@Test
	public void testGetRecursive() {
		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		listDeque.addLast(0);
		listDeque.addLast(1);
		listDeque.addLast(2);
		listDeque.addLast(3);
		listDeque.addLast(4);
		listDeque.addLast(5);
		assertEquals(0, listDeque.getRecursive(0).intValue());
		assertEquals(2, listDeque.getRecursive(2).intValue());
		assertEquals(5, listDeque.getRecursive(5).intValue());
	}
	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		/*
		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();

		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);
		*/
	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
		/*
		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);
		*/
	}

	public static void main(String[] args) {
//		System.out.println("Running tests.\n");
//		addIsEmptySizeTest();
//		addRemoveTest();

		LinkedListDeque<Integer> listDeque = new LinkedListDeque<>();
		listDeque.addFirst(4);
        listDeque.addFirst(3);
        listDeque.addFirst(2);
        listDeque.addFirst(1);

//        listDeque.removeFirst();
        listDeque.removeLast();
		listDeque.removeLast();
		listDeque.removeLast();
		listDeque.removeLast();
		listDeque.removeLast();
		listDeque.removeLast();

//        listDeque.addLast(1);
//        listDeque.addLast(2);
//        listDeque.addLast(3);
//        listDeque.addLast(4);
//        listDeque.printDeque();
		System.out.println(listDeque);

	}
} 