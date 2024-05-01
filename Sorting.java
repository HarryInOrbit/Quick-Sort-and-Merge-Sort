import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) throws FileNotFoundException {
        int[] array = Scanner();
        System.out.println("Before sorting:");
        printArray(array);
        menu(array);
    }

    private static void menu(int[] array) {
        Scanner userInput = new Scanner(System.in);
        int choice;
        System.out.println("1. Merge Sort");
        System.out.println("2. Quick Sort");
        System.out.println("3. Exit");
        System.out.println("Enter your choice: ");
        choice = userInput.nextInt();
        switch(choice) {
            case 1:
                mergeSort(array);
                printArray(array);
                System.out.println("Enter the element to search: ");
                int target = userInput.nextInt();
                int result = binarySearch(array, target);
                if (result == -1) {
                    System.out.println("Element not found");
                } else {
                    System.out.println("Element found at index: " + result);
                }
                break;
            case 2:
                quickSort(array, 0, array.length - 1);
                printArray(array);
                System.out.println("Enter the element to search: ");
                target = userInput.nextInt();
                result = binarySearch(array, target);
                if (result == -1) {
                    System.out.println("Element not found");
                } else {
                    System.out.println("Element found at index: " + result);
                }
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
    }

    private static int[] Scanner() throws FileNotFoundException {
        File file = new File("numbers-4.txt");
        Scanner scan = new Scanner(file);

        int count = 0;
        while (scan.hasNextInt()) {
            scan.nextInt();
            count++;
        }
        scan.close();

        scan = new Scanner(file);
        int[] Number = new int[count];

        for (int i = 0; i < count; i++) {
            Number[i] = scan.nextInt();
        }
        scan.close();
        return Number;
    }

    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] array) {
        int length = array.length;  // Number of elements in the array
        if (length < 2) {
            return;         // If the array has less than two elements, do nothing.
        }

        int mid = length / 2;        // Middle index of the array
        int[] leftHalf = new int[mid];
        int[] rightHalf = new int[length - mid];

        // Copy data to sub-arrays
        for (int i = 0; i < mid; i++) {
            leftHalf[i] = array[i];
        }

        for (int i = mid; i < length; i++) {
            rightHalf[i - mid] = array[i];
        }

        // Recursively sort sub-arrays
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        // Merge the sorted sub-arrays
        merge(array, leftHalf, rightHalf);
    }
    private static void merge(int[] array, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;       // Number of elements in sub-array left
        int rightSize = rightHalf.length;     // Number of elements in sub-array right
        int i, j, k;                          // i: index for left array, j: index for right array, k: index for merged array
        i = j = k = 0;

        // merging the left and right array
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                array[k] = leftHalf[i];
                i++;
            } else {
                array[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left array if any
        while (i < leftSize) {
            array[k] = leftHalf[i];
            i++;
            k++;
        }

        // Copy remaining elements of right array if any
        while (j < rightSize) {
            array[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);   // Partitioning index

            quickSort(array, low, pi - 1);    // Sort left sub-array
            quickSort(array, pi + 1, high);    // Sort right sub-array
        }
    }
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];       // Pivot element
        int i = (low - 1);             // Index of smaller element

        // Traverse the array
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap the pivot element with the element at index i + 1
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;   // Return the index position of the pivot
    }

    private static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // If not found
        return -1;
    }
}