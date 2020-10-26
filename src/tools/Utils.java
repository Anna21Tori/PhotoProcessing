package tools;

import panels.JCanvasHistogram;
import panels.JCanvasPanel;

import javax.print.attribute.standard.PresentationDirection;
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
                {-1, 8, -1},
                {-1, -1, -1}
        };
        filter(matrix);
    }

    public void filterGaussPass(){

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
                for(int i = wi-1, y=0; i < wi + 1 ;i++, y++){
                    for(int j = hi-1, x =0; j < hi +1 ; j++, x++) {

                        int color =  new Color(copiedImage.getRGB(i, j)).getRed();
                        sumMatrix += matrix[y][x];
                        sum += color*matrix[y][x];

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

        BufferedImage copedImage = copyImage();

        for(int wi = 1; wi < dm.getWidth()-1; wi++){
            for(int hi = 1; hi < dm.getHeight()-1; hi++){
                for(int i = wi-1; i < wi + 1 ;i++){
                    for(int j = hi-1; j < hi +1 ; j++) {
                        int color =  copedImage.getRGB(i, j);
                        if(color == Color.black.getRGB()) {
                            dm.getCopedImage().setRGB(wi, hi, color);
                            break;
                        }
                    }
                }
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();
    }

    public void erosion(){

        BufferedImage copedImage = copyImage();

        for(int wi = 1; wi < dm.getWidth()-1; wi++){
            for(int hi = 1; hi < dm.getHeight()-1; hi++){
                for(int i = wi-1; i < wi + 1 ;i++){
                    for(int j = hi-1; j < hi +1 ; j++) {
                        int color =  copedImage.getRGB(i, j);
                        if(color == Color.white.getRGB()) {
                            dm.getCopedImage().setRGB(wi, hi, color);
                            break;
                        }
                    }
                }
            }

        }

        canvasPanel.repaint();
        histogram();
        hist.repaint();
    }

    private BufferedImage copyImage(){
        ColorModel cm = dm.getCopedImage().getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = dm.getCopedImage().copyData(null);
        BufferedImage img = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
