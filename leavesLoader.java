import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class leavesLoader {
    private BufferedImage image;

    public leavesLoader(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new IllegalArgumentException("Image couldn't be loaded from: " + imagePath, e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void fixImage(BufferedImage image) {
        this.image = image;
    }
}   