package cooperativespace.utilities;

public class UtilMath {

    public static double sin(double input) {
        return Math.toDegrees(Math.sin(Math.toRadians(input)));
    }

    public static double cos(double input) {
        return Math.toDegrees(Math.cos(Math.toRadians(input)));
    }

    /**
     * Calculates the distance between two points
     * @param x1 X value of the first point
     * @param y1 Y Value of the first point
     * @param x2 X value of the second point
     * @param y2 Y Value of the second point
     * @return The distance between two points
     */
    public static double distanceBetweenPoints(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2 - y1), 2));
    }

}
