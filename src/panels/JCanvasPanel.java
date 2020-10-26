package panels;

import tools.DataManager;
import tools.Utils;

import javax.swing.*;
import java.awt.*;

public class JCanvasPanel extends JPanel {

    private DataManager dm;
    public JCanvasPanel(DataManager dm){

    this.dm = dm;
    this.setPreferredSize(new Dimension(620, 350));

    }

    private void drawFrame(Graphics2D g2, Color rgb, int weight, int startX, int startY, int endX, int endY){
        g2.setColor(rgb);
        g2.drawRect(startX, startY, dm.getWidth()+endX, dm.getHeight()+endY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.dm.getCopedImage(), 3, 3, this);
        drawFrame(g2, new Color(0, 0, 0), 1, 3, 3, 1,1);
        drawFrame(g2, new Color(255, 255, 255), 2, 0, 0,6,6);

    }

    @Override
    public void repaint() {
        super.repaint();
    }
}

