import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.Deque;

public class Finder {
    public int spotsCounter(boolean[][] diseasedArea) {
        int width = diseasedArea.length;
        int height = diseasedArea[0].length;
        boolean[][] visited = new boolean[width][height];
        int spotsCount = 0;

        for (int x=0; x < width; x++) {
            for (int y = 0; y< height; y++) {
                if (diseasedArea[x][y] && !visited[x][y]) {
                    spotsCount++;

                    int count = 0;
                    double mean = 0;
                    double m2 = 0;

                    ArrayDeque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{x, y});
                    while (!stack.isEmpty()) {
                        int[] current = stack.pop();
                        int currX = current[0];
                        int currY = current[1];

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

                        if (currX < 0 || currX >= width || currY < 0 || currY >= height || visited[currX][currY] || !diseasedArea[currX][currY]) {
                            continue;
                        }
                        visited[currX][currY] = true;
                    }
                }
            }
            double variance = m2 / (count - 1);
            if (count >= 20) {
                System.out.println("Spot " + spotsCount + ": Mean brightness = " + mean + ", Variance = " + variance);
            }
        }
    }
}