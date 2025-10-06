import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MyText extends Text {
    private Font font;

    public MyText() {
    }

    public MyText(double x, double y, String text, Font font) {
        super(x, y, text);
        this.font = font;
        setFont(font);
    }
}

