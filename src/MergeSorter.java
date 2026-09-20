public class MergeSorter {

    private static int currentDepth = 0;
    private static int maxDepth = 0;

    public static void sort(int[] array) {

        if (array == null || array.length < 2) {
            return;
        }

        currentDepth = 0;
        maxDepth = 0;

        int[] buffer = new int[array.length];

        mergeSort(array, buffer, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] buffer,
                                  int left, int right) {

        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        if (left >= right) {
            currentDepth--;
            return;
        }

        int middle = (left + right) / 2;

        mergeSort(array, buffer, left, middle);
        mergeSort(array, buffer, middle + 1, right);

        merge(array, buffer, left, middle, right);

        currentDepth--;
    }

    private static void merge(int[] array, int[] buffer,
                              int left, int middle, int right) {

        int i = left;
        int j = middle + 1;
        int k = left;

        while (i <= middle && j <= right) {

            if (array[i] <= array[j]) {
                buffer[k] = array[i];
                i++;
            } else {
                buffer[k] = array[j];
                j++;
            }

            k++;
        }

        while (i <= middle) {
            buffer[k] = array[i];
            i++;
            k++;
        }

        while (j <= right) {
            buffer[k] = array[j];
            j++;
            k++;
        }

        for (int p = left; p <= right; p++) {
            array[p] = buffer[p];
        }
    }

    public static int getMaxDepth() {
        return maxDepth;
    }
}