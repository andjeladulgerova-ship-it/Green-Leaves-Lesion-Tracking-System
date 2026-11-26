public class Lesion {
    private int id;
    private int area;
    private int perimeter;
    private double centerX;
    private double centerY;
    private double meanBrightness;
    private double varianceBrightness;

    public Lesion(int id, int area, int perimeter, double centerX, double centerY, double meanBrightness, double varianceBrightness) {
        this.id = id;
        this.area = area;
        this.perimeter = perimeter;
        this.centerX = centerX;
        this.centerY = centerY;
        this.meanBrightness = meanBrightness;
        this.varianceBrightness = varianceBrightness;
    }

    public int getId() {
        return id;
    }
    public int getArea() {
        return area;
    }
    public int getPerimeter() {
        return perimeter;
    }
    public double getCenterX() {
        return centerX;
    }
    public double getCenterY() {
        return centerY;
    }
    public double getMeanBrightness() {
        return meanBrightness;
    }
    public double getVarianceBrightness() {
        return varianceBrightness;
    }

    @Override
    public String toString() {
        return String.format("Lesion{" +
                "id=%d, area=%d, perimeter=%d, centerX=%.1f, centerY=%.1f, meanBrightness=%.1f, varianceBrightness=%.1f}", id, area, perimeter, centerX, centerY, meanBrightness, varianceBrightness);
    }
}