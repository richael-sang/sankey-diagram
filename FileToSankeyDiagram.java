import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.Map;

public class FileToSankeyDiagram extends Application {
    @Override
    public void start(Stage primaryStage) {
        // 读取文件并处理数据Read files and process data
        FileReader fileReader = new FileReader("/Users/sangrui/Desktop/curriculum/大二/CPT111/cpt111/SankeyDiagram/src/example4.txt");
        String title = fileReader.getTitle();
        String label = fileReader.getLabel();
        Map<String, Double> dataMap = fileReader.getDataMap();
        // 创建SankeyDiagram对象并生成图表Create the SankeyDiagram object and generate the diagram
        SankeyDiagram pane = new SankeyDiagram(title, label, dataMap);
        pane.setWidth(800);
        pane.setHeight(800);

        // 添加按钮Add button
        Button colorButton = new Button("Change The Colors");
        colorButton.setLayoutX(10);
        colorButton.setLayoutY(10);
        colorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // 调用changeColors方法更换颜色 Call the changeColors method to change the color
                pane.changeColors();
            }
        });
        Group newpane = new Group();
        newpane.getChildren().addAll(pane, colorButton);

        StackPane root = new StackPane();
        root.getChildren().add(newpane);

        // 使用绑定属性使图形保持居中Use binding properties to keep the graph centered
        pane.translateXProperty().bind(Bindings.createDoubleBinding(
                () -> (root.getWidth() - pane.getWidth()) / 2,
                root.widthProperty(), pane.widthProperty()
        ));
        pane.translateYProperty().bind(Bindings.createDoubleBinding(
                () -> (root.getHeight() - pane.getHeight()) / 2,
                root.heightProperty(), pane.heightProperty()
        ));

        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setTitle("Sankey Diagram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

