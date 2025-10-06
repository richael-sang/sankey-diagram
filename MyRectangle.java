import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends Rectangle {
    private Color strokeColor, fillColor;

    public MyRectangle() {
    }

    public MyRectangle(
            double x, double y,
            double width, double height,
            Color strokeColor, Color fillColor) {
        super(x, y, width, height);
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        setStroke(strokeColor);
        setFill(fillColor);
    }
}

