package panels;

import elements.ButtonRadio;
import elements.Slider;
import tools.Utils;
import elements.Button;
import layout.GBC;

import javax.swing.*;
import java.awt.*;

public class ToolsPanel extends JPanel {

    private Utils utils;
    private Button btnDarken;
    private Button btnBrighten;
    private Slider sliderDarkenOrBrighten;
    private Slider sliderThresholding;
    private Button btnThresholding;
    private Button btnReset;
    private Button btnFilter;
    private Button btnDilatation;
    private Button btnErosion;
    private Button btnMorphologicalOpening;
    private Button btnMorphologicalClosure;
    private ButtonGroup btnGroupFilter;
    private ButtonRadio btnRadioLowFilter;
    private ButtonRadio btnRadioHighFilter;
    private ButtonRadio btnRadioGaussFilter;


    public ToolsPanel(Utils utils) {
        this.setPreferredSize(new Dimension(500, 350));
        this.setBackground(new Color(60, 63, 65));
        this.setLayout(new GridBagLayout());
        this.utils = utils;
    }

    public void addTools(){


          sliderDarkenOrBrighten = new Slider(0, 255, 0);
          sliderThresholding = new Slider(0, 255, 0);

          btnDarken = new Button("Darken", new Color(184,179,172), new Color(60, 63, 65));
          btnBrighten = new Button("Brighten", new Color(184,179,172), new Color(60, 63, 65));



          btnThresholding = new Button("Bin.", new Color(184,179,172), new Color(60, 63, 65) );
          btnDilatation =  new Button("Dilation", new Color(184,179,172), new Color(60, 63, 65) );
          btnErosion = new Button("Erosion", new Color(184,179,172), new Color(60, 63, 65) );
          btnMorphologicalClosure = new Button("Mor. Closure", new Color(184,179,172), new Color(60, 63, 65) );
          btnMorphologicalOpening = new Button("Mor. Opening", new Color(184,179,172), new Color(60, 63, 65) );


          btnReset = new Button("Reset image", new Color(184,179,172), new Color(60, 63, 65) );

          btnFilter = new Button("Filter", new Color(184,179,172), new Color(60, 63, 65));
          btnGroupFilter = new ButtonGroup();
          btnRadioLowFilter = new ButtonRadio("Low",new Color(184,179,172), new Color(60, 63, 65) );
          btnRadioHighFilter = new ButtonRadio("High",new Color(184,179,172), new Color(60, 63, 65) );
          btnRadioGaussFilter = new ButtonRadio("Gauss",new Color(184,179,172), new Color(60, 63, 65) );
          btnGroupFilter.add(btnRadioGaussFilter);
          btnGroupFilter.add(btnRadioHighFilter);
          btnGroupFilter.add(btnRadioLowFilter);

          //set grid
          this.add(sliderDarkenOrBrighten, new GBC(0, 0, 2, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnDarken, new GBC(0, 1, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnBrighten, new GBC(1, 1, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

          this.add(sliderThresholding, new GBC(0, 2, 2, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10));
          this.add(btnThresholding, new GBC(0, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10));
          this.add(btnDilatation, new GBC(1, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10));
          this.add(btnErosion, new GBC(2, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10));
          this.add(btnMorphologicalClosure, new GBC(3, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnMorphologicalOpening, new GBC(4, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));

          this.add(btnFilter, new GBC(0, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnRadioLowFilter, new GBC(1, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnRadioHighFilter, new GBC(2, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));
          this.add(btnRadioGaussFilter, new GBC(3, 4, 1, 1).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));


            this.add(btnReset, new GBC(0, 5, 6, 2).setFill(GBC.HORIZONTAL).setInsets(10, 10, 10, 10).setAnchor(GBC.WEST));



        setEnableBtn(false);
        btnRadioLowFilter.setSelected(true);



        btnDarken.addActionListener(e -> {
                utils.dark(sliderDarkenOrBrighten.getValue());
        });

        btnBrighten.addActionListener(e -> {
            utils.light(sliderDarkenOrBrighten.getValue());
        });






        btnThresholding.addActionListener(e -> {
            utils.inverse(sliderThresholding.getValue(), new Color(0, 0, 0), new Color(255, 255, 255));
            setEnableBtn(true);
        });

        btnReset.addActionListener(e->{
             utils.resetImage();
             setEnableBtn(false);
        });

        btnFilter.addActionListener( e->{
            if(btnRadioLowFilter.isSelected())
                utils.filterLowPass();
            else if(btnRadioHighFilter.isSelected())
                utils.filterHighPass();
            else if(btnRadioGaussFilter.isSelected())
                utils.filterGauss();
        });


        btnDilatation.addActionListener( e->{
            utils.dilatation();
        });

        btnErosion.addActionListener( e->{
            utils.erosion();
        });

        btnMorphologicalOpening.addActionListener( e->{
            utils.morphologicalOpening();
        });

        btnMorphologicalClosure.addActionListener( e->{
            utils.morphologicalClosure();
        });
    }

    private void setEnableBtn(boolean val){
        btnDilatation.setEnabled(val);
        btnErosion.setEnabled(val);
        btnMorphologicalClosure.setEnabled(val);
        btnMorphologicalOpening.setEnabled(val);
    }
}
