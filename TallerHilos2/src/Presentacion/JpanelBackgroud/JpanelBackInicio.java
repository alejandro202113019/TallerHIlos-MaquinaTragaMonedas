package Presentacion.JpanelBackgroud;

import javax.swing.*;
import java.awt.*;

public class JpanelBackInicio extends JPanel {
    private Image backgroud;

    public JpanelBackInicio(String rutaImageBackgroud) {
        backgroud = new ImageIcon(rutaImageBackgroud).getImage();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroud,0,0,getWidth(),getHeight(),this);
    }
}
