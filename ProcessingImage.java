import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessingImage {
    public boolean[][] potentialLesionsDetection(BufferedImage image) {
        boolean [][] pixelsOfDeceasedArea = new boolean[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                if (red >= 60 && red <= 180 && 
                green >= 20 && green <= 120 &&
                blue <= 100 && 
                red > green* 1.15 && 
                red > blue * 1.15) {
                    pixelsOfDeceasedArea[x][y] = true;
                } else {
                    pixelsOfDeceasedArea[x][y] = false;
                }
    }
    return pixelsOfDeceasedArea;    
}
