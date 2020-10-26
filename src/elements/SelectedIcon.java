package elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SelectedIcon implements Icon {
    private BufferedImage icon;

    public SelectedIcon(){
        try {
            this.icon = ImageIO.read(new File("circle_green.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void paintIcon (Component c, Graphics g,
                           int x, int y) {
        g.drawImage(this.icon, x, y, null);


    }
    public int getIconWidth() {
        return this.icon.getWidth();
    }
    public int getIconHeight() {
        return this.icon.getHeight();
    }

}
