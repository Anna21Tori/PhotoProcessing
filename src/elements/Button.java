package elements;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {

    public Button(String text, Color colorText, Color colorBackGround) {
        super(text);

        this.setBackground(colorBackGround);
        this.setForeground(colorText);
    }

    public void setSizeButton(int x, int y){

        this.setMaximumSize(new Dimension(x, y));
    }
}
