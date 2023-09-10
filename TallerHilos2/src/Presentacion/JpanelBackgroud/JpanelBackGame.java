package Presentacion.JpanelBackgroud;

import javax.swing.*;
import java.awt.*;

public class JpanelBackGame extends JPanel {
    private Image background;

    public JpanelBackGame(String rutaImageBackgroud) {
        background = new ImageIcon(rutaImageBackgroud).getImage();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
    }
}