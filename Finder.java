import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Finder {
    private boolean[][] realSpots;

    public boolean[][] getRealSpots() {
        return realSpots;
    }
    public int spotsCounter(boolean[][] diseasedArea, BufferedImage image) {
        int width = diseasedArea.length;
        int height = diseasedArea[0].length;
        boolean[][] visited = new boolean[width][height];
        realSpots = new boolean[width][height];
        int spotsCount = 0;

        for (int x=0; x < width; x++) {
            for (int y = 0; y< height; y++) {
                if (diseasedArea[x][y] && !visited[x][y]) {

                    int count = 0;
                    double mean = 0;
                    double m2 = 0;
                    ArrayList<int[]> spotPixels = new ArrayList<>();
                    boolean edgeDetected = false;
                    long sumXPerimeter = 0;
                    long sumYPerimeter = 0;
                    int perimeterCount = 0;

                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.add(new int[]{x, y});
                    visited[x][y] = true;
                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();
                        int currX = current[0];
                        int currY = current[1];
                        spotPixels.add(current);

                        int rgb = image.getRGB(currX, currY);
                        int red = (rgb >> 16) & 0xff;
                        int green = (rgb >> 8) & 0xff;
                        int blue = rgb & 0xff;
                        double brightness = 0.299 * red + 0.587 * green + 0.114 * blue;

                        //Welfrod algorithm 
                        count++;
                        double delta = brightness - mean;
                        mean += delta / count;
                        m2 += delta * (brightness - mean);

                        sumXPerimeter += currX;
                        sumYPerimeter += currY;
                        perimeterCount++;

                        if (currX == 0 || currX >= width -1  || currY == 0 || currY >= height - 1) {
                            edgeDetected = true;
                        }
                        int [][] neighbors = {
                            {currX - 1, currY}, {currX + 1, currY},
                            {currX, currY - 1}, {currX, currY + 1}
                        };
                        for (int[] n: neighbors) {
                            int nx = n[0];
                            int ny = n[1];
                            boolean isInside = nx >= 0 && nx < width && ny >= 0 && ny < height;
                            if (!isInside || !diseasedArea[nx][ny]) {
                                perimeterCount++;
                            } else if (!visited[nx][ny]) {
                                queue.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                            }
                        }
                    }
                    if (!edgeDetected && count >= 20) {
                        spotsCount++;
                        for (int[] pixel : spotPixels) {
                            realSpots[pixel[0]][pixel[1]] = true;
                        }
                        double variance = m2 / count;
                        double centreXPerimeter = (double) sumXPerimeter / perimeterCount;
                        double centreYPerimeter = (double) sumYPerimeter / perimeterCount;
                        System.out.printf   ("Spot %d: area = %d, perimeter = %d, centre = (%.1f, %.1f), brightness = %.1f, variance = %.1f%n", spotsCount, count, perimeterCount, centreXPerimeter, centreYPerimeter, mean, variance);
                    }
                }
            }
        }
        return spotsCount;
    }
}