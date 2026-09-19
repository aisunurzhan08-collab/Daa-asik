import java.util.Random;

public class Experiment {

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000, 10000};

        for (int size : sizes) {

            System.out.println("========== Size: " + size + " ==========");

            testMergeSort(size);
            testQuickSort(size);
            testSelect(size);
            testClosestPair(size);

            System.out.println();
        }
    }

    public static void testMergeSort(int size) {

        int[] array = createArray(size);

        long start = System.nanoTime();

        MergeSorter.sort(array);

        long end = System.nanoTime();

        System.out.println("MergeSort: " + (end - start) + " ns");
    }

    public static void testQuickSort(int size) {

        int[] array = createArray(size);

        long start = System.nanoTime();

        QuickSorter.sort(array);

        long end = System.nanoTime();

        System.out.println("QuickSort: " + (end - start) + " ns");
    }

    public static void testSelect(int size) {

        int[] array = createArray(size);

        int k = size / 2;

        long start = System.nanoTime();

        int result = DeterministicSelector.select(array, k);

        long end = System.nanoTime();

        System.out.println("Select: " + (end - start) + " ns, result = " + result);
    }

    public static void testClosestPair(int size) {

        point[] points = createPoints(size);

        long start = System.nanoTime();

        double result = ClosestPairSolver.findClosest(points);

        long end = System.nanoTime();

        System.out.println("Closest Pair: " + (end - start) + " ns, distance = " + result);
    }

    public static int[] createArray(int size) {

        int[] array = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10000);
        }

        return array;
    }

    public static point[] createPoints(int size) {

        point[] points = new point[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            double x = random.nextDouble() * 1000;
            double y = random.nextDouble() * 1000;

            points[i] = new point(x, y);
        }

        return points;
    }
}