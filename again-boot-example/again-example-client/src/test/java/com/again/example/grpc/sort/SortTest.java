package com.again.example.grpc.sort;

import java.util.Arrays;

/**
 * @author create by 罗英杰 on 2021/9/2
 * @description:
 */
public class SortTest {

	public static void main(String[] args) {
		SelectSort2 selectSort = new SelectSort2();
		int[] items = { 3, 7, 9, 1, 6, 0 };
		int[] integers = selectSort.selectSort(items);
		System.out.println(Arrays.toString(integers));
	}

}

class SelectSort {

	public int[] selectSort(int[] items) {
		for (int i = 0; i < items.length; i++) {
			int min;
			for (int j = i + 1; j < items.length; j++) {
				if (items[i] > items[j]) {
					min = items[j];
					items[j] = items[i];
					items[i] = min;
				}
			}
		}
		return items;
	}

}

class SelectSort2 {

	public int[] selectSort(int[] items) {
		for (int i = 0; i < items.length; i++) {
			int k = i;
			for (int j = k + 1; j < items.length; j++) {
				if (items[j] < items[k]) {
					k = j;
				}
			}
			if (i != k) {
				int temp = items[i];
				items[i] = items[k];
				items[k] = temp;
			}
		}
		return items;
	}

}
