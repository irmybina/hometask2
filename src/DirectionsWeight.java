public class DirectionsWeight {
    private double up = 0.0;
    private double down = 0.0;
    private double left = 0.0;
    private double right = 0.0;

    DirectionsWeight(double weight){
        this.down = weight;
        this.left = weight;
        this.up = weight;
        this.right = weight;
    }
    @Override
    public String toString() {
        return "[up: " + up + ", right: " + right + ", down: " + down + ", left: " + left + "]";
    }
    public double getUp() {
        return up;
    }
    public void setUp(double up) {
        this.up = up;
    }
    public double getDown() {
        return down;
    }
    public void setDown(double down) {
        this.down = down;
    }
    public double getLeft() {
        return left;
    }
    public void setLeft(double left) {
        this.left = left;
    }
    public double getRight() {
        return right;
    }
    public void setRight(double right) {
        this.right = right;
    }
}
