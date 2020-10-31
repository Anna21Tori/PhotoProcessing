package tools;

import panels.JCanvasHistogram;
import panels.JCanvasPanel;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.text.DecimalFormat;

public class Utils {

    private final DecimalFormat df = new DecimalFormat("###.##");
    private DataManager dm;
    private JCanvasPanel canvasPanel;
    private JCanvasHistogram hist;
    private int[] arrayPercent;

    public Utils(DataManager dm, JCanvasPanel canvasPanel, JCanvasHistogram hist) {
        this.dm = dm;
        this.canvasPanel = canvasPanel;
        this.arrayPercent = new int[256];
        this.hist = hist;
    }

    public void dark(int step){

        for(int wi = 0; wi < dm.getWidth(); wi++){

            for(int hi = 0; hi < dm.getHeight(); hi++){

                Color readColor = new Color(dm.getCopedImage().getRGB(wi, hi));
                int r = readColor.getRed(), g = readColor.getGreen(), b = readColor.getBlue();
                if(r >step)
                    r-=step;
                if(g > step)
                    g-=step;
                if(b >step)
                    b-=step;
                int rgb = new Color(r,g,b).getRGB();
                dm.getCopedImage().setRGB(wi, hi, rgb);
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();

    }

    public void light(int step){


        for(int wi = 0; wi < dm.getWidth(); wi++){

            for(int hi = 0; hi < dm.getHeight(); hi++){

                Color readColor = new Color(dm.getCopedImage().getRGB(wi, hi));
                int r = readColor.getRed(), g = readColor.getGreen(), b = readColor.getBlue();
                if(r < 255 -step)
                    r+=step;
                if(g < 255 -step)
                    g+=step;
                if(b <255 -step)
                    b+=step;
                int rgb = new Color(r,g,b).getRGB();
                dm.getCopedImage().setRGB(wi, hi, rgb);
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();

    }

    public void inverse(int range, Color first, Color second){

        for(int wi = 0; wi < dm.getWidth(); wi++){

            for(int hi = 0; hi < dm.getHeight(); hi++){

                Color readColor = new Color(dm.getCopedImage().getRGB(wi, hi));

                int r = readColor.getRed(), g = readColor.getGreen(), b = readColor.getBlue();
                if(r <= range)
                    r = first.getRed();
                else
                    r = second.getRed();

                if(g <= range)
                    g = first.getGreen();
                else
                    g = second.getGreen();

                if(b <= range)
                    b = first.getBlue();
                else
                    b = second.getBlue();


                int rgb = new Color(r,g,b).getRGB();
                dm.getCopedImage().setRGB(wi, hi, rgb);

            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();
    }

    public void resetImage(){
        dm.resetImage();
        canvasPanel.repaint();
        histogram();
        hist.repaint();
        System.out.println("ok1");
    }

    public int[] countPixels(){

        int [] array = new int[256];


        for(int wi = 0; wi < dm.getWidth(); wi++){

            for(int hi = 0; hi < dm.getHeight(); hi++){
                array[new Color(dm.getCopedImage().getRGB(wi, hi)).getRed()]++;
            }

        }

      return array;
    }

    public void histogram(){

        int [] array = countPixels();
        int max = getMax(array);
        int range = (int) Math.floor(max/300);

        for(int i = 0; i < this.arrayPercent.length; i++) {
            this.arrayPercent[i] = (int) Math.ceil(array[i]/range);

        }
        dm.setArr(this.arrayPercent);
        hist.repaint();
    }



    private int getMax(int [] arr){
        int max = arr[0];

        for(int i = 0; i< arr.length; i++){
           if(max < arr[i])
               max = arr[i];
        }
       return max;
    }

    public void filterLowPass(){

        int [][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        filter(matrix);

    }

    public void filterHighPass(){

        int [][] matrix = {
                {-1, -1, -1},
                {-1, 9, -1},
                {-1, -1, -1}
        };
        filter(matrix);
    }

    public void filterGauss(){

        int [][] matrix = {
                {1, 2, 1},
                {2, 4, 2},
                {1, 2, 1}
        };

        filter(matrix);
    }

    private void filter(int [][]matrix){
        BufferedImage copiedImage = copyImage();

        for(int wi = 1; wi < dm.getWidth()-1; wi++){
            for(int hi = 1; hi < dm.getHeight()-1; hi++){
                int sum = 0;
                int sumMatrix = 0;
                for(int y = 0; y < matrix.length ;y++){
                    for(int x = 0; x < matrix.length; x++) {

                            int i = wi - matrix.length/2 + y;
                            int j = hi - matrix.length/2 + x;

                            int color =  new Color(copiedImage.getRGB(i, j)).getRed();
                            sumMatrix += matrix[y][x];
                            sum += color * matrix[y][x];
                    }
                }
                if(sumMatrix == 0)
                    sumMatrix = 1;
                sum /= sumMatrix;

                if(sum <= 255 && sum >= 0)
                    dm.getCopedImage().setRGB(wi, hi, new Color(sum, sum, sum).getRGB());
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();
    }
    public void dilatation(){

       executeErosionOrDilatation(Color.black.getRGB());
    }

    public void erosion(){

        executeErosionOrDilatation(Color.white.getRGB());
    }

    private void executeErosionOrDilatation(int color){

        BufferedImage copedImage = copyImage();
        final int SIZE = 3;
        for(int wi = 0; wi < dm.getWidth(); wi++){
            for(int hi = 0; hi < dm.getHeight(); hi++){
                for(int y = 0; y < SIZE ;y++){
                    for(int x = 0; x < SIZE; x++) {

                        int i = wi - SIZE/2 + y;
                        int j = hi - SIZE/2 + x;

                        if(i >= 0 && i < dm.getWidth() && j >= 0 && j < dm.getHeight()) {
                            int currentColor = copedImage.getRGB(i, j);
                            if ( currentColor == color) {
                                dm.getCopedImage().setRGB(wi, hi, color);
                                x= SIZE;
                                y = SIZE;
                            }
                        }
                    }
                }
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();
    }
    public void morphologicalOpening(){
        erosion();
        dilatation();
    }

    public void morphologicalClosure(){
        dilatation();
        erosion();
    }


    private BufferedImage copyImage(){
        ColorModel cm = dm.getCopedImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = dm.getCopedImage().copyData(null);
        BufferedImage img = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
