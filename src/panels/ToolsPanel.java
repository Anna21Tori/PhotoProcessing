package panels;

import elements.Slider;
import tools.Utils;
import elements.Button;
import elements.LabelValue;
import elements.ButtonRadio;
import layout.GBC;

import javax.swing.*;
import java.awt.*;

public class ToolsPanel extends JPanel {

    private Utils utils;
    private ButtonGroup buttonGroupDarkenOrBrighten;
    private ButtonRadio btnRadioDarken;
    private ButtonRadio btnRadioBrighten;
    private Slider sliderDarkenOrBrighten;
    private Slider sliderThresholding;
    private Button btnDarkenOrBrighten;
    private Button btnThresholding;
    private Button btnReset;
    private Button btnLowPassFilter;
    private Button btnHighPassFilter;
    private Button btnGaussPassFilter;
    private Button btnDilatation;
    private Button btnErosion;
    private LabelValue labelDarkenOrBrighten;
    private LabelValue labelThresholding;

    public ToolsPanel(Utils utils) {
        this.setPreferredSize(new Dimension(620, 350));
        this.setBackground(new Color(60, 63, 65));
        this.setLayout(new GridBagLayout());
        this.utils = utils;
    }

    public void addTools(){

          buttonGroupDarkenOrBrighten = new ButtonGroup();

          btnRadioDarken = new ButtonRadio("Darken", new Color(184,179,172), new Color(60, 63, 65));
          btnRadioBrighten = new ButtonRadio("Brighten", new Color(184,179,172), new Color(60, 63, 65));

          buttonGroupDarkenOrBrighten.add(btnRadioBrighten );
          buttonGroupDarkenOrBrighten.add(btnRadioDarken);

          btnDarkenOrBrighten =  new Button("OK", new Color(184,179,172), new Color(60, 63, 65) );

          btnThresholding = new Button("OK", new Color(184,179,172), new Color(60, 63, 65) );

          btnDilatation =  new Button("Dilation", new Color(184,179,172), new Color(60, 63, 65) );

          btnErosion = new Button("Erosion", new Color(184,179,172), new Color(60, 63, 65) );

          btnReset = new Button("Reset image", new Color(184,179,172), new Color(60, 63, 65) );
          btnLowPassFilter = new Button("Low pass", new Color(184,179,172), new Color(60, 63, 65));
          btnHighPassFilter = new Button("High pass", new Color(184,179,172), new Color(60, 63, 65));
          btnGaussPassFilter = new Button("Gauss pass", new Color(184,179,172), new Color(60, 63, 65));

          labelDarkenOrBrighten = new LabelValue("Value = 0", new Color(60, 63, 65), new Color(184,179,172) );
          labelThresholding = new LabelValue("Value = 0", new Color(60, 63, 65), new Color(184,179,172) );

          sliderDarkenOrBrighten = new Slider(0, 255, 0);
          sliderThresholding = new Slider(0, 255, 0);


          //set grid
          this.add(sliderDarkenOrBrighten, new GBC(0, 0, 15, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
         // this.add(labelDarkenOrBrighten, new GBC(0, 1, 15, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnRadioDarken, new GBC(19, 0, 3, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnRadioBrighten, new GBC(19, 1, 3, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnDarkenOrBrighten, new GBC(25, 0, 3, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));


          this.add(sliderThresholding, new GBC(0, 2, 15, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnDilatation, new GBC(15, 2, 5, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnErosion, new GBC(20, 2, 5, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnThresholding, new GBC(25, 2, 3, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

          this.add(btnReset, new GBC(0, 4, 29, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnLowPassFilter, new GBC(0, 6, 29, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnHighPassFilter, new GBC(0, 8, 29, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnGaussPassFilter, new GBC(0, 10, 29, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

          btnRadioDarken.setSelected(true);

        btnDarkenOrBrighten.addActionListener(e -> {

            if(btnRadioDarken.isSelected())
                utils.dark(sliderDarkenOrBrighten.getValue());
            else if (btnRadioBrighten.isSelected())
                utils.light(sliderDarkenOrBrighten.getValue());


        });

        sliderDarkenOrBrighten.addChangeListener(e->{
            int val = sliderDarkenOrBrighten.getValue();
            //labelDarkenOrBrighten.setText("Value = "+val);
        });

        sliderThresholding.addChangeListener(e->{
            int val = sliderThresholding.getValue();
           // labelThresholding.setText("Value = "+val);
        });
//
//        btnMinusTo.addActionListener(e->{
//            String color = utils.parseColorToString("to", -5);
//            rgbTo.setText(color);
//        });



        btnThresholding.addActionListener(e -> {
            utils.inverse(sliderThresholding.getValue(), new Color(0, 0, 0), new Color(255, 255, 255));
        });

        btnReset.addActionListener(e->{
             utils.resetImage();
             System.out.println("ok");
        });

        btnLowPassFilter.addActionListener( e->{
            utils.filterLowPass();
        });

        btnHighPassFilter.addActionListener( e->{
            utils.filterHighPass();
        });

        btnGaussPassFilter.addActionListener( e->{
            utils.filterGaussPass();
        });

        btnDilatation.addActionListener( e->{
            utils.dilatation();
        });

        btnErosion.addActionListener( e->{
            utils.erosion();
        });
    }
}
