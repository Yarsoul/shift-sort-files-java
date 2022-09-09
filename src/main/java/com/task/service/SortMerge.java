package com.task.service;

import static java.lang.System.arraycopy;

public class SortMerge {
    public Integer[] sortMergeInteger(Integer[] input1, Integer[] input2) {
        if (input1.length == 0) {
            return input2;
        }

        Integer[] result = new Integer[input1.length+input2.length];
        int i=0, j=0, k=0;
        while(i < input1.length && j < input2.length) {
            if (input1[i] < input2[j]) {
                result[k++] = input1[i++];
            } else {
                result[k++] = input2[j++];
            }
            if(i < input1.length) {
                arraycopy(input1, i, result, k, input1.length - i);
            } else if(j < input2.length) {
                arraycopy(input2, j, result, k, input2.length - j);
            }
        }

        return result;
    }

    public void myQuickSort(Integer[] array, int left, int right) {
        if (left >= right) return;
        int pivot = partition(array, left, right);

        myQuickSort(array, left, pivot - 1);
        myQuickSort(array, pivot + 1, right);
    }


    private int partition(Integer[] array, int left, int right) {
        int pivot = array[right];
        int pointer = left - 1;

        for (int i = left; i < right; i++) {
            if (array[i] < pivot) {
                pointer++;
                swap(array, pointer, i);
            }
        }
        swap(array, pointer + 1, right);
        return pointer + 1;
    }


    private void swap(Integer[] arr, int pointer, int i) {
        int temp = arr[pointer];
        arr[pointer] = arr[i];
        arr[i] = temp;
    }
}
