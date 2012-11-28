package com.griddynamics.datastructures.heap;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MaxHeapTest {
	
	private MaxHeap heap;

	@Before
	public void initHeap() {
		heap = new MaxHeap();
	}

	@Test
	public void testAddOneElement() {
		heap.add(1);
		assertEquals(heap.getHeap()[0], 1);
	}

	@Test
	public void testAddNegativeNumber() {
		heap.add(100);
		heap.add(-100);
		assertEquals(heap.getHeap()[1], -100);
	}

	@Test
	public void testAddSeveralElements() {
		int[] numbers = new int[] { 2, 6, 0 };
		int[] expectedHeap = new int[] { 6, 2, 0 };

		for (int i = 0; i < numbers.length; i++) {
			heap.add(numbers[i]);
		}

		for (int i = 0; i < expectedHeap.length; i++) {
			assertEquals(heap.getHeap()[i], expectedHeap[i]);
		}
	}

	@Test
	public void testEnsureHeapCapacity() {
		for (int i = 0; i < 20; i++) {
			heap.add(i);
		}
		assertEquals(heap.getHeap().length, 32);
	}

	@Test
	public void testContainsElement() {
		for (int i = 0; i < 15; i++) {
			heap.add(i);
		}
		for (int i = 0; i < 15; i++) {
			assertTrue(heap.contains(i));
		}
	}

	@Test
	public void testNotContainsElement() {
		for (int i = 0; i < 15; i++) {
			heap.add(i);
		}
		assertFalse(heap.contains(-25));
		assertFalse(heap.contains(-1));
		assertFalse(heap.contains(16));
	}

	@Test
	public void testAddEqualsElements() {
		assertTrue(heap.add(1));
		assertTrue(heap.add(23));
		assertTrue(heap.add(128));
		assertTrue(heap.add(-25));
		assertFalse(heap.add(128));
		assertFalse(heap.add(-25));
		assertFalse(heap.add(-25));
	}

	@Test
	public void testRemoveOneElement() {
		heap.add(10);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(6);

		heap.remove(6);
		int [] expectedHeap = { 10, 4, 3, 2 };
		assertArrayEquals(Arrays.copyOfRange(heap.getHeap(), 0, expectedHeap.length), expectedHeap);
	}

	@Test
	public void testRemoveHeadOfHeap() {
		heap.add(10);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(6);

		heap.remove(10);
		int [] expectedHeap = { 6, 4, 3, 2};
		assertArrayEquals(Arrays.copyOfRange(heap.getHeap(), 0, expectedHeap.length), expectedHeap);
	}

	@Test
	public void testRemoveAndAdd() {
		heap.add(10);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(6);

		heap.remove(10);

		heap.add(5);
		int [] expectedHeap = { 6, 5, 3, 2, 4};
		assertArrayEquals(Arrays.copyOfRange(heap.getHeap(), 0, expectedHeap.length), expectedHeap);
	}
	
	@Test
	public void testRemoveAndAddSeveralElements() {
		assertTrue(heap.add(10));
		assertTrue(heap.add(2));
		assertTrue(heap.add(3));
		assertTrue(heap.add(4));
		assertTrue(heap.add(6));
		assertTrue(heap.remove(10));
		assertTrue(heap.add(5));
		assertTrue(heap.remove(5));
		assertFalse(heap.remove(5));
		assertTrue(heap.add(5));
		assertTrue(heap.remove(5));
	}
	
	@Test
	public void testRemoveNotExistsElement() {
		heap.add(10);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(6);

        assertFalse(heap.remove(-100));
	}
	
	@Test
	public void testRemoveInBigHeap() {
		for (int i = 0; i < 40; i++) {
			heap.add(i);
		}
		for (int i = 5; i < 15; i++) {
			assertTrue(heap.remove(i));
		}
	}
}
