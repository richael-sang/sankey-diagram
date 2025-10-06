import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SankeyDiagram extends Pane {
    private MyRectangle rectangle;
    private MyText labelTitle, diagramTitle;
    private Group recs, text, curves;
    @Override
    public void setWidth(double width) {
        super.setWidth(width);
    }
    @Override
    public void setHeight(double heigth) {
        super.setHeight(heigth);
    }
    public SankeyDiagram(String title, String label,
                         Map<String, Double> dataMap) {
        rectangle = new MyRectangle();
        labelTitle = new MyText();
        diagramTitle = new MyText();
        recs = new Group();
        text = new Group();
        curves = new Group();
        createSankeyDiagram(title, label, dataMap);


    }
    public void createSankeyDiagram(String title, String label, Map<String, Double> dataMap) {
        diagramTitle = new MyText(350, 550, title,
                Font.font("Courier", FontWeight.BOLD, FontPosture.ITALIC, 25));
        rectangle = new MyRectangle(
                100, 100, 20, 300,
                Color.rgb(51, 166, 204, 0.5),
                Color.rgb(51, 166, 204, 0.5)
        );

        double totalHeight = rectangle.getHeight();
        double sum = calculateTotalSum(dataMap);
        label = label + " : " + sum;
        labelTitle = new MyText(
                rectangle.getX() + rectangle.getWidth() / 2,
                rectangle.getY() + rectangle.getHeight() / 2,
                label,
                Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20)
        );


        double recheheightchange = 0;
        double curveheightchange = 0;
        double X = rectangle.getX() + rectangle.getWidth();
        double Y = rectangle.getY();

        for (String key : dataMap.keySet()) {
            double currentheight = (dataMap.get(key) / sum) * totalHeight;

            int randomR = (int) (Math.random() * 256);
            int randomG = (int) (Math.random() * 256);
            int randomB = (int) (Math.random() * 256);

            MyRectangle r1 = new MyRectangle(
                    X + 600, Y - 60 + recheheightchange,
                    30, currentheight,
                    Color.rgb(randomR, randomG, randomB, 0.6),
                    Color.rgb(randomR, randomG, randomB, 0.6));

            recs.getChildren().add(r1);

            MyText type = new MyText(
                    r1.getX() + r1.getWidth() / 2,
                    r1.getY() + r1.getHeight() / 2,
                    key + " : " + dataMap.get(key),
                    Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 18));
            text.getChildren().add(type);

            Path path = createPath(X, Y, curveheightchange, r1, currentheight);
            Color pathColor = Color.rgb(randomR, randomG, randomB, 0.4);
            path.setStroke(pathColor);
            path.setFill(pathColor);

            curves.getChildren().add(path);

            recheheightchange += currentheight + 30;
            curveheightchange += currentheight;
        }
        getChildren().addAll(diagramTitle, rectangle, recs, curves, labelTitle, text);
    }
    public void changeColors() {
        List<MyRectangle> rectangles = new ArrayList<>();
        List<Path> paths = new ArrayList<>();

        for (Node node : recs.getChildren()) {
            if (node instanceof MyRectangle) {
                rectangles.add((MyRectangle) node);
            }
        }
        for (Node node : curves.getChildren()) {
            if (node instanceof Path) {
                paths.add((Path) node);
            }
        }
        for (int i = 0; i < rectangles.size(); i++) {
            MyRectangle rectangle = rectangles.get(i);
            Path path = paths.get(i);

            Color newColor = getRandomColor();
            rectangle.setStroke(newColor);
            rectangle.setFill(newColor);
            path.setStroke(newColor);
            path.setFill(newColor);
        }
    }
    private Color getRandomColor() {
        int randomR = (int) (Math.random() * 256);
        int randomG = (int) (Math.random() * 256);
        int randomB = (int) (Math.random() * 256);
        return Color.rgb(randomR, randomG, randomB, 0.4);
    }
    private double calculateTotalSum(Map<String, Double> dataMap) {
        double sum = 0;
        for (double value : dataMap.values()) {
            sum += value;
        }
        return sum;
    }
    private Path createPath(double x, double y,
                            double curveheightchange,
                            MyRectangle r1, double currentHeight) {
        //上曲线的起始点和控制点
        MoveTo moveTo1 = new MoveTo(x, y + curveheightchange);
        double endX = r1.getX();
        double endY = r1.getY();
        //Determine the amount of change at the control point确定控制点的变化量
        double changeX = Math.abs(x - endX);
        double controlX1 = moveTo1.getX() + changeX / 3;
        double controlY1 = moveTo1.getY();
        double controlX2 = endX - changeX / 3;
        double controlY2 = endY;

        CubicCurveTo curveTo1 = new CubicCurveTo(
                controlX1, controlY1,
                controlX2, controlY2,
                endX, endY
        );
        //下曲线的起始点和控制点
        LineTo lineTo1 = new LineTo(r1.getX(), r1.getY() + r1.getHeight());
        double startX = x;
        double startY = y + curveheightchange + currentHeight;
        double controlX3 = startX + changeX / 3;
        double controlY3 = startY;
        double controlX4 = lineTo1.getX() - changeX / 3;
        double controlY4 = lineTo1.getY();

        CubicCurveTo curveTo2 = new CubicCurveTo(
                controlX4, controlY4,
                controlX3, controlY3,
                startX, startY
        );

        Path path = new Path();
        path.getElements().addAll(moveTo1, curveTo1, lineTo1, curveTo2);
        return path;
    }
}
