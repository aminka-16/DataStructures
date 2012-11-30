package com.griddynamics.datastructures.heap;

public class Runner {
	public static void main(String[] args) {
		MaxHeap heap = new MaxHeap();
		heap.add(10);
		heap.add(2);
		heap.add(3);
		heap.add(4);
		heap.add(6);
		heap.add(9);

		System.out.println(heap.contains2(3));

		System.out.println(" ");
		for (int i : heap.getHeap()) {
			System.out.print(i + " ");
		}
	}
}
