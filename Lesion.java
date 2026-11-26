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
        return "Lesion{" +
                "id=" + id +
                ", area=" + area +
                ", perimeter=" + perimeter +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", meanBrightness=" + meanBrightness +
                ", varianceBrightness=" + varianceBrightness +
                '}';
    }
}