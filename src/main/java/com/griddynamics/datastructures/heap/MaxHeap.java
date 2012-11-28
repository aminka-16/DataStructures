package com.griddynamics.datastructures.heap;

import java.util.Arrays;

public class MaxHeap {

	private int[] array;

	private int currentIndex = 0;

	private static final int DEFAULT_CAPACITY = 16;

	private static final int INCREASE_CAPACITY_STEP = 2;

	public MaxHeap() {
		array = new int[DEFAULT_CAPACITY];
	}

	public MaxHeap(int size) {
		array = new int[size];
	}

	public boolean add(int element) {
		if (contains(element)) {
			return false;
		}
		ensureCapacity();
		array[currentIndex] = element;
		if (currentIndex != 0) {
			int parentIndex = getIndexOfParentElement(currentIndex);
			if (element > array[parentIndex]) {
				buildTreeFromTheBottom(element, parentIndex, currentIndex);
			}
		}
		currentIndex++;
		return true;
	}

	public boolean contains(int n) {
		if (array[0] < n) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == n)
				return true;
		}
		return false;
	}

	public boolean remove(int element) {
		int index = findIndexOfElement(element);
		if (index == -1) {
			return false;
		}
		currentIndex--;
		array[index] = array[currentIndex];
		array[currentIndex] = 0;
		buildTreeFromTheTop(index);
		return true;
	}

	int[] getHeap() {
		return Arrays.copyOfRange(array, 0, array.length);
	}


	
	private int getLeftChild(int parentIndex) {
		return parentIndex * 2 + 1;
	}

	private int getRightChild(int parentIndex) {
		return parentIndex * 2 + 2;
	}
	
//	private void buildTreeFromTheTop(int index) {
//	int leftChildIndex = getLeftChild(index);
//	int rightChildIndex = getRightChild(index);
//	while (leftChildIndex > array.length || rightChildIndex > array.length ||
//			array[index] > array[leftChildIndex] && array[index] > array[rightChildIndex]) {
//		leftChildIndex = getChildIndexes(index)[0];//TODO left & right
//		rightChildIndex = getChildIndexes(index)[1];
//		int childIndex;
////			if (array[leftChildIndex] < array[rightChildIndex]) {
////				childIndex = rightChildIndex;
////			} else {
////				childIndex = leftChildIndex;
////			}
//		childIndex =(array[leftChildIndex] < array[rightChildIndex])?rightChildIndex:leftChildIndex;
//		index = rearrangeElements(index, childIndex);
//	}
//}
	
	private void buildTreeFromTheTop(int index) {
		int leftChildIndex = getLeftChild(index);
		int rightChildIndex = getRightChild(index);
		while (!(leftChildIndex > array.length||rightChildIndex  > array.length||
				array[index] > array[leftChildIndex]&& array[index] > array[rightChildIndex])) {
			leftChildIndex = getLeftChild(index);
			rightChildIndex = getRightChild(index);
							int childIndex;
				if (array[leftChildIndex] < array[rightChildIndex]) {
					childIndex = rightChildIndex;
				} else {
					childIndex = leftChildIndex;
				}
				swap(index, childIndex);
				index = childIndex;
			
		}
	}

	
//	private void buildTreeFromTheTop(int index) {
//		while (true) {
//			int leftChildIndex = getLeftChild(index);//TODO left & right
//			int rightChildIndex = getRightChild(index);
//			if (leftChildIndex > array.length||rightChildIndex  > array.length||
//				array[index] > array[leftChildIndex]&& array[index] > array[rightChildIndex]) {
//				break;
//			} else {
//				int childIndex;
//				if (array[leftChildIndex] < array[rightChildIndex]) {
//					childIndex = rightChildIndex;
//				} else {
//					childIndex = leftChildIndex;
//				}
//				swap(index, childIndex);
//				index = childIndex;
//			}
//		}
//	}

	
	private void buildTreeFromTheBottom(int element, int parentIndex, int childIndex) {
		while (element > array[parentIndex]) {
			int temp = array[parentIndex];
			array[parentIndex] = element;
			array[childIndex] = temp;
			childIndex = parentIndex;
			parentIndex /= 2;
		}
	}

	private void swap(int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	private void ensureCapacity() {
		if (currentIndex >= array.length) {
			array = Arrays.copyOf(array, array.length * INCREASE_CAPACITY_STEP);
		}
	}

	private int[] getChildIndexes(int parentIndex) {
		int[] indexes = new int[2];
		indexes[0] = parentIndex * 2 + 1;
		indexes[1] = parentIndex * 2 + 2;
		return indexes;
	}

	private int getIndexOfParentElement(int childIndex) {
		if (childIndex % 2 == 0) {
			return childIndex / 2 - 1;
		} else {
			return childIndex / 2;
		}
	}

	// TODO: if the element wasn't found we shouldn't return 0
	private int findIndexOfElement(int element) {
		for (int i = 0; i < currentIndex; i++) {
			if (array[i] == element)
				return i;
		}
		return -1;
	}
}
