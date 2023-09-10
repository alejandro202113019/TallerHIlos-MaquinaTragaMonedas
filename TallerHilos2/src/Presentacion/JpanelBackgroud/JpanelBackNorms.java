package Presentacion.JpanelBackgroud;

import javax.swing.*;
import java.awt.*;

public class JpanelBackNorms extends JPanel {
    private Image background;

    public JpanelBackNorms(String rutaImageBackground){
        background = new ImageIcon(rutaImageBackground).getImage();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
    }
}
