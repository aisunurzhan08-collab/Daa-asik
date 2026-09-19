import java.util.Arrays;
import java.util.Random;

public class TestAlgorithms {

    public static void main(String[] args) {

        System.out.println("===== TESTING =====");

        testMergeSort();
        testQuickSort();
        testSelect();
        testClosestPair();

        System.out.println("===== TEST FINISHED =====");
    }

    public static void testMergeSort() {

        System.out.println("\nMergeSort:");

        testSort(new int[]{5, 2, 8, 1, 3}, "Random");
        testSort(new int[]{1, 2, 3, 4, 5}, "Sorted");
        testSort(new int[]{5, 4, 3, 2, 1}, "Reverse");
        testSort(new int[]{2, 2, 2, 1, 1}, "Duplicates");
    }

    public static void testQuickSort() {

        System.out.println("\nQuickSort:");

        int[] a = {5, 2, 8, 1, 3};
        int[] b = {1, 2, 3, 4, 5};
        int[] c = {5, 4, 3, 2, 1};
        int[] d = {2, 2, 2, 1, 1};

        testQuick(a, "Random");
        testQuick(b, "Sorted");
        testQuick(c, "Reverse");
        testQuick(d, "Duplicates");
    }

    public static void testSort(int[] array, String name) {

        int[] expected = array.clone();

        Arrays.sort(expected);
        MergeSorter.sort(array);

        System.out.println(name + ": " + Arrays.equals(array, expected));
    }

    public static void testQuick(int[] array, String name) {

        int[] expected = array.clone();

        Arrays.sort(expected);
        QuickSorter.sort(array);

        System.out.println(name + ": " + Arrays.equals(array, expected));
    }

    public static void testSelect() {

        System.out.println("\nSelect:");

        Random random = new Random();
        boolean correct = true;

        for (int test = 0; test < 100; test++) {

            int[] array = new int[50];

            for (int i = 0; i < array.length; i++) {
                array[i] = random.nextInt(100);
            }

            int[] sorted = array.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(array.length);

            int result = DeterministicSelector.select(array, k);

            if (result != sorted[k]) {
                correct = false;
                break;
            }
        }

        System.out.println("100 random tests: " + correct);
    }

    public static void testClosestPair() {

        System.out.println("\nClosest Pair:");

        point[] points = {
                new point(0, 0),
                new point(3, 4),
                new point(1, 1),
                new point(10, 10)
        };

        double result = ClosestPairSolver.findClosest(points);
        double expected = bruteForce(points);

        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);
        System.out.println(
                "Correct: " + (Math.abs(result - expected) < 0.000001)
        );
    }

    public static double bruteForce(point[] points) {

        double best = Double.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {

            for (int j = i + 1; j < points.length; j++) {

                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;

                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance < best) {
                    best = distance;
                }
            }
        }

        return best;
    }
}