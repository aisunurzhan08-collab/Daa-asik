import java.util.Arrays;

public class DeterministicSelector {

    public static int select(int[] array, int k) {

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException("Wrong k");
        }

        return find(array, 0, array.length - 1, k);
    }

    private static int find(int[] array, int left, int right, int k) {

        if (left == right) {
            return array[left];
        }

        int pivot = choosePivot(array, left, right);

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

        if (k <= j) {
            return find(array, left, j, k);
        }

        if (k >= i) {
            return find(array, i, right, k);
        }

        return pivot;
    }

    private static int choosePivot(int[] array, int left, int right) {

        int size = right - left + 1;

        if (size <= 5) {
            int[] small = Arrays.copyOfRange(array, left, right + 1);
            Arrays.sort(small);
            return small[size / 2];
        }

        int groups = (size + 4) / 5;
        int[] medians = new int[groups];

        int m = 0;

        for (int i = left; i <= right; i += 5) {

            int end = Math.min(i + 4, right);

            int[] group = Arrays.copyOfRange(array, i, end + 1);
            Arrays.sort(group);

            medians[m] = group[group.length / 2];
            m++;
        }

        return find(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static void swap(int[] array, int a, int b) {

        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}