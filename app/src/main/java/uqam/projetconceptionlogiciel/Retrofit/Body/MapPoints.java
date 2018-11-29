package uqam.projetconceptionlogiciel.Retrofit.Body;

public class MapPoints {
    private Double top;

    private Double bottom;

    private Double left;

    private Double right;

    private int returnNumber;

    public MapPoints(Double top, Double bottom, Double left, Double right, int returnNumber) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.returnNumber = returnNumber;
    }

    public Double getTop() {
        return top;
    }

    public Double getBottom() {
        return bottom;
    }

    public Double getLeft() {
        return left;
    }

    public Double getRight() {
        return right;
    }

    public int getReturnNumber() {
        return returnNumber;
    }
}
