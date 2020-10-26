package panels;

import tools.DataManager;

import javax.swing.*;
import java.awt.*;

public class JCanvasHistogram extends JPanel {
    private DataManager dm;
    public JCanvasHistogram(DataManager dm){

        this.dm = dm;
        this.setPreferredSize(new Dimension(530, 400));

    }

    private void drawFrame(Graphics2D g2, Color rgb, int weight, int startX, int startY, int endX, int endY){
        g2.setColor(rgb);
        g2.drawRect(startX, startY, 520+endX, 377+endY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(60, 63, 65));
        g2.fillRect(0, 0, 540, 390);

        drawFrame(g2, new Color(0, 0, 0), 1, 3, 3, 1,1);
        drawFrame(g2, new Color(255, 255, 255), 2, 0, 0,6,6);

        for(int i = 0; i< dm.getArr().length; i++){
            g2.setColor(new Color(i, i, i));
            if(dm.getArr()[i] != 0){
                g2.fillRect(10+(2*i), 380-dm.getArr()[i], 2, dm.getArr()[i]);
            }
        }

    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
