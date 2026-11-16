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
                    ArrayDeque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{x, y});
                    while (!stack.isEmpty()) {
                        int[] current = stack.pop();
                        int currX = current[0];
                        int currY = current[1];
                        if (currX < 0 || currX >= width || currY < 0 || currY >= height || visited[currX][currY] || !diseasedArea[currX][currY]) {
                            continue;
                        }
                        visited[currX][currY] = true;
                    }
                }
            }
        }
    })
}