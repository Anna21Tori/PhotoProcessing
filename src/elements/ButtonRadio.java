package elements;


import javax.swing.*;
import java.awt.*;

public class ButtonRadio extends JRadioButton {

    public ButtonRadio(String text, Color colorText, Color backgroundColor) {
        super(text);

        this.setForeground(colorText);
        this.setBackground(backgroundColor);
        this.setIcon(new DefaultIcon());
        this.setPressedIcon(new SelectedIcon());
        this.setSelectedIcon(new SelectedIcon());
    }
}
