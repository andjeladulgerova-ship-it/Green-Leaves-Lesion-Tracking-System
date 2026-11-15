import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Leaves2Main {
    public static void main(String[] args) throws Exception {
        leavesLoader loader = new leavesLoader("/Users/andjeladulgerova/Desktop/leaf.jpeg");
        BufferedImage image = loader.getImage();

        System.out.println("Successfully loaded: " + (image != null));
        System.out.println("Image dimensions: " + image.getWidth() + "x" + image.getHeight());
        ProcessingImage processor = new ProcessingImage();
        boolean[][] diseasedArea = processor.potentialLesionsDetection(image);

        int diseasedPixelCount = 0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (diseasedArea[x][y]) {
                    diseasedPixelCount++;
                }
            }
        }
        System.out.println("Total diseased pixels detected: " + diseasedPixelCount);

        ImageIO.write(image, "png", new File("/Users/andjeladulgerova/Desktop/processed_leaf.png"));
        System.out.println("The processed image was saved to: /Users/andjeladulgerova/Desktop/processed_leaf.png");

    }
}