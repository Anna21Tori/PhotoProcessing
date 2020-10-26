package elements;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {
    public Slider(int min, int max, int value) {
        super(min, max, value);

        this.setBackground(new Color(60, 63, 65));
        this.setForeground(new Color(184,179,172));
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(50);
        this.setMinorTickSpacing(10);
        this.setFont(new Font("Serif", Font.PLAIN, 10));
    }
}
