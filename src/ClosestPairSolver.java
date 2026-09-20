import java.util.Arrays;
import java.util.Comparator;

public class ClosestPairSolver {

    private static int currentDepth = 0;
    private static int maxDepth = 0;

    public static double findClosest(point[] points) {

        if (points == null || points.length < 2) {
            return 0;
        }

        currentDepth = 0;
        maxDepth = 0;

        point[] sorted = points.clone();

        Arrays.sort(sorted, Comparator.comparingDouble(p -> p.x));

        return find(sorted, 0, sorted.length - 1);
    }

    private static double find(point[] points, int left, int right) {

        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }

        if (right - left <= 2) {
            double result = simpleCheck(points, left, right);
            currentDepth--;
            return result;
        }

        int middle = (left + right) / 2;

        double leftDistance = find(points, left, middle);
        double rightDistance = find(points, middle + 1, right);

        double best = Math.min(leftDistance, rightDistance);

        double middleX = points[middle].x;

        point[] strip = new point[right - left + 1];
        int count = 0;

        for (int i = left; i <= right; i++) {

            if (Math.abs(points[i].x - middleX) < best) {
                strip[count] = points[i];
                count++;
            }
        }

        Arrays.sort(
                strip,
                0,
                count,
                Comparator.comparingDouble(p -> p.y)
        );

        for (int i = 0; i < count; i++) {

            for (int j = i + 1; j < count; j++) {

                if (strip[j].y - strip[i].y >= best) {
                    break;
                }

                double distance = distance(strip[i], strip[j]);

                if (distance < best) {
                    best = distance;
                }
            }
        }

        currentDepth--;

        return best;
    }

    private static double simpleCheck(
            point[] points, int left, int right) {

        double best = Double.MAX_VALUE;

        for (int i = left; i <= right; i++) {

            for (int j = i + 1; j <= right; j++) {

                double distance = distance(points[i], points[j]);

                if (distance < best) {
                    best = distance;
                }
            }
        }

        return best;
    }

    private static double distance(point a, point b) {

        double dx = a.x - b.x;
        double dy = a.y - b.y;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public static int getMaxDepth() {
        return maxDepth;
    }
}