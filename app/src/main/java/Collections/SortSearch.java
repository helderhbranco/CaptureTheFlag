package Collections;

import Collections.Lists.LinkedList;
import Collections.Lists.LinkedUnorderedList;

/**
 * SortSearch represents a class that contains algorithms of sorting and searching
 */
public class SortSearch {

    /**
     * Function that converts the elements of an array to a list previously created.
     * @param list the list to be updated
     * @param data the array with the elements
     * @param <T> the type of the elements
     */
    private static <T extends Comparable<? super T>> void arrayToLinkedUnorderedList(LinkedUnorderedList<T> list, T[] data) {
        list.clear();
        for (int i = 0; i <= data.length - 1; i++) {
            list.addToRear(data[i]);
        }
    }

    /**
     * Searches for a given element in an array using the linear search algorithm.
     * @param data the array to be searched
     * @param min the minimum index of the array to be searched
     * @param max the maximum index of the array to be searched
     * @param target the element to be searched
     * @return true if the element is found, false otherwise
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(T[] data, int min, int max, T target) {
        //go through the array
        for (int i = min; i <= max; i++) {
            //if the element is found, return true
            if (data[i].compareTo(target) == 0) {
                return true;
            }
        }
        //if the element is not found, return false
        return false;
    }

    /**
     * Searches for a given element in a linked list using the linear search function above.
     * The linked list is converted to an array before the search because of efficiency.
     * @param list the linked list to be searched
     * @param min the minimum index of the array to be searched
     * @param max the maximum index of the array to be searched
     * @param target the element to be searched
     * @return true if the element is found, false otherwise
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> boolean linearSearch(LinkedList<T> list, int min, int max, T target) {
        return linearSearch(list.toArray(), min, max, target);
    }

    /**
     * Searches for a given element in an array using the binary search algorithm.
     * @param data the array to be searched
     * @param min the minimum index of the array to be searched
     * @param max the maximum index of the array to be searched
     * @param target the element to be searched
     * @return true if the element is found, false otherwise
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(T[] data, int min, int max, T target) {
        //if no element is left to be searched, return false
        if (min > max) return false;
        //calculate the middle index
        int mid = (min + max) / 2;
        if (data[mid].compareTo(target) == 0) {
            //if the element is found, return true
            return true;
        } else if (data[mid].compareTo(target) < 0) {
            //if the element is greater than the middle element, search the right half
            return binarySearch(data, mid + 1, max, target);
        } else {
            //if the element is smaller than the middle element, search the left half
            return binarySearch(data, min, mid - 1, target);
        }
    }

    /**
     * Searches for a given element in a linked list using the binary search function above.
     * The linked list is converted to an array before the search because of efficiency.
     * @param list the linked list to be searched
     * @param min the minimum index of the array to be searched
     * @param max the maximum index of the array to be searched
     * @param target the element to be searched
     * @return true if the element is found, false otherwise
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> boolean binarySearch(LinkedList<T> list, int min, int max, T target) {
        return binarySearch(list.toArray(), min, max, target);
    }

    /**
     * Sorts an array using the selection sort algorithm.
     * @param data the array to be sorted
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> void selectionSort(T[] data) {
        //go through the array
        for (int i = 0; i < data.length - 1; i++) {
            //variable to store the index of the smallest element
            int min = i;
            //find the smallest element in the array starting from the current index
            for (int j = i + 1; j < data.length; j++) {
                if (data[j].compareTo(data[min]) < 0) {
                    min = j;
                }
            }
            //swap the smallest element with the current element if the current element is not the smallest
            if (min != i) {
                T temp = data[i];
                data[i] = data[min];
                data[min] = temp;
            }
        }
    }

    /**
     * Sorts a linked list using the selection sort function above.
     * The linked list is converted to an array before the sort because of efficiency.
     * @param list the linked list to be sorted
     * @param <T> the type of the elements in the linked list
     */
    public static <T extends Comparable<? super T>> void selectionSort(LinkedUnorderedList<T> list) {
        //convert the linked list to an array
        T[] data = list.toArray();
        //sort the array
        selectionSort(data);
        //convert the array back to a linked list
        arrayToLinkedUnorderedList(list, data);
    }

    /**
     * Sorts an array using the insertion sort algorithm.
     * @param data the array to be sorted
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] data) {
        //go from the second element to the last
        for (int i = 1; i < data.length; i++) {
            //store the current element
            T temp = data[i];
            //variable to store the index of the element to be compared
            int j = i;
            //go through the array from the current element to the first element and compare the current element with the elements before it
            while (j > 0 && data[j - 1].compareTo(temp) > 0) {
                //move the element to the right
                data[j] = data[j - 1];
                //decrease the index
                j--;
            }
            //insert the current element to the correct position
            data[j] = temp;
        }
    }

    /**
     * Sorts a linked list using the insertion sort function above.
     * The linked list is converted to an array before the sort because of efficiency.
     * @param list the linked list to be sorted
     * @param <T> the type of the elements in the linked list
     */
    public static <T extends Comparable<? super T>> void insertionSort(LinkedUnorderedList<T> list) {
        //convert the linked list to an array
        T[] data = list.toArray();
        //sort the array
        insertionSort(data);
        //convert the array back to a linked list
        arrayToLinkedUnorderedList(list, data);
    }

    /**
     * Sorts an array using the bubble sort algorithm.
     * @param data the array to be sorted
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> void bubbleSort(T[] data) {
        //go through the array until penultimate element
        for (int i = 0; i < data.length - 1; i++) {
            //go through the array from the penultimate to the current element
            for (int j = data.length - 1; j > i; j--) {
                //swap the elements if the current element is smaller than the previous element
                if (data[j].compareTo(data[j - 1]) < 0) {
                    T temp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = temp;
                }
            }
        }
    }

    /**
     * Sorts a linked list using the bubble sort function above.
     * The linked list is converted to an array before the sort because of efficiency.
     * @param list the linked list to be sorted
     * @param <T> the type of the elements in the linked list
     */
    public static <T extends Comparable<? super T>> void bubbleSort(LinkedUnorderedList<T> list) {
        T[] data = list.toArray();
        bubbleSort(data);
        arrayToLinkedUnorderedList(list, data);
    }

    /**
     * Sorts an array using the quick sort algorithm.
     * @param data the array to be sorted
     * @param min the index of the first element in the array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] data, int min, int max) {
        //if the array has more than one element
        if (min < max) {
            //partition the array
            int pivot = partition(data, min, max);
            //sort the left half
            quickSort(data, min, pivot - 1);
            //sort the right half
            quickSort(data, pivot + 1, max);
        }
    }

    /**
     * Partitions an array using the quick sort algorithm.
     * @param data the linked list to be sorted
     * @param min the index of the first element in the array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the linked list
     * @return the index of the pivot element
     */
    private static <T extends Comparable<? super T>> int partition(T[] data, int min, int max) {
        //store the pivot element
        T pivot = data[min];
        //store the index of the first element in the right half
        int left = min + 1;
        //store the index of the last element in the left half
        int right = max;
        //go through the array
        while (left <= right) {
            //find the first element in the right half that is smaller than the pivot element
            while (left <= right && data[left].compareTo(pivot) <= 0) {
                left++;
            }
            //find the first element in the left half that is larger than the pivot element
            while (left <= right && data[right].compareTo(pivot) > 0) {
                right--;
            }
            //swap the elements if the left element is larger than the right element
            if (left < right) {
                T temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }
        //swap the pivot element with the right element
        data[min] = data[right];
        data[right] = pivot;
        //return the index of the pivot element
        return right;
    }

    /**
     * Sorts a linked list using the quick sort function above.
     * The linked list is converted to an array before the sort because of efficiency.
     * @param list the linked list to be sorted
     * @param min the index of the first element in the array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the linked list
     */
    public static <T extends Comparable<? super T>> void quickSort(LinkedUnorderedList<T> list, int min, int max) {
        //convert the linked list to an array
        T[] data = list.toArray();
        //sort the array
        quickSort(data, min, max);
        //convert the array back to a linked list
        arrayToLinkedUnorderedList(list, data);
    }

    /**
     * Sorts an array using the merge sort algorithm.
     * @param data the array to be sorted
     * @param min the index of the first element in the array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the array
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] data, int min, int max) {
        //if the array has more than one element
        if (min < max) {
            //find the middle index
            int mid = (min + max) / 2;
            //sort the left half
            mergeSort(data, min, mid);
            //sort the right half
            mergeSort(data, mid + 1, max);
            //merge the two halves
            merge(data, min, mid, max);
        }
    }

    /**
     * Merges two sorted arrays.
     * @param data the array to be sorted
     * @param min the index of the first element in the array
     * @param mid the index of the last element in the first array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the array
     */
    private static <T extends Comparable<? super T>> void merge(T[] data, int min, int mid, int max) {
        //store the first array
        T[] temp = (T[]) new Comparable[max - min + 1];
        //store the index of the first element in the first array
        int left = min;
        //store the index of the first element in the second array
        int right = mid + 1;
        //store the index of the first element in the temporary array
        int index = 0;
        //go through the arrays
        while (left <= mid && right <= max) {
            //if the element in the first array is smaller than the element in the second array
            if (data[left].compareTo(data[right]) <= 0) {
                //add the element from the first array to the temporary array
                temp[index] = data[left];
                left++;
            } else {
                //add the element from the second array to the temporary array
                temp[index] = data[right];
                right++;
            }
            //increment the index
            index++;
        }
        //add the remaining elements from the first array to the temporary array
        while (left <= mid) {
            temp[index] = data[left];
            left++;
            index++;
        }
        //add the remaining elements from the second array to the temporary array
        while (right <= max) {
            temp[index] = data[right];
            right++;
            index++;
        }
        //copy the temporary array to the original array
        for (int i = min; i <= max; i++) {
            data[i] = temp[i - min];
        }
    }

    /**
     * Sorts a linked list using the merge sort function above.
     * The linked list is converted to an array before the sort because of efficiency.
     * @param list the linked list to be sorted
     * @param min the index of the first element in the array
     * @param max the index of the last element in the array
     * @param <T> the type of the elements in the linked list
     */
    public static <T extends Comparable<? super T>> void mergeSort(LinkedUnorderedList<T> list, int min, int max) {
        //convert the linked list to an array
        T[] data = list.toArray();
        //sort the array
        mergeSort(data, min, max);
        //convert the array back to a linked list
        arrayToLinkedUnorderedList(list, data);
    }
}
