import panels.JCanvasHistogram;
import tools.DataManager;
import tools.Utils;
import panels.JCanvasPanel;
import panels.ToolsPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    private JPanel mainPanel;
    private JPanel mapPanel;
    private ToolsPanel toolsPanel;
    private JCanvasPanel canvas;
    private JCanvasHistogram hist;
    private DataManager dm;
    private Utils utils;

    private void readImage(String name, String ext){
        BufferedImage img = null;
        try {
            img =  ImageIO.read(new File(name+"."+ext));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int width = img.getWidth();
        final int height = img.getHeight();
        BufferedImage newRGB = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        newRGB.createGraphics().drawImage(img, 0, 0, width, height, null);
         dm.setImage(newRGB);
    }

    private void layoutApp(){
        //==============================================================================================================


        mainPanel = new JPanel();
        toolsPanel = new ToolsPanel(this.utils);

       // mainPanel.setPreferredSize(new Dimension(700, 1000));

        canvas.setBackground(new Color(60, 63, 65));
        hist.setBackground(new Color(60, 63, 65));
        mainPanel.setBackground(new Color(60, 63, 65));

        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        //==============================================================================================================

        toolsPanel.addTools();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(canvas, BorderLayout.SOUTH);
        mainPanel.add(toolsPanel, BorderLayout.NORTH);
       // mainPanel.add(hist, BorderLayout.WEST);
        //==============================================================================================================

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setContentPane(mainPanel);
        this.setSize(new Dimension(710, 900));
        this.setLocationRelativeTo(null);
    }

    public Main(String title) {

        super(title);


        this.dm = new DataManager();
        canvas = new JCanvasPanel(this.dm);
        hist = new JCanvasHistogram(this.dm);

        this.utils = new Utils(dm, canvas, hist);

        this.readImage("Mapa_MD_no_terrain_low_res_dark_Gray", "bmp"); //"Mapa_MD_no_terrain_low_res_dark_Gray", "bmp"
        this.layoutApp();
        this.utils.histogram();

    }

    public static void main(String[] args) {

        Main mw = new Main("Mapa - Anna Dybel");
        mw.setVisible(true);

    }

}

