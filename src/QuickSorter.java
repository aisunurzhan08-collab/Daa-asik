import java.util.Random;

public class QuickSorter {

    private static final Random random = new Random();

    private static int currentDepth = 0;
    private static int maxDepth = 0;

    public static void sort(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }

        currentDepth = 0;
        maxDepth = 0;

        quickSort(array, 0, array.length - 1);

        System.out.println("QuickSort max depth: " + maxDepth);
    }

    private static void quickSort(int[] array, int left, int right) {

        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        while (left < right) {

            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = array[pivotIndex];

            int i = left;
            int j = right;

            while (i <= j) {

                while (array[i] < pivot) {
                    i++;
                }

                while (array[j] > pivot) {
                    j--;
                }

                if (i <= j) {
                    swap(array, i, j);
                    i++;
                    j--;
                }
            }

            if (j - left < right - i) {

                if (left < j) {
                    quickSort(array, left, j);
                }

                left = i;

            } else {

                if (i < right) {
                    quickSort(array, i, right);
                }

                right = j;
            }
        }

        currentDepth--;
    }

    private static void swap(int[] array, int first, int second) {

        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static int getMaxDepth() {
        return maxDepth;
    }
}