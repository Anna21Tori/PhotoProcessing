package elements;



import javax.swing.*;
import java.awt.*;

public class LabelValue extends JLabel {
    public LabelValue(String text, Color backgroundColor, Color textColor) {
        super(text);

        this.setBackground(backgroundColor);
        this.setForeground(textColor);
    }
}
