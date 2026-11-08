import java.awt.image.BufferedImage;

public class LeavesMain {
    public static void main(String[] args) {
        leavesLoader loader = new leavesLoader("path/to/your/image.png");
        BufferedImage image = loader.getImage();
        
        System.out.println("Successfull loading: " + (image != null));
        System.out.println("Image dimensions: " + image.getWidth() + "x" + image.getHeight());      
    }
}