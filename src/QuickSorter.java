import java.util.Random;

public class QuickSorter {

    private static final Random random = new Random();

    public static void sort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {

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
    }

    private static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}