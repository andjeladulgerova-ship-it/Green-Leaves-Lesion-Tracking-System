import java.awt.image.BufferedImage;
import java.util.ArrayDeque;

public class ProcessingImage {
    public boolean[][] potentialLesionsDetection(BufferedImage image) {
        boolean [][] pixelsOfDiseasedArea = new boolean[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                boolean darkBrownSpots = red < 70 && green < 70  && blue < 70 && green <= red + 10;

                if (red >= 60 && red <= 180 && 
                green >= 20 && green <= 120 &&
                blue <= 100 && 
                red > green* 1.15 && 
                red > blue * 1.15 ||
                darkBrownSpots) {
                    pixelsOfDiseasedArea[x][y] = true;
                } else {
                    pixelsOfDiseasedArea[x][y] = false;
                }
            }        
        }
        return pixelsOfDiseasedArea;  
    }
    public boolean [][] fillingSpots(boolean [][] diseased) {
        int width = diseased.length;
        int height = diseased[0].length;
        boolean [][] outsideOfTheSPots= new boolean[width][height];
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean itsOnTheEdge = x == 0 || y ==0 || x == width - 1 || y == height - 1;
                if (itsOnTheEdge && !diseased[x][y]) {
                    queue.add(new int[]{x, y});
                    outsideOfTheSPots[x][y] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currX = current[0];
            int currY = current[1];

            int[][] neighbors = {
                {currX - 1, currY}, {currX + 1, currY},
                {currX, currY - 1}, {currX, currY + 1}
            };
            for (int[] n : neighbors) {
                int nx = n[0];
                int ny = n[1];
                if (nx >= 0 && nx < width && ny >= 0 && ny < height && !diseased[nx][ny] && !outsideOfTheSPots[nx][ny]) {
                    outsideOfTheSPots[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        int filledSpotsCount = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!outsideOfTheSPots[x][y] && !diseased[x][y]) {
                    diseased[x][y] = true;
                    filledSpotsCount++;
                }
            }
        }
        System.out.println("Total filled hole pixels: " + filledSpotsCount);
        return diseased;
    }
}
