import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Experiment {

    private static FileWriter writer;

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000, 10000};

        try {

            new File("results").mkdirs();

            writer = new FileWriter("results/results.csv");

            writer.write("Algorithm,Size,TimeNs,RecursionDepth\n");

            System.out.println("Algorithm | Size | Time (ns) | Depth");
            System.out.println("--------------------------------------");

            for (int size : sizes) {

                testMergeSort(size);
                testQuickSort(size);
                testSelect(size);
                testClosestPair(size);
            }

            writer.close();

            System.out.println();
            System.out.println("Results saved to results/results.csv");

        } catch (IOException e) {

            System.out.println("Error saving results.");
        }
    }

    public static void testMergeSort(int size) {

        int[] array = createArray(size);

        long start = System.nanoTime();

        MergeSorter.sort(array);

        long end = System.nanoTime();

        long time = end - start;
        int depth = MergeSorter.getMaxDepth();

        System.out.println(
                "MergeSort | " + size + " | " + time + " | " + depth
        );

        writeResult("MergeSort", size, time, depth);
    }

    public static void testQuickSort(int size) {

        int[] array = createArray(size);

        long start = System.nanoTime();

        QuickSorter.sort(array);

        long end = System.nanoTime();

        long time = end - start;
        int depth = QuickSorter.getMaxDepth();

        System.out.println(
                "QuickSort | " + size + " | " + time + " | " + depth
        );

        writeResult("QuickSort", size, time, depth);
    }

    public static void testSelect(int size) {

        int[] array = createArray(size);

        int k = size / 2;

        long start = System.nanoTime();

        DeterministicSelector.select(array, k);

        long end = System.nanoTime();

        long time = end - start;
        int depth = DeterministicSelector.getMaxDepth();

        System.out.println(
                "Select | " + size + " | " + time + " | " + depth
        );

        writeResult("Select", size, time, depth);
    }

    public static void testClosestPair(int size) {

        point[] points = createPoints(size);

        long start = System.nanoTime();

        ClosestPairSolver.findClosest(points);

        long end = System.nanoTime();

        long time = end - start;
        int depth = ClosestPairSolver.getMaxDepth();

        System.out.println(
                "Closest Pair | " + size + " | " + time + " | " + depth
        );

        writeResult("Closest Pair", size, time, depth);
    }

    public static void writeResult(
            String algorithm,
            int size,
            long time,
            int depth) {

        try {

            writer.write(
                    algorithm + "," +
                            size + "," +
                            time + "," +
                            depth + "\n"
            );

        } catch (IOException e) {

            System.out.println("Error writing result.");
        }
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