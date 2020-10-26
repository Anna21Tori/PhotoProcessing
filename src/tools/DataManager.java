package tools;

import panels.JCanvasPanel;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public class DataManager {

    private BufferedImage image;
    private BufferedImage copedImage;
    private int width, height;
    private int[] arr;

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int[] getArr() {
        return arr;
    }

    public DataManager() {
        this.arr = new int[256];
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {

        this.image = image;
        this.copyImage();
        this.width = image.getWidth();
        this.height = image.getHeight();
    }



    public BufferedImage getCopedImage() {
        return copedImage;
    }

    private void copyImage(){
        ColorModel cm = this.image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = this.image.copyData(null);
        this.copedImage = new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void resetImage(){
       copyImage();
    }
}
